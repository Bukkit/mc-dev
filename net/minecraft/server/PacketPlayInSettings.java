package net.minecraft.server;

public class PacketPlayInSettings extends Packet {

    private String a;
    private int b;
    private EnumChatVisibility c;
    private boolean d;
    private EnumDifficulty e;
    private boolean f;

    public PacketPlayInSettings() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c(7);
        this.b = packetdataserializer.readByte();
        this.c = EnumChatVisibility.a(packetdataserializer.readByte());
        this.d = packetdataserializer.readBoolean();
        this.e = EnumDifficulty.a(packetdataserializer.readByte());
        this.f = packetdataserializer.readBoolean();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.writeByte(this.b);
        packetdataserializer.writeByte(this.c.a());
        packetdataserializer.writeBoolean(this.d);
        packetdataserializer.writeByte(this.e.a());
        packetdataserializer.writeBoolean(this.f);
    }

    public void a(PacketPlayInListener packetplayinlistener) {
        packetplayinlistener.a(this);
    }

    public String c() {
        return this.a;
    }

    public int d() {
        return this.b;
    }

    public EnumChatVisibility e() {
        return this.c;
    }

    public boolean f() {
        return this.d;
    }

    public EnumDifficulty g() {
        return this.e;
    }

    public boolean h() {
        return this.f;
    }

    public String b() {
        return String.format("lang=\'%s\', view=%d, chat=%s, col=%b, difficulty=%s, cape=%b", new Object[] { this.a, Integer.valueOf(this.b), this.c, Boolean.valueOf(this.d), this.e, Boolean.valueOf(this.f)});
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }
}
