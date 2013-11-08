package net.minecraft.server;

import java.security.PrivateKey;
import javax.crypto.SecretKey;

import net.minecraft.util.io.netty.buffer.ByteBuf;

public class PacketLoginInEncryptionBegin extends Packet {

    private byte[] a = new byte[0];
    private byte[] b = new byte[0];

    public PacketLoginInEncryptionBegin() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = a((ByteBuf) packetdataserializer);
        this.b = a((ByteBuf) packetdataserializer);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        a(packetdataserializer, this.a);
        a(packetdataserializer, this.b);
    }

    public void a(PacketLoginInListener packetlogininlistener) {
        packetlogininlistener.a(this);
    }

    public SecretKey a(PrivateKey privatekey) {
        return MinecraftEncryption.a(privatekey, this.a);
    }

    public byte[] b(PrivateKey privatekey) {
        return privatekey == null ? this.b : MinecraftEncryption.b(privatekey, this.b);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketLoginInListener) packetlistener);
    }
}
