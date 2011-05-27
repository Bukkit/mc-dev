package net.minecraft.server;

import java.util.Random;

public class BlockSand extends Block {

    public static boolean a = false;

    public BlockSand(int i, int j) {
        super(i, j, Material.m);
    }

    public void e(World world, int i, int j, int k) {
        world.h(i, j, k, this.bh);
    }

    public void b(World world, int i, int j, int k, int l) {
        world.h(i, j, k, this.bh);
    }

    public void a(World world, int i, int j, int k, Random random) {
        this.h(world, i, j, k);
    }

    private void h(World world, int i, int j, int k) {
        if (g(world, i, j - 1, k) && j >= 0) {
            EntityFallingSand entityfallingsand = new EntityFallingSand(world, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), this.bh);

            if (a) {
                while (!entityfallingsand.G) {
                    entityfallingsand.b_();
                }
            } else {
                world.a((Entity) entityfallingsand);
            }
        }
    }

    public int b() {
        return 3;
    }

    public static boolean g(World world, int i, int j, int k) {
        int l = world.a(i, j, k);

        if (l == 0) {
            return true;
        } else if (l == Block.FIRE.bh) {
            return true;
        } else {
            Material material = Block.m[l].bs;

            return material == Material.f ? true : material == Material.g;
        }
    }
}
