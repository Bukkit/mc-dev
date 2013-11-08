package net.minecraft.server;

import java.util.Random;

public class BlockLongGrass extends BlockPlant implements IBlockFragilePlantElement {

    private static final String[] a = new String[] { "deadbush", "tallgrass", "fern"};

    protected BlockLongGrass() {
        super(Material.REPLACEABLE_PLANT);
        float f = 0.4F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    public boolean j(World world, int i, int j, int k) {
        return this.a(world.getType(i, j - 1, k));
    }

    public Item getDropType(int i, Random random, int j) {
        return random.nextInt(8) == 0 ? Items.SEEDS : null;
    }

    public int getDropCount(int i, Random random) {
        return 1 + random.nextInt(i * 2 + 1);
    }

    public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l) {
        if (!world.isStatic && entityhuman.bD() != null && entityhuman.bD().getItem() == Items.SHEARS) {
            entityhuman.a(StatisticList.C[Block.b((Block) this)], 1);
            this.a(world, i, j, k, new ItemStack(Blocks.LONG_GRASS, 1, l));
        } else {
            super.a(world, entityhuman, i, j, k, l);
        }
    }

    public int getDropData(World world, int i, int j, int k) {
        return world.getData(i, j, k);
    }

    public boolean a(World world, int i, int j, int k, boolean flag) {
        int l = world.getData(i, j, k);

        return l != 0;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        return true;
    }

    public void b(World world, Random random, int i, int j, int k) {
        int l = world.getData(i, j, k);
        byte b0 = 2;

        if (l == 2) {
            b0 = 3;
        }

        if (Blocks.DOUBLE_PLANT.canPlace(world, i, j, k)) {
            Blocks.DOUBLE_PLANT.c(world, i, j, k, b0, 2);
        }
    }
}
