package net.minecraft.server;

public class ItemRecord extends Item {

    private String a;

    protected ItemRecord(int i, String s) {
        super(i);
        this.a = s;
        this.aX = 1;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (world.a(i, j, k) == Block.JUKEBOX.bi && world.b(i, j, k) == 0) {
            world.b(i, j, k, this.aW - Item.GOLD_RECORD.aW + 1);
            world.a(this.a, i, j, k);
            --itemstack.a;
            return true;
        } else {
            return false;
        }
    }
}
