package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet30Entity extends Packet {

    public int a;
    public byte b;
    public byte c;
    public byte d;
    public byte e;
    public byte f;
    public boolean g;

    public Packet30Entity() {}

    public Packet30Entity(int i) {
        this.a = i;
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 4;
    }

    public String toString() {
        return "Entity_" + super.toString();
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        Packet30Entity packet30entity = (Packet30Entity) packet;

        return packet30entity.a == this.a;
    }
}
