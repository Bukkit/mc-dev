package net.minecraft.server;

public interface IMerchant {

    void a(EntityHuman entityhuman);

    EntityHuman m_();

    MerchantRecipeList getOffers(EntityHuman entityhuman);

    void a(MerchantRecipe merchantrecipe);
}
