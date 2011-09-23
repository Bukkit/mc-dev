package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet42RemoveMobEffect extends Packet {

    public int a;
    public byte b;

    public Packet42RemoveMobEffect() {}

    public Packet42RemoveMobEffect(int i, MobEffect mobeffect) {
        this.a = i;
        this.b = (byte) (mobeffect.getEffectId() & 255);
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
