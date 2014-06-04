package net.minecraft.server;

public class PacketPlayOutRespawn extends Packet {

    private int a;
    private EnumDifficulty b;
    private EnumGamemode c;
    private WorldType d;

    public PacketPlayOutRespawn() {}

    public PacketPlayOutRespawn(int i, EnumDifficulty enumdifficulty, WorldType worldtype, EnumGamemode enumgamemode) {
        this.a = i;
        this.b = enumdifficulty;
        this.c = enumgamemode;
        this.d = worldtype;
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readInt();
        this.b = EnumDifficulty.getById(packetdataserializer.readUnsignedByte());
        this.c = EnumGamemode.getById(packetdataserializer.readUnsignedByte());
        this.d = WorldType.getType(packetdataserializer.c(16));
        if (this.d == null) {
            this.d = WorldType.NORMAL;
        }
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.a);
        packetdataserializer.writeByte(this.b.a());
        packetdataserializer.writeByte(this.c.getId());
        packetdataserializer.a(this.d.name());
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
