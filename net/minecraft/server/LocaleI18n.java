package net.minecraft.server;

public class LocaleI18n {

    private static LocaleLanguage a = LocaleLanguage.a();
    private static LocaleLanguage b = new LocaleLanguage();

    public static String get(String s) {
        return a.a(s);
    }

    public static String get(String s, Object... aobject) {
        return a.a(s, aobject);
    }

    public static String b(String s) {
        return b.a(s);
    }

    public static boolean c(String s) {
        return a.b(s);
    }

    public static long a() {
        return a.c();
    }
}
