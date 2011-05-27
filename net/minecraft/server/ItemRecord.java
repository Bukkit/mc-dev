package net.minecraft.server;

public class ItemRecord extends Item {

    private String a;

    protected ItemRecord(int i, String s) {
        super(i);
        this.a = s;
        this.maxStackSize = 1;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (world.getTypeId(i, j, k) == Block.JUKEBOX.id && world.getData(i, j, k) == 0) {
            world.c(i, j, k, this.id - Item.GOLD_RECORD.id + 1);
            world.a(this.a, i, j, k);
            --itemstack.count;
            return true;
        } else {
            return false;
        }
    }
}
