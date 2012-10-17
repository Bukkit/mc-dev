package net.minecraft.server;

public class ItemShears extends Item {

    public ItemShears(int i) {
        super(i);
        this.d(1);
        this.setMaxDurability(238);
        this.a(CreativeModeTab.i);
    }

    public boolean a(ItemStack itemstack, World world, int i, int j, int k, int l, EntityLiving entityliving) {
        if (i != Block.LEAVES.id && i != Block.WEB.id && i != Block.LONG_GRASS.id && i != Block.VINE.id && i != Block.TRIPWIRE.id) {
            return super.a(itemstack, world, i, j, k, l, entityliving);
        } else {
            itemstack.damage(1, entityliving);
            return true;
        }
    }

    public boolean canDestroySpecialBlock(Block block) {
        return block.id == Block.WEB.id || block.id == Block.REDSTONE_WIRE.id || block.id == Block.TRIPWIRE.id;
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        return block.id != Block.WEB.id && block.id != Block.LEAVES.id ? (block.id == Block.WOOL.id ? 5.0F : super.getDestroySpeed(itemstack, block)) : 15.0F;
    }
}
