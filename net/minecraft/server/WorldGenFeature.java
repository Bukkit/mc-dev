package net.minecraft.server;

public class WorldGenFeature extends WorldMapBase {

    private NBTTagCompound a = new NBTTagCompound("Features");

    public WorldGenFeature(String s) {
        super(s);
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.a = nbttagcompound.getCompound("Features");
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.set("Features", this.a);
    }

    public void a(NBTTagCompound nbttagcompound, int i, int j) {
        String s = this.b(i, j);

        nbttagcompound.setName(s);
        this.a.set(s, nbttagcompound);
    }

    public String b(int i, int j) {
        return "[" + i + "," + j + "]";
    }

    public NBTTagCompound a() {
        return this.a;
    }
}
