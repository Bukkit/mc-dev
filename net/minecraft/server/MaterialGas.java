package net.minecraft.server;

public class MaterialGas extends Material {

    public MaterialGas(MaterialMapColor materialmapcolor) {
        super(materialmapcolor);
        this.i();
    }

    public boolean isBuildable() {
        return false;
    }

    public boolean blocksLight() {
        return false;
    }

    public boolean isSolid() {
        return false;
    }
}
