package com.cydercode.cams.standalonenode;

import com.cydercode.cams.datamodel.AppStatus;
import com.cydercode.cams.datamodel.AppStatusBuilder;
import com.cydercode.cams.datamodel.Health;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Adam Tomaja
 */
@RestController
public class MainController {

    @Value("${cams.instancename}")
    private String instanceName;
    private long instanceStartTime;

    @PostConstruct
    public void init() {
        instanceStartTime = getCurrentTimestamp();
    }

    @RequestMapping(value = NodeConstants.STATUS_PATH, method = RequestMethod.GET)
    public AppStatus getStatus() throws Exception {
        return AppStatusBuilder.createAppStatusBuilder().setAppUptime(getAppUptime())
                .setSystemUptime(getSystemUptime())
                .setHealth(Health.HEALTHY)
                .setInstanceName(instanceName).create();
    }

    private long getAppUptime() {
        return getCurrentTimestamp() - instanceStartTime;
    }

    private long getCurrentTimestamp() {
        return new Date().getTime();
    }

    public static long getSystemUptime() throws Exception {
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
