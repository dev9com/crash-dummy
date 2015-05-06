package com.dev9.crash;

public interface BadThing {
    String doBadThing() throws Exception;

    String badThingName();

    String badThingDescription();

    /**
     * Web safe URL identifier
     */
    String badThingId();
}
