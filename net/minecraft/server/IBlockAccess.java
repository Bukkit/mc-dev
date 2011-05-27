package net.minecraft.server;

public interface IBlockAccess {

    int getTypeId(int i, int j, int k);

    int getData(int i, int j, int k);

    Material getMaterial(int i, int j, int k);

    boolean d(int i, int j, int k);
}
