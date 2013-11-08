package net.minecraft.server;

import java.util.Random;

public class BiomeDesert extends BiomeBase {

    public BiomeDesert(int i) {
        super(i);
        this.at.clear();
        this.ai = Blocks.SAND;
        this.ak = Blocks.SAND;
        this.ar.x = -999;
        this.ar.A = 2;
        this.ar.C = 50;
        this.ar.D = 10;
        this.at.clear();
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
