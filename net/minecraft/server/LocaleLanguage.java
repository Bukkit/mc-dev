package net.minecraft.server;

import java.io.IOException;
import java.util.Properties;

public class LocaleLanguage {

    private static LocaleLanguage a = new LocaleLanguage();
    private Properties b = new Properties();

    private LocaleLanguage() {
        try {
            this.b.load(LocaleLanguage.class.getResourceAsStream("/lang/en_US.lang"));
            this.b.load(LocaleLanguage.class.getResourceAsStream("/lang/stats_US.lang"));
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }

    public static LocaleLanguage a() {
        return a;
    }

    public String a(String s) {
        return this.b.getProperty(s, s);
    }

    public String a(String s, Object... aobject) {
        String s1 = this.b.getProperty(s, s);

        return String.format(s1, aobject);
    }
}
