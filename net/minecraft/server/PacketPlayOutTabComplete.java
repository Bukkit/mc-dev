package net.minecraft.server;

import net.minecraft.util.org.apache.commons.lang3.ArrayUtils;

public class PacketPlayOutTabComplete extends Packet {

    private String[] a;

    public PacketPlayOutTabComplete() {}

    public PacketPlayOutTabComplete(String[] astring) {
        this.a = astring;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = new String[packetdataserializer.a()];

        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = packetdataserializer.c(32767);
        }
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a.length);
        String[] astring = this.a;
        int i = astring.length;

        for (int j = 0; j < i; ++j) {
            String s = astring[j];

            packetdataserializer.a(s);
        }
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public String b() {
        return String.format("candidates=\'%s\'", new Object[] { ArrayUtils.toString(this.a)});
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
