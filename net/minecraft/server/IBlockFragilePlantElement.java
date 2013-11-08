package net.minecraft.server;

import java.util.Random;

public interface IBlockFragilePlantElement {

    boolean a(World world, int i, int j, int k, boolean flag);

    boolean a(World world, Random random, int i, int j, int k);

    void b(World world, Random random, int i, int j, int k);
}
