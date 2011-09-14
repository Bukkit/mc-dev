package net.minecraft.server;

import java.util.Random;

public class BiomeForest extends BiomeBase {

    public BiomeForest(int i) {
        super(i);
        this.w.add(new BiomeMeta(EntityWolf.class, 5, 4, 4));
        this.u.r = 10;
        this.u.t = 2;
    }

    public WorldGenerator a(Random random) {
        return (WorldGenerator) (random.nextInt(5) == 0 ? this.B : (random.nextInt(10) == 0 ? this.A : this.z));
    }
}
