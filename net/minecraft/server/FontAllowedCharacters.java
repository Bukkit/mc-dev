package net.minecraft.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FontAllowedCharacters {

    public static final String allowedCharacters = a();
    public static final char[] b = new char[] { '/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':'};

    public FontAllowedCharacters() {}

    private static String a() {
        String s = "";

        try {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(FontAllowedCharacters.class.getResourceAsStream("/font.txt"), "UTF-8"));
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
}
