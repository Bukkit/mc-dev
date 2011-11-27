package net.minecraft.server;

import java.util.Random;

class WorldGenStrongholdStones extends StructurePieceBlockSelector {

    private WorldGenStrongholdStones() {}

    public void a(Random random, int i, int j, int k, boolean flag) {
        if (!flag) {
            this.a = 0;
            this.b = 0;
        } else {
            this.a = Block.SMOOTH_BRICK.id;
            float f = random.nextFloat();

            if (f < 0.2F) {
                this.b = 2;
            } else if (f < 0.5F) {
                this.b = 1;
            } else if (f < 0.55F) {
                this.a = Block.MONSTER_EGGS.id;
                this.b = 2;
            } else {
                this.b = 0;
            }
        }
    }

    WorldGenStrongholdStones(WorldGenStrongholdUnknown worldgenstrongholdunknown) {
        this();
    }
}
