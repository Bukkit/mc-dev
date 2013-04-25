package net.minecraft.server;

import java.util.Random;

public class BlockDeadBush extends BlockFlower {

    protected BlockDeadBush(int i) {
        super(i, Material.REPLACEABLE_PLANT);
        float f = 0.4F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    protected boolean f_(int i) {
        return i == Block.SAND.id;
    }

    public int getDropType(int i, Random random, int j) {
        return -1;
    }

    public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l) {
        if (!world.isStatic && entityhuman.cd() != null && entityhuman.cd().id == Item.SHEARS.id) {
            entityhuman.a(StatisticList.C[this.id], 1);
            this.b(world, i, j, k, new ItemStack(Block.DEAD_BUSH, 1, l));
        } else {
            super.a(world, entityhuman, i, j, k, l);
        }
    }
}
