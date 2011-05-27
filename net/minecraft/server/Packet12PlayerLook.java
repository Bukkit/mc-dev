package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet12PlayerLook extends Packet10Flying {

    public Packet12PlayerLook() {
        this.hasLook = true;
    }

    public void a(DataInputStream datainputstream) {
        this.yaw = datainputstream.readFloat();
        this.pitch = datainputstream.readFloat();
        super.a(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeFloat(this.yaw);
        dataoutputstream.writeFloat(this.pitch);
        super.a(dataoutputstream);
    }

    public int a() {
        return 9;
    }
}
