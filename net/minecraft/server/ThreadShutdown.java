package net.minecraft.server;

public final class ThreadShutdown extends Thread {

    final DedicatedServer a;

    public ThreadShutdown(String s, DedicatedServer dedicatedserver) {
        super(s);
        this.a = dedicatedserver;
    }

    public void run() {
        this.a.stop();
    }
}
