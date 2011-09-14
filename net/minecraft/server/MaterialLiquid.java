package net.minecraft.server;

public class MaterialLiquid extends Material {

    public MaterialLiquid(MaterialMapColor materialmapcolor) {
        super(materialmapcolor);
        this.h();
        this.m();
    }

    public boolean isLiquid() {
        return true;
    }

    public boolean isSolid() {
        return false;
    }

    public boolean isBuildable() {
        return false;
    }
}
