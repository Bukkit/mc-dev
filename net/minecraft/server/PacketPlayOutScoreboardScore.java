package net.minecraft.server;

public class PacketPlayOutScoreboardScore extends Packet {

    private String a = "";
    private String b = "";
    private int c;
    private int d;

    public PacketPlayOutScoreboardScore() {}

    public PacketPlayOutScoreboardScore(ScoreboardScore scoreboardscore, int i) {
        this.a = scoreboardscore.getPlayerName();
        this.b = scoreboardscore.getObjective().getName();
        this.c = scoreboardscore.getScore();
        this.d = i;
    }

    public PacketPlayOutScoreboardScore(String s) {
        this.a = s;
        this.b = "";
        this.c = 0;
        this.d = 1;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c(16);
        this.d = packetdataserializer.readByte();
        if (this.d != 1) {
            this.b = packetdataserializer.c(16);
            this.c = packetdataserializer.readInt();
        }
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.writeByte(this.d);
        if (this.d != 1) {
            packetdataserializer.a(this.b);
            packetdataserializer.writeInt(this.c);
        }
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
