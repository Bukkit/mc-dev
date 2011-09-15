package net.minecraft.server;

public class PlayerList {

    private transient PlayerListEntry[] entries = new PlayerListEntry[16];
    private transient int count;
    private int c = 12;
    private final float d = 0.75F;
    private transient volatile int e;

    public PlayerList() {}

    private static int g(long i) {
        return a((int) (i ^ i >>> 32));
    }

    private static int a(int i) {
        i ^= i >>> 20 ^ i >>> 12;
        return i ^ i >>> 7 ^ i >>> 4;
    }

    private static int a(int i, int j) {
        return i & j - 1;
    }

    public Object getEntry(long i) {
        int j = g(i);

        for (PlayerListEntry playerlistentry = this.entries[a(j, this.entries.length)]; playerlistentry != null; playerlistentry = playerlistentry.c) {
            if (playerlistentry.a == i) {
                return playerlistentry.b;
            }
        }

        return null;
    }

    public boolean contains(long i) {
        return this.c(i) != null;
    }

    final PlayerListEntry c(long i) {
        int j = g(i);

        for (PlayerListEntry playerlistentry = this.entries[a(j, this.entries.length)]; playerlistentry != null; playerlistentry = playerlistentry.c) {
            if (playerlistentry.a == i) {
                return playerlistentry;
            }
        }

        return null;
    }

    public void put(long i, Object object) {
        int j = g(i);
        int k = a(j, this.entries.length);

        for (PlayerListEntry playerlistentry = this.entries[k]; playerlistentry != null; playerlistentry = playerlistentry.c) {
            if (playerlistentry.a == i) {
                playerlistentry.b = object;
            }
        }

        ++this.e;
        this.a(j, i, object, k);
    }

    private void b(int i) {
        PlayerListEntry[] aplayerlistentry = this.entries;
        int j = aplayerlistentry.length;

        if (j == 1073741824) {
            this.c = Integer.MAX_VALUE;
        } else {
            PlayerListEntry[] aplayerlistentry1 = new PlayerListEntry[i];

            this.a(aplayerlistentry1);
            this.entries = aplayerlistentry1;
            this.c = (int) ((float) i * this.d);
        }
    }

    private void a(PlayerListEntry[] aplayerlistentry) {
        PlayerListEntry[] aplayerlistentry1 = this.entries;
        int i = aplayerlistentry.length;

        for (int j = 0; j < aplayerlistentry1.length; ++j) {
            PlayerListEntry playerlistentry = aplayerlistentry1[j];

            if (playerlistentry != null) {
                aplayerlistentry1[j] = null;

                PlayerListEntry playerlistentry1;

                do {
                    playerlistentry1 = playerlistentry.c;
                    int k = a(playerlistentry.d, i);

                    playerlistentry.c = aplayerlistentry[k];
                    aplayerlistentry[k] = playerlistentry;
                    playerlistentry = playerlistentry1;
                } while (playerlistentry1 != null);
            }
        }
    }

    public Object remove(long i) {
        PlayerListEntry playerlistentry = this.e(i);

        return playerlistentry == null ? null : playerlistentry.b;
    }

    final PlayerListEntry e(long i) {
        int j = g(i);
        int k = a(j, this.entries.length);
        PlayerListEntry playerlistentry = this.entries[k];

        PlayerListEntry playerlistentry1;
        PlayerListEntry playerlistentry2;

        for (playerlistentry1 = playerlistentry; playerlistentry1 != null; playerlistentry1 = playerlistentry2) {
            playerlistentry2 = playerlistentry1.c;
            if (playerlistentry1.a == i) {
                ++this.e;
                --this.count;
                if (playerlistentry == playerlistentry1) {
                    this.entries[k] = playerlistentry2;
                } else {
                    playerlistentry.c = playerlistentry2;
                }

                return playerlistentry1;
            }

            playerlistentry = playerlistentry1;
        }

        return playerlistentry1;
    }

    private void a(int i, long j, Object object, int k) {
        PlayerListEntry playerlistentry = this.entries[k];

        this.entries[k] = new PlayerListEntry(i, j, object, playerlistentry);
        if (this.count++ >= this.c) {
            this.b(2 * this.entries.length);
        }
    }

    static int f(long i) {
        return g(i);
    }
}
