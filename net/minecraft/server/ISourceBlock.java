package net.minecraft.server;

public interface ISourceBlock extends ILocationSource {

    double getX();

    double getY();

    double getZ();

    int getBlockX();

    int getBlockY();

    int getBlockZ();

    int h();

    TileEntity getTileEntity();
}
