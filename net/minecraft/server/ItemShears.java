package net.minecraft.server;

public class ItemShears extends Item {

    public ItemShears(int i) {
        super(i);
        this.c(1);
        this.d(238);
    }

    public boolean a(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
        if (i == Block.LEAVES.id || i == Block.WEB.id) {
            itemstack.damage(1, entityliving);
        }

        return super.a(itemstack, i, j, k, l, entityliving);
    }

    public boolean a(Block block) {
        return block.id == Block.WEB.id;
    }

    public float a(ItemStack itemstack, Block block) {
        return block.id != Block.WEB.id && block.id != Block.LEAVES.id ? (block.id == Block.WOOL.id ? 5.0F : super.a(itemstack, block)) : 15.0F;
    }
}
