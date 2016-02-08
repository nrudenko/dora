package com.github.nrudenko.dora;

import java.util.List;

public class TextUtils {
    /**
     * Returns a CharSequence concatenating the specified CharSequences,
     * retaining their spans if any.
     */
    public static String concat(String... text) {
        if (text.length == 0) {
            return "";
        }

        if (text.length == 1) {
            return text[0];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length; i++) {
            sb.append(text[i]);
        }

        return sb.toString();
    }

    public static String join(CharSequence delimiter, List tokens) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (Object token : tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(token);
        }
        return sb.toString();
    }

    public static String join(CharSequence delimiter, Object[] tokens) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (Object token : tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(token);
        }
        return sb.toString();
    }

    public static boolean isNotEmpty(String text) {
        return text != null && text.length() > 0;
    }

    public static String toUnderscore(String text) {
        return text.replaceAll("(.)(\\p{Upper})", "$1_$2");
    }
}
