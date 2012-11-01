package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportSourceBlockType implements Callable {

    final int a;

    final World b;

    CrashReportSourceBlockType(World world, int i) {
        this.b = world;
        this.a = i;
    }

    public String a() {
        try {
            return String.format("ID #%d (%s // %s)", new Object[] { Integer.valueOf(this.a), Block.byId[this.a].a(), Block.byId[this.a].getClass().getCanonicalName()});
        } catch (Throwable throwable) {
            return "ID #" + this.a;
        }
    }

    public Object call() {
        return this.a();
    }
}
