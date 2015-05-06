package com.dev9.crash.info;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class WebServerInformation extends InformationFormatter {

    public void setContextProperties(ServletContext context, SortedMap<String, String> props) {
        Map<String, String> values = new HashMap<String, String>();

        values.put("realPath", context.getRealPath("."));

        Enumeration<String> parameters = context.getInitParameterNames();
        while (parameters.hasMoreElements()) {
            String key = parameters.nextElement();
            values.put("initParameter." + key, context.getInitParameter(key));
        }

        values.put("serverInfo", context.getServerInfo());
        values.put("servletContextName", context.getServletContextName());

        Enumeration<String> attributes = context.getInitParameterNames();
        while (attributes.hasMoreElements()) {
            String key = attributes.nextElement();
            String value = "<emphasis>null</emphasis>";
            if (context.getAttribute(key) != null)
                value = context.getAttribute(key).toString();
            values.put("attribute." + key, value);
        }

        values.put("majorVersion", context.getMajorVersion() + "");
        values.put("minorVersion", context.getMinorVersion() + "");

        this.setProperties("CONTEXT", values, props, new String[]{});
    }

    public void setRequestProperties(HttpServletRequest request, SortedMap<String, String> props) {
        Map<String, String> values = new HashMap<String, String>();

        Enumeration<String> attributes = request.getAttributeNames();
        while (attributes.hasMoreElements()) {
            String key = attributes.nextElement();
            values.put("attribute." + key, request.getAttribute(key).toString());
        }

        values.put("authType", request.getAuthType());
        values.put("characterEncoding", request.getCharacterEncoding());
        values.put("contentType", request.getContentType());
        values.put("contextPath", request.getContextPath());

        attributes = request.getHeaderNames();
        while (attributes.hasMoreElements()) {
            String key = attributes.nextElement();
            values.put("header." + key, request.getHeader(key));
        }

        values.put("localAddr", request.getLocalAddr());
        values.put("localName", request.getLocalName());
        values.put("method", request.getMethod());

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            values.put("parameter." + parameterName, request.getParameter(parameterName));
        }

        Map parameters = request.getParameterMap();
        Set<String> keys = parameters.keySet();
        for (String key : keys) {
            for (int i = 0; i < request.getParameterValues(key).length; i++) {
                if (i > 0) {
                    values.put("parameterMap." + key, request.getParameterValues(key)[i]);
                } else {
                    values.put("parameterMap." + key + "." + i, request.getParameterValues(key)[i]);
                }
            }
        }
        values.put("pathInfo", request.getPathInfo());
        values.put("pathTranslated", request.getPathTranslated());
        values.put("protocol", request.getProtocol());
        values.put("queryString", request.getQueryString());
        values.put("remoteAddr", request.getRemoteAddr());
        values.put("remoteHost", request.getRemoteHost());
        values.put("remoteUser", request.getRemoteUser());
        values.put("requestedSessionId", request.getRequestedSessionId());
        values.put("requestURI", request.getRequestURI());
        values.put("scheme", request.getScheme());
        values.put("serverName", request.getServerName());
        values.put("servletPath", request.getServletPath());
        values.put("contentLength", request.getContentLength() + "");
        for (Cookie cookie : request.getCookies()) {
            values.put("cookie." + cookie.getName() + ".comment", cookie.getComment());
            values.put("cookie." + cookie.getName() + ".domain", cookie.getDomain());
            values.put("cookie." + cookie.getName() + ".path", cookie.getPath());
            values.put("cookie." + cookie.getName() + ".value", cookie.getValue());
            values.put("cookie." + cookie.getName() + ".maxAge", cookie.getMaxAge() + "");
            values.put("cookie." + cookie.getName() + ".secure", cookie.getSecure() + "");
            values.put("cookie." + cookie.getName() + ".maxAge", cookie.getVersion() + "");
        }
        Enumeration<Locale> locales = request.getLocales();
        int i = 0;
        while (locales.hasMoreElements()) {
            Locale locale = locales.nextElement();
            values.put("locale." + i, locale.toString());
        }

        values.put("localPort", request.getLocalPort() + "");
        values.put("remotePort", request.getRemotePort() + "");

        values.put("isRequestedSessionIdFromCookie", request.isRequestedSessionIdFromCookie() + "");
        values.put("isRequestedSessionIdFromURL", request.isRequestedSessionIdFromURL() + "");
        values.put("isRequestedSessionIdValid", request.isRequestedSessionIdValid() + "");

        values.put("serverPort", request.getServerPort() + "");
        values.put("secure", request.isSecure() + "");

        this.setProperties("REQUEST", values, props, new String[]{});
    }

    public void setSessionProperties(HttpServletRequest request, SortedMap<String, String> props) {
        Map<String, String> values = new HashMap<String, String>();

        HttpSession session = request.getSession();

        values.put("id", session.getId());
        values.put("creationTime", session.getCreationTime() + "");
        values.put("lastAccessedTime", session.getLastAccessedTime() + "");
        values.put("maxInactiveInterval", session.getMaxInactiveInterval() + "");
        values.put("new", session.isNew() + "");

        Enumeration<String> attributes = session.getAttributeNames();
        while (attributes.hasMoreElements()) {
            String key = attributes.nextElement();
            String value = "<i>null</i>";
            if (session.getAttribute(key) != null) {
                value = session.getAttribute(key).toString();
            }

            values.put("attribute." + key, value);
        }

        this.setProperties("SESSION", values, props, new String[]{});
    }

    @Override
    public String[] getImportantProperties() {
        return new String[]{};
    }

    public SortedMap<String, String> allProperties(HttpServletRequest request, ServletContext context) {
        SortedMap<String, String> results = new TreeMap<String, String>();

        setRequestProperties(request, results);
        setContextProperties(context, results);
        setSessionProperties(request, results);

        results.put("TIME:currentTimeMillis", System.currentTimeMillis() + "");
        results.put("TIME:java.util.Date.default", new java.util.Date().toString());

        return results;

    }

}
