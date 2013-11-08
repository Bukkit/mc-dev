package net.minecraft.server;

public class ItemShears extends Item {

    public ItemShears() {
        this.e(1);
        this.setMaxDurability(238);
        this.a(CreativeModeTab.i);
    }

    public boolean a(ItemStack itemstack, World world, Block block, int i, int j, int k, EntityLiving entityliving) {
        if (block.getMaterial() != Material.LEAVES && block != Blocks.WEB && block != Blocks.LONG_GRASS && block != Blocks.VINE && block != Blocks.TRIPWIRE) {
            return super.a(itemstack, world, block, i, j, k, entityliving);
        } else {
            itemstack.damage(1, entityliving);
            return true;
        }
    }

    public boolean canDestroySpecialBlock(Block block) {
        return block == Blocks.WEB || block == Blocks.REDSTONE_WIRE || block == Blocks.TRIPWIRE;
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        return block != Blocks.WEB && block.getMaterial() != Material.LEAVES ? (block == Blocks.WOOL ? 5.0F : super.getDestroySpeed(itemstack, block)) : 15.0F;
    }
}
