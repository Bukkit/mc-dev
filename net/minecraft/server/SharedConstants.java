package net.minecraft.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SharedConstants {

    public static final String allowedCharacters = a();
    public static final char[] b = new char[] { '/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':'};

    public SharedConstants() {}

    private static String a() {
        String s = "";

        try {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(SharedConstants.class.getResourceAsStream("/font.txt"), "UTF-8"));
            String s1 = "";

            while ((s1 = bufferedreader.readLine()) != null) {
                if (!s1.startsWith("#")) {
                    s = s + s1;
                }
            }

            bufferedreader.close();
        } catch (Exception exception) {
            ;
        }

        return s;
    }

    public static final boolean isAllowedChatCharacter(char c0) {
        return c0 != 167 && (allowedCharacters.indexOf(c0) >= 0 || c0 > 32);
    }
}
