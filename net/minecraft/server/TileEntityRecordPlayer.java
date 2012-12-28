package net.minecraft.server;

public class TileEntityRecordPlayer extends TileEntity {

    public ItemStack record;

    public TileEntityRecordPlayer() {}

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.hasKey("RecordItem")) {
            this.record = ItemStack.createStack(nbttagcompound.getCompound("RecordItem"));
        } else {
            this.record = new ItemStack(nbttagcompound.getInt("Record"), 1, 0);
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.record != null) {
            nbttagcompound.setCompound("RecordItem", this.record.save(new NBTTagCompound()));
            nbttagcompound.setInt("Record", this.record.id);
        }
    }
}
