package net.minecraft.server;

public class MaterialLogic extends Material {

    public MaterialLogic() {}

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
