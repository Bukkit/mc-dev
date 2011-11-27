package net.minecraft.server;

import java.util.Random;

public class BlockStep extends Block {

    public static final String[] a = new String[] { "stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick"};
    private boolean b;

    public BlockStep(int i, boolean flag) {
        super(i, 6, Material.STONE);
        this.b = flag;
        if (!flag) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        } else {
            o[i] = true;
        }

        this.g(255);
    }

    public int a(int i, int j) {
        return j == 0 ? (i <= 1 ? 6 : 5) : (j == 1 ? (i == 0 ? 208 : (i == 1 ? 176 : 192)) : (j == 2 ? 4 : (j == 3 ? 16 : (j == 4 ? Block.BRICK.textureId : (j == 5 ? Block.SMOOTH_BRICK.textureId : 6)))));
    }

    public int a(int i) {
        return this.a(i, 0);
    }

    public boolean a() {
        return this.b;
    }

    public void onPlace(World world, int i, int j, int k) {}

    public int getDropType(int i, Random random, int j) {
        return Block.STEP.id;
    }

    public int a(Random random) {
        return this.b ? 2 : 1;
    }

    protected int getDropData(int i) {
        return i;
    }

    public boolean b() {
        return this.b;
    }

    protected ItemStack a_(int i) {
        return new ItemStack(Block.STEP.id, 1, i);
    }
}
