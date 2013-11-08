package net.minecraft.server;

class MobSpawner extends MobSpawnerAbstract {

    final TileEntityMobSpawner a;

    MobSpawner(TileEntityMobSpawner tileentitymobspawner) {
        this.a = tileentitymobspawner;
    }

    public void a(int i) {
        this.a.world.playNote(this.a.x, this.a.y, this.a.z, Blocks.MOB_SPAWNER, i, 0);
    }

    public World a() {
        return this.a.world;
    }

    public int b() {
        return this.a.x;
    }

    public int c() {
        return this.a.y;
    }

    public int d() {
        return this.a.z;
    }

    public void a(TileEntityMobSpawnerData tileentitymobspawnerdata) {
        super.a(tileentitymobspawnerdata);
        if (this.a() != null) {
            this.a().notify(this.a.x, this.a.y, this.a.z);
        }
    }
}
