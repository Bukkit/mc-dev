package net.minecraft.server;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.Callable;

class CrashReportSuspiciousClasses implements Callable {

    final CrashReport a;

    CrashReportSuspiciousClasses(CrashReport crashreport) {
        this.a = crashreport;
    }

    public String a() {
        StringBuilder stringbuilder = new StringBuilder();
        Field field = ClassLoader.class.getDeclaredField("classes");

        field.setAccessible(true);
        ArrayList arraylist = new ArrayList((Vector) field.get(CrashReport.class.getClassLoader()));
        boolean flag = true;
        boolean flag1 = !CrashReport.class.getCanonicalName().equals("net.minecraft.CrashReport");
        HashMap hashmap = new HashMap();
        String s = "";

        Collections.sort(arraylist, new PackageNameComparator(this));
        Iterator iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            Class oclass = (Class) iterator.next();

            if (oclass != null) {
                String s1 = oclass.getCanonicalName();

                if (s1 != null && !s1.startsWith("org.lwjgl.") && !s1.startsWith("paulscode.") && !s1.startsWith("org.bouncycastle.") && !s1.startsWith("argo.") && !s1.startsWith("com.jcraft.") && !s1.startsWith("com.fasterxml.") && !s1.startsWith("com.google.") && !s1.startsWith("joptsimple.") && !s1.startsWith("org.apache.") && !s1.equals("util.GLX")) {
                    if (flag1) {
                        if (s1.length() <= 3 || s1.equals("net.minecraft.client.main.Main") || s1.equals("net.minecraft.client.Minecraft") || s1.equals("net.minecraft.client.ClientBrandRetriever") || s1.equals("net.minecraft.server.MinecraftServer")) {
                            continue;
                        }
                    } else if (s1.startsWith("net.minecraft")) {
                        continue;
                    }

                    Package package = oclass.getPackage();
                    String s2 = package == null ? "" : package.getName();

                    if (hashmap.containsKey(s2)) {
                        int i = ((Integer) hashmap.get(s2)).intValue();

                        hashmap.put(s2, Integer.valueOf(i + 1));
                        if (i == 3) {
                            if (!flag) {
                                stringbuilder.append(", ");
                            }

                            stringbuilder.append("...");
                            flag = false;
                            continue;
                        }

                        if (i > 3) {
                            continue;
                        }
                    } else {
                        hashmap.put(s2, Integer.valueOf(1));
                    }

                    if (!s.equals(s2) && s.length() > 0) {
                        stringbuilder.append("], ");
                    }

                    if (!flag && s.equals(s2)) {
                        stringbuilder.append(", ");
                    }

                    if (!s.equals(s2)) {
                        stringbuilder.append("[");
                        stringbuilder.append(s2);
                        stringbuilder.append(".");
                    }

                    stringbuilder.append(oclass.getSimpleName());
                    s = s2;
                    flag = false;
                }
            }
        }

        if (flag) {
            stringbuilder.append("No suspicious classes found.");
        } else {
            stringbuilder.append("]");
        }

        return stringbuilder.toString();
    }

    public Object call() {
        return this.a();
    }
}
