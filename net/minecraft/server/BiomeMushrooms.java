package net.minecraft.server;

public class BiomeMushrooms extends BiomeBase {

    public BiomeMushrooms(int i) {
        super(i);
        this.B.z = -100;
        this.B.A = -100;
        this.B.B = -100;
        this.B.D = 1;
        this.B.J = 1;
        this.t = (byte) Block.MYCEL.id;
        this.C.clear();
        this.D.clear();
        this.E.clear();
        this.D.add(new BiomeMeta(EntityMushroomCow.class, 8, 4, 8));
    }
}
