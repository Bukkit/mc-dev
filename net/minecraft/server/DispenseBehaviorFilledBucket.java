package net.minecraft.server;

final class DispenseBehaviorFilledBucket extends DispenseBehaviorItem {

    private final DispenseBehaviorItem b = new DispenseBehaviorItem();

    DispenseBehaviorFilledBucket() {}

    public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        ItemBucket itembucket = (ItemBucket) itemstack.getItem();
        int i = isourceblock.getBlockX();
        int j = isourceblock.getBlockY();
        int k = isourceblock.getBlockZ();
        EnumFacing enumfacing = BlockDispenser.b(isourceblock.h());

        if (itembucket.a(isourceblock.k(), i + enumfacing.c(), j + enumfacing.d(), k + enumfacing.e())) {
            itemstack.setItem(Items.BUCKET);
            itemstack.count = 1;
            return itemstack;
        } else {
            return this.b.a(isourceblock, itemstack);
        }
    }
}
