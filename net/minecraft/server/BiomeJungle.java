package net.minecraft.server;

import java.util.Random;

public class BiomeJungle extends BiomeBase {

    private boolean aC;

    public BiomeJungle(int i, boolean flag) {
        super(i);
        this.aC = flag;
        if (flag) {
            this.ar.x = 2;
        } else {
            this.ar.x = 50;
        }

        this.ar.z = 25;
        this.ar.y = 4;
        if (!flag) {
            this.as.add(new BiomeMeta(EntityOcelot.class, 2, 1, 1));
        }

        this.at.add(new BiomeMeta(EntityChicken.class, 10, 4, 4));
    }

    public WorldGenTreeAbstract a(Random random) {
        return (WorldGenTreeAbstract) (random.nextInt(10) == 0 ? this.aA : (random.nextInt(2) == 0 ? new WorldGenGroundBush(3, 0) : (!this.aC && random.nextInt(3) == 0 ? new WorldGenJungleTree(false, 10, 20, 3, 3) : new WorldGenTrees(false, 4 + random.nextInt(7), 3, 3, true))));
    }

    public WorldGenerator b(Random random) {
        return random.nextInt(4) == 0 ? new WorldGenGrass(Blocks.LONG_GRASS, 2) : new WorldGenGrass(Blocks.LONG_GRASS, 1);
    }

    public void a(World world, Random random, int i, int j) {
        super.a(world, random, i, j);
        int k = i + random.nextInt(16) + 8;
        int l = j + random.nextInt(16) + 8;
        int i1 = random.nextInt(world.getHighestBlockYAt(k, l) * 2);

        (new WorldGenMelon()).a(world, random, k, i1, l);
        WorldGenVines worldgenvines = new WorldGenVines();

        for (l = 0; l < 50; ++l) {
            i1 = i + random.nextInt(16) + 8;
            short short1 = 128;
            int j1 = j + random.nextInt(16) + 8;

            worldgenvines.a(world, random, i1, short1, j1);
        }
    }
}
