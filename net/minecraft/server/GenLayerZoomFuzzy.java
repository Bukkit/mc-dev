package net.minecraft.server;

public class GenLayerZoomFuzzy extends GenLayerZoom {

    public GenLayerZoomFuzzy(long i, GenLayer genlayer) {
        super(i, genlayer);
    }

    protected int b(int i, int j, int k, int l) {
        return this.a(new int[] { i, j, k, l});
    }
}
