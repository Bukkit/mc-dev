package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet18ArmAnimation extends Packet {

    public int a;
    public int b;

    public Packet18ArmAnimation() {}

    public Packet18ArmAnimation(Entity entity, int i) {
        this.a = entity.id;
        this.b = i;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeByte(this.b);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 5;
    }
}
