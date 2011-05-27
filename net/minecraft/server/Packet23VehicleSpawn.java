package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet23VehicleSpawn extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public Packet23VehicleSpawn() {}

    public Packet23VehicleSpawn(Entity entity, int i) {
        this.a = entity.id;
        this.b = MathHelper.b(entity.locX * 32.0D);
        this.c = MathHelper.b(entity.locY * 32.0D);
        this.d = MathHelper.b(entity.locZ * 32.0D);
        this.e = i;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.e = datainputstream.readByte();
        this.b = datainputstream.readInt();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeByte(this.e);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 17;
    }
}
