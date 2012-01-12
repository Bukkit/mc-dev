package net.minecraft.server;

public class BiomeHell extends BiomeBase {

    public BiomeHell(int i) {
        super(i);
        this.H.clear();
        this.I.clear();
        this.J.clear();
        this.H.add(new BiomeMeta(EntityGhast.class, 50, 4, 4));
        this.H.add(new BiomeMeta(EntityPigZombie.class, 100, 4, 4));
        this.H.add(new BiomeMeta(EntityMagmaCube.class, 1, 4, 4));
    }
}
