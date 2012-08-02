package net.minecraft.server;

import java.util.Random;

class WorldGenJungleTemplePiece extends StructurePieceBlockSelector {

    private WorldGenJungleTemplePiece() {}

    public void a(Random random, int i, int j, int k, boolean flag) {
        if (random.nextFloat() < 0.4F) {
            this.a = Block.COBBLESTONE.id;
        } else {
            this.a = Block.MOSSY_COBBLESTONE.id;
        }
    }

    WorldGenJungleTemplePiece(WorldGenJungleTempleUnknown worldgenjungletempleunknown) {
        this();
    }
}
