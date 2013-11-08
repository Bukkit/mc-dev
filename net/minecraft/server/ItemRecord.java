package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;

public class ItemRecord extends Item {

    private static final Map b = new HashMap();
    public final String a;

    protected ItemRecord(String s) {
        this.a = s;
        this.maxStackSize = 1;
        this.a(CreativeModeTab.f);
        b.put(s, this);
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        if (world.getType(i, j, k) == Blocks.JUKEBOX && world.getData(i, j, k) == 0) {
            if (world.isStatic) {
                return true;
            } else {
                ((BlockJukeBox) Blocks.JUKEBOX).b(world, i, j, k, itemstack);
                world.a((EntityHuman) null, 1005, i, j, k, Item.b(this));
                --itemstack.count;
                return true;
            }
        } else {
            return false;
        }
    }

    public EnumItemRarity f(ItemStack itemstack) {
        return EnumItemRarity.RARE;
    }
}
