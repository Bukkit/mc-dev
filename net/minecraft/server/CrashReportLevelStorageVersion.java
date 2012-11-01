package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportLevelStorageVersion implements Callable {

    final WorldData a;

    CrashReportLevelStorageVersion(WorldData worlddata) {
        this.a = worlddata;
    }

    public String a() {
        String s = "Unknown?";

        try {
            switch (WorldData.j(this.a)) {
            case 19132:
                s = "McRegion";
                break;

            case 19133:
                s = "Anvil";
            }
        } catch (Throwable throwable) {
            ;
        }

        return String.format("0x%05X - %s", new Object[] { Integer.valueOf(WorldData.j(this.a)), s});
    }

    public Object call() {
        return this.a();
    }
}
