package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet10Flying extends Packet {

    public double x;
    public double y;
    public double z;
    public double stance;
    public float yaw;
    public float pitch;
    public boolean g;
    public boolean hasPos;
    public boolean hasLook;

    public Packet10Flying() {}

    public void handle(Connection connection) {
        connection.a(this);
    }

    public void a(DataInput datainput) {
        this.g = datainput.readUnsignedByte() != 0;
    }

    public void a(DataOutput dataoutput) {
        dataoutput.write(this.g ? 1 : 0);
    }

    public int a() {
        return 1;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        return true;
    }
}
