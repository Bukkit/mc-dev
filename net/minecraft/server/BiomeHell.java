package net.minecraft.server;

public class BiomeHell extends BiomeBase {

    public BiomeHell() {
        this.r.clear();
        this.s.clear();
        this.t.clear();
        this.r.add(new BiomeMeta(EntityGhast.class, 10));
        this.r.add(new BiomeMeta(EntityPigZombie.class, 10));
    }
}
