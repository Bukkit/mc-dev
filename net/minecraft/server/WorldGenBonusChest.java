package net.minecraft.server;

import java.util.Random;

public class WorldGenBonusChest extends WorldGenerator {

    private final StructurePieceTreasure[] a;
    private final int b;

    public WorldGenBonusChest(StructurePieceTreasure[] astructurepiecetreasure, int i) {
        this.a = astructurepiecetreasure;
        this.b = i;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        Block block;

        while (((block = world.getType(i, j, k)).getMaterial() == Material.AIR || block.getMaterial() == Material.LEAVES) && j > 1) {
            --j;
        }

        if (j < 1) {
            return false;
        } else {
            ++j;

            for (int l = 0; l < 4; ++l) {
                int i1 = i + random.nextInt(4) - random.nextInt(4);
                int j1 = j + random.nextInt(3) - random.nextInt(3);
                int k1 = k + random.nextInt(4) - random.nextInt(4);

                if (world.isEmpty(i1, j1, k1) && World.a((IBlockAccess) world, i1, j1 - 1, k1)) {
                    world.setTypeAndData(i1, j1, k1, Blocks.CHEST, 0, 2);
                    TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(i1, j1, k1);

                    if (tileentitychest != null && tileentitychest != null) {
                        StructurePieceTreasure.a(random, this.a, (IInventory) tileentitychest, this.b);
                    }

                    if (world.isEmpty(i1 - 1, j1, k1) && World.a((IBlockAccess) world, i1 - 1, j1 - 1, k1)) {
                        world.setTypeAndData(i1 - 1, j1, k1, Blocks.TORCH, 0, 2);
                    }

                    if (world.isEmpty(i1 + 1, j1, k1) && World.a((IBlockAccess) world, i1 - 1, j1 - 1, k1)) {
                        world.setTypeAndData(i1 + 1, j1, k1, Blocks.TORCH, 0, 2);
                    }

                    if (world.isEmpty(i1, j1, k1 - 1) && World.a((IBlockAccess) world, i1 - 1, j1 - 1, k1)) {
                        world.setTypeAndData(i1, j1, k1 - 1, Blocks.TORCH, 0, 2);
                    }

                    if (world.isEmpty(i1, j1, k1 + 1) && World.a((IBlockAccess) world, i1 - 1, j1 - 1, k1)) {
                        world.setTypeAndData(i1, j1, k1 + 1, Blocks.TORCH, 0, 2);
                    }

                    return true;
                }
            }

            return false;
        }
    }
}
