package net.minecraft.server;

class ThreadSleepForever extends Thread {

    final DedicatedServer a;

    ThreadSleepForever(DedicatedServer dedicatedserver, String s) {
        super(s);
        this.a = dedicatedserver;
        this.setDaemon(true);
        this.start();
    }

    public void run() {
        while (true) {
            try {
                while (true) {
                    Thread.sleep(2147483647L);
                }
            } catch (InterruptedException interruptedexception) {
                ;
            }
        }
    }
}
