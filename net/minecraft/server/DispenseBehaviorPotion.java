package net.minecraft.server;

final class DispenseBehaviorPotion implements IDispenseBehavior {

    private final DispenseBehaviorItem b = new DispenseBehaviorItem();

    DispenseBehaviorPotion() {}

    public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
        return ItemPotion.f(itemstack.getData()) ? (new DispenseBehaviorThrownPotion(this, itemstack)).a(isourceblock, itemstack) : this.b.a(isourceblock, itemstack);
    }
}
