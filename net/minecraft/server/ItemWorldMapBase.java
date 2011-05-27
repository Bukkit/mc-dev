package net.minecraft.server;

public class ItemWorldMapBase extends Item {

    protected ItemWorldMapBase(int i) {
        super(i);
    }

    public boolean b() {
        return true;
    }

    public Packet b(ItemStack itemstack, World world, EntityHuman entityhuman) {
        return null;
    }
}
