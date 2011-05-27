package net.minecraft.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChunkProviderServer implements IChunkProvider {

    private Set unloadQueue = new HashSet();
    private Chunk emptyChunk;
    private IChunkProvider chunkProvider;
    private IChunkLoader d;
    private Map chunks = new HashMap();
    private List chunkList = new ArrayList();
    private WorldServer world;

    public ChunkProviderServer(WorldServer worldserver, IChunkLoader ichunkloader, IChunkProvider ichunkprovider) {
        this.emptyChunk = new EmptyChunk(worldserver, new byte['\u8000'], 0, 0);
        this.world = worldserver;
        this.d = ichunkloader;
        this.chunkProvider = ichunkprovider;
    }

    public boolean isChunkLoaded(int i, int j) {
        return this.chunks.containsKey(Integer.valueOf(ChunkCoordIntPair.a(i, j)));
    }

    public void queueUnload(int i, int j) {
        ChunkCoordinates chunkcoordinates = this.world.getSpawn();
        int k = i * 16 + 8 - chunkcoordinates.x;
        int l = j * 16 + 8 - chunkcoordinates.z;
        short short1 = 128;

        if (k < -short1 || k > short1 || l < -short1 || l > short1) {
            this.unloadQueue.add(Integer.valueOf(ChunkCoordIntPair.a(i, j)));
        }
    }

    public Chunk getChunkAt(int i, int j) {
        int k = ChunkCoordIntPair.a(i, j);

        this.unloadQueue.remove(Integer.valueOf(k));
        Chunk chunk = (Chunk) this.chunks.get(Integer.valueOf(k));

        if (chunk == null) {
            chunk = this.loadChunk(i, j);
            if (chunk == null) {
                if (this.chunkProvider == null) {
                    chunk = this.emptyChunk;
                } else {
                    chunk = this.chunkProvider.getOrCreateChunk(i, j);
                }
            }

            this.chunks.put(Integer.valueOf(k), chunk);
            this.chunkList.add(chunk);
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
        Chunk chunk = (Chunk) this.chunks.get(Integer.valueOf(ChunkCoordIntPair.a(i, j)));

        return chunk == null ? (this.world.isLoading ? this.getChunkAt(i, j) : this.emptyChunk) : chunk;
    }

    private Chunk loadChunk(int i, int j) {
        if (this.d == null) {
            return null;
        } else {
            try {
                Chunk chunk = this.d.a(this.world, i, j);

                if (chunk != null) {
                    chunk.r = this.world.getTime();
                }

                return chunk;
            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }
    }

    private void saveChunkNOP(Chunk chunk) {
        if (this.d != null) {
            try {
                this.d.b(this.world, chunk);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void saveChunk(Chunk chunk) {
        if (this.d != null) {
            try {
                chunk.r = this.world.getTime();
                this.d.a(this.world, chunk);
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }

    public void getChunkAt(IChunkProvider ichunkprovider, int i, int j) {
        Chunk chunk = this.getOrCreateChunk(i, j);

        if (!chunk.done) {
            chunk.done = true;
            if (this.chunkProvider != null) {
                this.chunkProvider.getChunkAt(ichunkprovider, i, j);
                chunk.f();
            }
        }
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
        int i = 0;

        for (int j = 0; j < this.chunkList.size(); ++j) {
            Chunk chunk = (Chunk) this.chunkList.get(j);

            if (flag && !chunk.p) {
                this.saveChunkNOP(chunk);
            }

            if (chunk.a(flag)) {
                this.saveChunk(chunk);
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
        if (!this.world.w) {
            for (int i = 0; i < 100; ++i) {
                if (!this.unloadQueue.isEmpty()) {
                    Integer integer = (Integer) this.unloadQueue.iterator().next();
                    Chunk chunk = (Chunk) this.chunks.get(integer);

                    chunk.removeEntities();
                    this.saveChunk(chunk);
                    this.saveChunkNOP(chunk);
                    this.unloadQueue.remove(integer);
                    this.chunks.remove(integer);
                    this.chunkList.remove(chunk);
                }
            }

            if (this.d != null) {
                this.d.a();
            }
        }

        return this.chunkProvider.unloadChunks();
    }

    public boolean b() {
        return !this.world.w;
    }
}
