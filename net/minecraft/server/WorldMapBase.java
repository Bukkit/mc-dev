package net.minecraft.server;

public abstract class WorldMapBase {

    public final String a;
    private boolean b;

    public WorldMapBase(String s) {
        this.a = s;
    }

    public abstract void a(NBTTagCompound nbttagcompound);

    public abstract void b(NBTTagCompound nbttagcompound);

    public void a() {
        this.a(true);
    }

    public void a(boolean flag) {
        this.b = flag;
    }

    public boolean b() {
        return this.b;
    }
}
