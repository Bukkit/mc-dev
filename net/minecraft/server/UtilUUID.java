package net.minecraft.server;

import java.util.UUID;
import java.util.regex.Pattern;

public class UtilUUID {

    private static final Pattern a = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}");

    public static boolean a(String s) {
        return a.matcher(s).matches();
    }

    public static UUID b(String s) {
        if (s == null) {
            return null;
        } else if (a(s)) {
            return UUID.fromString(s);
        } else {
            if (s.length() == 32) {
                String s1 = s.substring(0, 8) + "-" + s.substring(8, 12) + "-" + s.substring(12, 16) + "-" + s.substring(16, 20) + "-" + s.substring(20, 32);

                if (a(s1)) {
                    return UUID.fromString(s1);
                }
            }

            return null;
        }
    }
}
