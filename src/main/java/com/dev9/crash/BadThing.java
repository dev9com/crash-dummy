package com.dev9.crash;

import javax.servlet.http.HttpServletRequest;

public interface BadThing {
    String doBadThing() throws Exception;

    String getBadThingName();

    String getBadThingDescription();

    /**
     * Web safe URL identifier
     */
    String getBadThingId();

    void setHttpRequest(HttpServletRequest request);
}
