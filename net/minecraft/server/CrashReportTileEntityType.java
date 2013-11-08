package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportTileEntityType implements Callable {

    final TileEntity a;

    CrashReportTileEntityType(TileEntity tileentity) {
        this.a = tileentity;
    }

    public String a() {
        int i = Block.b(this.a.world.getType(this.a.x, this.a.y, this.a.z));

        try {
            return String.format("ID #%d (%s // %s)", new Object[] { Integer.valueOf(i), Block.e(i).a(), Block.e(i).getClass().getCanonicalName()});
        } catch (Throwable throwable) {
            return "ID #" + i;
        }
    }

    public Object call() {
        return this.a();
    }
}
