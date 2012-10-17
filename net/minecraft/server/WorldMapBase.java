package net.minecraft.server;

public abstract class WorldMapBase {

    public final String id;
    private boolean a;

    public WorldMapBase(String s) {
        this.id = s;
    }

    public abstract void a(NBTTagCompound nbttagcompound);

    public abstract void b(NBTTagCompound nbttagcompound);

    public void c() {
        this.a(true);
    }

    public void a(boolean flag) {
        this.a = flag;
    }

    public boolean d() {
        return this.a;
    }
}
