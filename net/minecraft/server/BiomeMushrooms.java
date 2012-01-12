package net.minecraft.server;

public class BiomeMushrooms extends BiomeBase {

    public BiomeMushrooms(int i) {
        super(i);
        this.G.z = -100;
        this.G.A = -100;
        this.G.B = -100;
        this.G.D = 1;
        this.G.J = 1;
        this.y = (byte) Block.MYCEL.id;
        this.H.clear();
        this.I.clear();
        this.J.clear();
        this.I.add(new BiomeMeta(EntityMushroomCow.class, 8, 4, 8));
    }
}
