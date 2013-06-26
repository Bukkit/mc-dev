package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

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
        this.e = entityexperienceorb.c();
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readInt();
        this.c = datainput.readInt();
        this.d = datainput.readInt();
        this.e = datainput.readShort();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeInt(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.writeInt(this.d);
        dataoutput.writeShort(this.e);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 18;
    }
}
