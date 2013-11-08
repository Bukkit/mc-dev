package net.minecraft.server;

import java.util.Random;

class WorldGenStrongholdStones extends StructurePieceBlockSelector {

    private WorldGenStrongholdStones() {}

    public void a(Random random, int i, int j, int k, boolean flag) {
        if (flag) {
            this.a = Blocks.SMOOTH_BRICK;
            float f = random.nextFloat();

            if (f < 0.2F) {
                this.b = 2;
            } else if (f < 0.5F) {
                this.b = 1;
            } else if (f < 0.55F) {
                this.a = Blocks.MONSTER_EGGS;
                this.b = 2;
            } else {
                this.b = 0;
            }
        } else {
            this.a = Blocks.AIR;
            this.b = 0;
        }
    }

    WorldGenStrongholdStones(WorldGenStrongholdUnknown worldgenstrongholdunknown) {
        this();
    }
}
