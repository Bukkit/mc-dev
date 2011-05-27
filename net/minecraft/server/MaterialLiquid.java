package net.minecraft.server;

public class MaterialLiquid extends Material {

    public MaterialLiquid() {
        this.f();
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
