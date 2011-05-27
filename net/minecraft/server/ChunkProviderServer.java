package net.minecraft.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChunkProviderServer implements IChunkProvider {

    private Set a = new HashSet();
    private Chunk b;
    private IChunkProvider c;
    private IChunkLoader d;
    private Map e = new HashMap();
    private List f = new ArrayList();
    private WorldServer g;

    public ChunkProviderServer(WorldServer worldserver, IChunkLoader ichunkloader, IChunkProvider ichunkprovider) {
        this.b = new Chunk(worldserver, new byte['\u8000'], 0, 0);
        this.b.q = true;
        this.b.p = true;
        this.g = worldserver;
        this.d = ichunkloader;
        this.c = ichunkprovider;
    }

    public boolean a(int i, int j) {
        ChunkCoordinates chunkcoordinates = new ChunkCoordinates(i, j);

        return this.e.containsKey(chunkcoordinates);
    }

    public void c(int i, int j) {
        int k = i * 16 + 8 - this.g.m;
        int l = j * 16 + 8 - this.g.o;
        short short1 = 128;

        if (k < -short1 || k > short1 || l < -short1 || l > short1) {
            this.a.add(new ChunkCoordinates(i, j));
        }
    }

    public Chunk d(int i, int j) {
        ChunkCoordinates chunkcoordinates = new ChunkCoordinates(i, j);

        this.a.remove(new ChunkCoordinates(i, j));
        Chunk chunk = (Chunk) this.e.get(chunkcoordinates);

        if (chunk == null) {
            chunk = this.e(i, j);
            if (chunk == null) {
                if (this.c == null) {
                    chunk = this.b;
                } else {
                    chunk = this.c.b(i, j);
                }
            }

            this.e.put(chunkcoordinates, chunk);
            this.f.add(chunk);
            chunk.c();
            if (chunk != null) {
                chunk.d();
            }

            if (!chunk.n && this.a(i + 1, j + 1) && this.a(i, j + 1) && this.a(i + 1, j)) {
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

        return chunk;
    }

    public Chunk b(int i, int j) {
        ChunkCoordinates chunkcoordinates = new ChunkCoordinates(i, j);
        Chunk chunk = (Chunk) this.e.get(chunkcoordinates);

        return chunk == null ? (this.g.x ? this.d(i, j) : this.b) : chunk;
    }

    private Chunk e(int i, int j) {
        if (this.d == null) {
            return null;
        } else {
            try {
                Chunk chunk = this.d.a(this.g, i, j);

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
        if (this.d != null) {
            try {
                this.d.b(this.g, chunk);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void b(Chunk chunk) {
        if (this.d != null) {
            try {
                chunk.s = this.g.e;
                this.d.a(this.g, chunk);
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }

    public void a(IChunkProvider ichunkprovider, int i, int j) {
        Chunk chunk = this.b(i, j);

        if (!chunk.n) {
            chunk.n = true;
            if (this.c != null) {
                this.c.a(ichunkprovider, i, j);
                chunk.f();
            }
        }
    }

    public boolean a(boolean flag, IProgressUpdate iprogressupdate) {
        int i = 0;

        for (int j = 0; j < this.f.size(); ++j) {
            Chunk chunk = (Chunk) this.f.get(j);

            if (flag && !chunk.p) {
                this.a(chunk);
            }

            if (chunk.a(flag)) {
                this.b(chunk);
                chunk.o = false;
                ++i;
                if (i == 32 && !flag) {
                    return false;
                }
            }
        }

        if (flag) {
            if (this.d == null) {
                return true;
            }

            this.d.b();
        }

        return true;
    }

    public boolean a() {
        if (!this.g.C) {
            for (int i = 0; i < 100; ++i) {
                if (!this.a.isEmpty()) {
                    ChunkCoordinates chunkcoordinates = (ChunkCoordinates) this.a.iterator().next();
                    Chunk chunk = this.b(chunkcoordinates.a, chunkcoordinates.b);

                    chunk.e();
                    this.b(chunk);
                    this.a(chunk);
                    this.a.remove(chunkcoordinates);
                    this.e.remove(chunkcoordinates);
                    this.f.remove(chunk);
                }
            }

            if (this.d != null) {
                this.d.a();
            }
        }

        return this.c.a();
    }

    public boolean b() {
        return !this.g.C;
    }
}
