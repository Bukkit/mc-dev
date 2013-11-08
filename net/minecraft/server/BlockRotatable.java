package net.minecraft.server;

public abstract class BlockRotatable extends Block {

    protected BlockRotatable(Material material) {
        super(material);
    }

    public int b() {
        return 31;
    }

    public int getPlacedData(World world, int i, int j, int k, int l, float f, float f1, float f2, int i1) {
        int j1 = i1 & 3;
        byte b0 = 0;

        switch (l) {
        case 0:
        case 1:
            b0 = 0;
            break;

        case 2:
        case 3:
            b0 = 8;
            break;

        case 4:
        case 5:
            b0 = 4;
        }

        return j1 | b0;
    }

    public int getDropData(int i) {
        return i & 3;
    }

    public int k(int i) {
        return i & 3;
    }

    protected ItemStack j(int i) {
        return new ItemStack(Item.getItemOf(this), 1, this.k(i));
    }
}
