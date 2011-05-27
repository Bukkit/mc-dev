package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;

public class FurnaceRecipes {

    private static final FurnaceRecipes a = new FurnaceRecipes();
    private Map b = new HashMap();

    public static final FurnaceRecipes a() {
        return a;
    }

    private FurnaceRecipes() {
        this.a(Block.IRON_ORE.id, new ItemStack(Item.IRON_INGOT));
        this.a(Block.GOLD_ORE.id, new ItemStack(Item.GOLD_INGOT));
        this.a(Block.DIAMOND_ORE.id, new ItemStack(Item.DIAMOND));
        this.a(Block.SAND.id, new ItemStack(Block.GLASS));
        this.a(Item.PORK.id, new ItemStack(Item.GRILLED_PORK));
        this.a(Item.RAW_FISH.id, new ItemStack(Item.COOKED_FISH));
        this.a(Block.COBBLESTONE.id, new ItemStack(Block.STONE));
        this.a(Item.CLAY_BALL.id, new ItemStack(Item.CLAY_BRICK));
        this.a(Block.CACTUS.id, new ItemStack(Item.INK_SACK, 1, 2));
        this.a(Block.LOG.id, new ItemStack(Item.COAL, 1, 1));
    }

    public void a(int i, ItemStack itemstack) {
        this.b.put(Integer.valueOf(i), itemstack);
    }

    public ItemStack a(int i) {
        return (ItemStack) this.b.get(Integer.valueOf(i));
    }

    public Map b() {
        return this.b;
    }
}
