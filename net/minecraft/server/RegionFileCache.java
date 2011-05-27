package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RegionFileCache {

    private static final Map a = new HashMap();

    private RegionFileCache() {}

    public static synchronized RegionFile a(File file1, int i, int j) {
        File file2 = new File(file1, "region");
        File file3 = new File(file2, "r." + (i >> 5) + "." + (j >> 5) + ".mcr");
        Reference reference = (Reference) a.get(file3);
        RegionFile regionfile;

        if (reference != null) {
            regionfile = (RegionFile) reference.get();
            if (regionfile != null) {
                return regionfile;
            }
        }

        if (!file2.exists()) {
            file2.mkdirs();
        }

        if (a.size() >= 256) {
            a();
        }

        regionfile = new RegionFile(file3);
        a.put(file3, new SoftReference(regionfile));
        return regionfile;
    }

    public static synchronized void a() {
        Iterator iterator = a.values().iterator();

        while (iterator.hasNext()) {
            Reference reference = (Reference) iterator.next();

            try {
                RegionFile regionfile = (RegionFile) reference.get();

                if (regionfile != null) {
                    regionfile.b();
                }
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }

        a.clear();
    }

    public static int b(File file1, int i, int j) {
        RegionFile regionfile = a(file1, i, j);

        return regionfile.a();
    }

    public static DataInputStream c(File file1, int i, int j) {
        RegionFile regionfile = a(file1, i, j);

        return regionfile.a(i & 31, j & 31);
    }

    public static DataOutputStream d(File file1, int i, int j) {
        RegionFile regionfile = a(file1, i, j);

        return regionfile.b(i & 31, j & 31);
    }
}
