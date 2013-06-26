package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Packet60Explosion extends Packet {

    public double a;
    public double b;
    public double c;
    public float d;
    public List e;
    private float f;
    private float g;
    private float h;

    public Packet60Explosion() {}

    public Packet60Explosion(double d0, double d1, double d2, float f, List list, Vec3D vec3d) {
        this.a = d0;
        this.b = d1;
        this.c = d2;
        this.d = f;
        this.e = new ArrayList(list);
        if (vec3d != null) {
            this.f = (float) vec3d.c;
            this.g = (float) vec3d.d;
            this.h = (float) vec3d.e;
        }
    }

    public void a(DataInput datainput) {
        this.a = datainput.readDouble();
        this.b = datainput.readDouble();
        this.c = datainput.readDouble();
        this.d = datainput.readFloat();
        int i = datainput.readInt();

        this.e = new ArrayList(i);
        int j = (int) this.a;
        int k = (int) this.b;
        int l = (int) this.c;

        for (int i1 = 0; i1 < i; ++i1) {
            int j1 = datainput.readByte() + j;
            int k1 = datainput.readByte() + k;
            int l1 = datainput.readByte() + l;

            this.e.add(new ChunkPosition(j1, k1, l1));
        }

        this.f = datainput.readFloat();
        this.g = datainput.readFloat();
        this.h = datainput.readFloat();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeDouble(this.a);
        dataoutput.writeDouble(this.b);
        dataoutput.writeDouble(this.c);
        dataoutput.writeFloat(this.d);
        dataoutput.writeInt(this.e.size());
        int i = (int) this.a;
        int j = (int) this.b;
        int k = (int) this.c;
        Iterator iterator = this.e.iterator();

        while (iterator.hasNext()) {
            ChunkPosition chunkposition = (ChunkPosition) iterator.next();
            int l = chunkposition.x - i;
            int i1 = chunkposition.y - j;
            int j1 = chunkposition.z - k;

            dataoutput.writeByte(l);
            dataoutput.writeByte(i1);
            dataoutput.writeByte(j1);
        }

        dataoutput.writeFloat(this.f);
        dataoutput.writeFloat(this.g);
        dataoutput.writeFloat(this.h);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 32 + this.e.size() * 3 + 3;
    }
}
