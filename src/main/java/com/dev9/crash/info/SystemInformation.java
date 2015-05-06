package com.dev9.crash.info;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SystemInformation extends InformationFormatter {

    public void setSystemProperties(SortedMap<String, String> results) {
        Map props = System.getProperties();

        String[] pathProperties = {"sun.boot.class.path", "java.class.path",
                "java.ext.dirs", "java.library.path", "sun.boot.library.path"};

        setProperties("JAVA", props, results, pathProperties);
    }

    public void setEnvironmentProperties(SortedMap<String, String> results) {
        Map props = System.getenv();

        String[] pathProperties = {"MANPATH", "PATH"};

        setProperties("ENV", props, results, pathProperties);
    }

    public SortedMap<String, String> allProperties() {
        SortedMap<String, String> results = new TreeMap<String, String>();

        setEnvironmentProperties(results);
        setSystemProperties(results);

        results.put("TIME:currentTimeMillis", System.currentTimeMillis() + "");
        results.put("TIME:java.util.Date.default", new java.util.Date()
                .toString());

        return results;
    }

    @Override
    public String[] getImportantProperties() {
        return new String[]{"file.separator", "java.class.path", "java.home",
                "java.vendor", "java.vendor.url", "java.version",
                "line.separator", "os.arch", "os.name", "os.version",
                "path.separator", "user.dir", "user.home", "user.name"};

    }
}
