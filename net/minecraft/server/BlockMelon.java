package net.minecraft.server;

import java.util.Random;

public class BlockMelon extends Block {

    protected BlockMelon(int i) {
        super(i, Material.PUMPKIN);
        this.textureId = 136;
    }

    public int a(int i, int j) {
        return i != 1 && i != 0 ? 136 : 137;
    }

    public int a(int i) {
        return i != 1 && i != 0 ? 136 : 137;
    }

    public int getDropType(int i, Random random, int j) {
        return Item.MELON.id;
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
