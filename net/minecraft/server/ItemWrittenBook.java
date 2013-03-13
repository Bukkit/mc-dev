package net.minecraft.server;

public class ItemWrittenBook extends Item {

    public ItemWrittenBook(int i) {
        super(i);
        this.d(1);
    }

    public static boolean a(NBTTagCompound nbttagcompound) {
        if (!ItemBookAndQuill.a(nbttagcompound)) {
            return false;
        } else if (!nbttagcompound.hasKey("title")) {
            return false;
        } else {
            String s = nbttagcompound.getString("title");

            return s != null && s.length() <= 16 ? nbttagcompound.hasKey("author") : false;
        }
    }

    public String l(ItemStack itemstack) {
        if (itemstack.hasTag()) {
            NBTTagCompound nbttagcompound = itemstack.getTag();
            NBTTagString nbttagstring = (NBTTagString) nbttagcompound.get("title");

            if (nbttagstring != null) {
                return nbttagstring.toString();
            }
        }

        return super.l(itemstack);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        entityhuman.d(itemstack);
        return itemstack;
    }

    public boolean r() {
        return true;
    }
}
