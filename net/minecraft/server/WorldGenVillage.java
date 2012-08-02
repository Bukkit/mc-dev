package net.minecraft.server;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldGenVillage extends StructureGenerator {

    public static List e = Arrays.asList(new BiomeBase[] { BiomeBase.PLAINS, BiomeBase.DESERT});
    private final int f;

    public WorldGenVillage(int i) {
        this.f = i;
    }

    protected boolean a(int i, int j) {
        byte b0 = 32;
        byte b1 = 8;
        int k = i;
        int l = j;

        if (i < 0) {
            i -= b0 - 1;
        }

        if (j < 0) {
            j -= b0 - 1;
        }

        int i1 = i / b0;
        int j1 = j / b0;
        Random random = this.c.D(i1, j1, 10387312);

        i1 *= b0;
        j1 *= b0;
        i1 += random.nextInt(b0 - b1);
        j1 += random.nextInt(b0 - b1);
        if (k == i1 && l == j1) {
            boolean flag = this.c.getWorldChunkManager().a(k * 16 + 8, l * 16 + 8, 0, e);

            if (flag) {
                return true;
            }
        }

        return false;
    }

    protected StructureStart b(int i, int j) {
        return new WorldGenVillageStart(this.c, this.b, i, j, this.f);
    }
}
