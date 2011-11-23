package net.minecraft.server;

import java.util.Random;

public class BiomeDecorator {

    protected World a;
    protected Random b;
    protected int c;
    protected int d;
    protected BiomeBase e;
    protected WorldGenerator f = new WorldGenClay(4);
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
    protected WorldGenerator r;
    protected WorldGenerator s;
    protected WorldGenerator t;
    protected WorldGenerator u;
    protected WorldGenerator v;
    protected WorldGenerator w;
    protected WorldGenerator x;
    protected int y;
    protected int z;
    protected int A;
    protected int B;
    protected int C;
    protected int D;
    protected int E;
    protected int F;
    protected int G;
    protected int H;
    protected int I;
    protected int J;
    public boolean K;

    public BiomeDecorator(BiomeBase biomebase) {
        this.g = new WorldGenSand(7, Block.SAND.id);
        this.h = new WorldGenSand(6, Block.GRAVEL.id);
        this.i = new WorldGenMinable(Block.DIRT.id, 32);
        this.j = new WorldGenMinable(Block.GRAVEL.id, 32);
        this.k = new WorldGenMinable(Block.COAL_ORE.id, 16);
        this.l = new WorldGenMinable(Block.IRON_ORE.id, 8);
        this.m = new WorldGenMinable(Block.GOLD_ORE.id, 8);
        this.n = new WorldGenMinable(Block.REDSTONE_ORE.id, 7);
        this.o = new WorldGenMinable(Block.DIAMOND_ORE.id, 7);
        this.p = new WorldGenMinable(Block.LAPIS_ORE.id, 6);
        this.q = new WorldGenFlowers(Block.YELLOW_FLOWER.id);
        this.r = new WorldGenFlowers(Block.RED_ROSE.id);
        this.s = new WorldGenFlowers(Block.BROWN_MUSHROOM.id);
        this.t = new WorldGenFlowers(Block.RED_MUSHROOM.id);
        this.u = new WorldGenHugeMushroom();
        this.v = new WorldGenReed();
        this.w = new WorldGenCactus();
        this.x = new WorldGenWaterLily();
        this.y = 0;
        this.z = 0;
        this.A = 2;
        this.B = 1;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.G = 1;
        this.H = 3;
        this.I = 1;
        this.J = 0;
        this.K = true;
        this.e = biomebase;
    }

    public void a(World world, Random random, int i, int j) {
        if (this.a != null) {
            throw new RuntimeException("Already decorating!!");
        } else {
            this.a = world;
            this.b = random;
            this.c = i;
            this.d = j;
            this.a();
            this.a = null;
            this.b = null;
        }
    }

    protected void a() {
        this.b();

        int i;
        int j;
        int k;

        for (i = 0; i < this.H; ++i) {
            j = this.c + this.b.nextInt(16) + 8;
            k = this.d + this.b.nextInt(16) + 8;
            this.g.a(this.a, this.b, j, this.a.f(j, k), k);
        }

        for (i = 0; i < this.I; ++i) {
            j = this.c + this.b.nextInt(16) + 8;
            k = this.d + this.b.nextInt(16) + 8;
            this.f.a(this.a, this.b, j, this.a.f(j, k), k);
        }

        for (i = 0; i < this.G; ++i) {
            j = this.c + this.b.nextInt(16) + 8;
            k = this.d + this.b.nextInt(16) + 8;
            this.g.a(this.a, this.b, j, this.a.f(j, k), k);
        }

        i = this.z;
        if (this.b.nextInt(10) == 0) {
            ++i;
        }

        int l;

        for (j = 0; j < i; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.d + this.b.nextInt(16) + 8;
            WorldGenerator worldgenerator = this.e.a(this.b);

            worldgenerator.a(1.0D, 1.0D, 1.0D);
            worldgenerator.a(this.a, this.b, k, this.a.getHighestBlockYAt(k, l), l);
        }

        for (j = 0; j < this.J; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.d + this.b.nextInt(16) + 8;
            this.u.a(this.a, this.b, k, this.a.getHighestBlockYAt(k, l), l);
        }

        int i1;

        for (j = 0; j < this.A; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.b.nextInt(this.a.height);
            i1 = this.d + this.b.nextInt(16) + 8;
            this.q.a(this.a, this.b, k, l, i1);
            if (this.b.nextInt(4) == 0) {
                k = this.c + this.b.nextInt(16) + 8;
                l = this.b.nextInt(this.a.height);
                i1 = this.d + this.b.nextInt(16) + 8;
                this.r.a(this.a, this.b, k, l, i1);
            }
        }

        for (j = 0; j < this.B; ++j) {
            byte b0 = 1;

            l = this.c + this.b.nextInt(16) + 8;
            i1 = this.b.nextInt(this.a.height);
            int j1 = this.d + this.b.nextInt(16) + 8;

            (new WorldGenGrass(Block.LONG_GRASS.id, b0)).a(this.a, this.b, l, i1, j1);
        }

        for (j = 0; j < this.C; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.b.nextInt(this.a.height);
            i1 = this.d + this.b.nextInt(16) + 8;
            (new WorldGenDeadBush(Block.DEAD_BUSH.id)).a(this.a, this.b, k, l, i1);
        }

        for (j = 0; j < this.y; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.d + this.b.nextInt(16) + 8;

            for (i1 = this.b.nextInt(this.a.height); i1 > 0 && this.a.getTypeId(k, i1 - 1, l) == 0; --i1) {
                ;
            }

            this.x.a(this.a, this.b, k, i1, l);
        }

        for (j = 0; j < this.D; ++j) {
            if (this.b.nextInt(4) == 0) {
                k = this.c + this.b.nextInt(16) + 8;
                l = this.d + this.b.nextInt(16) + 8;
                i1 = this.a.getHighestBlockYAt(k, l);
                this.s.a(this.a, this.b, k, i1, l);
            }

            if (this.b.nextInt(8) == 0) {
                k = this.c + this.b.nextInt(16) + 8;
                l = this.d + this.b.nextInt(16) + 8;
                i1 = this.b.nextInt(this.a.height);
                this.t.a(this.a, this.b, k, i1, l);
            }
        }

        if (this.b.nextInt(4) == 0) {
            j = this.c + this.b.nextInt(16) + 8;
            k = this.b.nextInt(this.a.height);
            l = this.d + this.b.nextInt(16) + 8;
            this.s.a(this.a, this.b, j, k, l);
        }

        if (this.b.nextInt(8) == 0) {
            j = this.c + this.b.nextInt(16) + 8;
            k = this.b.nextInt(this.a.height);
            l = this.d + this.b.nextInt(16) + 8;
            this.t.a(this.a, this.b, j, k, l);
        }

        for (j = 0; j < this.E; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.d + this.b.nextInt(16) + 8;
            i1 = this.b.nextInt(this.a.height);
            this.v.a(this.a, this.b, k, i1, l);
        }

        for (j = 0; j < 10; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.b.nextInt(this.a.height);
            i1 = this.d + this.b.nextInt(16) + 8;
            this.v.a(this.a, this.b, k, l, i1);
        }

        if (this.b.nextInt(32) == 0) {
            j = this.c + this.b.nextInt(16) + 8;
            k = this.b.nextInt(this.a.height);
            l = this.d + this.b.nextInt(16) + 8;
            (new WorldGenPumpkin()).a(this.a, this.b, j, k, l);
        }

        for (j = 0; j < this.F; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.b.nextInt(this.a.height);
            i1 = this.d + this.b.nextInt(16) + 8;
            this.w.a(this.a, this.b, k, l, i1);
        }

        if (this.K) {
            for (j = 0; j < 50; ++j) {
                k = this.c + this.b.nextInt(16) + 8;
                l = this.b.nextInt(this.b.nextInt(this.a.height - 8) + 8);
                i1 = this.d + this.b.nextInt(16) + 8;
                (new WorldGenLiquids(Block.WATER.id)).a(this.a, this.b, k, l, i1);
            }

            for (j = 0; j < 20; ++j) {
                k = this.c + this.b.nextInt(16) + 8;
                l = this.b.nextInt(this.b.nextInt(this.b.nextInt(this.a.height - 16) + 8) + 8);
                i1 = this.d + this.b.nextInt(16) + 8;
                (new WorldGenLiquids(Block.LAVA.id)).a(this.a, this.b, k, l, i1);
            }
        }
    }

    protected void a(int i, WorldGenerator worldgenerator, int j, int k) {
        for (int l = 0; l < i; ++l) {
            int i1 = this.c + this.b.nextInt(16);
            int j1 = this.b.nextInt(k - j) + j;
            int k1 = this.d + this.b.nextInt(16);

            worldgenerator.a(this.a, this.b, i1, j1, k1);
        }
    }

    protected void b(int i, WorldGenerator worldgenerator, int j, int k) {
        for (int l = 0; l < i; ++l) {
            int i1 = this.c + this.b.nextInt(16);
            int j1 = this.b.nextInt(k) + this.b.nextInt(k) + (j - k);
            int k1 = this.d + this.b.nextInt(16);

            worldgenerator.a(this.a, this.b, i1, j1, k1);
        }
    }

    protected void b() {
        this.a(20, this.i, 0, this.a.height);
        this.a(10, this.j, 0, this.a.height);
        this.a(20, this.k, 0, this.a.height);
        this.a(20, this.l, 0, this.a.height / 2);
        this.a(2, this.m, 0, this.a.height / 4);
        this.a(8, this.n, 0, this.a.height / 8);
        this.a(1, this.o, 0, this.a.height / 8);
        this.b(1, this.p, this.a.height / 8, this.a.height / 8);
    }
}
