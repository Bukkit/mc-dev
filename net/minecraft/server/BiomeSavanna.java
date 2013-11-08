package net.minecraft.server;

import java.util.Random;

public class BiomeSavanna extends BiomeBase {

    private static final WorldGenAcaciaTree aC = new WorldGenAcaciaTree(false);

    protected BiomeSavanna(int i) {
        super(i);
        this.at.add(new BiomeMeta(EntityHorse.class, 1, 2, 6));
        this.ar.x = 1;
        this.ar.y = 4;
        this.ar.z = 20;
    }

    public WorldGenTreeAbstract a(Random random) {
        return (WorldGenTreeAbstract) (random.nextInt(5) > 0 ? aC : this.az);
    }

    protected BiomeBase k() {
        BiomeSavannaSub biomesavannasub = new BiomeSavannaSub(this.id + 128, this);

        biomesavannasub.temperature = (this.temperature + 1.0F) * 0.5F;
        biomesavannasub.am = this.am * 0.5F + 0.3F;
        biomesavannasub.an = this.an * 0.5F + 1.2F;
        return biomesavannasub;
    }

    public void a(World world, Random random, int i, int j) {
        ae.a(2);

        for (int k = 0; k < 7; ++k) {
            int l = i + random.nextInt(16) + 8;
            int i1 = j + random.nextInt(16) + 8;
            int j1 = random.nextInt(world.getHighestBlockYAt(l, i1) + 32);

            ae.a(world, random, l, j1, i1);
        }

        super.a(world, random, i, j);
    }
}
