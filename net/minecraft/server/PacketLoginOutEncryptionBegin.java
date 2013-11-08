package net.minecraft.server;

import java.security.PublicKey;

import net.minecraft.util.io.netty.buffer.ByteBuf;

public class PacketLoginOutEncryptionBegin extends Packet {

    private String a;
    private PublicKey b;
    private byte[] c;

    public PacketLoginOutEncryptionBegin() {}

    public PacketLoginOutEncryptionBegin(String s, PublicKey publickey, byte[] abyte) {
        this.a = s;
        this.b = publickey;
        this.c = abyte;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c(20);
        this.b = MinecraftEncryption.a(a((ByteBuf) packetdataserializer));
        this.c = a((ByteBuf) packetdataserializer);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        a(packetdataserializer, this.b.getEncoded());
        a(packetdataserializer, this.c);
    }

    public void a(PacketLoginOutListener packetloginoutlistener) {
        packetloginoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketLoginOutListener) packetlistener);
    }
}
