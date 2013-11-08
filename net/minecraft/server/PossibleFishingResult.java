package net.minecraft.server;

import java.util.Random;

public class PossibleFishingResult extends WeightedRandomChoice {

    private final ItemStack b;
    private float c;
    private boolean d;

    public PossibleFishingResult(ItemStack itemstack, int i) {
        super(i);
        this.b = itemstack;
    }

    public ItemStack a(Random random) {
        ItemStack itemstack = this.b.cloneItemStack();

        if (this.c > 0.0F) {
            int i = (int) (this.c * (float) this.b.l());
            int j = itemstack.l() - random.nextInt(random.nextInt(i) + 1);

            if (j > i) {
                j = i;
            }

            if (j < 1) {
                j = 1;
            }

            itemstack.setData(j);
        }

        if (this.d) {
            EnchantmentManager.a(random, itemstack, 30);
        }

        return itemstack;
    }

    public PossibleFishingResult a(float f) {
        this.c = f;
        return this;
    }

    public PossibleFishingResult a() {
        this.d = true;
        return this;
    }
}
