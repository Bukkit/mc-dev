package net.minecraft.server;

public class PacketPlayInLook extends PacketPlayInFlying {

    public PacketPlayInLook() {
        this.hasLook = true;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.yaw = packetdataserializer.readFloat();
        this.pitch = packetdataserializer.readFloat();
        super.a(packetdataserializer);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeFloat(this.yaw);
        packetdataserializer.writeFloat(this.pitch);
        super.b(packetdataserializer);
    }

    public void handle(PacketListener packetlistener) {
        super.a((PacketPlayInListener) packetlistener);
    }
}
