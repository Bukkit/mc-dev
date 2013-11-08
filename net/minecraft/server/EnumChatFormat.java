package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public enum EnumChatFormat {

    BLACK("BLACK", 0, '0'), DARK_BLUE("DARK_BLUE", 1, '1'), DARK_GREEN("DARK_GREEN", 2, '2'), DARK_AQUA("DARK_AQUA", 3, '3'), DARK_RED("DARK_RED", 4, '4'), DARK_PURPLE("DARK_PURPLE", 5, '5'), GOLD("GOLD", 6, '6'), GRAY("GRAY", 7, '7'), DARK_GRAY("DARK_GRAY", 8, '8'), BLUE("BLUE", 9, '9'), GREEN("GREEN", 10, 'a'), AQUA("AQUA", 11, 'b'), RED("RED", 12, 'c'), LIGHT_PURPLE("LIGHT_PURPLE", 13, 'd'), YELLOW("YELLOW", 14, 'e'), WHITE("WHITE", 15, 'f'), RANDOM("OBFUSCATED", 16, 'k', true), BOLD("BOLD", 17, 'l', true), STRIKETHROUGH("STRIKETHROUGH", 18, 'm', true), UNDERLINE("UNDERLINE", 19, 'n', true), ITALIC("ITALIC", 20, 'o', true), RESET("RESET", 21, 'r');
    private static final Map w = new HashMap();
    private static final Map x = new HashMap();
    private static final Pattern y = Pattern.compile("(?i)" + String.valueOf('\u00a7') + "[0-9A-FK-OR]");
    private final char z;
    private final boolean A;
    private final String B;
    private static final EnumChatFormat[] C = new EnumChatFormat[] { BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE, RANDOM, BOLD, STRIKETHROUGH, UNDERLINE, ITALIC, RESET};

    private EnumChatFormat(String s, int i, char c0) {
        this(s, i, c0, false);
    }

    private EnumChatFormat(String s, int i, char c0, boolean flag) {
        this.z = c0;
        this.A = flag;
        this.B = "�" + c0;
    }

    public char getChar() {
        return this.z;
    }

    public boolean isFormat() {
        return this.A;
    }

    public boolean c() {
        return !this.A && this != RESET;
    }

    public String d() {
        return this.name().toLowerCase();
    }

    public String toString() {
        return this.B;
    }

    public static EnumChatFormat b(String s) {
        return s == null ? null : (EnumChatFormat) x.get(s.toLowerCase());
    }

    public static Collection a(boolean flag, boolean flag1) {
        ArrayList arraylist = new ArrayList();
        EnumChatFormat[] aenumchatformat = values();
        int i = aenumchatformat.length;

        for (int j = 0; j < i; ++j) {
            EnumChatFormat enumchatformat = aenumchatformat[j];

            if ((!enumchatformat.c() || flag) && (!enumchatformat.isFormat() || flag1)) {
                arraylist.add(enumchatformat.d());
            }
        }

        return arraylist;
    }

    static {
        EnumChatFormat[] aenumchatformat = values();
        int i = aenumchatformat.length;

        for (int j = 0; j < i; ++j) {
            EnumChatFormat enumchatformat = aenumchatformat[j];

            w.put(Character.valueOf(enumchatformat.getChar()), enumchatformat);
            x.put(enumchatformat.d(), enumchatformat);
        }
    }
}
