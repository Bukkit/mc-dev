package net.minecraft.server;

public class ItemTool extends Item {

    private Block[] bb;
    private float bc = 4.0F;
    private int bd;
    protected int a;

    public ItemTool(int i, int j, int k, Block[] ablock) {
        super(i);
        this.a = k;
        this.bb = ablock;
        this.aX = 1;
        this.aY = 32 << k;
        if (k == 3) {
            this.aY *= 4;
        }

        this.bc = (float) ((k + 1) * 2);
        this.bd = j + k;
    }

    public float a(ItemStack itemstack, Block block) {
        for (int i = 0; i < this.bb.length; ++i) {
            if (this.bb[i] == block) {
                return this.bc;
            }
        }

        return 1.0F;
    }

    public void a(ItemStack itemstack, int i, int j, int k, int l) {
        itemstack.a(1);
    }
}
