package net.minecraft.server;

import java.io.IOException;

public class ChunkProviderLoadOrGenerate implements IChunkProvider {

    private Chunk c;
    private IChunkProvider d;
    private IChunkLoader e;
    private Chunk[] f = new Chunk[1024];
    private World g;
    int a = -999999999;
    int b = -999999999;
    private Chunk h;

    public ChunkProviderLoadOrGenerate(World world, IChunkLoader ichunkloader, IChunkProvider ichunkprovider) {
        this.c = new Chunk(world, new byte['\u8000'], 0, 0);
        this.c.q = true;
        this.c.p = true;
        this.g = world;
        this.e = ichunkloader;
        this.d = ichunkprovider;
    }

    public boolean a(int i, int j) {
        if (i == this.a && j == this.b && this.h != null) {
            return true;
        } else {
            int k = i & 31;
            int l = j & 31;
            int i1 = k + l * 32;

            return this.f[i1] != null && (this.f[i1] == this.c || this.f[i1].a(i, j));
        }
    }

    public Chunk b(int i, int j) {
        if (i == this.a && j == this.b && this.h != null) {
            return this.h;
        } else {
            int k = i & 31;
            int l = j & 31;
            int i1 = k + l * 32;

            if (!this.a(i, j)) {
                if (this.f[i1] != null) {
                    this.f[i1].e();
                    this.b(this.f[i1]);
                    this.a(this.f[i1]);
                }

                Chunk chunk = this.c(i, j);

                if (chunk == null) {
                    if (this.d == null) {
                        chunk = this.c;
                    } else {
                        chunk = this.d.b(i, j);
                    }
                }

                this.f[i1] = chunk;
                chunk.c();
                if (this.f[i1] != null) {
                    this.f[i1].d();
                }

                if (!this.f[i1].n && this.a(i + 1, j + 1) && this.a(i, j + 1) && this.a(i + 1, j)) {
                    this.a(this, i, j);
                }

                if (this.a(i - 1, j) && !this.b(i - 1, j).n && this.a(i - 1, j + 1) && this.a(i, j + 1) && this.a(i - 1, j)) {
                    this.a(this, i - 1, j);
                }

                if (this.a(i, j - 1) && !this.b(i, j - 1).n && this.a(i + 1, j - 1) && this.a(i, j - 1) && this.a(i + 1, j)) {
                    this.a(this, i, j - 1);
                }

                if (this.a(i - 1, j - 1) && !this.b(i - 1, j - 1).n && this.a(i - 1, j - 1) && this.a(i, j - 1) && this.a(i - 1, j)) {
                    this.a(this, i - 1, j - 1);
                }
            }

            this.a = i;
            this.b = j;
            this.h = this.f[i1];
            return this.f[i1];
        }
    }

    private Chunk c(int i, int j) {
        if (this.e == null) {
            return null;
        } else {
            try {
                Chunk chunk = this.e.a(this.g, i, j);

                if (chunk != null) {
                    chunk.s = this.g.e;
                }

                return chunk;
            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }
    }

    private void a(Chunk chunk) {
        if (this.e != null) {
            try {
                this.e.b(this.g, chunk);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void b(Chunk chunk) {
        if (this.e != null) {
            try {
                chunk.s = this.g.e;
                this.e.a(this.g, chunk);
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }

    public void a(IChunkProvider ichunkprovider, int i, int j) {
        Chunk chunk = this.b(i, j);

        if (!chunk.n) {
            chunk.n = true;
            if (this.d != null) {
                this.d.a(ichunkprovider, i, j);
                chunk.f();
            }
        }
    }

    public boolean a(boolean flag, IProgressUpdate iprogressupdate) {
        int i = 0;
        int j = 0;
        int k;

        if (iprogressupdate != null) {
            for (k = 0; k < this.f.length; ++k) {
                if (this.f[k] != null && this.f[k].a(flag)) {
                    ++j;
                }
            }
        }

        k = 0;

        for (int l = 0; l < this.f.length; ++l) {
            if (this.f[l] != null) {
                if (flag && !this.f[l].p) {
                    this.a(this.f[l]);
                }

                if (this.f[l].a(flag)) {
                    this.b(this.f[l]);
                    this.f[l].o = false;
                    ++i;
                    if (i == 2 && !flag) {
                        return false;
                    }

                    if (iprogressupdate != null) {
                        ++k;
                        if (k % 10 == 0) {
                            iprogressupdate.a(k * 100 / j);
                        }
                    }
                }
            }
        }

        if (flag) {
            if (this.e == null) {
                return true;
            }

            this.e.b();
        }

        return true;
    }

    public boolean a() {
        if (this.e != null) {
            this.e.a();
        }

        return this.d.a();
    }

    public boolean b() {
        return true;
    }
}
