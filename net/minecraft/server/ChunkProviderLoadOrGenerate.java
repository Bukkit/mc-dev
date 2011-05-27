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
    private int i;
    private int j;

    public ChunkProviderLoadOrGenerate(World world, IChunkLoader ichunkloader, IChunkProvider ichunkprovider) {
        this.c = new EmptyChunk(world, new byte['\u8000'], 0, 0);
        this.g = world;
        this.e = ichunkloader;
        this.d = ichunkprovider;
    }

    public boolean d(int i, int j) {
        byte b0 = 15;

        return i >= this.i - b0 && j >= this.j - b0 && i <= this.i + b0 && j <= this.j + b0;
    }

    public boolean isChunkLoaded(int i, int j) {
        if (!this.d(i, j)) {
            return false;
        } else if (i == this.a && j == this.b && this.h != null) {
            return true;
        } else {
            int k = i & 31;
            int l = j & 31;
            int i1 = k + l * 32;

            return this.f[i1] != null && (this.f[i1] == this.c || this.f[i1].a(i, j));
        }
    }

    public Chunk getChunkAt(int i, int j) {
        return this.getOrCreateChunk(i, j);
    }

    public Chunk getOrCreateChunk(int i, int j) {
        if (i == this.a && j == this.b && this.h != null) {
            return this.h;
        } else if (!this.g.isLoading && !this.d(i, j)) {
            return this.c;
        } else {
            int k = i & 31;
            int l = j & 31;
            int i1 = k + l * 32;

            if (!this.isChunkLoaded(i, j)) {
                if (this.f[i1] != null) {
                    this.f[i1].removeEntities();
                    this.b(this.f[i1]);
                    this.a(this.f[i1]);
                }

                Chunk chunk = this.e(i, j);

                if (chunk == null) {
                    if (this.d == null) {
                        chunk = this.c;
                    } else {
                        chunk = this.d.getOrCreateChunk(i, j);
                    }
                }

                this.f[i1] = chunk;
                chunk.loadNOP();
                if (this.f[i1] != null) {
                    this.f[i1].addEntities();
                }

                if (!this.f[i1].done && this.isChunkLoaded(i + 1, j + 1) && this.isChunkLoaded(i, j + 1) && this.isChunkLoaded(i + 1, j)) {
                    this.getChunkAt(this, i, j);
                }

                if (this.isChunkLoaded(i - 1, j) && !this.getOrCreateChunk(i - 1, j).done && this.isChunkLoaded(i - 1, j + 1) && this.isChunkLoaded(i, j + 1) && this.isChunkLoaded(i - 1, j)) {
                    this.getChunkAt(this, i - 1, j);
                }

                if (this.isChunkLoaded(i, j - 1) && !this.getOrCreateChunk(i, j - 1).done && this.isChunkLoaded(i + 1, j - 1) && this.isChunkLoaded(i, j - 1) && this.isChunkLoaded(i + 1, j)) {
                    this.getChunkAt(this, i, j - 1);
                }

                if (this.isChunkLoaded(i - 1, j - 1) && !this.getOrCreateChunk(i - 1, j - 1).done && this.isChunkLoaded(i - 1, j - 1) && this.isChunkLoaded(i, j - 1) && this.isChunkLoaded(i - 1, j)) {
                    this.getChunkAt(this, i - 1, j - 1);
                }
            }

            this.a = i;
            this.b = j;
            this.h = this.f[i1];
            return this.f[i1];
        }
    }

    private Chunk e(int i, int j) {
        if (this.e == null) {
            return this.c;
        } else {
            try {
                Chunk chunk = this.e.a(this.g, i, j);

                if (chunk != null) {
                    chunk.r = this.g.getTime();
                }

                return chunk;
            } catch (Exception exception) {
                exception.printStackTrace();
                return this.c;
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
                chunk.r = this.g.getTime();
                this.e.a(this.g, chunk);
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }

    public void getChunkAt(IChunkProvider ichunkprovider, int i, int j) {
        Chunk chunk = this.getOrCreateChunk(i, j);

        if (!chunk.done) {
            chunk.done = true;
            if (this.d != null) {
                this.d.getChunkAt(ichunkprovider, i, j);
                chunk.f();
            }
        }
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
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

    public boolean unloadChunks() {
        if (this.e != null) {
            this.e.a();
        }

        return this.d.unloadChunks();
    }

    public boolean b() {
        return true;
    }
}
