package net.minecraft.server;

import java.util.Random;

public class BlockMinecartTrack extends Block {

    protected BlockMinecartTrack(int i, int j) {
        super(i, j, Material.n);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
        this.a((IBlockAccess) world, i, j, k);
        return super.a(world, i, j, k, vec3d, vec3d1);
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.b(i, j, k);

        if (l >= 2 && l <= 5) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        }
    }

    public int a(Random random) {
        return 1;
    }

    public boolean a(World world, int i, int j, int k) {
        return world.d(i, j - 1, k);
    }

    public void e(World world, int i, int j, int k) {
        if (!world.z) {
            world.b(i, j, k, 15);
            this.g(world, i, j, k);
        }
    }

    public void b(World world, int i, int j, int k, int l) {
        if (!world.z) {
            int i1 = world.b(i, j, k);
            boolean flag = false;

            if (!world.d(i, j - 1, k)) {
                flag = true;
            }

            if (i1 == 2 && !world.d(i + 1, j, k)) {
                flag = true;
            }

            if (i1 == 3 && !world.d(i - 1, j, k)) {
                flag = true;
            }

            if (i1 == 4 && !world.d(i, j, k - 1)) {
                flag = true;
            }

            if (i1 == 5 && !world.d(i, j, k + 1)) {
                flag = true;
            }

            if (flag) {
                this.a_(world, i, j, k, world.b(i, j, k));
                world.d(i, j, k, 0);
            } else if (l > 0 && Block.n[l].c() && MinecartTrackLogic.a(new MinecartTrackLogic(this, world, i, j, k)) == 3) {
                this.g(world, i, j, k);
            }
        }
    }

    private void g(World world, int i, int j, int k) {
        if (!world.z) {
            (new MinecartTrackLogic(this, world, i, j, k)).a(world.n(i, j, k));
        }
    }
}
