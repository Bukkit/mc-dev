package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class PacketUnusedFishing extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public byte e;
    public byte f;
    public byte g;
    public int h;

    public PacketUnusedFishing() {}

    public PacketUnusedFishing(Entity entity, int i) {
        this.a = entity.g;
        this.b = MathHelper.b(entity.p * 32.0D);
        this.c = MathHelper.b(entity.q * 32.0D);
        this.d = MathHelper.b(entity.r * 32.0D);
        this.e = (byte) ((int) (entity.s * 128.0D));
        this.f = (byte) ((int) (entity.t * 128.0D));
        this.g = (byte) ((int) (entity.u * 128.0D));
        this.h = i;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.h = datainputstream.readByte();
        this.b = datainputstream.readInt();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = datainputstream.readByte();
        this.f = datainputstream.readByte();
        this.g = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeByte(this.h);
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
        return 20;
    }
}
