package net.minecraft.server;

public class PacketPlayOutKickDisconnect extends Packet {

    private IChatBaseComponent a;

    public PacketPlayOutKickDisconnect() {}

    public PacketPlayOutKickDisconnect(IChatBaseComponent ichatbasecomponent) {
        this.a = ichatbasecomponent;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = ChatSerializer.a(packetdataserializer.c(32767));
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(ChatSerializer.a(this.a));
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public boolean a() {
        return true;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
