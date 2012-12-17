package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

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

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readShort();
        this.c = c(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeShort(this.b);
        a(this.c, dataoutputstream);
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
