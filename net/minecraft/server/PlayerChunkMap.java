package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerChunkMap {

    private static final Logger a = LogManager.getLogger();
    private final WorldServer world;
    private final List managedPlayers = new ArrayList();
    private final LongHashMap d = new LongHashMap();
    private final List e = new ArrayList();
    private final List f = new ArrayList();
    private int g;
    private long h;
    private final int[][] i = new int[][] { { 1, 0}, { 0, 1}, { -1, 0}, { 0, -1}};

    public PlayerChunkMap(WorldServer worldserver) {
        this.world = worldserver;
        this.a(worldserver.getMinecraftServer().getPlayerList().s());
    }

    public WorldServer a() {
        return this.world;
    }

    public void flush() {
        long i = this.world.getTime();
        int j;
        PlayerChunk playerchunk;

        if (i - this.h > 8000L) {
            this.h = i;

            for (j = 0; j < this.f.size(); ++j) {
                playerchunk = (PlayerChunk) this.f.get(j);
                playerchunk.b();
                playerchunk.a();
            }
        } else {
            for (j = 0; j < this.e.size(); ++j) {
                playerchunk = (PlayerChunk) this.e.get(j);
                playerchunk.b();
            }
        }

        this.e.clear();
        if (this.managedPlayers.isEmpty()) {
            WorldProvider worldprovider = this.world.worldProvider;

            if (!worldprovider.e()) {
                this.world.chunkProviderServer.b();
            }
        }
    }

    public boolean a(int i, int j) {
        long k = (long) i + 2147483647L | (long) j + 2147483647L << 32;

        return this.d.getEntry(k) != null;
    }

    private PlayerChunk a(int i, int j, boolean flag) {
        long k = (long) i + 2147483647L | (long) j + 2147483647L << 32;
        PlayerChunk playerchunk = (PlayerChunk) this.d.getEntry(k);

        if (playerchunk == null && flag) {
            playerchunk = new PlayerChunk(this, i, j);
            this.d.put(k, playerchunk);
            this.f.add(playerchunk);
        }

        return playerchunk;
    }

    public void flagDirty(int i, int j, int k) {
        int l = i >> 4;
        int i1 = k >> 4;
        PlayerChunk playerchunk = this.a(l, i1, false);

        if (playerchunk != null) {
            playerchunk.a(i & 15, j, k & 15);
        }
    }

    public void addPlayer(EntityPlayer entityplayer) {
        int i = (int) entityplayer.locX >> 4;
        int j = (int) entityplayer.locZ >> 4;

        entityplayer.d = entityplayer.locX;
        entityplayer.e = entityplayer.locZ;

        for (int k = i - this.g; k <= i + this.g; ++k) {
            for (int l = j - this.g; l <= j + this.g; ++l) {
                this.a(k, l, true).a(entityplayer);
            }
        }

        this.managedPlayers.add(entityplayer);
        this.b(entityplayer);
    }

    public void b(EntityPlayer entityplayer) {
        ArrayList arraylist = new ArrayList(entityplayer.chunkCoordIntPairQueue);
        int i = 0;
        int j = this.g;
        int k = (int) entityplayer.locX >> 4;
        int l = (int) entityplayer.locZ >> 4;
        int i1 = 0;
        int j1 = 0;
        ChunkCoordIntPair chunkcoordintpair = PlayerChunk.a(this.a(k, l, true));

        entityplayer.chunkCoordIntPairQueue.clear();
        if (arraylist.contains(chunkcoordintpair)) {
            entityplayer.chunkCoordIntPairQueue.add(chunkcoordintpair);
        }

        int k1;

        for (k1 = 1; k1 <= j * 2; ++k1) {
            for (int l1 = 0; l1 < 2; ++l1) {
                int[] aint = this.i[i++ % 4];

                for (int i2 = 0; i2 < k1; ++i2) {
                    i1 += aint[0];
                    j1 += aint[1];
                    chunkcoordintpair = PlayerChunk.a(this.a(k + i1, l + j1, true));
                    if (arraylist.contains(chunkcoordintpair)) {
                        entityplayer.chunkCoordIntPairQueue.add(chunkcoordintpair);
                    }
                }
            }
        }

        i %= 4;

        for (k1 = 0; k1 < j * 2; ++k1) {
            i1 += this.i[i][0];
            j1 += this.i[i][1];
            chunkcoordintpair = PlayerChunk.a(this.a(k + i1, l + j1, true));
            if (arraylist.contains(chunkcoordintpair)) {
                entityplayer.chunkCoordIntPairQueue.add(chunkcoordintpair);
            }
        }
    }

    public void removePlayer(EntityPlayer entityplayer) {
        int i = (int) entityplayer.d >> 4;
        int j = (int) entityplayer.e >> 4;

        for (int k = i - this.g; k <= i + this.g; ++k) {
            for (int l = j - this.g; l <= j + this.g; ++l) {
                PlayerChunk playerchunk = this.a(k, l, false);

                if (playerchunk != null) {
                    playerchunk.b(entityplayer);
                }
            }
        }

        this.managedPlayers.remove(entityplayer);
    }

    private boolean a(int i, int j, int k, int l, int i1) {
        int j1 = i - k;
        int k1 = j - l;

        return j1 >= -i1 && j1 <= i1 ? k1 >= -i1 && k1 <= i1 : false;
    }

    public void movePlayer(EntityPlayer entityplayer) {
        int i = (int) entityplayer.locX >> 4;
        int j = (int) entityplayer.locZ >> 4;
        double d0 = entityplayer.d - entityplayer.locX;
        double d1 = entityplayer.e - entityplayer.locZ;
        double d2 = d0 * d0 + d1 * d1;

        if (d2 >= 64.0D) {
            int k = (int) entityplayer.d >> 4;
            int l = (int) entityplayer.e >> 4;
            int i1 = this.g;
            int j1 = i - k;
            int k1 = j - l;

            if (j1 != 0 || k1 != 0) {
                for (int l1 = i - i1; l1 <= i + i1; ++l1) {
                    for (int i2 = j - i1; i2 <= j + i1; ++i2) {
                        if (!this.a(l1, i2, k, l, i1)) {
                            this.a(l1, i2, true).a(entityplayer);
                        }

                        if (!this.a(l1 - j1, i2 - k1, i, j, i1)) {
                            PlayerChunk playerchunk = this.a(l1 - j1, i2 - k1, false);

                            if (playerchunk != null) {
                                playerchunk.b(entityplayer);
                            }
                        }
                    }
                }

                this.b(entityplayer);
                entityplayer.d = entityplayer.locX;
                entityplayer.e = entityplayer.locZ;
            }
        }
    }

    public boolean a(EntityPlayer entityplayer, int i, int j) {
        PlayerChunk playerchunk = this.a(i, j, false);

        return playerchunk != null && PlayerChunk.b(playerchunk).contains(entityplayer) && !entityplayer.chunkCoordIntPairQueue.contains(PlayerChunk.a(playerchunk));
    }

    public void a(int i) {
        i = MathHelper.a(i, 3, 20);
        if (i != this.g) {
            int j = i - this.g;
            Iterator iterator = this.managedPlayers.iterator();

            while (iterator.hasNext()) {
                EntityPlayer entityplayer = (EntityPlayer) iterator.next();
                int k = (int) entityplayer.locX >> 4;
                int l = (int) entityplayer.locZ >> 4;
                int i1;
                int j1;

                if (j > 0) {
                    for (i1 = k - i; i1 <= k + i; ++i1) {
                        for (j1 = l - i; j1 <= l + i; ++j1) {
                            PlayerChunk playerchunk = this.a(i1, j1, true);

                            if (!PlayerChunk.b(playerchunk).contains(entityplayer)) {
                                playerchunk.a(entityplayer);
                            }
                        }
                    }
                } else {
                    for (i1 = k - this.g; i1 <= k + this.g; ++i1) {
                        for (j1 = l - this.g; j1 <= l + this.g; ++j1) {
                            if (!this.a(i1, j1, k, l, i)) {
                                this.a(i1, j1, true).b(entityplayer);
                            }
                        }
                    }
                }
            }

            this.g = i;
        }
    }

    public static int getFurthestViewableBlock(int i) {
        return i * 16 - 16;
    }

    static Logger c() {
        return a;
    }

    static WorldServer a(PlayerChunkMap playerchunkmap) {
        return playerchunkmap.world;
    }

    static LongHashMap b(PlayerChunkMap playerchunkmap) {
        return playerchunkmap.d;
    }

    static List c(PlayerChunkMap playerchunkmap) {
        return playerchunkmap.f;
    }

    static List d(PlayerChunkMap playerchunkmap) {
        return playerchunkmap.e;
    }
}
