package net.minecraft.server;

public class TileEntityRecordPlayer extends TileEntity {

    public int record;

    public TileEntityRecordPlayer() {}

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.record = nbttagcompound.getInt("Record");
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.record > 0) {
            nbttagcompound.setInt("Record", this.record);
        }
    }
}
