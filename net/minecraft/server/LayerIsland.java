package net.minecraft.server;

public class LayerIsland extends GenLayer {

    public LayerIsland(long i) {
        super(i);
    }

    public int[] a(int i, int j, int k, int l) {
        int[] aint = IntCache.a(k * l);

        for (int i1 = 0; i1 < l; ++i1) {
            for (int j1 = 0; j1 < k; ++j1) {
                this.a((long) (i + j1), (long) (j + i1));
                aint[j1 + i1 * k] = this.a(10) == 0 ? 1 : 0;
            }
        }

        if (i > -k && i <= 0 && j > -l && j <= 0) {
            aint[-i + -j * k] = 1;
        }

        return aint;
    }
}
