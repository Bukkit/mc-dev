package net.minecraft.server;

public class PacketHandshakingInSetProtocol extends Packet {

    private int a;
    private String b;
    private int c;
    private EnumProtocol d;

    public PacketHandshakingInSetProtocol() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.a();
        this.b = packetdataserializer.c(255);
        this.c = packetdataserializer.readUnsignedShort();
        this.d = EnumProtocol.a(packetdataserializer.a());
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
        packetdataserializer.a(this.b);
        packetdataserializer.writeShort(this.c);
        packetdataserializer.b(this.d.c());
    }

    public void a(PacketHandshakingInListener packethandshakinginlistener) {
        packethandshakinginlistener.a(this);
    }

    public boolean a() {
        return true;
    }

    public EnumProtocol c() {
        return this.d;
    }

    public int d() {
        return this.a;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketHandshakingInListener) packetlistener);
    }
}
