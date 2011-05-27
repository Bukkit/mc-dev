package net.minecraft.server;

public class ItemCookie extends ItemFood {

    public ItemCookie(int i, int j, boolean flag, int k) {
        super(i, j, flag);
        this.maxStackSize = k;
    }
}
