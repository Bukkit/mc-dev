package net.minecraft.server;

import java.util.Set;

import net.minecraft.util.com.google.common.collect.Sets;

public class ItemPickaxe extends ItemTool {

    private static final Set c = Sets.newHashSet(new Block[] { Blocks.COBBLESTONE, Blocks.DOUBLE_STEP, Blocks.STEP, Blocks.STONE, Blocks.SANDSTONE, Blocks.MOSSY_COBBLESTONE, Blocks.IRON_ORE, Blocks.IRON_BLOCK, Blocks.COAL_ORE, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.DIAMOND_ORE, Blocks.DIAMOND_BLOCK, Blocks.ICE, Blocks.NETHERRACK, Blocks.LAPIS_ORE, Blocks.LAPIS_BLOCK, Blocks.REDSTONE_ORE, Blocks.GLOWING_REDSTONE_ORE, Blocks.RAILS, Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.ACTIVATOR_RAIL});

    protected ItemPickaxe(EnumToolMaterial enumtoolmaterial) {
        super(2.0F, enumtoolmaterial, c);
    }

    public boolean canDestroySpecialBlock(Block block) {
        return block == Blocks.OBSIDIAN ? this.b.d() == 3 : (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE ? (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK ? (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE ? (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE ? (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE ? (block != Blocks.REDSTONE_ORE && block != Blocks.GLOWING_REDSTONE_ORE ? (block.getMaterial() == Material.STONE ? true : (block.getMaterial() == Material.ORE ? true : block.getMaterial() == Material.HEAVY)) : this.b.d() >= 2) : this.b.d() >= 1) : this.b.d() >= 1) : this.b.d() >= 2) : this.b.d() >= 2) : this.b.d() >= 2);
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        return block.getMaterial() != Material.ORE && block.getMaterial() != Material.HEAVY && block.getMaterial() != Material.STONE ? super.getDestroySpeed(itemstack, block) : this.a;
    }
}
