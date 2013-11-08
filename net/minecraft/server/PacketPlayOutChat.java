package net.minecraft.server;

public class PacketPlayOutChat extends Packet {

    private IChatBaseComponent a;
    private boolean b;

    public PacketPlayOutChat() {
        this.b = true;
    }

    public PacketPlayOutChat(IChatBaseComponent ichatbasecomponent) {
        this(ichatbasecomponent, true);
    }

    public PacketPlayOutChat(IChatBaseComponent ichatbasecomponent, boolean flag) {
        this.b = true;
        this.a = ichatbasecomponent;
        this.b = flag;
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

    public String b() {
        return String.format("message=\'%s\'", new Object[] { this.a});
    }

    public boolean d() {
        return this.b;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
