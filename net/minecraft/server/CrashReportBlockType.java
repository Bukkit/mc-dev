package net.minecraft.server;

import java.util.concurrent.Callable;

final class CrashReportBlockType implements Callable {

    final int a;
    final Block b;

    CrashReportBlockType(int i, Block block) {
        this.a = i;
        this.b = block;
    }

    public String a() {
        try {
            return String.format("ID #%d (%s // %s)", new Object[] { Integer.valueOf(this.a), this.b.a(), this.b.getClass().getCanonicalName()});
        } catch (Throwable throwable) {
            return "ID #" + this.a;
        }
    }

    public Object call() {
        return this.a();
    }
}
