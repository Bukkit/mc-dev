package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet21PickupSpawn extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public byte e;
    public byte f;
    public byte g;
    public ItemStack h;

    public Packet21PickupSpawn() {}

    public Packet21PickupSpawn(EntityItem entityitem) {
        this.a = entityitem.id;
        this.h = entityitem.itemStack.cloneItemStack();
        this.b = MathHelper.floor(entityitem.locX * 32.0D);
        this.c = MathHelper.floor(entityitem.locY * 32.0D);
        this.d = MathHelper.floor(entityitem.locZ * 32.0D);
        this.e = (byte) ((int) (entityitem.motX * 128.0D));
        this.f = (byte) ((int) (entityitem.motY * 128.0D));
        this.g = (byte) ((int) (entityitem.motZ * 128.0D));
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.h = c(datainputstream);
        this.b = datainputstream.readInt();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = datainputstream.readByte();
        this.f = datainputstream.readByte();
        this.g = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        a(this.h, dataoutputstream);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeByte(this.e);
        dataoutputstream.writeByte(this.f);
        dataoutputstream.writeByte(this.g);
    }

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 24;
    }
}
