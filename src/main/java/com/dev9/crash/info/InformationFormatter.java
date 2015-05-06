package com.dev9.crash.info;

import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

public abstract class InformationFormatter {
    String newLine = System.getProperty("line.separator");

    public String formatLineSeparator(String in) {
        String result = "Unicode value[s]:";
        for (int i = 0; i < in.length(); i++) {
            result += " ";
            result += in.codePointAt(i);
        }

        return result;
    }

    public String formatPath(String in) {
        return formatPath(in, System.getProperty("path.separator"));
    }

    public String formatPath(String in, String pathSeparator) {
        String result = in.replaceAll(pathSeparator, "<br />" + newLine);
        return result;
    }

    abstract public String[] getImportantProperties();

    public void setProperties(String type, Map props,
                              SortedMap<String, String> results, String[] pathProperties) {

        Iterator keys = props.keySet().iterator();

        boolean alternateFormat = false;

        while (keys.hasNext()) {
            String key = (String) keys.next();
            String keyvalue = type + ":" + key;
            String value = "null";

            alternateFormat = false;

            for (String pathCheck : pathProperties) {
                if (key.compareTo(pathCheck) == 0) {
                    value = formatPath((String) props.get(key));
                    alternateFormat = true;
                }
            }

            if (key.compareTo("line.separator") == 0) {
                value = formatLineSeparator((String) props.get(key));
                alternateFormat = true;
            }

            if (!alternateFormat)
                value = (String) props.get(key);

            for (String importantCheck : this.getImportantProperties()) {
                if (key.compareTo(importantCheck) == 0) {
                    value = "<b>" + value + "</b>";
                }
            }
            results.put(keyvalue, value);
        }
    }

}
