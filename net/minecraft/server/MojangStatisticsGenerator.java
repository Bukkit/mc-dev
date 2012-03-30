package net.minecraft.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MojangStatisticsGenerator {

    private Map a = new HashMap();
    private final URL b;

    public MojangStatisticsGenerator(String s) {
        try {
            this.b = new URL("http://snoop.minecraft.net/" + s);
        } catch (MalformedURLException malformedurlexception) {
            throw new IllegalArgumentException();
        }
    }

    public void a(String s, Object object) {
        this.a.put(s, object);
    }

    public void a() {
        MojangStatisticsThread mojangstatisticsthread = new MojangStatisticsThread(this, "reporter");

        mojangstatisticsthread.setDaemon(true);
        mojangstatisticsthread.start();
    }

    static URL a(MojangStatisticsGenerator mojangstatisticsgenerator) {
        return mojangstatisticsgenerator.b;
    }

    static Map b(MojangStatisticsGenerator mojangstatisticsgenerator) {
        return mojangstatisticsgenerator.a;
    }
}
