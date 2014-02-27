package net.minecraft.server;

public class PacketStatistics {

    private final int a;
    private final PackStatisticData b;

    public PacketStatistics(int i, PackStatisticData packstatisticdata) {
        this.a = i;
        this.b = packstatisticdata;
    }

    public String toString() {
        return "PacketStat(" + this.a + ")" + this.b;
    }
}
