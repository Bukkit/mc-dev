package net.minecraft.server;

public class ItemBookAndQuill extends Item {

    public ItemBookAndQuill() {
        this.e(1);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        entityhuman.b(itemstack);
        return itemstack;
    }

    public boolean s() {
        return true;
    }

    public static boolean a(NBTTagCompound nbttagcompound) {
        if (nbttagcompound == null) {
            return false;
        } else if (!nbttagcompound.hasKeyOfType("pages", 9)) {
            return false;
        } else {
            NBTTagList nbttaglist = nbttagcompound.getList("pages", 8);

            for (int i = 0; i < nbttaglist.size(); ++i) {
                String s = nbttaglist.f(i);

                if (s == null) {
                    return false;
                }

                if (s.length() > 256) {
                    return false;
                }
            }

            return true;
        }
    }
}
