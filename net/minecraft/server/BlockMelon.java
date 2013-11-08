package net.minecraft.server;

import java.util.Random;

public class BlockMelon extends Block {

    protected BlockMelon() {
        super(Material.PUMPKIN);
        this.a(CreativeModeTab.b);
    }

    public Item getDropType(int i, Random random, int j) {
        return Items.MELON;
    }

    public int a(Random random) {
        return 3 + random.nextInt(5);
    }

    public int getDropCount(int i, Random random) {
        int j = this.a(random) + random.nextInt(1 + i);

        if (j > 9) {
            j = 9;
        }

        return j;
    }
}
