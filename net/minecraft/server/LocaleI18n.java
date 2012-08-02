package net.minecraft.server;

public class LocaleI18n {

    private static LocaleLanguage a = LocaleLanguage.a();

    public static String get(String s) {
        return a.b(s);
    }

    public static String get(String s, Object... aobject) {
        return a.a(s, aobject);
    }
}
