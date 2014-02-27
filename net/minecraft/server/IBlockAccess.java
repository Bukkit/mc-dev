package net.minecraft.server;

public interface IBlockAccess {

    Block getType(int i, int j, int k);

    TileEntity getTileEntity(int i, int j, int k);

    int getData(int i, int j, int k);

    int getBlockPower(int i, int j, int k, int l);
}
