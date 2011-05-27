package net.minecraft.server;

import java.util.Comparator;

class RecipeSorter implements Comparator {

    final CraftingManager a;

    RecipeSorter(CraftingManager craftingmanager) {
        this.a = craftingmanager;
    }

    public int a(CraftingRecipe craftingrecipe, CraftingRecipe craftingrecipe1) {
        return craftingrecipe instanceof ShapelessRecipes && craftingrecipe1 instanceof ShapedRecipes ? 1 : (craftingrecipe1 instanceof ShapelessRecipes && craftingrecipe instanceof ShapedRecipes ? -1 : (craftingrecipe1.a() < craftingrecipe.a() ? -1 : (craftingrecipe1.a() > craftingrecipe.a() ? 1 : 0)));
    }
}
