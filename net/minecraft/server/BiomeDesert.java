package net.minecraft.server;

import java.util.Random;

public class BiomeDesert extends BiomeBase {

    public BiomeDesert(int i) {
        super(i);
        this.K.clear();
        this.A = (byte) Block.SAND.id;
        this.B = (byte) Block.SAND.id;
        this.I.z = -999;
        this.I.C = 2;
        this.I.E = 50;
        this.I.F = 10;
    }

    public void a(World world, Random random, int i, int j) {
        super.a(world, random, i, j);
        if (random.nextInt(1000) == 0) {
            int k = i + random.nextInt(16) + 8;
            int l = j + random.nextInt(16) + 8;
            WorldGenDesertWell worldgendesertwell = new WorldGenDesertWell();

            worldgendesertwell.a(world, random, k, world.getHighestBlockYAt(k, l) + 1, l);
        }
    }
}
