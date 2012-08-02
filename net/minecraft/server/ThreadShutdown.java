package net.minecraft.server;

public final class ThreadShutdown extends Thread {

    final DedicatedServer a;

    public ThreadShutdown(DedicatedServer dedicatedserver) {
        this.a = dedicatedserver;
    }

    public void run() {
        this.a.stop();
    }
}
