package com.cydercode.cams.standalonenode.fetcher;

import java.util.Map;

/**
 * @author Adam Tomaja
 */
public interface Fetcher {
    Map<String, Object> fetch();
}
