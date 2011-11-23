package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class IntCache {

    private static int a = 256;
    private static List b = new ArrayList();
    private static List c = new ArrayList();
    private static List d = new ArrayList();
    private static List e = new ArrayList();

    public IntCache() {}

    public static int[] a(int i) {
        int[] aint;

        if (i <= 256) {
            if (b.size() == 0) {
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
        } else if (d.size() == 0) {
            aint = new int[a];
            e.add(aint);
            return aint;
        } else {
            aint = (int[]) d.remove(d.size() - 1);
            e.add(aint);
            return aint;
        }
    }

    public static void a() {
        if (d.size() > 0) {
            d.remove(d.size() - 1);
        }

        if (b.size() > 0) {
            b.remove(b.size() - 1);
        }

        d.addAll(e);
        b.addAll(c);
        e.clear();
        c.clear();
    }
}
