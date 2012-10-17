package net.minecraft.server;

public class SourceBlock implements ISourceBlock {

    private final World a;
    private final int b;
    private final int c;
    private final int d;

    public SourceBlock(World world, int i, int j, int k) {
        this.a = world;
        this.b = i;
        this.c = j;
        this.d = k;
    }

    public World k() {
        return this.a;
    }

    public double getX() {
        return (double) this.b + 0.5D;
    }

    public double getY() {
        return (double) this.c + 0.5D;
    }

    public double getZ() {
        return (double) this.d + 0.5D;
    }

    public int getBlockX() {
        return this.b;
    }

    public int getBlockY() {
        return this.c;
    }

    public int getBlockZ() {
        return this.d;
    }

    public int h() {
        return this.a.getData(this.b, this.c, this.d);
    }

    public TileEntity getTileEntity() {
        return this.a.getTileEntity(this.b, this.c, this.d);
    }
}
