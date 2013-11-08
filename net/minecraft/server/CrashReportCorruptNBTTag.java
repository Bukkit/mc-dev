package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportCorruptNBTTag implements Callable {

    final String a;
    final NBTTagCompound b;

    CrashReportCorruptNBTTag(NBTTagCompound nbttagcompound, String s) {
        this.b = nbttagcompound;
        this.a = s;
    }

    public String a() {
        return NBTBase.a[((NBTBase) NBTTagCompound.a(this.b).get(this.a)).getTypeId()];
    }

    public Object call() {
        return this.a();
    }
}
