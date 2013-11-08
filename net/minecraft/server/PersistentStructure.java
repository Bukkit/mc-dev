package net.minecraft.server;

public class PersistentStructure extends PersistentBase {

    private NBTTagCompound a = new NBTTagCompound();

    public PersistentStructure(String s) {
        super(s);
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.a = nbttagcompound.getCompound("Features");
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.set("Features", this.a);
    }

    public void a(NBTTagCompound nbttagcompound, int i, int j) {
        this.a.set(b(i, j), nbttagcompound);
    }

    public static String b(int i, int j) {
        return "[" + i + "," + j + "]";
    }

    public NBTTagCompound a() {
        return this.a;
    }
}
