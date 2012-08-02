package net.minecraft.server;

import java.util.regex.Pattern;

public class StripColor {

    private static final Pattern a = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

    public static String a(String s) {
        return a.matcher(s).replaceAll("");
    }
}
