package net.minecraft.server;

public class TileEntitySign extends TileEntity {

    public String[] lines = new String[] { "", "", "", ""};
    public int i = -1;
    private boolean isEditable = true;
    private EntityHuman k;

    public TileEntitySign() {}

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setString("Text1", this.lines[0]);
        nbttagcompound.setString("Text2", this.lines[1]);
        nbttagcompound.setString("Text3", this.lines[2]);
        nbttagcompound.setString("Text4", this.lines[3]);
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.isEditable = false;
        super.a(nbttagcompound);

        for (int i = 0; i < 4; ++i) {
            this.lines[i] = nbttagcompound.getString("Text" + (i + 1));
            if (this.lines[i].length() > 15) {
                this.lines[i] = this.lines[i].substring(0, 15);
            }
        }
    }

    public Packet getUpdatePacket() {
        String[] astring = new String[4];

        System.arraycopy(this.lines, 0, astring, 0, 4);
        return new PacketPlayOutUpdateSign(this.x, this.y, this.z, astring);
    }

    public boolean a() {
        return this.isEditable;
    }

    public void a(EntityHuman entityhuman) {
        this.k = entityhuman;
    }

    public EntityHuman b() {
        return this.k;
    }
}
