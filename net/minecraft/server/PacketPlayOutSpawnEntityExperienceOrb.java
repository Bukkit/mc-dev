package net.minecraft.server;

public class PacketPlayOutSpawnEntityExperienceOrb extends Packet {

    private int a;
    private int b;
    private int c;
    private int d;
    private int e;

    public PacketPlayOutSpawnEntityExperienceOrb() {}

    public PacketPlayOutSpawnEntityExperienceOrb(EntityExperienceOrb entityexperienceorb) {
        this.a = entityexperienceorb.getId();
        this.b = MathHelper.floor(entityexperienceorb.locX * 32.0D);
        this.c = MathHelper.floor(entityexperienceorb.locY * 32.0D);
        this.d = MathHelper.floor(entityexperienceorb.locZ * 32.0D);
        this.e = entityexperienceorb.e();
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.a();
        this.b = packetdataserializer.readInt();
        this.c = packetdataserializer.readInt();
        this.d = packetdataserializer.readInt();
        this.e = packetdataserializer.readShort();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
        packetdataserializer.writeInt(this.b);
        packetdataserializer.writeInt(this.c);
        packetdataserializer.writeInt(this.d);
        packetdataserializer.writeShort(this.e);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public String b() {
        return String.format("id=%d, value=%d, x=%.2f, y=%.2f, z=%.2f", new Object[] { Integer.valueOf(this.a), Integer.valueOf(this.e), Float.valueOf((float) this.b / 32.0F), Float.valueOf((float) this.c / 32.0F), Float.valueOf((float) this.d / 32.0F)});
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
