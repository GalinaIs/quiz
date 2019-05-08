package com.sbt.utils;

public class ClassNameUtil {
    private ClassNameUtil() {
    }

    public static String getCurrentClassName() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException ex) {
            return ex.getStackTrace()[1].getClassName();
        }
    }
}
