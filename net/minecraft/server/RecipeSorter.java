package net.minecraft.server;

import java.util.Comparator;

class RecipeSorter implements Comparator {

    final CraftingManager a;

    RecipeSorter(CraftingManager craftingmanager) {
        this.a = craftingmanager;
    }

    public int a(IRecipe irecipe, IRecipe irecipe1) {
        return irecipe instanceof ShapelessRecipes && irecipe1 instanceof ShapedRecipes ? 1 : (irecipe1 instanceof ShapelessRecipes && irecipe instanceof ShapedRecipes ? -1 : (irecipe1.a() < irecipe.a() ? -1 : (irecipe1.a() > irecipe.a() ? 1 : 0)));
    }

    public int compare(Object object, Object object1) {
        return this.a((IRecipe) object, (IRecipe) object1);
    }
}
