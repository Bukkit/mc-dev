package net.minecraft.server;

import java.util.regex.Pattern;

public class UtilColor {

    private static final Pattern a = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

    public static boolean b(String s) {
        return s == null || "".equals(s);
    }
}
