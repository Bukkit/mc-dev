package net.minecraft.server;

public interface IBlockAccess {

    Block getType(int i, int j, int k);

    TileEntity getTileEntity(int i, int j, int k);

    int getData(int i, int j, int k);

    Vec3DPool getVec3DPool();

    int getBlockPower(int i, int j, int k, int l);
}
