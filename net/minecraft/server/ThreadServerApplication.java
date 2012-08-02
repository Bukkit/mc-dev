package net.minecraft.server;

public class ThreadServerApplication extends Thread {

    final MinecraftServer a;

    public ThreadServerApplication(MinecraftServer minecraftserver, String s) {
        super(s);
        this.a = minecraftserver;
    }

    public void run() {
        this.a.run();
    }
}
