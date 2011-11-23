package net.minecraft.server;

import java.util.Random;

public class BiomeForest extends BiomeBase {

    public BiomeForest(int i) {
        super(i);
        this.D.add(new BiomeMeta(EntityWolf.class, 5, 4, 4));
        this.B.z = 10;
        this.B.B = 2;
    }

    public WorldGenerator a(Random random) {
        return (WorldGenerator) (random.nextInt(5) == 0 ? this.I : (random.nextInt(10) == 0 ? this.H : this.G));
    }
}
