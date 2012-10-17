package net.minecraft.server;

import java.util.concurrent.Callable;

public class CrashReportVec3DPoolSize implements Callable {

    final MinecraftServer a;

    public CrashReportVec3DPoolSize(MinecraftServer minecraftserver) {
        this.a = minecraftserver;
    }

    public String a() {
        int i = this.a.worldServer[0].getVec3DPool().c();
        int j = 56 * i;
        int k = j / 1024 / 1024;
        int l = this.a.worldServer[0].getVec3DPool().d();
        int i1 = 56 * l;
        int j1 = i1 / 1024 / 1024;

        return i + " (" + j + " bytes; " + k + " MB) allocated, " + l + " (" + i1 + " bytes; " + j1 + " MB) used";
    }

    public Object call() {
        return this.a();
    }
}
