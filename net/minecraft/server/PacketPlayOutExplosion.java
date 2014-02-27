package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PacketPlayOutExplosion extends Packet {

    private double a;
    private double b;
    private double c;
    private float d;
    private List e;
    private float f;
    private float g;
    private float h;

    public PacketPlayOutExplosion() {}

    public PacketPlayOutExplosion(double d0, double d1, double d2, float f, List list, Vec3D vec3d) {
        this.a = d0;
        this.b = d1;
        this.c = d2;
        this.d = f;
        this.e = new ArrayList(list);
        if (vec3d != null) {
            this.f = (float) vec3d.a;
            this.g = (float) vec3d.b;
            this.h = (float) vec3d.c;
        }
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = (double) packetdataserializer.readFloat();
        this.b = (double) packetdataserializer.readFloat();
        this.c = (double) packetdataserializer.readFloat();
        this.d = packetdataserializer.readFloat();
        int i = packetdataserializer.readInt();

        this.e = new ArrayList(i);
        int j = (int) this.a;
        int k = (int) this.b;
        int l = (int) this.c;

        for (int i1 = 0; i1 < i; ++i1) {
            int j1 = packetdataserializer.readByte() + j;
            int k1 = packetdataserializer.readByte() + k;
            int l1 = packetdataserializer.readByte() + l;

            this.e.add(new ChunkPosition(j1, k1, l1));
        }

        this.f = packetdataserializer.readFloat();
        this.g = packetdataserializer.readFloat();
        this.h = packetdataserializer.readFloat();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeFloat((float) this.a);
        packetdataserializer.writeFloat((float) this.b);
        packetdataserializer.writeFloat((float) this.c);
        packetdataserializer.writeFloat(this.d);
        packetdataserializer.writeInt(this.e.size());
        int i = (int) this.a;
        int j = (int) this.b;
        int k = (int) this.c;
        Iterator iterator = this.e.iterator();

        while (iterator.hasNext()) {
            ChunkPosition chunkposition = (ChunkPosition) iterator.next();
            int l = chunkposition.x - i;
            int i1 = chunkposition.y - j;
            int j1 = chunkposition.z - k;

            packetdataserializer.writeByte(l);
            packetdataserializer.writeByte(i1);
            packetdataserializer.writeByte(j1);
        }

        packetdataserializer.writeFloat(this.f);
        packetdataserializer.writeFloat(this.g);
        packetdataserializer.writeFloat(this.h);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
