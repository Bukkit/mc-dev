package net.minecraft.server;

public class PacketLoginOutDisconnect extends Packet {

    private IChatBaseComponent a;

    public PacketLoginOutDisconnect() {}

    public PacketLoginOutDisconnect(IChatBaseComponent ichatbasecomponent) {
        this.a = ichatbasecomponent;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = ChatSerializer.a(packetdataserializer.c(32767));
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(ChatSerializer.a(this.a));
    }

    public void a(PacketLoginOutListener packetloginoutlistener) {
        packetloginoutlistener.a(this);
    }

    public boolean a() {
        return true;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketLoginOutListener) packetlistener);
    }
}
