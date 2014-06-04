package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportLevelGameMode implements Callable {

    final WorldData a;

    CrashReportLevelGameMode(WorldData worlddata) {
        this.a = worlddata;
    }

    public String a() {
        return String.format("Game mode: %s (ID %d). Hardcore: %b. Cheats: %b", new Object[] { WorldData.o(this.a).b(), Integer.valueOf(WorldData.o(this.a).getId()), Boolean.valueOf(WorldData.p(this.a)), Boolean.valueOf(WorldData.q(this.a))});
    }

    public Object call() {
        return this.a();
    }
}
