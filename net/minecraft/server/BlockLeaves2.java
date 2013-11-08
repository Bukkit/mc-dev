package net.minecraft.server;

public class BlockLeaves2 extends BlockLeaves {

    public static final String[][] N = new String[][] { { "leaves_acacia", "leaves_big_oak"}, { "leaves_acacia_opaque", "leaves_big_oak_opaque"}};
    public static final String[] O = new String[] { "acacia", "big_oak"};

    public BlockLeaves2() {}

    protected void c(World world, int i, int j, int k, int l, int i1) {
        if ((l & 3) == 1 && world.random.nextInt(i1) == 0) {
            this.a(world, i, j, k, new ItemStack(Items.APPLE, 1, 0));
        }
    }

    public int getDropData(int i) {
        return super.getDropData(i) + 4;
    }

    public int getDropData(World world, int i, int j, int k) {
        return world.getData(i, j, k) & 3;
    }

    public String[] e() {
        return O;
    }
}
