package com.dev9.crash;

import javax.servlet.http.HttpServletRequest;

public interface WebBadThing extends BadThing {
    void setHttpRequest(HttpServletRequest request);
}
