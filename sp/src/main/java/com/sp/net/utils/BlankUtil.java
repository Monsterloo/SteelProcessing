package com.sp.net.utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class BlankUtil {

	public static boolean isBlank(String str) {
        return str == null || str.trim().length() <= 0;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(Character cha) {
        return cha == null || cha.equals(Character.valueOf(' '));
    }

    public static boolean isNotBlank(Character cha) {
        return !isBlank(cha);
    }

    public static boolean isBlank(Object obj) {
        return obj == null;
    }

    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }

    public static boolean isBlank(Object[] objs) {
        return objs == null || objs.length <= 0;
    }

    public static boolean isNotBlank(Object[] objs) {
        return !isBlank(objs);
    }

    public static boolean isBlank(Collection<?> obj) {
        return obj == null || obj.size() <= 0;
    }

    public static boolean isNotBlank(Collection<?> obj) {
        return !isBlank(obj);
    }

    public static boolean isBlank(Set<?> obj) {
        return obj == null || obj.size() <= 0;
    }

    public static boolean isNotBlank(Set<?> obj) {
        return !isBlank(obj);
    }

    public static boolean isBlank(Serializable obj) {
        return obj == null;
    }

    public static boolean isNotBlank(Serializable obj) {
        return !isBlank(obj);
    }

    public static boolean isBlank(Map<?, ?> obj) {
        return obj == null || obj.size() <= 0;
    }

    public static boolean isNotBlank(Map<?, ?> obj) {
        return !isBlank(obj);
    }
}
