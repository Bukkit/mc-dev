package net.minecraft.server;

public class ItemMultiTexture extends ItemBlock {

    private final Block a;
    private final String[] b;

    public ItemMultiTexture(int i, Block block, String[] astring) {
        super(i);
        this.a = block;
        this.b = astring;
        this.setMaxDurability(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }

    public String d(ItemStack itemstack) {
        int i = itemstack.getData();

        if (i < 0 || i >= this.b.length) {
            i = 0;
        }

        return super.getName() + "." + this.b[i];
    }
}
