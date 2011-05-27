package net.minecraft.server;

import java.io.IOException;
import java.util.Properties;

public class StatisticStorage {

    private static StatisticStorage a = new StatisticStorage();
    private Properties b = new Properties();

    private StatisticStorage() {
        try {
            this.b.load(StatisticStorage.class.getResourceAsStream("/lang/en_US.lang"));
            this.b.load(StatisticStorage.class.getResourceAsStream("/lang/stats_US.lang"));
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }

    public static StatisticStorage a() {
        return a;
    }

    public String a(String s) {
        return this.b.getProperty(s, s);
    }

    public String a(String s, Object... aobject) {
        String s1 = this.b.getProperty(s, s);

        return String.format(s1, aobject);
    }
}
