package net.minecraft.server;

import java.util.Random;

public abstract class BlockMinecartTrackAbstract extends Block {

    protected final boolean a;

    public static final boolean d_(World world, int i, int j, int k) {
        return d_(world.getTypeId(i, j, k));
    }

    public static final boolean d_(int i) {
        return i == Block.RAILS.id || i == Block.GOLDEN_RAIL.id || i == Block.DETECTOR_RAIL.id || i == Block.ACTIVATOR_RAIL.id;
    }

    protected BlockMinecartTrackAbstract(int i, boolean flag) {
        super(i, Material.ORIENTABLE);
        this.a = flag;
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.a(CreativeModeTab.e);
    }

    public boolean e() {
        return this.a;
    }

    public AxisAlignedBB b(World world, int i, int j, int k) {
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

    public boolean b() {
        return false;
    }

    public int d() {
        return 9;
    }

    public int a(Random random) {
        return 1;
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return world.w(i, j - 1, k);
    }

    public void onPlace(World world, int i, int j, int k) {
        if (!world.isStatic) {
            this.a(world, i, j, k, true);
            if (this.a) {
                this.doPhysics(world, i, j, k, this.id);
            }
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (!world.isStatic) {
            int i1 = world.getData(i, j, k);
            int j1 = i1;

            if (this.a) {
                j1 = i1 & 7;
            }

            boolean flag = false;

            if (!world.w(i, j - 1, k)) {
                flag = true;
            }

            if (j1 == 2 && !world.w(i + 1, j, k)) {
                flag = true;
            }

            if (j1 == 3 && !world.w(i - 1, j, k)) {
                flag = true;
            }

            if (j1 == 4 && !world.w(i, j, k - 1)) {
                flag = true;
            }

            if (j1 == 5 && !world.w(i, j, k + 1)) {
                flag = true;
            }

            if (flag) {
                this.c(world, i, j, k, world.getData(i, j, k), 0);
                world.setAir(i, j, k);
            } else {
                this.a(world, i, j, k, i1, j1, l);
            }
        }
    }

    protected void a(World world, int i, int j, int k, int l, int i1, int j1) {}

    protected void a(World world, int i, int j, int k, boolean flag) {
        if (!world.isStatic) {
            (new MinecartTrackLogic(this, world, i, j, k)).a(world.isBlockIndirectlyPowered(i, j, k), flag);
        }
    }

    public int h() {
        return 0;
    }

    public void remove(World world, int i, int j, int k, int l, int i1) {
        int j1 = i1;

        if (this.a) {
            j1 = i1 & 7;
        }

        super.remove(world, i, j, k, l, i1);
        if (j1 == 2 || j1 == 3 || j1 == 4 || j1 == 5) {
            world.applyPhysics(i, j + 1, k, l);
        }

        if (this.a) {
            world.applyPhysics(i, j, k, l);
            world.applyPhysics(i, j - 1, k, l);
        }
    }
}
