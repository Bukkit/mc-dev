package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet11PlayerPosition extends Packet10Flying {

    public Packet11PlayerPosition() {
        this.h = true;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readDouble();
        this.b = datainputstream.readDouble();
        this.d = datainputstream.readDouble();
        this.c = datainputstream.readDouble();
        super.a(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeDouble(this.a);
        dataoutputstream.writeDouble(this.b);
        dataoutputstream.writeDouble(this.d);
        dataoutputstream.writeDouble(this.c);
        super.a(dataoutputstream);
    }

    public int a() {
        return 33;
    }
}
