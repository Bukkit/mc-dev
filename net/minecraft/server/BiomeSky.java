package net.minecraft.server;

public class BiomeSky extends BiomeBase {

    public BiomeSky(int i) {
        super(i);
        this.v.clear();
        this.w.clear();
        this.x.clear();
        this.w.add(new BiomeMeta(EntityChicken.class, 10, 4, 4));
    }
}
