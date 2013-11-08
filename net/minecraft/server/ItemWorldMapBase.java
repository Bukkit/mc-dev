package net.minecraft.server;

public class ItemWorldMapBase extends Item {

    protected ItemWorldMapBase() {}

    public boolean h() {
        return true;
    }

    public Packet c(ItemStack itemstack, World world, EntityHuman entityhuman) {
        return null;
    }
}
