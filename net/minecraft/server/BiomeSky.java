package net.minecraft.server;

public class BiomeSky extends BiomeBase {

    public BiomeSky() {
        this.s.clear();
        this.t.clear();
        this.u.clear();
        this.t.add(new BiomeMeta(EntityChicken.class, 10));
    }
}
