package com.cydercode.cams.standalonenode;

import com.cydercode.cams.datamodel.AppStatus;
import com.cydercode.cams.datamodel.AppStatusBuilder;
import com.cydercode.cams.datamodel.Health;
import com.cydercode.cams.standalonenode.fetcher.Fetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cydercode.cams.datamodel.AppStatusBuilder.createAppStatusBuilder;
import static com.cydercode.cams.datamodel.Constants.*;

/**
 * @author Adam Tomaja
 */
@RestController
public class MainController {

    @Value("${cams.instancename}")
    private String instanceName;

    @Autowired
    List<Fetcher> fetchers;

    @RequestMapping(value = STATUS_PATH, method = RequestMethod.GET)
    public AppStatus getStatus() throws Exception {
        AppStatusBuilder appStatusBuilder = createAppStatusBuilder()
                .setHealth(Health.HEALTHY)
                .withMetadata(NODE_TYPE, NODE_TYPE_STANDALONE)
                .setInstanceName(instanceName);

        fetchers.forEach(fetcher ->
                appStatusBuilder.withMetadatas(fetcher.fetch()));

        return appStatusBuilder.build();
    }
}
