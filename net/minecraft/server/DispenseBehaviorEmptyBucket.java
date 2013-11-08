package net.minecraft.server;

final class DispenseBehaviorEmptyBucket extends DispenseBehaviorItem {

    private final DispenseBehaviorItem b = new DispenseBehaviorItem();

    DispenseBehaviorEmptyBucket() {}

    public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.b(isourceblock.h());
        World world = isourceblock.k();
        int i = isourceblock.getBlockX() + enumfacing.c();
        int j = isourceblock.getBlockY() + enumfacing.d();
        int k = isourceblock.getBlockZ() + enumfacing.e();
        Material material = world.getType(i, j, k).getMaterial();
        int l = world.getData(i, j, k);
        Item item;

        if (Material.WATER.equals(material) && l == 0) {
            item = Items.WATER_BUCKET;
        } else {
            if (!Material.LAVA.equals(material) || l != 0) {
                return super.b(isourceblock, itemstack);
            }

            item = Items.LAVA_BUCKET;
        }

        world.setAir(i, j, k);
        if (--itemstack.count == 0) {
            itemstack.setItem(item);
            itemstack.count = 1;
        } else if (((TileEntityDispenser) isourceblock.getTileEntity()).addItem(new ItemStack(item)) < 0) {
            this.b.a(isourceblock, new ItemStack(item));
        }

        return itemstack;
    }
}
