package net.minecraft.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChunkProviderLoadOrGenerate implements IChunkProvider {

    private Set a = new HashSet();
    private Chunk b;
    private IChunkProvider c;
    private IChunkLoader d;
    private Map e = new HashMap();
    private List f = new ArrayList();
    private World g;

    public ChunkProviderLoadOrGenerate(World world, IChunkLoader ichunkloader, IChunkProvider ichunkprovider) {
        this.b = new EmptyChunk(world, new byte['\u8000'], 0, 0);
        this.g = world;
        this.d = ichunkloader;
        this.c = ichunkprovider;
    }

    public boolean isChunkLoaded(int i, int j) {
        return this.e.containsKey(Integer.valueOf(ChunkCoordIntPair.a(i, j)));
    }

    public Chunk getChunkAt(int i, int j) {
        int k = ChunkCoordIntPair.a(i, j);

        this.a.remove(Integer.valueOf(k));
        Chunk chunk = (Chunk) this.e.get(Integer.valueOf(k));

        if (chunk == null) {
            chunk = this.d(i, j);
            if (chunk == null) {
                if (this.c == null) {
                    chunk = this.b;
                } else {
                    chunk = this.c.getOrCreateChunk(i, j);
                }
            }

            this.e.put(Integer.valueOf(k), chunk);
            this.f.add(chunk);
            if (chunk != null) {
                chunk.loadNOP();
                chunk.addEntities();
            }

            if (!chunk.done && this.isChunkLoaded(i + 1, j + 1) && this.isChunkLoaded(i, j + 1) && this.isChunkLoaded(i + 1, j)) {
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

        return chunk;
    }

    public Chunk getOrCreateChunk(int i, int j) {
        Chunk chunk = (Chunk) this.e.get(Integer.valueOf(ChunkCoordIntPair.a(i, j)));

        return chunk == null ? this.getChunkAt(i, j) : chunk;
    }

    private Chunk d(int i, int j) {
        if (this.d == null) {
            return null;
        } else {
            try {
                Chunk chunk = this.d.a(this.g, i, j);

                if (chunk != null) {
                    chunk.r = this.g.getTime();
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
                chunk.r = this.g.getTime();
                this.d.a(this.g, chunk);
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }

    public void getChunkAt(IChunkProvider ichunkprovider, int i, int j) {
        Chunk chunk = this.getOrCreateChunk(i, j);

        if (!chunk.done) {
            chunk.done = true;
            if (this.c != null) {
                this.c.getChunkAt(ichunkprovider, i, j);
                chunk.f();
            }
        }
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
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
                if (i == 24 && !flag) {
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

    public boolean unloadChunks() {
        for (int i = 0; i < 100; ++i) {
            if (!this.a.isEmpty()) {
                Integer integer = (Integer) this.a.iterator().next();
                Chunk chunk = (Chunk) this.e.get(integer);

                chunk.removeEntities();
                this.b(chunk);
                this.a(chunk);
                this.a.remove(integer);
                this.e.remove(integer);
                this.f.remove(chunk);
            }
        }

        if (this.d != null) {
            this.d.a();
        }

        return this.c.unloadChunks();
    }

    public boolean b() {
        return true;
    }
}
