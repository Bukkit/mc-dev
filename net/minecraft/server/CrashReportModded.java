package net.minecraft.server;

import java.util.concurrent.Callable;

public class CrashReportModded implements Callable {

    final MinecraftServer a;

    public CrashReportModded(MinecraftServer minecraftserver) {
        this.a = minecraftserver;
    }

    public String a() {
        String s = this.a.getServerModName();

        return !s.equals("vanilla") ? "Definitely; \'" + s + "\'" : "Unknown (can\'t tell)";
    }

    public Object call() {
        return this.a();
    }
}
