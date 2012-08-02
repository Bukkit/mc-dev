package net.minecraft.server;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

class CrashReportJVMFlags implements Callable {

    final CrashReport a;

    CrashReportJVMFlags(CrashReport crashreport) {
        this.a = crashreport;
    }

    public String a() {
        RuntimeMXBean runtimemxbean = ManagementFactory.getRuntimeMXBean();
        List list = runtimemxbean.getInputArguments();
        int i = 0;
        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            if (s.startsWith("-X")) {
                if (i++ > 0) {
                    stringbuilder.append(" ");
                }

                stringbuilder.append(s);
            }
        }

        return String.format("%d total; %s", new Object[] { Integer.valueOf(i), stringbuilder.toString()});
    }

    public Object call() {
        return this.a();
    }
}
