package net.minecraft.server;

import java.util.Random;

public class BiomeTaiga extends BiomeBase {

    public BiomeTaiga(int i) {
        super(i);
        this.K.add(new BiomeMeta(EntityWolf.class, 8, 4, 4));
        this.I.z = 10;
        this.I.B = 1;
    }

    public WorldGenerator a(Random random) {
        return (WorldGenerator) (random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false));
    }
}
