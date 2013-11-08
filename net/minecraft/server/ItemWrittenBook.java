package net.minecraft.server;

public class ItemWrittenBook extends Item {

    public ItemWrittenBook() {
        this.e(1);
    }

    public static boolean a(NBTTagCompound nbttagcompound) {
        if (!ItemBookAndQuill.a(nbttagcompound)) {
            return false;
        } else if (!nbttagcompound.hasKeyOfType("title", 8)) {
            return false;
        } else {
            String s = nbttagcompound.getString("title");

            return s != null && s.length() <= 16 ? nbttagcompound.hasKeyOfType("author", 8) : false;
        }
    }

    public String n(ItemStack itemstack) {
        if (itemstack.hasTag()) {
            NBTTagCompound nbttagcompound = itemstack.getTag();
            String s = nbttagcompound.getString("title");

            if (!UtilColor.b(s)) {
                return s;
            }
        }

        return super.n(itemstack);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        entityhuman.b(itemstack);
        return itemstack;
    }

    public boolean s() {
        return true;
    }
}
