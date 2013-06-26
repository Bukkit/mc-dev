package net.minecraft.server;

public class TileEntityComparator extends TileEntity {

    private int a;

    public TileEntityComparator() {}

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("OutputSignal", this.a);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a = nbttagcompound.getInt("OutputSignal");
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }
}
