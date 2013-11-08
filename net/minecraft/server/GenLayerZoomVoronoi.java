package net.minecraft.server;

public class GenLayerZoomVoronoi extends GenLayer {

    public GenLayerZoomVoronoi(long i, GenLayer genlayer) {
        super(i);
        super.a = genlayer;
    }

    public int[] a(int i, int j, int k, int l) {
        i -= 2;
        j -= 2;
        int i1 = i >> 2;
        int j1 = j >> 2;
        int k1 = (k >> 2) + 2;
        int l1 = (l >> 2) + 2;
        int[] aint = this.a.a(i1, j1, k1, l1);
        int i2 = k1 - 1 << 2;
        int j2 = l1 - 1 << 2;
        int[] aint1 = IntCache.a(i2 * j2);

        int k2;

        for (int l2 = 0; l2 < l1 - 1; ++l2) {
            k2 = 0;
            int i3 = aint[k2 + 0 + (l2 + 0) * k1];

            for (int j3 = aint[k2 + 0 + (l2 + 1) * k1]; k2 < k1 - 1; ++k2) {
                double d0 = 3.6D;

                this.a((long) (k2 + i1 << 2), (long) (l2 + j1 << 2));
                double d1 = ((double) this.a(1024) / 1024.0D - 0.5D) * 3.6D;
                double d2 = ((double) this.a(1024) / 1024.0D - 0.5D) * 3.6D;

                this.a((long) (k2 + i1 + 1 << 2), (long) (l2 + j1 << 2));
                double d3 = ((double) this.a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
                double d4 = ((double) this.a(1024) / 1024.0D - 0.5D) * 3.6D;

                this.a((long) (k2 + i1 << 2), (long) (l2 + j1 + 1 << 2));
                double d5 = ((double) this.a(1024) / 1024.0D - 0.5D) * 3.6D;
                double d6 = ((double) this.a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;

                this.a((long) (k2 + i1 + 1 << 2), (long) (l2 + j1 + 1 << 2));
                double d7 = ((double) this.a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
                double d8 = ((double) this.a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
                int k3 = aint[k2 + 1 + (l2 + 0) * k1] & 255;
                int l3 = aint[k2 + 1 + (l2 + 1) * k1] & 255;

                for (int i4 = 0; i4 < 4; ++i4) {
                    int j4 = ((l2 << 2) + i4) * i2 + (k2 << 2);

                    for (int k4 = 0; k4 < 4; ++k4) {
                        double d9 = ((double) i4 - d2) * ((double) i4 - d2) + ((double) k4 - d1) * ((double) k4 - d1);
                        double d10 = ((double) i4 - d4) * ((double) i4 - d4) + ((double) k4 - d3) * ((double) k4 - d3);
                        double d11 = ((double) i4 - d6) * ((double) i4 - d6) + ((double) k4 - d5) * ((double) k4 - d5);
                        double d12 = ((double) i4 - d8) * ((double) i4 - d8) + ((double) k4 - d7) * ((double) k4 - d7);

                        if (d9 < d10 && d9 < d11 && d9 < d12) {
                            aint1[j4++] = i3;
                        } else if (d10 < d9 && d10 < d11 && d10 < d12) {
                            aint1[j4++] = k3;
                        } else if (d11 < d9 && d11 < d10 && d11 < d12) {
                            aint1[j4++] = j3;
                        } else {
                            aint1[j4++] = l3;
                        }
                    }
                }

                i3 = k3;
                j3 = l3;
            }
        }

        int[] aint2 = IntCache.a(k * l);

        for (k2 = 0; k2 < l; ++k2) {
            System.arraycopy(aint1, (k2 + (j & 3)) * i2 + (i & 3), aint2, k2 * k, k);
        }

        return aint2;
    }
}
