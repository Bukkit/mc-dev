package net.minecraft.server;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.UUID;

public class MojangStatisticsGenerator {

    private Map a = new HashMap();
    private final String b = UUID.randomUUID().toString();
    private final URL c;
    private final IMojangStatistics d;
    private final Timer e = new Timer("Snooper Timer", true);
    private final Object f = new Object();
    private final long g = System.currentTimeMillis();
    private boolean h = false;
    private int i = 0;

    public MojangStatisticsGenerator(String s, IMojangStatistics imojangstatistics) {
        try {
            this.c = new URL("http://snoop.minecraft.net/" + s + "?version=" + 1);
        } catch (MalformedURLException malformedurlexception) {
            throw new IllegalArgumentException();
        }

        this.d = imojangstatistics;
    }

    public void a() {
        if (!this.h) {
            this.h = true;
            this.g();
            this.e.schedule(new MojangStatisticsTask(this), 0L, 900000L);
        }
    }

    private void g() {
        this.h();
        this.a("snooper_token", this.b);
        this.a("os_name", System.getProperty("os.name"));
        this.a("os_version", System.getProperty("os.version"));
        this.a("os_architecture", System.getProperty("os.arch"));
        this.a("java_version", System.getProperty("java.version"));
        this.a("version", "1.5.2");
        this.d.b(this);
    }

    private void h() {
        RuntimeMXBean runtimemxbean = ManagementFactory.getRuntimeMXBean();
        List list = runtimemxbean.getInputArguments();
        int i = 0;
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            if (s.startsWith("-X")) {
                this.a("jvm_arg[" + i++ + "]", s);
            }
        }

        this.a("jvm_args", Integer.valueOf(i));
    }

    public void b() {
        this.a("memory_total", Long.valueOf(Runtime.getRuntime().totalMemory()));
        this.a("memory_max", Long.valueOf(Runtime.getRuntime().maxMemory()));
        this.a("memory_free", Long.valueOf(Runtime.getRuntime().freeMemory()));
        this.a("cpu_cores", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
        this.a("run_time", Long.valueOf((System.currentTimeMillis() - this.g) / 60L * 1000L));
        this.d.a(this);
    }

    public void a(String s, Object object) {
        Object object1 = this.f;

        synchronized (this.f) {
            this.a.put(s, object);
        }
    }

    public boolean d() {
        return this.h;
    }

    public void e() {
        this.e.cancel();
    }

    static IMojangStatistics a(MojangStatisticsGenerator mojangstatisticsgenerator) {
        return mojangstatisticsgenerator.d;
    }

    static Object b(MojangStatisticsGenerator mojangstatisticsgenerator) {
        return mojangstatisticsgenerator.f;
    }

    static Map c(MojangStatisticsGenerator mojangstatisticsgenerator) {
        return mojangstatisticsgenerator.a;
    }

    static int d(MojangStatisticsGenerator mojangstatisticsgenerator) {
        return mojangstatisticsgenerator.i++;
    }

    static URL e(MojangStatisticsGenerator mojangstatisticsgenerator) {
        return mojangstatisticsgenerator.c;
    }
}
