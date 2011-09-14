package net.minecraft.server;

public class GenLayerSmoothZoom extends GenLayer {

    public GenLayerSmoothZoom(long i, GenLayer genlayer) {
        super(i);
        this.a = genlayer;
    }

    public int[] a(int i, int j, int k, int l) {
        int i1 = i >> 1;
        int j1 = j >> 1;
        int k1 = (k >> 1) + 3;
        int l1 = (l >> 1) + 3;
        int[] aint = this.a.a(i1, j1, k1, l1);
        int[] aint1 = IntCache.a(k1 * 2 * l1 * 2);
        int i2 = k1 << 1;

        int j2;

        for (int k2 = 0; k2 < l1 - 1; ++k2) {
            j2 = k2 << 1;
            int l2 = j2 * i2;
            int i3 = aint[0 + (k2 + 0) * k1];
            int j3 = aint[0 + (k2 + 1) * k1];

            for (int k3 = 0; k3 < k1 - 1; ++k3) {
                this.a((long) (k3 + i1 << 1), (long) (k2 + j1 << 1));
                int l3 = aint[k3 + 1 + (k2 + 0) * k1];
                int i4 = aint[k3 + 1 + (k2 + 1) * k1];

                aint1[l2] = i3;
                aint1[l2++ + i2] = i3 + (j3 - i3) * this.a(256) / 256;
                aint1[l2] = i3 + (l3 - i3) * this.a(256) / 256;
                int j4 = i3 + (l3 - i3) * this.a(256) / 256;
                int k4 = j3 + (i4 - j3) * this.a(256) / 256;

                aint1[l2++ + i2] = j4 + (k4 - j4) * this.a(256) / 256;
                i3 = l3;
                j3 = i4;
            }
        }

        int[] aint2 = IntCache.a(k * l);

        for (j2 = 0; j2 < l; ++j2) {
            System.arraycopy(aint1, (j2 + (j & 1)) * (k1 << 1) + (i & 1), aint2, j2 * k, k);
        }

        return aint2;
    }

    public static GenLayer a(long i, GenLayer genlayer, int j) {
        Object object = genlayer;

        for (int k = 0; k < j; ++k) {
            object = new GenLayerSmoothZoom(i + (long) k, (GenLayer) object);
        }

        return (GenLayer) object;
    }
}
