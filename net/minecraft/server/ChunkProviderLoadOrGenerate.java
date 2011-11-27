package net.minecraft.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChunkProviderLoadOrGenerate implements IChunkProvider {

    private Set a = new HashSet();
    private Chunk b;
    private IChunkProvider c;
    private IChunkLoader d;
    private LongHashMap e = new LongHashMap();
    private List f = new ArrayList();
    private World g;
    private int h;

    public ChunkProviderLoadOrGenerate(World world, IChunkLoader ichunkloader, IChunkProvider ichunkprovider) {
        this.b = new EmptyChunk(world, new byte[256 * world.height], 0, 0);
        this.g = world;
        this.d = ichunkloader;
        this.c = ichunkprovider;
    }

    public boolean isChunkLoaded(int i, int j) {
        return this.e.contains(ChunkCoordIntPair.a(i, j));
    }

    public void d(int i, int j) {
        ChunkCoordinates chunkcoordinates = this.g.getSpawn();
        int k = i * 16 + 8 - chunkcoordinates.x;
        int l = j * 16 + 8 - chunkcoordinates.z;
        short short1 = 128;

        if (k < -short1 || k > short1 || l < -short1 || l > short1) {
            this.a.add(Long.valueOf(ChunkCoordIntPair.a(i, j)));
        }
    }

    public Chunk getChunkAt(int i, int j) {
        long k = ChunkCoordIntPair.a(i, j);

        this.a.remove(Long.valueOf(k));
        Chunk chunk = (Chunk) this.e.getEntry(k);

        if (chunk == null) {
            int l = 1875004;

            if (i < -l || j < -l || i >= l || j >= l) {
                return this.b;
            }

            chunk = this.e(i, j);
            if (chunk == null) {
                if (this.c == null) {
                    chunk = this.b;
                } else {
                    chunk = this.c.getOrCreateChunk(i, j);
                }
            }

            this.e.put(k, chunk);
            this.f.add(chunk);
            if (chunk != null) {
                chunk.loadNOP();
                chunk.addEntities();
            }

            chunk.a(this, this, i, j);
        }

        return chunk;
    }

    public Chunk getOrCreateChunk(int i, int j) {
        Chunk chunk = (Chunk) this.e.getEntry(ChunkCoordIntPair.a(i, j));

        return chunk == null ? this.getChunkAt(i, j) : chunk;
    }

    private Chunk e(int i, int j) {
        if (this.d == null) {
            return null;
        } else {
            try {
                Chunk chunk = this.d.a(this.g, i, j);

                if (chunk != null) {
                    chunk.t = this.g.getTime();
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
                chunk.t = this.g.getTime();
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

            if (flag && !chunk.r) {
                this.a(chunk);
            }

            if (chunk.a(flag)) {
                this.b(chunk);
                chunk.q = false;
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
        int i;

        for (i = 0; i < 100; ++i) {
            if (!this.a.isEmpty()) {
                Long olong = (Long) this.a.iterator().next();
                Chunk chunk = (Chunk) this.e.getEntry(olong.longValue());

                chunk.removeEntities();
                this.b(chunk);
                this.a(chunk);
                this.a.remove(olong);
                this.e.remove(olong.longValue());
                this.f.remove(chunk);
            }
        }

        for (i = 0; i < 10; ++i) {
            if (this.h >= this.f.size()) {
                this.h = 0;
                break;
            }

            Chunk chunk1 = (Chunk) this.f.get(this.h++);
            EntityHuman entityhuman = this.g.findNearbyPlayer((double) (chunk1.x << 4) + 8.0D, 64.0D, (double) (chunk1.z << 4) + 8.0D, 288.0D);

            if (entityhuman == null) {
                this.d(chunk1.x, chunk1.z);
            }
        }

        if (this.d != null) {
            this.d.a();
        }

        return this.c.unloadChunks();
    }

    public boolean canSave() {
        return true;
    }

    public List a(EnumCreatureType enumcreaturetype, int i, int j, int k) {
        return this.c.a(enumcreaturetype, i, j, k);
    }

    public ChunkPosition a(World world, String s, int i, int j, int k) {
        return this.c.a(world, s, i, j, k);
    }
}
