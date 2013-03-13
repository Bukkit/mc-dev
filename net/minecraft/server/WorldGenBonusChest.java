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
        int l;

        for (boolean flag = false; ((l = world.getTypeId(i, j, k)) == 0 || l == Block.LEAVES.id) && j > 1; --j) {
            ;
        }

        if (j < 1) {
            return false;
        } else {
            ++j;

            for (int i1 = 0; i1 < 4; ++i1) {
                int j1 = i + random.nextInt(4) - random.nextInt(4);
                int k1 = j + random.nextInt(3) - random.nextInt(3);
                int l1 = k + random.nextInt(4) - random.nextInt(4);

                if (world.isEmpty(j1, k1, l1) && world.w(j1, k1 - 1, l1)) {
                    world.setTypeIdAndData(j1, k1, l1, Block.CHEST.id, 0, 2);
                    TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(j1, k1, l1);

                    if (tileentitychest != null && tileentitychest != null) {
                        StructurePieceTreasure.a(random, this.a, (IInventory) tileentitychest, this.b);
                    }

                    if (world.isEmpty(j1 - 1, k1, l1) && world.w(j1 - 1, k1 - 1, l1)) {
                        world.setTypeIdAndData(j1 - 1, k1, l1, Block.TORCH.id, 0, 2);
                    }

                    if (world.isEmpty(j1 + 1, k1, l1) && world.w(j1 - 1, k1 - 1, l1)) {
                        world.setTypeIdAndData(j1 + 1, k1, l1, Block.TORCH.id, 0, 2);
                    }

                    if (world.isEmpty(j1, k1, l1 - 1) && world.w(j1 - 1, k1 - 1, l1)) {
                        world.setTypeIdAndData(j1, k1, l1 - 1, Block.TORCH.id, 0, 2);
                    }

                    if (world.isEmpty(j1, k1, l1 + 1) && world.w(j1 - 1, k1 - 1, l1)) {
                        world.setTypeIdAndData(j1, k1, l1 + 1, Block.TORCH.id, 0, 2);
                    }

                    return true;
                }
            }

            return false;
        }
    }
}
