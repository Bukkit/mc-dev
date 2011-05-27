package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet24MobSpawn extends Packet {

    public int a;
    public byte b;
    public int c;
    public int d;
    public int e;
    public byte f;
    public byte g;

    public Packet24MobSpawn() {}

    public Packet24MobSpawn(EntityLiving entityliving) {
        this.a = entityliving.g;
        this.b = (byte) EntityTypes.a(entityliving);
        this.c = MathHelper.b(entityliving.p * 32.0D);
        this.d = MathHelper.b(entityliving.q * 32.0D);
        this.e = MathHelper.b(entityliving.r * 32.0D);
        this.f = (byte) ((int) (entityliving.v * 256.0F / 360.0F));
        this.g = (byte) ((int) (entityliving.w * 256.0F / 360.0F));
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readByte();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = datainputstream.readInt();
        this.f = datainputstream.readByte();
        this.g = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeByte(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeInt(this.e);
        dataoutputstream.writeByte(this.f);
        dataoutputstream.writeByte(this.g);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 19;
    }
}
