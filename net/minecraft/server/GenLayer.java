package net.minecraft.server;

public abstract class GenLayer {

    private long b;
    protected GenLayer a;
    private long c;
    private long d;

    public static GenLayer[] a(long i, WorldType worldtype) {
        LayerIsland layerisland = new LayerIsland(1L);
        GenLayerZoomFuzzy genlayerzoomfuzzy = new GenLayerZoomFuzzy(2000L, layerisland);
        GenLayerIsland genlayerisland = new GenLayerIsland(1L, genlayerzoomfuzzy);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayerisland);

        genlayerisland = new GenLayerIsland(2L, genlayerzoom);
        GenLayerIcePlains genlayericeplains = new GenLayerIcePlains(2L, genlayerisland);

        genlayerzoom = new GenLayerZoom(2002L, genlayericeplains);
        genlayerisland = new GenLayerIsland(3L, genlayerzoom);
        genlayerzoom = new GenLayerZoom(2003L, genlayerisland);
        genlayerisland = new GenLayerIsland(4L, genlayerzoom);
        GenLayerMushroomIsland genlayermushroomisland = new GenLayerMushroomIsland(5L, genlayerisland);
        byte b0 = 4;

        if (worldtype == WorldType.LARGE_BIOMES) {
            b0 = 6;
        }

        GenLayer genlayer = GenLayerZoom.a(1000L, genlayermushroomisland, 0);
        GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);

        genlayer = GenLayerZoom.a(1000L, genlayerriverinit, b0 + 2);
        GenLayerRiver genlayerriver = new GenLayerRiver(1L, genlayer);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
        GenLayer genlayer1 = GenLayerZoom.a(1000L, genlayermushroomisland, 0);
        GenLayerBiome genlayerbiome = new GenLayerBiome(200L, genlayer1, worldtype);

        genlayer1 = GenLayerZoom.a(1000L, genlayerbiome, 2);
        Object object = new GenLayerRegionHills(1000L, genlayer1);

        for (int j = 0; j < b0; ++j) {
            object = new GenLayerZoom((long) (1000 + j), (GenLayer) object);
            if (j == 0) {
                object = new GenLayerIsland(3L, (GenLayer) object);
            }

            if (j == 1) {
                object = new GenLayerMushroomShore(1000L, (GenLayer) object);
            }

            if (j == 1) {
                object = new GenLayerSwampRivers(1000L, (GenLayer) object);
            }
        }

        GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer) object);
        GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, genlayersmooth1, genlayersmooth);
        GenLayerZoomVoronoi genlayerzoomvoronoi = new GenLayerZoomVoronoi(10L, genlayerrivermix);

        genlayerrivermix.a(i);
        genlayerzoomvoronoi.a(i);
        return new GenLayer[] { genlayerrivermix, genlayerzoomvoronoi, genlayerrivermix};
    }

    public GenLayer(long i) {
        this.d = i;
        this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
        this.d += i;
        this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
        this.d += i;
        this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
        this.d += i;
    }

    public void a(long i) {
        this.b = i;
        if (this.a != null) {
            this.a.a(i);
        }

        this.b *= this.b * 6364136223846793005L + 1442695040888963407L;
        this.b += this.d;
        this.b *= this.b * 6364136223846793005L + 1442695040888963407L;
        this.b += this.d;
        this.b *= this.b * 6364136223846793005L + 1442695040888963407L;
        this.b += this.d;
    }

    public void a(long i, long j) {
        this.c = this.b;
        this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
        this.c += i;
        this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
        this.c += j;
        this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
        this.c += i;
        this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
        this.c += j;
    }

    protected int a(int i) {
        int j = (int) ((this.c >> 24) % (long) i);

        if (j < 0) {
            j += i;
        }

        this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
        this.c += this.b;
        return j;
    }

    public abstract int[] a(int i, int j, int k, int l);
}
