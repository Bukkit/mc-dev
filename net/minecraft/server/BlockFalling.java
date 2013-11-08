package net.minecraft.server;

import java.util.Random;

public class BlockFalling extends Block {

    public static boolean instaFall;

    public BlockFalling() {
        super(Material.SAND);
        this.a(CreativeModeTab.b);
    }

    public BlockFalling(Material material) {
        super(material);
    }

    public void onPlace(World world, int i, int j, int k) {
        world.a(i, j, k, this, this.a(world));
    }

    public void doPhysics(World world, int i, int j, int k, Block block) {
        world.a(i, j, k, this, this.a(world));
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (!world.isStatic) {
            this.m(world, i, j, k);
        }
    }

    private void m(World world, int i, int j, int k) {
        if (canFall(world, i, j - 1, k) && j >= 0) {
            byte b0 = 32;

            if (!instaFall && world.b(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0)) {
                if (!world.isStatic) {
                    EntityFallingBlock entityfallingblock = new EntityFallingBlock(world, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), this, world.getData(i, j, k));

                    this.a(entityfallingblock);
                    world.addEntity(entityfallingblock);
                }
            } else {
                world.setAir(i, j, k);

                while (canFall(world, i, j - 1, k) && j > 0) {
                    --j;
                }

                if (j > 0) {
                    world.setTypeUpdate(i, j, k, this);
                }
            }
        }
    }

    protected void a(EntityFallingBlock entityfallingblock) {}

    public int a(World world) {
        return 2;
    }

    public static boolean canFall(World world, int i, int j, int k) {
        Block block = world.getType(i, j, k);

        if (block.material == Material.AIR) {
            return true;
        } else if (block == Blocks.FIRE) {
            return true;
        } else {
            Material material = block.material;

            return material == Material.WATER ? true : material == Material.LAVA;
        }
    }

    public void a(World world, int i, int j, int k, int l) {}
}
