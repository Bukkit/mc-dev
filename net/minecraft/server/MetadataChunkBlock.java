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

        if (l > '\u8000') {
            System.out.println("Light too large, skipping!");
        } else {
            int i1 = 0;
            int j1 = 0;
            boolean flag = false;
            boolean flag1 = false;

            for (int k1 = this.b; k1 <= this.e; ++k1) {
                for (int l1 = this.d; l1 <= this.g; ++l1) {
                    int i2 = k1 >> 4;
                    int j2 = l1 >> 4;
                    boolean flag2 = false;

                    if (flag && i2 == i1 && j2 == j1) {
                        flag2 = flag1;
                    } else {
                        flag2 = world.areChunksLoaded(k1, 0, l1, 1);
                        if (flag2) {
                            Chunk chunk = world.getChunkAt(k1 >> 4, l1 >> 4);

                            if (chunk.isEmpty()) {
                                flag2 = false;
                            }
                        }

                        flag1 = flag2;
                        i1 = i2;
                        j1 = j2;
                    }

                    if (flag2) {
                        if (this.c < 0) {
                            this.c = 0;
                        }

                        if (this.f >= 128) {
                            this.f = 127;
                        }

                        for (int k2 = this.c; k2 <= this.f; ++k2) {
                            int l2 = world.a(this.a, k1, k2, l1);
                            boolean flag3 = false;
                            int i3 = world.getTypeId(k1, k2, l1);
                            int j3 = Block.q[i3];

                            if (j3 == 0) {
                                j3 = 1;
                            }

                            int k3 = 0;

                            if (this.a == EnumSkyBlock.SKY) {
                                if (world.m(k1, k2, l1)) {
                                    k3 = 15;
                                }
                            } else if (this.a == EnumSkyBlock.BLOCK) {
                                k3 = Block.s[i3];
                            }

                            int l3;
                            int i4;

                            if (j3 >= 15 && k3 == 0) {
                                i4 = 0;
                            } else {
                                l3 = world.a(this.a, k1 - 1, k2, l1);
                                int j4 = world.a(this.a, k1 + 1, k2, l1);
                                int k4 = world.a(this.a, k1, k2 - 1, l1);
                                int l4 = world.a(this.a, k1, k2 + 1, l1);
                                int i5 = world.a(this.a, k1, k2, l1 - 1);
                                int j5 = world.a(this.a, k1, k2, l1 + 1);

                                i4 = l3;
                                if (j4 > l3) {
                                    i4 = j4;
                                }

                                if (k4 > i4) {
                                    i4 = k4;
                                }

                                if (l4 > i4) {
                                    i4 = l4;
                                }

                                if (i5 > i4) {
                                    i4 = i5;
                                }

                                if (j5 > i4) {
                                    i4 = j5;
                                }

                                i4 -= j3;
                                if (i4 < 0) {
                                    i4 = 0;
                                }

                                if (k3 > i4) {
                                    i4 = k3;
                                }
                            }

                            if (l2 != i4) {
                                world.b(this.a, k1, k2, l1, i4);
                                l3 = i4 - 1;
                                if (l3 < 0) {
                                    l3 = 0;
                                }

                                world.a(this.a, k1 - 1, k2, l1, l3);
                                world.a(this.a, k1, k2 - 1, l1, l3);
                                world.a(this.a, k1, k2, l1 - 1, l3);
                                if (k1 + 1 >= this.e) {
                                    world.a(this.a, k1 + 1, k2, l1, l3);
                                }

                                if (k2 + 1 >= this.f) {
                                    world.a(this.a, k1, k2 + 1, l1, l3);
                                }

                                if (l1 + 1 >= this.g) {
                                    world.a(this.a, k1, k2, l1 + 1, l3);
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
