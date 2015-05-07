package com.dev9.crash;

public interface BadThing {
    String doBadThing() throws Exception;

    String getBadThingName();

    String getBadThingDescription();

    /**
     * Web safe URL identifier
     */
    String getBadThingId();
}
