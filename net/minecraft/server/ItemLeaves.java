package net.minecraft.server;

public class ItemLeaves extends ItemBlock {

    private final BlockLeaves b;

    public ItemLeaves(BlockLeaves blockleaves) {
        super(blockleaves);
        this.b = blockleaves;
        this.setMaxDurability(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i | 4;
    }

    public String a(ItemStack itemstack) {
        int i = itemstack.getData();

        if (i < 0 || i >= this.b.e().length) {
            i = 0;
        }

        return super.getName() + "." + this.b.e()[i];
    }
}
