package com.common.string.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringUtil {

	/**
	 * @param String s
	 * @return null or "" return false
	 */
	public static boolean hasText(String s) {
		return (null != s) && (s.trim().length() > 0);
	}

	/**
	 * @param s
	 * @return if null return false
	 */
	public static boolean hasText(Object s) {
		if ((null != s) && (s instanceof String) && (s.toString().trim().length() > 0)) {
			return true;
		}
		if ((null != s) && (s instanceof List) && (((List) s).size() > 0)) {
			return true;
		}
		if ((null != s) && (s instanceof Map) && (((Map) s).size() > 0)) {
			return true;
		}
		if ((null != s) && (s instanceof Set) && (((Set) s).size() > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * @param obj
	 * @return String Value
	 */
	public static String getStringValue(Object obj) {
		if (hasText(obj)) {
			return obj.toString();
		}
		return null;
	}
}
