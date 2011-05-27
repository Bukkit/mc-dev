package net.minecraft.server;

class SpawnerMonsters extends SpawnerCreature {

    final WorldServer a;

    SpawnerMonsters(WorldServer worldserver, int i, Class oclass, Class[] aclass) {
        super(i, oclass, aclass);
        this.a = worldserver;
    }

    protected ChunkPosition a(World world, int i, int j) {
        int k = i + world.m.nextInt(16);
        int l = world.m.nextInt(world.m.nextInt(120) + 8);
        int i1 = j + world.m.nextInt(16);

        return new ChunkPosition(k, l, i1);
    }
}
