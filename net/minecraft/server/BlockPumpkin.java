package net.minecraft.server;

public class BlockPumpkin extends BlockDirectional {

    private boolean a;

    protected BlockPumpkin(boolean flag) {
        super(Material.PUMPKIN);
        this.a(true);
        this.a = flag;
        this.a(CreativeModeTab.b);
    }

    public void onPlace(World world, int i, int j, int k) {
        super.onPlace(world, i, j, k);
        if (world.getType(i, j - 1, k) == Blocks.SNOW_BLOCK && world.getType(i, j - 2, k) == Blocks.SNOW_BLOCK) {
            if (!world.isStatic) {
                world.setTypeAndData(i, j, k, e(0), 0, 2);
                world.setTypeAndData(i, j - 1, k, e(0), 0, 2);
                world.setTypeAndData(i, j - 2, k, e(0), 0, 2);
                EntitySnowman entitysnowman = new EntitySnowman(world);

                entitysnowman.setPositionRotation((double) i + 0.5D, (double) j - 1.95D, (double) k + 0.5D, 0.0F, 0.0F);
                world.addEntity(entitysnowman);
                world.update(i, j, k, e(0));
                world.update(i, j - 1, k, e(0));
                world.update(i, j - 2, k, e(0));
            }

            for (int l = 0; l < 120; ++l) {
                world.addParticle("snowshovel", (double) i + world.random.nextDouble(), (double) (j - 2) + world.random.nextDouble() * 2.5D, (double) k + world.random.nextDouble(), 0.0D, 0.0D, 0.0D);
            }
        } else if (world.getType(i, j - 1, k) == Blocks.IRON_BLOCK && world.getType(i, j - 2, k) == Blocks.IRON_BLOCK) {
            boolean flag = world.getType(i - 1, j - 1, k) == Blocks.IRON_BLOCK && world.getType(i + 1, j - 1, k) == Blocks.IRON_BLOCK;
            boolean flag1 = world.getType(i, j - 1, k - 1) == Blocks.IRON_BLOCK && world.getType(i, j - 1, k + 1) == Blocks.IRON_BLOCK;

            if (flag || flag1) {
                world.setTypeAndData(i, j, k, e(0), 0, 2);
                world.setTypeAndData(i, j - 1, k, e(0), 0, 2);
                world.setTypeAndData(i, j - 2, k, e(0), 0, 2);
                if (flag) {
                    world.setTypeAndData(i - 1, j - 1, k, e(0), 0, 2);
                    world.setTypeAndData(i + 1, j - 1, k, e(0), 0, 2);
                } else {
                    world.setTypeAndData(i, j - 1, k - 1, e(0), 0, 2);
                    world.setTypeAndData(i, j - 1, k + 1, e(0), 0, 2);
                }

                EntityIronGolem entityirongolem = new EntityIronGolem(world);

                entityirongolem.setPlayerCreated(true);
                entityirongolem.setPositionRotation((double) i + 0.5D, (double) j - 1.95D, (double) k + 0.5D, 0.0F, 0.0F);
                world.addEntity(entityirongolem);

                for (int i1 = 0; i1 < 120; ++i1) {
                    world.addParticle("snowballpoof", (double) i + world.random.nextDouble(), (double) (j - 2) + world.random.nextDouble() * 3.9D, (double) k + world.random.nextDouble(), 0.0D, 0.0D, 0.0D);
                }

                world.update(i, j, k, e(0));
                world.update(i, j - 1, k, e(0));
                world.update(i, j - 2, k, e(0));
                if (flag) {
                    world.update(i - 1, j - 1, k, e(0));
                    world.update(i + 1, j - 1, k, e(0));
                } else {
                    world.update(i, j - 1, k - 1, e(0));
                    world.update(i, j - 1, k + 1, e(0));
                }
            }
        }
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return world.getType(i, j, k).material.isReplaceable() && World.a((IBlockAccess) world, i, j - 1, k);
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemstack) {
        int l = MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 2.5D) & 3;

        world.setData(i, j, k, l, 2);
    }
}
