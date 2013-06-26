package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet25EntityPainting extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public String f;

    public Packet25EntityPainting() {}

    public Packet25EntityPainting(EntityPainting entitypainting) {
        this.a = entitypainting.id;
        this.b = entitypainting.x;
        this.c = entitypainting.y;
        this.d = entitypainting.z;
        this.e = entitypainting.direction;
        this.f = entitypainting.art.B;
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.f = a(datainput, EnumArt.A);
        this.b = datainput.readInt();
        this.c = datainput.readInt();
        this.d = datainput.readInt();
        this.e = datainput.readInt();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        a(this.f, dataoutput);
        dataoutput.writeInt(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.writeInt(this.d);
        dataoutput.writeInt(this.e);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 24;
    }
}
