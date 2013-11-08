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
                hashmap.put("snooper_count", Integer.valueOf(MojangStatisticsGenerator.d(this.a)));
            }

            HttpUtilities.a(MojangStatisticsGenerator.e(this.a), (Map) hashmap, true);
        }
    }
}
