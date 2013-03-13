package net.minecraft.server;

public class BlockPoweredRail extends BlockMinecartTrackAbstract {

    protected BlockPoweredRail(int i) {
        super(i, true);
    }

    protected boolean a(World world, int i, int j, int k, int l, boolean flag, int i1) {
        if (i1 >= 8) {
            return false;
        } else {
            int j1 = l & 7;
            boolean flag1 = true;

            switch (j1) {
            case 0:
                if (flag) {
                    ++k;
                } else {
                    --k;
                }
                break;

            case 1:
                if (flag) {
                    --i;
                } else {
                    ++i;
                }
                break;

            case 2:
                if (flag) {
                    --i;
                } else {
                    ++i;
                    ++j;
                    flag1 = false;
                }

                j1 = 1;
                break;

            case 3:
                if (flag) {
                    --i;
                    ++j;
                    flag1 = false;
                } else {
                    ++i;
                }

                j1 = 1;
                break;

            case 4:
                if (flag) {
                    ++k;
                } else {
                    --k;
                    ++j;
                    flag1 = false;
                }

                j1 = 0;
                break;

            case 5:
                if (flag) {
                    ++k;
                    ++j;
                    flag1 = false;
                } else {
                    --k;
                }

                j1 = 0;
            }

            return this.a(world, i, j, k, flag, i1, j1) ? true : flag1 && this.a(world, i, j - 1, k, flag, i1, j1);
        }
    }

    protected boolean a(World world, int i, int j, int k, boolean flag, int l, int i1) {
        int j1 = world.getTypeId(i, j, k);

        if (j1 == this.id) {
            int k1 = world.getData(i, j, k);
            int l1 = k1 & 7;

            if (i1 == 1 && (l1 == 0 || l1 == 4 || l1 == 5)) {
                return false;
            }

            if (i1 == 0 && (l1 == 1 || l1 == 2 || l1 == 3)) {
                return false;
            }

            if ((k1 & 8) != 0) {
                if (world.isBlockIndirectlyPowered(i, j, k)) {
                    return true;
                }

                return this.a(world, i, j, k, k1, flag, l + 1);
            }
        }

        return false;
    }

    protected void a(World world, int i, int j, int k, int l, int i1, int j1) {
        boolean flag = world.isBlockIndirectlyPowered(i, j, k);

        flag = flag || this.a(world, i, j, k, l, true, 0) || this.a(world, i, j, k, l, false, 0);
        boolean flag1 = false;

        if (flag && (l & 8) == 0) {
            world.setData(i, j, k, i1 | 8, 3);
            flag1 = true;
        } else if (!flag && (l & 8) != 0) {
            world.setData(i, j, k, i1, 3);
            flag1 = true;
        }

        if (flag1) {
            world.applyPhysics(i, j - 1, k, this.id);
            if (i1 == 2 || i1 == 3 || i1 == 4 || i1 == 5) {
                world.applyPhysics(i, j + 1, k, this.id);
            }
        }
    }
}
