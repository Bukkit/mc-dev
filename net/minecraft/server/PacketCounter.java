package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;

public class PacketCounter {

    public static boolean a = true;
    private static final Map b = new HashMap();
    private static final Map c = new HashMap();
    private static final Object d = new Object();

    public PacketCounter() {}

    public static void a(int i, long j) {
        if (a) {
            Object object = d;

            synchronized (d) {
                if (b.containsKey(Integer.valueOf(i))) {
                    b.put(Integer.valueOf(i), Long.valueOf(((Long) b.get(Integer.valueOf(i))).longValue() + 1L));
                    c.put(Integer.valueOf(i), Long.valueOf(((Long) c.get(Integer.valueOf(i))).longValue() + j));
                } else {
                    b.put(Integer.valueOf(i), Long.valueOf(1L));
                    c.put(Integer.valueOf(i), Long.valueOf(j));
                }
            }
        }
    }
}
