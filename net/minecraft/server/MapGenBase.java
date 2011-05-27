package net.minecraft.server;

import java.util.Random;

public class MapGenBase {

    protected int a = 8;
    protected Random b = new Random();

    public MapGenBase() {}

    public void a(IChunkProvider ichunkprovider, World world, int i, int j, byte[] abyte) {
        int k = this.a;

        this.b.setSeed(world.getSeed());
        long l = this.b.nextLong() / 2L * 2L + 1L;
        long i1 = this.b.nextLong() / 2L * 2L + 1L;

        for (int j1 = i - k; j1 <= i + k; ++j1) {
            for (int k1 = j - k; k1 <= j + k; ++k1) {
                this.b.setSeed((long) j1 * l + (long) k1 * i1 ^ world.getSeed());
                this.a(world, j1, k1, i, j, abyte);
            }
        }
    }

    protected void a(World world, int i, int j, int k, int l, byte[] abyte) {}
}
