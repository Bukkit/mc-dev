package net.minecraft.server;

import java.util.Random;

public class BiomeJungle extends BiomeBase {

    public BiomeJungle(int i) {
        super(i);
        this.I.z = 50;
        this.I.B = 25;
        this.I.A = 4;
        this.J.add(new BiomeMeta(EntityOcelot.class, 2, 1, 1));
        this.K.add(new BiomeMeta(EntityChicken.class, 10, 4, 4));
    }

    public WorldGenerator a(Random random) {
        return (WorldGenerator) (random.nextInt(10) == 0 ? this.P : (random.nextInt(2) == 0 ? new WorldGenGroundBush(3, 0) : (random.nextInt(3) == 0 ? new WorldGenMegaTree(false, 10 + random.nextInt(20), 3, 3) : new WorldGenTrees(false, 4 + random.nextInt(7), 3, 3, true))));
    }

    public WorldGenerator b(Random random) {
        return random.nextInt(4) == 0 ? new WorldGenGrass(Block.LONG_GRASS.id, 2) : new WorldGenGrass(Block.LONG_GRASS.id, 1);
    }

    public void a(World world, Random random, int i, int j) {
        super.a(world, random, i, j);
        WorldGenVines worldgenvines = new WorldGenVines();

        for (int k = 0; k < 50; ++k) {
            int l = i + random.nextInt(16) + 8;
            byte b0 = 64;
            int i1 = j + random.nextInt(16) + 8;

            worldgenvines.a(world, random, l, b0, i1);
        }
    }
}
