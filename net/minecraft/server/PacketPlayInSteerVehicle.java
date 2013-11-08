package net.minecraft.server;

public class PacketPlayInSteerVehicle extends Packet {

    private float a;
    private float b;
    private boolean c;
    private boolean d;

    public PacketPlayInSteerVehicle() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readFloat();
        this.b = packetdataserializer.readFloat();
        this.c = packetdataserializer.readBoolean();
        this.d = packetdataserializer.readBoolean();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeFloat(this.a);
        packetdataserializer.writeFloat(this.b);
        packetdataserializer.writeBoolean(this.c);
        packetdataserializer.writeBoolean(this.d);
    }

    public void a(PacketPlayInListener packetplayinlistener) {
        packetplayinlistener.a(this);
    }

    public float c() {
        return this.a;
    }

    public float d() {
        return this.b;
    }

    public boolean e() {
        return this.c;
    }

    public boolean f() {
        return this.d;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }
}
