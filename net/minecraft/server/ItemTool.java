package net.minecraft.server;

public class ItemTool extends Item {

    private Block[] aX;
    private float aY = 4.0F;
    private int aZ;
    protected int a;

    public ItemTool(int i, int j, int k, Block[] ablock) {
        super(i);
        this.a = k;
        this.aX = ablock;
        this.aT = 1;
        this.aU = 32 << k;
        if (k == 3) {
            this.aU *= 4;
        }

        this.aY = (float) ((k + 1) * 2);
        this.aZ = j + k;
    }

    public float a(ItemStack itemstack, Block block) {
        for (int i = 0; i < this.aX.length; ++i) {
            if (this.aX[i] == block) {
                return this.aY;
            }
        }

        return 1.0F;
    }

    public void a(ItemStack itemstack, int i, int j, int k, int l) {
        itemstack.a(1);
    }
}
