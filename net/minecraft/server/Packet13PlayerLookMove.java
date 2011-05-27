package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet13PlayerLookMove extends Packet10Flying {

    public Packet13PlayerLookMove() {
        this.hasLook = true;
        this.h = true;
    }

    public Packet13PlayerLookMove(double d0, double d1, double d2, double d3, float f, float f1, boolean flag) {
        this.x = d0;
        this.y = d1;
        this.stance = d2;
        this.z = d3;
        this.yaw = f;
        this.pitch = f1;
        this.g = flag;
        this.hasLook = true;
        this.h = true;
    }

    public void a(DataInputStream datainputstream) {
        this.x = datainputstream.readDouble();
        this.y = datainputstream.readDouble();
        this.stance = datainputstream.readDouble();
        this.z = datainputstream.readDouble();
        this.yaw = datainputstream.readFloat();
        this.pitch = datainputstream.readFloat();
        super.a(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeDouble(this.x);
        dataoutputstream.writeDouble(this.y);
        dataoutputstream.writeDouble(this.stance);
        dataoutputstream.writeDouble(this.z);
        dataoutputstream.writeFloat(this.yaw);
        dataoutputstream.writeFloat(this.pitch);
        super.a(dataoutputstream);
    }

    public int a() {
        return 41;
    }
}
