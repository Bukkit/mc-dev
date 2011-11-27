package net.minecraft.server;

import java.util.Random;

public abstract class StructurePieceBlockSelector {

    protected int a;
    protected int b;

    protected StructurePieceBlockSelector() {}

    public abstract void a(Random random, int i, int j, int k, boolean flag);

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }
}
