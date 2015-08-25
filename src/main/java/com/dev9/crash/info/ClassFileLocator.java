package com.dev9.crash.info;

import java.security.CodeSource;
import java.security.ProtectionDomain;

public class ClassFileLocator {
    public String find(String canonicalName) {
        Class clazz = null;

        if(canonicalName == null)
            return "No class specified.";

        try {
            clazz = Class.forName(canonicalName);
        } catch (ClassNotFoundException e) {
            return "Can't find the class at all.";
        }

        ProtectionDomain pd = clazz.getProtectionDomain();

        CodeSource cs = pd.getCodeSource();
        if (cs == null)
            return "<unknown, likely rt.jar / Core JDK>";
        else
            return cs.getLocation().toExternalForm();
    }
}
