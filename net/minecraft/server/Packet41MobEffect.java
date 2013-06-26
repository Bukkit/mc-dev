package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet41MobEffect extends Packet {

    public int a;
    public byte b;
    public byte c;
    public short d;

    public Packet41MobEffect() {}

    public Packet41MobEffect(int i, MobEffect mobeffect) {
        this.a = i;
        this.b = (byte) (mobeffect.getEffectId() & 255);
        this.c = (byte) (mobeffect.getAmplifier() & 255);
        if (mobeffect.getDuration() > 32767) {
            this.d = 32767;
        } else {
            this.d = (short) mobeffect.getDuration();
        }
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readByte();
        this.c = datainput.readByte();
        this.d = datainput.readShort();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeByte(this.b);
        dataoutput.writeByte(this.c);
        dataoutput.writeShort(this.d);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 8;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        Packet41MobEffect packet41mobeffect = (Packet41MobEffect) packet;

        return packet41mobeffect.a == this.a && packet41mobeffect.b == this.b;
    }
}
