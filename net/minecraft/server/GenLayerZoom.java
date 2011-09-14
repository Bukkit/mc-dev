package net.minecraft.server;

public class GenLayerZoom extends GenLayer {

    public GenLayerZoom(long i, GenLayer genlayer) {
        super(i);
        super.a = genlayer;
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
                aint1[l2++ + i2] = this.a(i3, j3);
                aint1[l2] = this.a(i3, l3);
                aint1[l2++ + i2] = this.b(i3, l3, j3, i4);
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

    protected int a(int i, int j) {
        return this.a(2) == 0 ? i : j;
    }

    protected int b(int i, int j, int k, int l) {
        if (j == k && k == l) {
            return j;
        } else if (i == j && i == k) {
            return i;
        } else if (i == j && i == l) {
            return i;
        } else if (i == k && i == l) {
            return i;
        } else if (i == j && k != l) {
            return i;
        } else if (i == k && j != l) {
            return i;
        } else if (i == l && j != k) {
            return i;
        } else if (j == i && k != l) {
            return j;
        } else if (j == k && i != l) {
            return j;
        } else if (j == l && i != k) {
            return j;
        } else if (k == i && j != l) {
            return k;
        } else if (k == j && i != l) {
            return k;
        } else if (k == l && i != j) {
            return k;
        } else if (l == i && j != k) {
            return k;
        } else if (l == j && i != k) {
            return k;
        } else if (l == k && i != j) {
            return k;
        } else {
            int i1 = this.a(4);

            return i1 == 0 ? i : (i1 == 1 ? j : (i1 == 2 ? k : l));
        }
    }

    public static GenLayer a(long i, GenLayer genlayer, int j) {
        Object object = genlayer;

        for (int k = 0; k < j; ++k) {
            object = new GenLayerZoom(i + (long) k, (GenLayer) object);
        }

        return (GenLayer) object;
    }
}
