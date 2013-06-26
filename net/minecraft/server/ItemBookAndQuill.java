package net.minecraft.server;

public class ItemBookAndQuill extends Item {

    public ItemBookAndQuill(int i) {
        super(i);
        this.d(1);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        entityhuman.c(itemstack);
        return itemstack;
    }

    public boolean s() {
        return true;
    }

    public static boolean a(NBTTagCompound nbttagcompound) {
        if (nbttagcompound == null) {
            return false;
        } else if (!nbttagcompound.hasKey("pages")) {
            return false;
        } else {
            NBTTagList nbttaglist = (NBTTagList) nbttagcompound.get("pages");

            for (int i = 0; i < nbttaglist.size(); ++i) {
                NBTTagString nbttagstring = (NBTTagString) nbttaglist.get(i);

                if (nbttagstring.data == null) {
                    return false;
                }

                if (nbttagstring.data.length() > 256) {
                    return false;
                }
            }

            return true;
        }
    }
}
