package net.minecraft.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class WorldGenLargeFeature extends StructureGenerator {

    private static List e = Arrays.asList(new BiomeBase[] { BiomeBase.DESERT, BiomeBase.DESERT_HILLS, BiomeBase.JUNGLE, BiomeBase.JUNGLE_HILLS, BiomeBase.SWAMPLAND});
    private List f;
    private int g;
    private int h;

    public WorldGenLargeFeature() {
        this.f = new ArrayList();
        this.g = 32;
        this.h = 8;
        this.f.add(new BiomeMeta(EntityWitch.class, 1, 1, 1));
    }

    public WorldGenLargeFeature(Map map) {
        this();
        Iterator iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();

            if (((String) entry.getKey()).equals("distance")) {
                this.g = MathHelper.a((String) entry.getValue(), this.g, this.h + 1);
            }
        }
    }

    protected boolean a(int i, int j) {
        int k = i;
        int l = j;

        if (i < 0) {
            i -= this.g - 1;
        }

        if (j < 0) {
            j -= this.g - 1;
        }

        int i1 = i / this.g;
        int j1 = j / this.g;
        Random random = this.c.D(i1, j1, 14357617);

        i1 *= this.g;
        j1 *= this.g;
        i1 += random.nextInt(this.g - this.h);
        j1 += random.nextInt(this.g - this.h);
        if (k == i1 && l == j1) {
            boolean flag = this.c.getWorldChunkManager().a(k * 16 + 8, l * 16 + 8, 0, e);

            if (flag) {
                return true;
            }
        }

        return false;
    }

    protected StructureStart b(int i, int j) {
        return new WorldGenLargeFeatureStart(this.c, this.b, i, j);
    }

    public List a() {
        return this.f;
    }
}
