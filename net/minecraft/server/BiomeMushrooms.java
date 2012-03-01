package net.minecraft.server;

public class BiomeMushrooms extends BiomeBase {

    public BiomeMushrooms(int i) {
        super(i);
        this.I.z = -100;
        this.I.A = -100;
        this.I.B = -100;
        this.I.D = 1;
        this.I.J = 1;
        this.A = (byte) Block.MYCEL.id;
        this.J.clear();
        this.K.clear();
        this.L.clear();
        this.K.add(new BiomeMeta(EntityMushroomCow.class, 8, 4, 8));
    }
}
