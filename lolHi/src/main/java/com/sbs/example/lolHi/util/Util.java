package com.sbs.example.lolHi.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;

public class Util {

	public static int getAsInt(Object object) {
		return getAsInt(object, -1);
	}

	public static int getAsInt(Object object, int defaultValue) {
		if (object instanceof BigInteger) {
			return ((BigInteger) object).intValue();
		} else if (object instanceof String) {
			try {
				return Integer.parseInt((String) object);
			} catch (NumberFormatException e) {
				return defaultValue;
			}
		} else if (object instanceof Long) {
			return (int) ((long) object);
		} else if (object instanceof Integer) {
			return (int) object;
		}

		return defaultValue;
	}

	public static String getAsStr(Object object, String defaultValue) {
		if (object == null) {
			return defaultValue;
		}

		if (object instanceof String) {
			return (String) (object);
		}

		return object.toString();
	}

	public static String getUriEncoded(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	public static String getNewUriRemoved(String uri, String paramName) {
		String deleteStrStarts = paramName + "=";
		int delStartPos = uri.indexOf(deleteStrStarts);

		if (delStartPos != -1) {
			int delEndPos = uri.indexOf("&", delStartPos);

			if (delEndPos != -1) {
				delEndPos++;
				uri = uri.substring(0, delStartPos) + uri.substring(delEndPos, uri.length());
			} else {
				uri = uri.substring(0, delStartPos);
			}
		}

		if (uri.charAt(uri.length() - 1) == '?') {
			uri = uri.substring(0, uri.length() - 1);
		}

		if (uri.charAt(uri.length() - 1) == '&') {
			uri = uri.substring(0, uri.length() - 1);
		}

		return uri;
	}

	public static String getNewUri(String uri, String paramName, String paramValue) {
		uri = getNewUriRemoved(uri, paramName);

		if (uri.contains("?")) {
			uri += "&" + paramName + "=" + paramValue;
		} else {
			uri += "?" + paramName + "=" + paramValue;
		}

		uri = uri.replace("?&", "?");

		return uri;
	}

	public static String getNewUriAndEncoded(String uri, String paramName, String pramValue) {
		return getUriEncoded(getNewUri(uri, paramName, pramValue));
	}

	public static String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();

		} catch (Exception ex) {
			return "";
		}
	}
}