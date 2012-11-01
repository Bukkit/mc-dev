package net.minecraft.server;

import java.util.Comparator;

class PackageNameComparator implements Comparator {

    final CrashReportSuspiciousClasses a;

    PackageNameComparator(CrashReportSuspiciousClasses crashreportsuspiciousclasses) {
        this.a = crashreportsuspiciousclasses;
    }

    public int a(Class oclass, Class oclass1) {
        String s = oclass.getPackage() == null ? "" : oclass.getPackage().getName();
        String s1 = oclass1.getPackage() == null ? "" : oclass1.getPackage().getName();

        return s.compareTo(s1);
    }

    public int compare(Object object, Object object1) {
        return this.a((Class) object, (Class) object1);
    }
}
