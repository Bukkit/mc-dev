package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class IntCache {

    private static int a = 256;
    private static List b = new ArrayList();
    private static List c = new ArrayList();
    private static List d = new ArrayList();
    private static List e = new ArrayList();

    public static synchronized int[] a(int i) {
        int[] aint;

        if (i <= 256) {
            if (b.isEmpty()) {
                aint = new int[256];
                c.add(aint);
                return aint;
            } else {
                aint = (int[]) b.remove(b.size() - 1);
                c.add(aint);
                return aint;
            }
        } else if (i > a) {
            a = i;
            d.clear();
            e.clear();
            aint = new int[a];
            e.add(aint);
            return aint;
        } else if (d.isEmpty()) {
            aint = new int[a];
            e.add(aint);
            return aint;
        } else {
            aint = (int[]) d.remove(d.size() - 1);
            e.add(aint);
            return aint;
        }
    }

    public static synchronized void a() {
        if (!d.isEmpty()) {
            d.remove(d.size() - 1);
        }

        if (!b.isEmpty()) {
            b.remove(b.size() - 1);
        }

        d.addAll(e);
        b.addAll(c);
        e.clear();
        c.clear();
    }

    public static synchronized String b() {
        return "cache: " + d.size() + ", tcache: " + b.size() + ", allocated: " + e.size() + ", tallocated: " + c.size();
    }
}
