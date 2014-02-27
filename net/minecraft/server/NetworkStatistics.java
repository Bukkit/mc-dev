package net.minecraft.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class NetworkStatistics {

    private static final Logger a = LogManager.getLogger();
    private static final Marker b = MarkerManager.getMarker("NETSTAT_MARKER", NetworkManager.c);
    private PacketStatisticsTracker c = new PacketStatisticsTracker();
    private PacketStatisticsTracker d = new PacketStatisticsTracker();

    public NetworkStatistics() {}

    public void a(int i, long j) {
        this.c.a(i, j);
    }

    public void b(int i, long j) {
        this.d.a(i, j);
    }

    public long a() {
        return this.c.a();
    }

    public long b() {
        return this.d.a();
    }

    public long c() {
        return this.c.b();
    }

    public long d() {
        return this.d.b();
    }

    public PacketStatistics e() {
        return this.c.c();
    }

    public PacketStatistics f() {
        return this.c.d();
    }

    public PacketStatistics g() {
        return this.d.c();
    }

    public PacketStatistics h() {
        return this.d.d();
    }

    public PacketStatistics a(int i) {
        return this.c.a(i);
    }

    public PacketStatistics b(int i) {
        return this.d.a(i);
    }

    static Logger i() {
        return a;
    }

    static Marker j() {
        return b;
    }
}
