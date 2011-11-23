package net.minecraft.server;

import java.util.Random;

public class BiomeTaiga extends BiomeBase {

    public BiomeTaiga(int i) {
        super(i);
        this.D.add(new BiomeMeta(EntityWolf.class, 8, 4, 4));
        this.B.z = 10;
        this.B.B = 1;
    }

    public WorldGenerator a(Random random) {
        return (WorldGenerator) (random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false));
    }
}
