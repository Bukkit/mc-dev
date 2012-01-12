package net.minecraft.server;

public class LocaleI18n {

    private static LocaleLanguage a = LocaleLanguage.a();

    public LocaleI18n() {}

    public static String a(String s) {
        return a.b(s);
    }

    public static String a(String s, Object... aobject) {
        return a.a(s, aobject);
    }
}
