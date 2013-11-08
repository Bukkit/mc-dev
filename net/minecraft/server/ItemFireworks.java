package net.minecraft.server;

public class ItemFireworks extends Item {

    public ItemFireworks() {}

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        if (!world.isStatic) {
            EntityFireworks entityfireworks = new EntityFireworks(world, (double) ((float) i + f), (double) ((float) j + f1), (double) ((float) k + f2), itemstack);

            world.addEntity(entityfireworks);
            if (!entityhuman.abilities.canInstantlyBuild) {
                --itemstack.count;
            }

            return true;
        } else {
            return false;
        }
    }
}
