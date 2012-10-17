package net.minecraft.server;

public class DispenseBehaviorPotion implements IDispenseBehavior {

    private final DispenseBehaviorItem c;

    final MinecraftServer b;

    public DispenseBehaviorPotion(MinecraftServer minecraftserver) {
        this.b = minecraftserver;
        this.c = new DispenseBehaviorItem();
    }

    public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
        return ItemPotion.g(itemstack.getData()) ? (new DispenseBehaviorThrownPotion(this, itemstack)).a(isourceblock, itemstack) : this.c.a(isourceblock, itemstack);
    }
}
