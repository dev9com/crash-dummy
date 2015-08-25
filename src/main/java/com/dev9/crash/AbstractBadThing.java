package com.dev9.crash;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBadThing implements BadThing {

    public HttpServletRequest request;

    @Override
    public void setHttpRequest(HttpServletRequest request) {
        this.request = request;
    }
}
