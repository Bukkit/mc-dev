package net.minecraft.server;

public class DispenseBehaviorEmptyBucket extends DispenseBehaviorItem {

    private final DispenseBehaviorItem c;

    final MinecraftServer b;

    public DispenseBehaviorEmptyBucket(MinecraftServer minecraftserver) {
        this.b = minecraftserver;
        this.c = new DispenseBehaviorItem();
    }

    public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        EnumFacing enumfacing = EnumFacing.a(isourceblock.h());
        World world = isourceblock.k();
        int i = isourceblock.getBlockX() + enumfacing.c();
        int j = isourceblock.getBlockY();
        int k = isourceblock.getBlockZ() + enumfacing.e();
        Material material = world.getMaterial(i, j, k);
        int l = world.getData(i, j, k);
        Item item;

        if (Material.WATER.equals(material) && l == 0) {
            item = Item.WATER_BUCKET;
        } else {
            if (!Material.LAVA.equals(material) || l != 0) {
                return super.b(isourceblock, itemstack);
            }

            item = Item.LAVA_BUCKET;
        }

        world.setTypeId(i, j, k, 0);
        if (--itemstack.count == 0) {
            itemstack.id = item.id;
            itemstack.count = 1;
        } else if (((TileEntityDispenser) isourceblock.getTileEntity()).addItem(new ItemStack(item)) < 0) {
            this.c.a(isourceblock, new ItemStack(item));
        }

        return itemstack;
    }
}
