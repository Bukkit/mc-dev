package net.minecraft.server;

public class TileEntityFlowerPot extends TileEntity {

    private Item a;
    private int i;

    public TileEntityFlowerPot() {}

    public TileEntityFlowerPot(Item item, int i) {
        this.a = item;
        this.i = i;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Item", Item.b(this.a));
        nbttagcompound.setInt("Data", this.i);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a = Item.d(nbttagcompound.getInt("Item"));
        this.i = nbttagcompound.getInt("Data");
    }

    public Packet getUpdatePacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        this.b(nbttagcompound);
        return new PacketPlayOutTileEntityData(this.x, this.y, this.z, 5, nbttagcompound);
    }

    public void a(Item item, int i) {
        this.a = item;
        this.i = i;
    }

    public Item a() {
        return this.a;
    }

    public int b() {
        return this.i;
    }
}
