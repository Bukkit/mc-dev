package net.minecraft.server;

public interface IMerchant {

    void a_(EntityHuman entityhuman);

    EntityHuman b();

    MerchantRecipeList getOffers(EntityHuman entityhuman);

    void a(MerchantRecipe merchantrecipe);

    void a_(ItemStack itemstack);
}
