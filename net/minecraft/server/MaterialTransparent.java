package net.minecraft.server;

public class MaterialTransparent extends Material {

    public MaterialTransparent(MaterialMapColor materialmapcolor) {
        super(materialmapcolor);
        this.f();
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
