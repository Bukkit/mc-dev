package net.minecraft.server;

import java.util.Random;

public class WorldGenBase {

    protected int b = 8;
    protected Random c = new Random();
    protected World d;

    public WorldGenBase() {}

    public void a(IChunkProvider ichunkprovider, World world, int i, int j, byte[] abyte) {
        int k = this.b;

        this.d = world;
        this.c.setSeed(world.getSeed());
        long l = this.c.nextLong();
        long i1 = this.c.nextLong();

        for (int j1 = i - k; j1 <= i + k; ++j1) {
            for (int k1 = j - k; k1 <= j + k; ++k1) {
                long l1 = (long) j1 * l;
                long i2 = (long) k1 * i1;

                this.c.setSeed(l1 ^ i2 ^ world.getSeed());
                this.a(world, j1, k1, i, j, abyte);
            }
        }
    }

    protected void a(World world, int i, int j, int k, int l, byte[] abyte) {}
}
