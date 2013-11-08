package net.minecraft.server;

import java.util.Set;

import net.minecraft.util.com.google.common.collect.Sets;

public class ItemAxe extends ItemTool {

    private static final Set c = Sets.newHashSet(new Block[] { Blocks.WOOD, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.JACK_O_LANTERN});

    protected ItemAxe(EnumToolMaterial enumtoolmaterial) {
        super(3.0F, enumtoolmaterial, c);
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        return block.getMaterial() != Material.WOOD && block.getMaterial() != Material.PLANT && block.getMaterial() != Material.REPLACEABLE_PLANT ? super.getDestroySpeed(itemstack, block) : this.a;
    }
}
