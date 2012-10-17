package net.minecraft.server;

import java.util.Random;

public class BiomeForest extends BiomeBase {

    public BiomeForest(int i) {
        super(i);
        this.K.add(new BiomeMeta(EntityWolf.class, 5, 4, 4));
        this.I.z = 10;
        this.I.B = 2;
    }

    public WorldGenerator a(Random random) {
        return (WorldGenerator) (random.nextInt(5) == 0 ? this.Q : (random.nextInt(10) == 0 ? this.P : this.O));
    }
}
