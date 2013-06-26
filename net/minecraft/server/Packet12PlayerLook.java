package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet12PlayerLook extends Packet10Flying {

    public Packet12PlayerLook() {
        this.hasLook = true;
    }

    public void a(DataInput datainput) {
        this.yaw = datainput.readFloat();
        this.pitch = datainput.readFloat();
        super.a(datainput);
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeFloat(this.yaw);
        dataoutput.writeFloat(this.pitch);
        super.a(dataoutput);
    }

    public int a() {
        return 9;
    }
}
