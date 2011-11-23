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
    public int h;
    public int i;
    public int m;

    public Packet21PickupSpawn() {}

    public Packet21PickupSpawn(EntityItem entityitem) {
        this.a = entityitem.id;
        this.h = entityitem.itemStack.id;
        this.i = entityitem.itemStack.count;
        this.m = entityitem.itemStack.getData();
        this.b = MathHelper.floor(entityitem.locX * 32.0D);
        this.c = MathHelper.floor(entityitem.locY * 32.0D);
        this.d = MathHelper.floor(entityitem.locZ * 32.0D);
        this.e = (byte) ((int) (entityitem.motX * 128.0D));
        this.f = (byte) ((int) (entityitem.motY * 128.0D));
        this.g = (byte) ((int) (entityitem.motZ * 128.0D));
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.h = datainputstream.readShort();
        this.i = datainputstream.readByte();
        this.m = datainputstream.readShort();
        this.b = datainputstream.readInt();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = datainputstream.readByte();
        this.f = datainputstream.readByte();
        this.g = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeShort(this.h);
        dataoutputstream.writeByte(this.i);
        dataoutputstream.writeShort(this.m);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeByte(this.e);
        dataoutputstream.writeByte(this.f);
        dataoutputstream.writeByte(this.g);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 24;
    }
}
