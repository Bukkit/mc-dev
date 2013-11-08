package net.minecraft.server;

public class PacketPlayOutEntityHeadRotation extends Packet {

    private int a;
    private byte b;

    public PacketPlayOutEntityHeadRotation() {}

    public PacketPlayOutEntityHeadRotation(Entity entity, byte b0) {
        this.a = entity.getId();
        this.b = b0;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readInt();
        this.b = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.a);
        packetdataserializer.writeByte(this.b);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public String b() {
        return String.format("id=%d, rot=%d", new Object[] { Integer.valueOf(this.a), Byte.valueOf(this.b)});
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
