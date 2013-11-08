package net.minecraft.server;

public class PacketPlayOutLogin extends Packet {

    private int a;
    private boolean b;
    private EnumGamemode c;
    private int d;
    private EnumDifficulty e;
    private int f;
    private WorldType g;

    public PacketPlayOutLogin() {}

    public PacketPlayOutLogin(int i, EnumGamemode enumgamemode, boolean flag, int j, EnumDifficulty enumdifficulty, int k, WorldType worldtype) {
        this.a = i;
        this.d = j;
        this.e = enumdifficulty;
        this.c = enumgamemode;
        this.f = k;
        this.b = flag;
        this.g = worldtype;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readInt();
        short short1 = packetdataserializer.readUnsignedByte();

        this.b = (short1 & 8) == 8;
        int i = short1 & -9;

        this.c = EnumGamemode.a(i);
        this.d = packetdataserializer.readByte();
        this.e = EnumDifficulty.a(packetdataserializer.readUnsignedByte());
        this.f = packetdataserializer.readUnsignedByte();
        this.g = WorldType.getType(packetdataserializer.c(16));
        if (this.g == null) {
            this.g = WorldType.NORMAL;
        }
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.a);
        int i = this.c.a();

        if (this.b) {
            i |= 8;
        }

        packetdataserializer.writeByte(i);
        packetdataserializer.writeByte(this.d);
        packetdataserializer.writeByte(this.e.a());
        packetdataserializer.writeByte(this.f);
        packetdataserializer.a(this.g.name());
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public String b() {
        return String.format("eid=%d, gameType=%d, hardcore=%b, dimension=%d, difficulty=%s, maxplayers=%d", new Object[] { Integer.valueOf(this.a), Integer.valueOf(this.c.a()), Boolean.valueOf(this.b), Integer.valueOf(this.d), this.e, Integer.valueOf(this.f)});
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
