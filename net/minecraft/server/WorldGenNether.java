package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class WorldGenNether extends StructureGenerator {

    private List a = new ArrayList();

    public WorldGenNether() {
        this.a.add(new BiomeMeta(EntityBlaze.class, 10, 2, 3));
        this.a.add(new BiomeMeta(EntityPigZombie.class, 10, 4, 4));
        this.a.add(new BiomeMeta(EntityMagmaCube.class, 3, 4, 4));
    }

    public List b() {
        return this.a;
    }

    protected boolean a(int i, int j) {
        int k = i >> 4;
        int l = j >> 4;

        this.c.setSeed((long) (k ^ l << 4) ^ this.d.getSeed());
        this.c.nextInt();
        return this.c.nextInt(3) != 0 ? false : (i != (k << 4) + 4 + this.c.nextInt(8) ? false : j == (l << 4) + 4 + this.c.nextInt(8));
    }

    protected StructureStart b(int i, int j) {
        return new WorldGenNetherStart(this.d, this.c, i, j);
    }
}
