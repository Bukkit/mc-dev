package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet71Weather extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public Packet71Weather() {}

    public Packet71Weather(Entity entity) {
        this.a = entity.id;
        this.b = MathHelper.floor(entity.locX * 32.0D);
        this.c = MathHelper.floor(entity.locY * 32.0D);
        this.d = MathHelper.floor(entity.locZ * 32.0D);
        if (entity instanceof EntityLightning) {
            this.e = 1;
        }
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.e = datainput.readByte();
        this.b = datainput.readInt();
        this.c = datainput.readInt();
        this.d = datainput.readInt();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeByte(this.e);
        dataoutput.writeInt(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.writeInt(this.d);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 17;
    }
}
