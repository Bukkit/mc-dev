package net.minecraft.server;

public class MetadataChunkBlock {

    public final EnumSkyBlock a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;

    public MetadataChunkBlock(EnumSkyBlock enumskyblock, int i, int j, int k, int l, int i1, int j1) {
        this.a = enumskyblock;
        this.b = i;
        this.c = j;
        this.d = k;
        this.e = l;
        this.f = i1;
        this.g = j1;
    }

    public void a(World world) {
        int i = this.e - this.b + 1;
        int j = this.f - this.c + 1;
        int k = this.g - this.d + 1;
        int l = i * j * k;

        if (l <= '\u8000') {
            for (int i1 = this.b; i1 <= this.e; ++i1) {
                for (int j1 = this.d; j1 <= this.g; ++j1) {
                    if (world.e(i1, 0, j1)) {
                        for (int k1 = this.c; k1 <= this.f; ++k1) {
                            if (k1 >= 0 && k1 < 128) {
                                int l1 = world.a(this.a, i1, k1, j1);
                                boolean flag = false;
                                int i2 = world.a(i1, k1, j1);
                                int j2 = Block.r[i2];

                                if (j2 == 0) {
                                    j2 = 1;
                                }

                                int k2 = 0;

                                if (this.a == EnumSkyBlock.SKY) {
                                    if (world.i(i1, k1, j1)) {
                                        k2 = 15;
                                    }
                                } else if (this.a == EnumSkyBlock.BLOCK) {
                                    k2 = Block.t[i2];
                                }

                                int l2;
                                int i3;

                                if (j2 >= 15 && k2 == 0) {
                                    i3 = 0;
                                } else {
                                    l2 = world.a(this.a, i1 - 1, k1, j1);
                                    int j3 = world.a(this.a, i1 + 1, k1, j1);
                                    int k3 = world.a(this.a, i1, k1 - 1, j1);
                                    int l3 = world.a(this.a, i1, k1 + 1, j1);
                                    int i4 = world.a(this.a, i1, k1, j1 - 1);
                                    int j4 = world.a(this.a, i1, k1, j1 + 1);

                                    i3 = l2;
                                    if (j3 > l2) {
                                        i3 = j3;
                                    }

                                    if (k3 > i3) {
                                        i3 = k3;
                                    }

                                    if (l3 > i3) {
                                        i3 = l3;
                                    }

                                    if (i4 > i3) {
                                        i3 = i4;
                                    }

                                    if (j4 > i3) {
                                        i3 = j4;
                                    }

                                    i3 -= j2;
                                    if (i3 < 0) {
                                        i3 = 0;
                                    }

                                    if (k2 > i3) {
                                        i3 = k2;
                                    }
                                }

                                if (l1 != i3) {
                                    world.b(this.a, i1, k1, j1, i3);
                                    l2 = i3 - 1;
                                    if (l2 < 0) {
                                        l2 = 0;
                                    }

                                    world.a(this.a, i1 - 1, k1, j1, l2);
                                    world.a(this.a, i1, k1 - 1, j1, l2);
                                    world.a(this.a, i1, k1, j1 - 1, l2);
                                    if (i1 + 1 >= this.e) {
                                        world.a(this.a, i1 + 1, k1, j1, l2);
                                    }

                                    if (k1 + 1 >= this.f) {
                                        world.a(this.a, i1, k1 + 1, j1, l2);
                                    }

                                    if (j1 + 1 >= this.g) {
                                        world.a(this.a, i1, k1, j1 + 1, l2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean a(int i, int j, int k, int l, int i1, int j1) {
        if (i >= this.b && j >= this.c && k >= this.d && l <= this.e && i1 <= this.f && j1 <= this.g) {
            return true;
        } else {
            byte b0 = 1;

            if (i >= this.b - b0 && j >= this.c - b0 && k >= this.d - b0 && l <= this.e + b0 && i1 <= this.f + b0 && j1 <= this.g + b0) {
                int k1 = this.e - this.b;
                int l1 = this.f - this.c;
                int i2 = this.g - this.d;

                if (i > this.b) {
                    i = this.b;
                }

                if (j > this.c) {
                    j = this.c;
                }

                if (k > this.d) {
                    k = this.d;
                }

                if (l < this.e) {
                    l = this.e;
                }

                if (i1 < this.f) {
                    i1 = this.f;
                }

                if (j1 < this.g) {
                    j1 = this.g;
                }

                int j2 = l - i;
                int k2 = i1 - j;
                int l2 = j1 - k;
                int i3 = k1 * l1 * i2;
                int j3 = j2 * k2 * l2;

                if (j3 - i3 <= 2) {
                    this.b = i;
                    this.c = j;
                    this.d = k;
                    this.e = l;
                    this.f = i1;
                    this.g = j1;
                    return true;
                }
            }

            return false;
        }
    }
}
