package net.minecraft.server;

final class DispenseBehaviorNoop implements IDispenseBehavior {

    DispenseBehaviorNoop() {}

    public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
        return itemstack;
    }
}
