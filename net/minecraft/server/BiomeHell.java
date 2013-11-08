package net.minecraft.server;

public class BiomeHell extends BiomeBase {

    public BiomeHell(int i) {
        super(i);
        this.as.clear();
        this.at.clear();
        this.au.clear();
        this.av.clear();
        this.as.add(new BiomeMeta(EntityGhast.class, 50, 4, 4));
        this.as.add(new BiomeMeta(EntityPigZombie.class, 100, 4, 4));
        this.as.add(new BiomeMeta(EntityMagmaCube.class, 1, 4, 4));
    }
}
