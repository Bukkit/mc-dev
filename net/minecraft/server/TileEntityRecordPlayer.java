package net.minecraft.server;

public class TileEntityRecordPlayer extends TileEntity {

    public int a;

    public TileEntityRecordPlayer() {}

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a = nbttagcompound.getInt("Record");
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.a > 0) {
            nbttagcompound.setInt("Record", this.a);
        }
    }
}
