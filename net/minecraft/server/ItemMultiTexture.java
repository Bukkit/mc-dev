package net.minecraft.server;

public class ItemMultiTexture extends ItemBlock {

    protected final Block b;
    protected final String[] c;

    public ItemMultiTexture(Block block, Block block1, String[] astring) {
        super(block);
        this.b = block1;
        this.c = astring;
        this.setMaxDurability(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }

    public String a(ItemStack itemstack) {
        int i = itemstack.getData();

        if (i < 0 || i >= this.c.length) {
            i = 0;
        }

        return super.getName() + "." + this.c[i];
    }
}
