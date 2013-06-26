package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet13PlayerLookMove extends Packet10Flying {

    public Packet13PlayerLookMove() {
        this.hasLook = true;
        this.hasPos = true;
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
        this.hasPos = true;
    }

    public void a(DataInput datainput) {
        this.x = datainput.readDouble();
        this.y = datainput.readDouble();
        this.stance = datainput.readDouble();
        this.z = datainput.readDouble();
        this.yaw = datainput.readFloat();
        this.pitch = datainput.readFloat();
        super.a(datainput);
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeDouble(this.x);
        dataoutput.writeDouble(this.y);
        dataoutput.writeDouble(this.stance);
        dataoutput.writeDouble(this.z);
        dataoutput.writeFloat(this.yaw);
        dataoutput.writeFloat(this.pitch);
        super.a(dataoutput);
    }

    public int a() {
        return 41;
    }
}
