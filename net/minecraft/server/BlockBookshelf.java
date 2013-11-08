package net.minecraft.server;

import java.util.Random;

public class BlockBookshelf extends Block {

    public BlockBookshelf() {
        super(Material.WOOD);
        this.a(CreativeModeTab.b);
    }

    public int a(Random random) {
        return 3;
    }

    public Item getDropType(int i, Random random, int j) {
        return Items.BOOK;
    }
}
