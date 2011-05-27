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

    public Packet21PickupSpawn() {}

    public Packet21PickupSpawn(EntityItem entityitem) {
        this.a = entityitem.g;
        this.h = entityitem.a.c;
        this.i = entityitem.a.a;
        this.b = MathHelper.b(entityitem.p * 32.0D);
        this.c = MathHelper.b(entityitem.q * 32.0D);
        this.d = MathHelper.b(entityitem.r * 32.0D);
        this.e = (byte) ((int) (entityitem.s * 128.0D));
        this.f = (byte) ((int) (entityitem.t * 128.0D));
        this.g = (byte) ((int) (entityitem.u * 128.0D));
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.h = datainputstream.readShort();
        this.i = datainputstream.readByte();
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
        return 22;
    }
}
