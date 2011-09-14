package net.minecraft.server;

public class BiomeHell extends BiomeBase {

    public BiomeHell(int i) {
        super(i);
        this.v.clear();
        this.w.clear();
        this.x.clear();
        this.v.add(new BiomeMeta(EntityGhast.class, 10, 4, 4));
        this.v.add(new BiomeMeta(EntityPigZombie.class, 10, 4, 4));
    }
}
