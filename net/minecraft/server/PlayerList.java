package net.minecraft.server;

public class PlayerList {

    private transient PlayerListEntry[] a = new PlayerListEntry[16];
    private transient int b;
    private int c = 12;
    private final float d = 0.75F;
    private transient volatile int e;

    public PlayerList() {}

    private static int e(long i) {
        return a((int) (i ^ i >>> 32));
    }

    private static int a(int i) {
        i ^= i >>> 20 ^ i >>> 12;
        return i ^ i >>> 7 ^ i >>> 4;
    }

    private static int a(int i, int j) {
        return i & j - 1;
    }

    public Object a(long i) {
        int j = e(i);

        for (PlayerListEntry playerlistentry = this.a[a(j, this.a.length)]; playerlistentry != null; playerlistentry = playerlistentry.c) {
            if (playerlistentry.a == i) {
                return playerlistentry.b;
            }
        }

        return null;
    }

    public void a(long i, Object object) {
        int j = e(i);
        int k = a(j, this.a.length);

        for (PlayerListEntry playerlistentry = this.a[k]; playerlistentry != null; playerlistentry = playerlistentry.c) {
            if (playerlistentry.a == i) {
                playerlistentry.b = object;
            }
        }

        ++this.e;
        this.a(j, i, object, k);
    }

    private void b(int i) {
        PlayerListEntry[] aplayerlistentry = this.a;
        int j = aplayerlistentry.length;

        if (j == 1073741824) {
            this.c = Integer.MAX_VALUE;
        } else {
            PlayerListEntry[] aplayerlistentry1 = new PlayerListEntry[i];

            this.a(aplayerlistentry1);
            this.a = aplayerlistentry1;
            this.c = (int) ((float) i * this.d);
        }
    }

    private void a(PlayerListEntry[] aplayerlistentry) {
        PlayerListEntry[] aplayerlistentry1 = this.a;
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

    public Object b(long i) {
        PlayerListEntry playerlistentry = this.c(i);

        return playerlistentry == null ? null : playerlistentry.b;
    }

    final PlayerListEntry c(long i) {
        int j = e(i);
        int k = a(j, this.a.length);
        PlayerListEntry playerlistentry = this.a[k];

        PlayerListEntry playerlistentry1;
        PlayerListEntry playerlistentry2;

        for (playerlistentry1 = playerlistentry; playerlistentry1 != null; playerlistentry1 = playerlistentry2) {
            playerlistentry2 = playerlistentry1.c;
            if (playerlistentry1.a == i) {
                ++this.e;
                --this.b;
                if (playerlistentry == playerlistentry1) {
                    this.a[k] = playerlistentry2;
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
        PlayerListEntry playerlistentry = this.a[k];

        this.a[k] = new PlayerListEntry(i, j, object, playerlistentry);
        if (this.b++ >= this.c) {
            this.b(2 * this.a.length);
        }
    }

    static int d(long i) {
        return e(i);
    }
}
