package net.minecraft.server;

import java.util.Random;

public abstract class StructurePieceBlockSelector {

    protected Block a;
    protected int b;

    protected StructurePieceBlockSelector() {
        this.a = Blocks.AIR;
    }

    public abstract void a(Random random, int i, int j, int k, boolean flag);

    public Block a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }
}
