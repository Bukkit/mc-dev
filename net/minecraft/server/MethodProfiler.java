package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodProfiler {

    public static boolean a = false;
    private static List b = new ArrayList();
    private static List c = new ArrayList();
    private static String d = "";
    private static Map e = new HashMap();

    public MethodProfiler() {}

    public static void a(String s) {
        if (a) {
            if (d.length() > 0) {
                d = d + ".";
            }

            d = d + s;
            b.add(d);
            c.add(Long.valueOf(System.nanoTime()));
        }
    }

    public static void a() {
        if (a) {
            long i = System.nanoTime();
            long j = ((Long) c.remove(c.size() - 1)).longValue();

            b.remove(b.size() - 1);
            long k = i - j;

            if (e.containsKey(d)) {
                e.put(d, Long.valueOf(((Long) e.get(d)).longValue() + k));
            } else {
                e.put(d, Long.valueOf(k));
            }

            d = b.size() > 0 ? (String) b.get(b.size() - 1) : "";
        }
    }

    public static void b(String s) {
        a();
        a(s);
    }
}
