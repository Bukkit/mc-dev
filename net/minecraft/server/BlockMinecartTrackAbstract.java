package net.minecraft.server;

import java.util.Random;

public abstract class BlockMinecartTrackAbstract extends Block {

    protected final boolean a;

    public static final boolean b_(World world, int i, int j, int k) {
        return a(world.getType(i, j, k));
    }

    public static final boolean a(Block block) {
        return block == Blocks.RAILS || block == Blocks.GOLDEN_RAIL || block == Blocks.DETECTOR_RAIL || block == Blocks.ACTIVATOR_RAIL;
    }

    protected BlockMinecartTrackAbstract(boolean flag) {
        super(Material.ORIENTABLE);
        this.a = flag;
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.a(CreativeModeTab.e);
    }

    public boolean e() {
        return this.a;
    }

    public AxisAlignedBB a(World world, int i, int j, int k) {
        return null;
    }

    public boolean c() {
        return false;
    }

    public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
        this.updateShape(world, i, j, k);
        return super.a(world, i, j, k, vec3d, vec3d1);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.getData(i, j, k);

        if (l >= 2 && l <= 5) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        }
    }

    public boolean d() {
        return false;
    }

    public int b() {
        return 9;
    }

    public int a(Random random) {
        return 1;
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return World.a((IBlockAccess) world, i, j - 1, k);
    }

    public void onPlace(World world, int i, int j, int k) {
        if (!world.isStatic) {
            this.a(world, i, j, k, true);
            if (this.a) {
                this.doPhysics(world, i, j, k, this);
            }
        }
    }

    public void doPhysics(World world, int i, int j, int k, Block block) {
        if (!world.isStatic) {
            int l = world.getData(i, j, k);
            int i1 = l;

            if (this.a) {
                i1 = l & 7;
            }

            boolean flag = false;

            if (!World.a((IBlockAccess) world, i, j - 1, k)) {
                flag = true;
            }

            if (i1 == 2 && !World.a((IBlockAccess) world, i + 1, j, k)) {
                flag = true;
            }

            if (i1 == 3 && !World.a((IBlockAccess) world, i - 1, j, k)) {
                flag = true;
            }

            if (i1 == 4 && !World.a((IBlockAccess) world, i, j, k - 1)) {
                flag = true;
            }

            if (i1 == 5 && !World.a((IBlockAccess) world, i, j, k + 1)) {
                flag = true;
            }

            if (flag) {
                this.b(world, i, j, k, world.getData(i, j, k), 0);
                world.setAir(i, j, k);
            } else {
                this.a(world, i, j, k, l, i1, block);
            }
        }
    }

    protected void a(World world, int i, int j, int k, int l, int i1, Block block) {}

    protected void a(World world, int i, int j, int k, boolean flag) {
        if (!world.isStatic) {
            (new MinecartTrackLogic(this, world, i, j, k)).a(world.isBlockIndirectlyPowered(i, j, k), flag);
        }
    }

    public int h() {
        return 0;
    }

    public void remove(World world, int i, int j, int k, Block block, int l) {
        int i1 = l;

        if (this.a) {
            i1 = l & 7;
        }

        super.remove(world, i, j, k, block, l);
        if (i1 == 2 || i1 == 3 || i1 == 4 || i1 == 5) {
            world.applyPhysics(i, j + 1, k, block);
        }

        if (this.a) {
            world.applyPhysics(i, j, k, block);
            world.applyPhysics(i, j - 1, k, block);
        }
    }
}
