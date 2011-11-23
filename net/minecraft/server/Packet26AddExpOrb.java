package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet26AddExpOrb extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public Packet26AddExpOrb() {}

    public Packet26AddExpOrb(EntityExperienceOrb entityexperienceorb) {
        this.a = entityexperienceorb.id;
        this.b = MathHelper.floor(entityexperienceorb.locX * 32.0D);
        this.c = MathHelper.floor(entityexperienceorb.locY * 32.0D);
        this.d = MathHelper.floor(entityexperienceorb.locZ * 32.0D);
        this.e = entityexperienceorb.g();
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readInt();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeShort(this.e);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 18;
    }
}
