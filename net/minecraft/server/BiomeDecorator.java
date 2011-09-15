package net.minecraft.server;

import java.util.Random;

public class BiomeDecorator {

    private World B;
    private Random C;
    private int D;
    private int E;
    private BiomeBase F;
    protected WorldGenerator a = new WorldGenClay(4);
    protected WorldGenerator b;
    protected WorldGenerator c;
    protected WorldGenerator d;
    protected WorldGenerator e;
    protected WorldGenerator f;
    protected WorldGenerator g;
    protected WorldGenerator h;
    protected WorldGenerator i;
    protected WorldGenerator j;
    protected WorldGenerator k;
    protected WorldGenerator l;
    protected WorldGenerator m;
    protected WorldGenerator n;
    protected WorldGenerator o;
    protected WorldGenerator p;
    protected WorldGenerator q;
    protected int r;
    protected int s;
    protected int t;
    protected int u;
    protected int v;
    protected int w;
    protected int x;
    protected int y;
    protected int z;
    protected int A;

    public BiomeDecorator(BiomeBase biomebase) {
        this.b = new WorldGenSand(7, Block.SAND.id);
        this.c = new WorldGenSand(6, Block.GRAVEL.id);
        this.d = new WorldGenMinable(Block.DIRT.id, 32);
        this.e = new WorldGenMinable(Block.GRAVEL.id, 32);
        this.f = new WorldGenMinable(Block.COAL_ORE.id, 16);
        this.g = new WorldGenMinable(Block.IRON_ORE.id, 8);
        this.h = new WorldGenMinable(Block.GOLD_ORE.id, 8);
        this.i = new WorldGenMinable(Block.REDSTONE_ORE.id, 7);
        this.j = new WorldGenMinable(Block.DIAMOND_ORE.id, 7);
        this.k = new WorldGenMinable(Block.LAPIS_ORE.id, 6);
        this.l = new WorldGenFlowers(Block.YELLOW_FLOWER.id);
        this.m = new WorldGenFlowers(Block.YELLOW_FLOWER.id);
        this.n = new WorldGenFlowers(Block.BROWN_MUSHROOM.id);
        this.o = new WorldGenFlowers(Block.RED_MUSHROOM.id);
        this.p = new WorldGenReed();
        this.q = new WorldGenCactus();
        this.r = 0;
        this.s = 2;
        this.t = 1;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 1;
        this.z = 3;
        this.A = 1;
        this.F = biomebase;
    }

    public void a(World world, Random random, int i, int j) {
        if (this.B != null) {
            throw new RuntimeException("Already decorating!!");
        } else {
            this.B = world;
            this.C = random;
            this.D = i;
            this.E = j;
            this.b();
            this.B = null;
            this.C = null;
        }
    }

    private void b() {
        this.a();

        int i;
        int j;
        int k;

        for (i = 0; i < this.z; ++i) {
            j = this.D + this.C.nextInt(16) + 8;
            k = this.E + this.C.nextInt(16) + 8;
            this.b.a(this.B, this.C, j, this.B.f(j, k), k);
        }

        for (i = 0; i < this.A; ++i) {
            j = this.D + this.C.nextInt(16) + 8;
            k = this.E + this.C.nextInt(16) + 8;
            this.a.a(this.B, this.C, j, this.B.f(j, k), k);
        }

        for (i = 0; i < this.y; ++i) {
            j = this.D + this.C.nextInt(16) + 8;
            k = this.E + this.C.nextInt(16) + 8;
            this.b.a(this.B, this.C, j, this.B.f(j, k), k);
        }

        i = this.r;
        if (this.C.nextInt(10) == 0) {
            ++i;
        }

        int l;

        for (j = 0; j < i; ++j) {
            k = this.D + this.C.nextInt(16) + 8;
            l = this.E + this.C.nextInt(16) + 8;
            WorldGenerator worldgenerator = this.F.a(this.C);

            worldgenerator.a(1.0D, 1.0D, 1.0D);
            worldgenerator.a(this.B, this.C, k, this.B.getHighestBlockYAt(k, l), l);
        }

        int i1;
        Random random;

        for (j = 0; j < this.s; ++j) {
            k = this.D + this.C.nextInt(16) + 8;
            random = this.C;

            l = random.nextInt(128);
            i1 = this.E + this.C.nextInt(16) + 8;
            this.l.a(this.B, this.C, k, l, i1);
            if (this.C.nextInt(4) == 0) {
                k = this.D + this.C.nextInt(16) + 8;
                random = this.C;

                l = random.nextInt(128);
                i1 = this.E + this.C.nextInt(16) + 8;
                this.m.a(this.B, this.C, k, l, i1);
            }
        }

        for (j = 0; j < this.t; ++j) {
            byte b0 = 1;

            l = this.D + this.C.nextInt(16) + 8;
            random = this.C;

            i1 = random.nextInt(128);
            int j1 = this.E + this.C.nextInt(16) + 8;

            (new WorldGenGrass(Block.LONG_GRASS.id, b0)).a(this.B, this.C, l, i1, j1);
        }

        for (j = 0; j < this.u; ++j) {
            k = this.D + this.C.nextInt(16) + 8;
            random = this.C;

            l = random.nextInt(128);
            i1 = this.E + this.C.nextInt(16) + 8;
            (new WorldGenDeadBush(Block.DEAD_BUSH.id)).a(this.B, this.C, k, l, i1);
        }

        for (j = 0; j < this.v; ++j) {
            if (this.C.nextInt(4) == 0) {
                k = this.D + this.C.nextInt(16) + 8;
                l = this.E + this.C.nextInt(16) + 8;
                i1 = this.B.getHighestBlockYAt(k, l);
                this.n.a(this.B, this.C, k, i1, l);
            }

            if (this.C.nextInt(8) == 0) {
                k = this.D + this.C.nextInt(16) + 8;
                l = this.E + this.C.nextInt(16) + 8;
                random = this.C;

                i1 = random.nextInt(128);
                this.o.a(this.B, this.C, k, i1, l);
            }
        }

        if (this.C.nextInt(4) == 0) {
            j = this.D + this.C.nextInt(16) + 8;
            random = this.C;

            k = random.nextInt(128);
            l = this.E + this.C.nextInt(16) + 8;
            this.n.a(this.B, this.C, j, k, l);
        }

        if (this.C.nextInt(8) == 0) {
            j = this.D + this.C.nextInt(16) + 8;
            random = this.C;

            k = random.nextInt(128);
            l = this.E + this.C.nextInt(16) + 8;
            this.o.a(this.B, this.C, j, k, l);
        }

        for (j = 0; j < this.w; ++j) {
            k = this.D + this.C.nextInt(16) + 8;
            l = this.E + this.C.nextInt(16) + 8;
            random = this.C;

            i1 = random.nextInt(128);
            this.p.a(this.B, this.C, k, i1, l);
        }

        for (j = 0; j < 10; ++j) {
            k = this.D + this.C.nextInt(16) + 8;
            random = this.C;

            l = random.nextInt(128);
            i1 = this.E + this.C.nextInt(16) + 8;
            this.p.a(this.B, this.C, k, l, i1);
        }

        if (this.C.nextInt(32) == 0) {
            j = this.D + this.C.nextInt(16) + 8;
            random = this.C;

            k = random.nextInt(128);
            l = this.E + this.C.nextInt(16) + 8;
            (new WorldGenPumpkin()).a(this.B, this.C, j, k, l);
        }

        for (j = 0; j < this.x; ++j) {
            k = this.D + this.C.nextInt(16) + 8;
            random = this.C;

            l = random.nextInt(128);
            i1 = this.E + this.C.nextInt(16) + 8;
            this.q.a(this.B, this.C, k, l, i1);
        }

        Random random1;

        for (j = 0; j < 50; ++j) {
            k = this.D + this.C.nextInt(16) + 8;
            random = this.C;
            random1 = this.C;

            l = random.nextInt(random1.nextInt(128 - 8) + 8);
            i1 = this.E + this.C.nextInt(16) + 8;
            (new WorldGenLiquids(Block.WATER.id)).a(this.B, this.C, k, l, i1);
        }

        for (j = 0; j < 20; ++j) {
            k = this.D + this.C.nextInt(16) + 8;
            random = this.C;
            random1 = this.C;
            Random random2 = this.C;

            l = random.nextInt(random1.nextInt(random2.nextInt(128 - 16) + 8) + 8);
            i1 = this.E + this.C.nextInt(16) + 8;
            (new WorldGenLiquids(Block.LAVA.id)).a(this.B, this.C, k, l, i1);
        }
    }

    protected void a(int i, WorldGenerator worldgenerator, int j, int k) {
        for (int l = 0; l < i; ++l) {
            int i1 = this.D + this.C.nextInt(16);
            int j1 = this.C.nextInt(k - j) + j;
            int k1 = this.E + this.C.nextInt(16);

            worldgenerator.a(this.B, this.C, i1, j1, k1);
        }
    }

    protected void b(int i, WorldGenerator worldgenerator, int j, int k) {
        for (int l = 0; l < i; ++l) {
            int i1 = this.D + this.C.nextInt(16);
            int j1 = this.C.nextInt(k) + this.C.nextInt(k) + (j - k);
            int k1 = this.E + this.C.nextInt(16);

            worldgenerator.a(this.B, this.C, i1, j1, k1);
        }
    }

    protected void a() {
        WorldGenerator worldgenerator = this.d;

        this.a(20, worldgenerator, 0, 128);
        worldgenerator = this.e;

        this.a(10, worldgenerator, 0, 128);
        worldgenerator = this.f;

        this.a(20, worldgenerator, 0, 128);
        worldgenerator = this.g;

        this.a(20, worldgenerator, 0, 128 / 2);
        worldgenerator = this.h;

        this.a(2, worldgenerator, 0, 128 / 4);
        worldgenerator = this.i;

        this.a(8, worldgenerator, 0, 128 / 8);
        worldgenerator = this.j;

        this.a(1, worldgenerator, 0, 128 / 8);
        worldgenerator = this.k;

        int i = 128 / 8;

        this.b(1, worldgenerator, i, 128 / 8);
    }
}
