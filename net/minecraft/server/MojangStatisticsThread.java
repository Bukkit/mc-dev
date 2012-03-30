package net.minecraft.server;

class MojangStatisticsThread extends Thread {

    final MojangStatisticsGenerator a;

    MojangStatisticsThread(MojangStatisticsGenerator mojangstatisticsgenerator, String s) {
        super(s);
        this.a = mojangstatisticsgenerator;
    }

    public void run() {
        HttpUtilities.a(MojangStatisticsGenerator.a(this.a), MojangStatisticsGenerator.b(this.a), true);
    }
}
