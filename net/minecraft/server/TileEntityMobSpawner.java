package net.minecraft.server;

public class TileEntityMobSpawner extends TileEntity {

    private final MobSpawnerAbstract a = new MobSpawner(this);

    public TileEntityMobSpawner() {}

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a.b(nbttagcompound);
    }

    public void h() {
        this.a.g();
        super.h();
    }

    public Packet getUpdatePacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        this.b(nbttagcompound);
        nbttagcompound.remove("SpawnPotentials");
        return new PacketPlayOutTileEntityData(this.x, this.y, this.z, 1, nbttagcompound);
    }

    public boolean c(int i, int j) {
        return this.a.b(i) ? true : super.c(i, j);
    }

    public MobSpawnerAbstract a() {
        return this.a;
    }
}
