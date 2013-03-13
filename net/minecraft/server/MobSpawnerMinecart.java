package net.minecraft.server;

class MobSpawnerMinecart extends MobSpawnerAbstract {

    final EntityMinecartMobSpawner a;

    MobSpawnerMinecart(EntityMinecartMobSpawner entityminecartmobspawner) {
        this.a = entityminecartmobspawner;
    }

    public void a(int i) {
        this.a.world.broadcastEntityEffect(this.a, (byte) i);
    }

    public World a() {
        return this.a.world;
    }

    public int b() {
        return MathHelper.floor(this.a.locX);
    }

    public int c() {
        return MathHelper.floor(this.a.locY);
    }

    public int d() {
        return MathHelper.floor(this.a.locZ);
    }
}
