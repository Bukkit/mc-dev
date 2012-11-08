package net.minecraft.server;

public interface IMerchant {

    void b_(EntityHuman entityhuman);

    EntityHuman m_();

    MerchantRecipeList getOffers(EntityHuman entityhuman);

    void a(MerchantRecipe merchantrecipe);
}
