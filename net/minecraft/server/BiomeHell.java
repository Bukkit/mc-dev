package net.minecraft.server;

public class BiomeHell extends BiomeBase {

    public BiomeHell(int i) {
        super(i);
        this.C.clear();
        this.D.clear();
        this.E.clear();
        this.C.add(new BiomeMeta(EntityGhast.class, 50, 4, 4));
        this.C.add(new BiomeMeta(EntityPigZombie.class, 100, 4, 4));
        this.C.add(new BiomeMeta(EntityMagmaCube.class, 1, 4, 4));
    }
}
