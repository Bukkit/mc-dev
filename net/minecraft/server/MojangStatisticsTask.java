package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

class MojangStatisticsTask extends TimerTask {

    final MojangStatisticsGenerator a;

    MojangStatisticsTask(MojangStatisticsGenerator mojangstatisticsgenerator) {
        this.a = mojangstatisticsgenerator;
    }

    public void run() {
        if (MojangStatisticsGenerator.a(this.a).getSnooperEnabled()) {
            HashMap hashmap;

            synchronized (MojangStatisticsGenerator.b(this.a)) {
                hashmap = new HashMap(MojangStatisticsGenerator.c(this.a));
                if (MojangStatisticsGenerator.d(this.a) == 0) {
                    hashmap.putAll(MojangStatisticsGenerator.e(this.a));
                }

                hashmap.put("snooper_count", Integer.valueOf(MojangStatisticsGenerator.f(this.a)));
                hashmap.put("snooper_token", MojangStatisticsGenerator.g(this.a));
            }

            HttpUtilities.a(MojangStatisticsGenerator.h(this.a), (Map) hashmap, true);
        }
    }
}
