package net.minecraft.server;

public class PacketPlayInUseEntity extends Packet {

    private int a;
    private EnumEntityUseAction action;

    public PacketPlayInUseEntity() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readInt();
        this.action = EnumEntityUseAction.a()[packetdataserializer.readByte() % EnumEntityUseAction.a().length];
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.a);
        packetdataserializer.writeByte(EnumEntityUseAction.a(this.action));
    }

    public void a(PacketPlayInListener packetplayinlistener) {
        packetplayinlistener.a(this);
    }

    public Entity a(World world) {
        return world.getEntity(this.a);
    }

    public EnumEntityUseAction c() {
        return this.action;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }
}
