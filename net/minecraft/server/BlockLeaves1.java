package net.minecraft.server;

public class BlockLeaves1 extends BlockLeaves {

    public static final String[][] N = new String[][] { { "leaves_oak", "leaves_spruce", "leaves_birch", "leaves_jungle"}, { "leaves_oak_opaque", "leaves_spruce_opaque", "leaves_birch_opaque", "leaves_jungle_opaque"}};
    public static final String[] O = new String[] { "oak", "spruce", "birch", "jungle"};

    public BlockLeaves1() {}

    protected void c(World world, int i, int j, int k, int l, int i1) {
        if ((l & 3) == 0 && world.random.nextInt(i1) == 0) {
            this.a(world, i, j, k, new ItemStack(Items.APPLE, 1, 0));
        }
    }

    protected int b(int i) {
        int j = super.b(i);

        if ((i & 3) == 3) {
            j = 40;
        }

        return j;
    }

    public String[] e() {
        return O;
    }
}
