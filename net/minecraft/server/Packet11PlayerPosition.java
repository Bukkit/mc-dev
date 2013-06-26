package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet11PlayerPosition extends Packet10Flying {

    public Packet11PlayerPosition() {
        this.hasPos = true;
    }

    public void a(DataInput datainput) {
        this.x = datainput.readDouble();
        this.y = datainput.readDouble();
        this.stance = datainput.readDouble();
        this.z = datainput.readDouble();
        super.a(datainput);
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeDouble(this.x);
        dataoutput.writeDouble(this.y);
        dataoutput.writeDouble(this.stance);
        dataoutput.writeDouble(this.z);
        super.a(dataoutput);
    }

    public int a() {
        return 33;
    }
}
