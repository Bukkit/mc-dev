package net.minecraft.server;

public class WorldGenMineshaft extends StructureGenerator {

    public WorldGenMineshaft() {}

    protected boolean a(int i, int j) {
        return this.c.nextInt(100) == 0 && this.c.nextInt(80) < Math.max(Math.abs(i), Math.abs(j));
    }

    protected StructureStart b(int i, int j) {
        return new WorldGenMineshaftStart(this.d, this.c, i, j);
    }
}
