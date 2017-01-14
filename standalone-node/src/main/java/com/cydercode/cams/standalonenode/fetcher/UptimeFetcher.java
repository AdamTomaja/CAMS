package com.cydercode.cams.standalonenode.fetcher;

import com.cydercode.cams.datamodel.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.cydercode.cams.datamodel.Constants.NODE_UPTIME;
import static com.cydercode.cams.datamodel.Constants.TIMESTAMP;

/**
 * @author Adam Tomaja
 */
@Component
public class UptimeFetcher implements Fetcher {

    static final Logger LOGGER = LoggerFactory.getLogger(UptimeFetcher.class);

    private long nodeStartTime;

    @PostConstruct
    public void init() {
        nodeStartTime = getCurrentTimestamp();
    }

    private long getNodeUptime() {
        return getCurrentTimestamp() - nodeStartTime;
    }

    private long getCurrentTimestamp() {
        return new Date().getTime();
    }

    @Override
    public Map<String, Object> fetch() {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put(Constants.SYSTEM_UPTIME, getSystemUptime());
        } catch (Exception e) {
            LOGGER.error("Cannot fetch system uptime", e);
        }

        result.put(NODE_UPTIME, getNodeUptime());

        result.put(TIMESTAMP, new Date().getTime());

        return result;
    }

    public static long getSystemUptime() throws IOException, ParseException {
        long uptime = -1;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            Process uptimeProc = Runtime.getRuntime().exec("net stats srv");
            BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("Statistics since")) {
                    SimpleDateFormat format = new SimpleDateFormat("'Statistics since' MM/dd/yyyy hh:mm:ss a");
                    Date boottime = format.parse(line);
                    uptime = System.currentTimeMillis() - boottime.getTime();
                    break;
                }
            }
        } else if (os.contains("mac") || os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            Process uptimeProc = Runtime.getRuntime().exec("uptime");
            BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
            String line = in.readLine();
            if (line != null) {
                Pattern parse = Pattern.compile("((\\d+) days,)? (\\d+):(\\d+)");
                Matcher matcher = parse.matcher(line);
                if (matcher.find()) {
                    String _days = matcher.group(2);
                    String _hours = matcher.group(3);
                    String _minutes = matcher.group(4);
                    int days = _days != null ? Integer.parseInt(_days) : 0;
                    int hours = _hours != null ? Integer.parseInt(_hours) : 0;
                    int minutes = _minutes != null ? Integer.parseInt(_minutes) : 0;
                    uptime = (minutes * 60000) + (hours * 60000 * 60) + (days * 6000 * 60 * 24);
                }
            }
        }
        return uptime;
    }
}
