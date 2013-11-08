package net.minecraft.server;

public class PacketPlayOutAbilities extends Packet {

    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private float e;
    private float f;

    public PacketPlayOutAbilities() {}

    public PacketPlayOutAbilities(PlayerAbilities playerabilities) {
        this.a(playerabilities.isInvulnerable);
        this.b(playerabilities.isFlying);
        this.c(playerabilities.canFly);
        this.d(playerabilities.canInstantlyBuild);
        this.a(playerabilities.a());
        this.b(playerabilities.b());
    }

    public void a(PacketDataSerializer packetdataserializer) {
        byte b0 = packetdataserializer.readByte();

        this.a((b0 & 1) > 0);
        this.b((b0 & 2) > 0);
        this.c((b0 & 4) > 0);
        this.d((b0 & 8) > 0);
        this.a(packetdataserializer.readFloat());
        this.b(packetdataserializer.readFloat());
    }

    public void b(PacketDataSerializer packetdataserializer) {
        byte b0 = 0;

        if (this.c()) {
            b0 = (byte) (b0 | 1);
        }

        if (this.d()) {
            b0 = (byte) (b0 | 2);
        }

        if (this.e()) {
            b0 = (byte) (b0 | 4);
        }

        if (this.f()) {
            b0 = (byte) (b0 | 8);
        }

        packetdataserializer.writeByte(b0);
        packetdataserializer.writeFloat(this.e);
        packetdataserializer.writeFloat(this.f);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public String b() {
        return String.format("invuln=%b, flying=%b, canfly=%b, instabuild=%b, flyspeed=%.4f, walkspped=%.4f", new Object[] { Boolean.valueOf(this.c()), Boolean.valueOf(this.d()), Boolean.valueOf(this.e()), Boolean.valueOf(this.f()), Float.valueOf(this.g()), Float.valueOf(this.h())});
    }

    public boolean c() {
        return this.a;
    }

    public void a(boolean flag) {
        this.a = flag;
    }

    public boolean d() {
        return this.b;
    }

    public void b(boolean flag) {
        this.b = flag;
    }

    public boolean e() {
        return this.c;
    }

    public void c(boolean flag) {
        this.c = flag;
    }

    public boolean f() {
        return this.d;
    }

    public void d(boolean flag) {
        this.d = flag;
    }

    public float g() {
        return this.e;
    }

    public void a(float f) {
        this.e = f;
    }

    public float h() {
        return this.f;
    }

    public void b(float f) {
        this.f = f;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
