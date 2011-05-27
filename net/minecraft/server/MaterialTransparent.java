package net.minecraft.server;

public class MaterialTransparent extends Material {

    public MaterialTransparent() {
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
