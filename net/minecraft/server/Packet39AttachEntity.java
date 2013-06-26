package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet39AttachEntity extends Packet {

    public int a;
    public int b;
    public int c;

    public Packet39AttachEntity() {}

    public Packet39AttachEntity(int i, Entity entity, Entity entity1) {
        this.a = i;
        this.b = entity.id;
        this.c = entity1 != null ? entity1.id : -1;
    }

    public int a() {
        return 8;
    }

    public void a(DataInput datainput) {
        this.b = datainput.readInt();
        this.c = datainput.readInt();
        this.a = datainput.readUnsignedByte();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.writeByte(this.a);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        Packet39AttachEntity packet39attachentity = (Packet39AttachEntity) packet;

        return packet39attachentity.b == this.b;
    }
}
