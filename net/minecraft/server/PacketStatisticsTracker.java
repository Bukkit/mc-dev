package net.minecraft.server;

import java.util.concurrent.atomic.AtomicReference;

class PacketStatisticsTracker {

    private AtomicReference[] a = new AtomicReference[100];

    public PacketStatisticsTracker() {
        for (int i = 0; i < 100; ++i) {
            this.a[i] = new AtomicReference(new PackStatisticData(0L, 0, 0.0D, (ModdingApi) null));
        }
    }

    public void a(int i, long j) {
        try {
            if (i < 0 || i >= 100) {
                return;
            }

            PackStatisticData packstatisticdata;
            PackStatisticData packstatisticdata1;

            do {
                packstatisticdata = (PackStatisticData) this.a[i].get();
                packstatisticdata1 = packstatisticdata.a(j);
            } while (!this.a[i].compareAndSet(packstatisticdata, packstatisticdata1));
        } catch (Exception exception) {
            if (NetworkStatistics.i().isDebugEnabled()) {
                NetworkStatistics.i().debug(NetworkStatistics.j(), "NetStat failed with packetId: " + i, exception);
            }
        }
    }

    public long a() {
        long i = 0L;

        for (int j = 0; j < 100; ++j) {
            i += ((PackStatisticData) this.a[j].get()).a();
        }

        return i;
    }

    public long b() {
        long i = 0L;

        for (int j = 0; j < 100; ++j) {
            i += (long) ((PackStatisticData) this.a[j].get()).b();
        }

        return i;
    }

    public PacketStatistics c() {
        int i = -1;
        PackStatisticData packstatisticdata = new PackStatisticData(-1L, -1, 0.0D, (ModdingApi) null);

        for (int j = 0; j < 100; ++j) {
            PackStatisticData packstatisticdata1 = (PackStatisticData) this.a[j].get();

            if (PackStatisticData.a(packstatisticdata1) > PackStatisticData.a(packstatisticdata)) {
                i = j;
                packstatisticdata = packstatisticdata1;
            }
        }

        return new PacketStatistics(i, packstatisticdata);
    }

    public PacketStatistics d() {
        int i = -1;
        PackStatisticData packstatisticdata = new PackStatisticData(-1L, -1, 0.0D, (ModdingApi) null);

        for (int j = 0; j < 100; ++j) {
            PackStatisticData packstatisticdata1 = (PackStatisticData) this.a[j].get();

            if (PackStatisticData.b(packstatisticdata1) > PackStatisticData.b(packstatisticdata)) {
                i = j;
                packstatisticdata = packstatisticdata1;
            }
        }

        return new PacketStatistics(i, packstatisticdata);
    }

    public PacketStatistics a(int i) {
        return i >= 0 && i < 100 ? new PacketStatistics(i, (PackStatisticData) this.a[i].get()) : null;
    }
}
