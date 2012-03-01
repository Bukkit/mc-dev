package net.minecraft.server;

public class ItemShears extends Item {

    public ItemShears(int i) {
        super(i);
        this.e(1);
        this.setMaxDurability(238);
    }

    public boolean a(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
        if (i != Block.LEAVES.id && i != Block.WEB.id && i != Block.LONG_GRASS.id && i != Block.VINE.id) {
            return super.a(itemstack, i, j, k, l, entityliving);
        } else {
            itemstack.damage(1, entityliving);
            return true;
        }
    }

    public boolean canDestroySpecialBlock(Block block) {
        return block.id == Block.WEB.id;
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        return block.id != Block.WEB.id && block.id != Block.LEAVES.id ? (block.id == Block.WOOL.id ? 5.0F : super.getDestroySpeed(itemstack, block)) : 15.0F;
    }
}
