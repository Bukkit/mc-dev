package net.minecraft.server;

import java.util.Set;

import net.minecraft.util.com.google.common.collect.Sets;

public class ItemSpade extends ItemTool {

    private static final Set c = Sets.newHashSet(new Block[] { Blocks.GRASS, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL, Blocks.SNOW, Blocks.SNOW_BLOCK, Blocks.CLAY, Blocks.SOIL, Blocks.SOUL_SAND, Blocks.MYCEL});

    public ItemSpade(EnumToolMaterial enumtoolmaterial) {
        super(1.0F, enumtoolmaterial, c);
    }

    public boolean canDestroySpecialBlock(Block block) {
        return block == Blocks.SNOW ? true : block == Blocks.SNOW_BLOCK;
    }
}
