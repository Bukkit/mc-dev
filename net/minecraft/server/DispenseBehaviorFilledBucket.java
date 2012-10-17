package net.minecraft.server;

public class DispenseBehaviorFilledBucket extends DispenseBehaviorItem {

    private final DispenseBehaviorItem c;

    final MinecraftServer b;

    public DispenseBehaviorFilledBucket(MinecraftServer minecraftserver) {
        this.b = minecraftserver;
        this.c = new DispenseBehaviorItem();
    }

    public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        ItemBucket itembucket = (ItemBucket) itemstack.getItem();
        int i = isourceblock.getBlockX();
        int j = isourceblock.getBlockY();
        int k = isourceblock.getBlockZ();
        EnumFacing enumfacing = EnumFacing.a(isourceblock.h());

        if (itembucket.a(isourceblock.k(), (double) i, (double) j, (double) k, i + enumfacing.c(), j, k + enumfacing.e())) {
            itemstack.id = Item.BUCKET.id;
            itemstack.count = 1;
            return itemstack;
        } else {
            return this.c.a(isourceblock, itemstack);
        }
    }
}
