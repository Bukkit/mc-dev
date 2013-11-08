package net.minecraft.server;

public class PacketPlayOutScoreboardObjective extends Packet {

    private String a;
    private String b;
    private int c;

    public PacketPlayOutScoreboardObjective() {}

    public PacketPlayOutScoreboardObjective(ScoreboardObjective scoreboardobjective, int i) {
        this.a = scoreboardobjective.getName();
        this.b = scoreboardobjective.getDisplayName();
        this.c = i;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c(16);
        this.b = packetdataserializer.c(32);
        this.c = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.a(this.b);
        packetdataserializer.writeByte(this.c);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
