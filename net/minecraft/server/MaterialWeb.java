package net.minecraft.server;

final class MaterialWeb extends Material {

    MaterialWeb(MaterialMapColor materialmapcolor) {
        super(materialmapcolor);
    }

    public boolean isSolid() {
        return false;
    }
}
