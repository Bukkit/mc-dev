package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportCorruptNBTTag2 implements Callable {

    final int a;
    final NBTTagCompound b;

    CrashReportCorruptNBTTag2(NBTTagCompound nbttagcompound, int i) {
        this.b = nbttagcompound;
        this.a = i;
    }

    public String a() {
        return NBTBase.a[this.a];
    }

    public Object call() {
        return this.a();
    }
}
