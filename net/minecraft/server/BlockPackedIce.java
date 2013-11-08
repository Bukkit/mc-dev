package net.minecraft.server;

import java.util.Random;

public class BlockPackedIce extends Block {

    public BlockPackedIce() {
        super(Material.SNOW_LAYER);
        this.frictionFactor = 0.98F;
        this.a(CreativeModeTab.b);
    }

    public int a(Random random) {
        return 0;
    }
}
