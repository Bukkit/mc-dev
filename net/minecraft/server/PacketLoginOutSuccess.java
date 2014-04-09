package net.minecraft.server;

import java.util.UUID;

import net.minecraft.util.com.mojang.authlib.GameProfile;

public class PacketLoginOutSuccess extends Packet {

    private GameProfile a;

    public PacketLoginOutSuccess() {}

    public PacketLoginOutSuccess(GameProfile gameprofile) {
        this.a = gameprofile;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        String s = packetdataserializer.c(36);
        String s1 = packetdataserializer.c(16);
        UUID uuid = UUID.fromString(s);

        this.a = new GameProfile(uuid, s1);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        UUID uuid = this.a.getId();

        packetdataserializer.a(uuid == null ? "" : uuid.toString());
        packetdataserializer.a(this.a.getName());
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
