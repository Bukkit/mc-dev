package net.minecraft.server;

import java.util.Random;

public class BiomeForest extends BiomeBase {

    public BiomeForest(int i) {
        super(i);
        this.I.add(new BiomeMeta(EntityWolf.class, 5, 4, 4));
        this.G.z = 10;
        this.G.B = 2;
    }

    public WorldGenerator a(Random random) {
        return (WorldGenerator) (random.nextInt(5) == 0 ? this.N : (random.nextInt(10) == 0 ? this.M : this.L));
    }
}
