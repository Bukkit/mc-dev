package net.minecraft.server;

public class PacketPlayInUpdateSign extends Packet {

    private int a;
    private int b;
    private int c;
    private String[] d;

    public PacketPlayInUpdateSign() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readInt();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.readInt();
        this.d = new String[4];

        for (int i = 0; i < 4; ++i) {
            this.d[i] = packetdataserializer.c(15);
        }
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.a);
        packetdataserializer.writeShort(this.b);
        packetdataserializer.writeInt(this.c);

        for (int i = 0; i < 4; ++i) {
            packetdataserializer.a(this.d[i]);
        }
    }

    public void a(PacketPlayInListener packetplayinlistener) {
        packetplayinlistener.a(this);
    }

    public int c() {
        return this.a;
    }

    public int d() {
        return this.b;
    }

    public int e() {
        return this.c;
    }

    public String[] f() {
        return this.d;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }
}
