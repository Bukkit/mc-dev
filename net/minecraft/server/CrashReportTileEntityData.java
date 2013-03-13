package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportTileEntityData implements Callable {

    final TileEntity a;

    CrashReportTileEntityData(TileEntity tileentity) {
        this.a = tileentity;
    }

    public String a() {
        int i = this.a.world.getData(this.a.x, this.a.y, this.a.z);

        if (i < 0) {
            return "Unknown? (Got " + i + ")";
        } else {
            String s = String.format("%4s", new Object[] { Integer.toBinaryString(i)}).replace(" ", "0");

            return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[] { Integer.valueOf(i), s});
        }
    }

    public Object call() {
        return this.a();
    }
}
