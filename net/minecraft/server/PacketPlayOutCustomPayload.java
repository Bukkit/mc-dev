package net.minecraft.server;

import net.minecraft.util.io.netty.buffer.ByteBuf;

public class PacketPlayOutCustomPayload extends Packet {

    private String tag;
    private byte[] data;

    public PacketPlayOutCustomPayload() {}

    public PacketPlayOutCustomPayload(String s, ByteBuf bytebuf) {
        this(s, bytebuf.array());
    }

    public PacketPlayOutCustomPayload(String s, byte[] abyte) {
        this.tag = s;
        this.data = abyte;
        if (abyte.length >= 1048576) {
            throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
        }
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.tag = packetdataserializer.c(20);
        this.data = new byte[packetdataserializer.readUnsignedShort()];
        packetdataserializer.readBytes(this.data);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.tag);
        packetdataserializer.writeShort(this.data.length);
        packetdataserializer.writeBytes(this.data);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
