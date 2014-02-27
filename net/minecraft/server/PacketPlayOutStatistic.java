package net.minecraft.server;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.util.com.google.common.collect.Maps;

public class PacketPlayOutStatistic extends Packet {

    private Map a;

    public PacketPlayOutStatistic() {}

    public PacketPlayOutStatistic(Map map) {
        this.a = map;
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        int i = packetdataserializer.a();

        this.a = Maps.newHashMap();

        for (int j = 0; j < i; ++j) {
            Statistic statistic = StatisticList.getStatistic(packetdataserializer.c(32767));
            int k = packetdataserializer.a();

            if (statistic != null) {
                this.a.put(statistic, Integer.valueOf(k));
            }
        }
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a.size());
        Iterator iterator = this.a.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();

            packetdataserializer.a(((Statistic) entry.getKey()).name);
            packetdataserializer.b(((Integer) entry.getValue()).intValue());
        }
    }

    public String b() {
        return String.format("count=%d", new Object[] { Integer.valueOf(this.a.size())});
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
