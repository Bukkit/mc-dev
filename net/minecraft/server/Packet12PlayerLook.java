package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet12PlayerLook extends Packet10Flying {

    public Packet12PlayerLook() {
        this.i = true;
    }

    public void a(DataInputStream datainputstream) {
        this.e = datainputstream.readFloat();
        this.f = datainputstream.readFloat();
        super.a(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeFloat(this.e);
        dataoutputstream.writeFloat(this.f);
        super.a(dataoutputstream);
    }

    public int a() {
        return 9;
    }
}
