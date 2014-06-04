package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportSourceBlockType implements Callable {

    final Block a;
    final World b;

    CrashReportSourceBlockType(World world, Block block) {
        this.b = world;
        this.a = block;
    }

    public String a() {
        try {
            return String.format("ID #%d (%s // %s)", new Object[] { Integer.valueOf(Block.getId(this.a)), this.a.a(), this.a.getClass().getCanonicalName()});
        } catch (Throwable throwable) {
            return "ID #" + Block.getId(this.a);
        }
    }

    public Object call() {
        return this.a();
    }
}
