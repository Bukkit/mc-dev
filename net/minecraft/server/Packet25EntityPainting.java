package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

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

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.f = a(datainputstream, EnumArt.A);
        this.b = datainputstream.readInt();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        a(this.f, dataoutputstream);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeInt(this.e);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 24;
    }
}
