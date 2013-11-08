package net.minecraft.server;

import java.util.Random;

public class BlockLightStone extends Block {

    public BlockLightStone(Material material) {
        super(material);
        this.a(CreativeModeTab.b);
    }

    public int getDropCount(int i, Random random) {
        return MathHelper.a(this.a(random) + random.nextInt(i + 1), 1, 4);
    }

    public int a(Random random) {
        return 2 + random.nextInt(3);
    }

    public Item getDropType(int i, Random random, int j) {
        return Items.GLOWSTONE_DUST;
    }

    public MaterialMapColor f(int i) {
        return MaterialMapColor.d;
    }
}
