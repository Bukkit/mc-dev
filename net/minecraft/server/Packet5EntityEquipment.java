package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet5EntityEquipment extends Packet {

    public int a;
    public int b;
    private ItemStack c;

    public Packet5EntityEquipment() {}

    public Packet5EntityEquipment(int i, int j, ItemStack itemstack) {
        this.a = i;
        this.b = j;
        this.c = itemstack == null ? null : itemstack.cloneItemStack();
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readShort();
        this.c = c(datainput);
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeShort(this.b);
        a(this.c, dataoutput);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 8;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        Packet5EntityEquipment packet5entityequipment = (Packet5EntityEquipment) packet;

        return packet5entityequipment.a == this.a && packet5entityequipment.b == this.b;
    }
}
