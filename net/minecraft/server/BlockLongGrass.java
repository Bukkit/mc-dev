package net.minecraft.server;

import java.util.Random;

public class BlockLongGrass extends BlockFlower {

    private static final String[] a = new String[] { "deadbush", "tallgrass", "fern"};

    protected BlockLongGrass(int i) {
        super(i, Material.REPLACEABLE_PLANT);
        float f = 0.4F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    public int getDropType(int i, Random random, int j) {
        return random.nextInt(8) == 0 ? Item.SEEDS.id : -1;
    }

    public int getDropCount(int i, Random random) {
        return 1 + random.nextInt(i * 2 + 1);
    }

    public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l) {
        if (!world.isStatic && entityhuman.cd() != null && entityhuman.cd().id == Item.SHEARS.id) {
            entityhuman.a(StatisticList.C[this.id], 1);
            this.b(world, i, j, k, new ItemStack(Block.LONG_GRASS, 1, l));
        } else {
            super.a(world, entityhuman, i, j, k, l);
        }
    }

    public int getDropData(World world, int i, int j, int k) {
        return world.getData(i, j, k);
    }
}
