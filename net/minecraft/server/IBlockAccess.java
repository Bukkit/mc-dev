package net.minecraft.server;

public interface IBlockAccess {

    int getTypeId(int i, int j, int k);

    TileEntity getTileEntity(int i, int j, int k);

    int getData(int i, int j, int k);

    Material getMaterial(int i, int j, int k);

    boolean t(int i, int j, int k);

    Vec3DPool getVec3DPool();

    boolean isBlockFacePowered(int i, int j, int k, int l);
}
