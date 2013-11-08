package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportTileEntityName implements Callable {

    final TileEntity a;

    CrashReportTileEntityName(TileEntity tileentity) {
        this.a = tileentity;
    }

    public String a() {
        return (String) TileEntity.v().get(this.a.getClass()) + " // " + this.a.getClass().getCanonicalName();
    }

    public Object call() {
        return this.a();
    }
}
