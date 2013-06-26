package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet34EntityTeleport extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public byte e;
    public byte f;

    public Packet34EntityTeleport() {}

    public Packet34EntityTeleport(Entity entity) {
        this.a = entity.id;
        this.b = MathHelper.floor(entity.locX * 32.0D);
        this.c = MathHelper.floor(entity.locY * 32.0D);
        this.d = MathHelper.floor(entity.locZ * 32.0D);
        this.e = (byte) ((int) (entity.yaw * 256.0F / 360.0F));
        this.f = (byte) ((int) (entity.pitch * 256.0F / 360.0F));
    }

    public Packet34EntityTeleport(int i, int j, int k, int l, byte b0, byte b1) {
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = l;
        this.e = b0;
        this.f = b1;
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readInt();
        this.c = datainput.readInt();
        this.d = datainput.readInt();
        this.e = datainput.readByte();
        this.f = datainput.readByte();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeInt(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.writeInt(this.d);
        dataoutput.write(this.e);
        dataoutput.write(this.f);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 34;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        Packet34EntityTeleport packet34entityteleport = (Packet34EntityTeleport) packet;

        return packet34entityteleport.a == this.a;
    }
}
