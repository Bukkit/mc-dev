package net.minecraft.server;

public class WorldMapHumanTracker {

    public final EntityHuman trackee;
    public int[] b;
    public int[] c;
    private int e;
    private int f;
    private byte[] g;

    final WorldMap d;

    public WorldMapHumanTracker(WorldMap worldmap, EntityHuman entityhuman) {
        this.d = worldmap;
        this.b = new int[128];
        this.c = new int[128];
        this.e = 0;
        this.f = 0;
        this.trackee = entityhuman;

        for (int i = 0; i < this.b.length; ++i) {
            this.b[i] = 0;
            this.c[i] = 127;
        }
    }

    public byte[] a(ItemStack itemstack) {
        int i;
        int j;

        if (--this.f < 0) {
            this.f = 4;
            byte[] abyte = new byte[this.d.decorations.size() * 3 + 1];

            abyte[0] = 1;

            for (i = 0; i < this.d.decorations.size(); ++i) {
                WorldMapDecoration worldmapdecoration = (WorldMapDecoration) this.d.decorations.get(i);

                abyte[i * 3 + 1] = (byte) (worldmapdecoration.type + (worldmapdecoration.rotation & 15) * 16);
                abyte[i * 3 + 2] = worldmapdecoration.locX;
                abyte[i * 3 + 3] = worldmapdecoration.locY;
            }

            boolean flag = true;

            if (this.g != null && this.g.length == abyte.length) {
                for (j = 0; j < abyte.length; ++j) {
                    if (abyte[j] != this.g[j]) {
                        flag = false;
                        break;
                    }
                }
            } else {
                flag = false;
            }

            if (!flag) {
                this.g = abyte;
                return abyte;
            }
        }

        for (int k = 0; k < 10; ++k) {
            i = this.e * 11 % 128;
            ++this.e;
            if (this.b[i] >= 0) {
                j = this.c[i] - this.b[i] + 1;
                int l = this.b[i];
                byte[] abyte1 = new byte[j + 3];

                abyte1[0] = 0;
                abyte1[1] = (byte) i;
                abyte1[2] = (byte) l;

                for (int i1 = 0; i1 < abyte1.length - 3; ++i1) {
                    abyte1[i1 + 3] = this.d.colors[(i1 + l) * 128 + i];
                }

                this.c[i] = -1;
                this.b[i] = -1;
                return abyte1;
            }
        }

        return null;
    }
}
