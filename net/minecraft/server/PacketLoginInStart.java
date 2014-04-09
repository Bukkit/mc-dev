package net.minecraft.server;

import java.util.UUID;

import net.minecraft.util.com.mojang.authlib.GameProfile;

public class PacketLoginInStart extends Packet {

    private GameProfile a;

    public PacketLoginInStart() {}

    public PacketLoginInStart(GameProfile gameprofile) {
        this.a = gameprofile;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = new GameProfile((UUID) null, packetdataserializer.c(16));
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a.getName());
    }

    public void a(PacketLoginInListener packetlogininlistener) {
        packetlogininlistener.a(this);
    }

    public GameProfile c() {
        return this.a;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketLoginInListener) packetlistener);
    }
}
