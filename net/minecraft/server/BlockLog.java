package net.minecraft.server;

import java.util.Random;

public class BlockLog extends Block {

    protected BlockLog(int i) {
        super(i, Material.c);
        this.bh = 20;
    }

    public int a(Random random) {
        return 1;
    }

    public int a(int i, Random random) {
        return Block.LOG.bi;
    }

    public int a(int i) {
        return i == 1 ? 21 : (i == 0 ? 21 : 20);
    }
}
