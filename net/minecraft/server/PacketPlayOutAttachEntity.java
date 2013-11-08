package net.minecraft.server;

public class PacketPlayOutAttachEntity extends Packet {

    private int a;
    private int b;
    private int c;

    public PacketPlayOutAttachEntity() {}

    public PacketPlayOutAttachEntity(int i, Entity entity, Entity entity1) {
        this.a = i;
        this.b = entity.getId();
        this.c = entity1 != null ? entity1.getId() : -1;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.b = packetdataserializer.readInt();
        this.c = packetdataserializer.readInt();
        this.a = packetdataserializer.readUnsignedByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.b);
        packetdataserializer.writeInt(this.c);
        packetdataserializer.writeByte(this.a);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
