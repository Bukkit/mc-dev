package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportTileEntityType implements Callable {

    final TileEntity a;

    CrashReportTileEntityType(TileEntity tileentity) {
        this.a = tileentity;
    }

    public String a() {
        int i = this.a.world.getTypeId(this.a.x, this.a.y, this.a.z);

        try {
            return String.format("ID #%d (%s // %s)", new Object[] { Integer.valueOf(i), Block.byId[i].a(), Block.byId[i].getClass().getCanonicalName()});
        } catch (Throwable throwable) {
            return "ID #" + i;
        }
    }

    public Object call() {
        return this.a();
    }
}
