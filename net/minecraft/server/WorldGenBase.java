package net.minecraft.server;

import java.util.Random;

public class WorldGenBase {

    protected int a = 8;
    protected Random b = new Random();
    protected World c;

    public WorldGenBase() {}

    public void a(IChunkProvider ichunkprovider, World world, int i, int j, Block[] ablock) {
        int k = this.a;

        this.c = world;
        this.b.setSeed(world.getSeed());
        long l = this.b.nextLong();
        long i1 = this.b.nextLong();

        for (int j1 = i - k; j1 <= i + k; ++j1) {
            for (int k1 = j - k; k1 <= j + k; ++k1) {
                long l1 = (long) j1 * l;
                long i2 = (long) k1 * i1;

                this.b.setSeed(l1 ^ i2 ^ world.getSeed());
                this.a(world, j1, k1, i, j, ablock);
            }
        }
    }

    protected void a(World world, int i, int j, int k, int l, Block[] ablock) {}
}
