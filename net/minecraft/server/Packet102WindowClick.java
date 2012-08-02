package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet102WindowClick extends Packet {

    public int a;
    public int slot;
    public int button;
    public short d;
    public ItemStack item;
    public boolean shift;

    public Packet102WindowClick() {}

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readByte();
        this.slot = datainputstream.readShort();
        this.button = datainputstream.readByte();
        this.d = datainputstream.readShort();
        this.shift = datainputstream.readBoolean();
        this.item = c(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.a);
        dataoutputstream.writeShort(this.slot);
        dataoutputstream.writeByte(this.button);
        dataoutputstream.writeShort(this.d);
        dataoutputstream.writeBoolean(this.shift);
        a(this.item, dataoutputstream);
    }

    public int a() {
        return 11;
    }
}
