package net.minecraft.server;

public class ItemDoor extends Item {

    private Material a;

    public ItemDoor(int i, Material material) {
        super(i);
        this.a = material;
        this.maxStackSize = 1;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (l != 1) {
            return false;
        } else {
            ++j;
            Block block;

            if (this.a == Material.WOOD) {
                block = Block.WOODEN_DOOR;
            } else {
                block = Block.IRON_DOOR_BLOCK;
            }

            if (entityhuman.c(i, j, k) && entityhuman.c(i, j + 1, k)) {
                if (!block.canPlace(world, i, j, k)) {
                    return false;
                } else {
                    int i1 = MathHelper.floor((double) ((entityhuman.yaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;

                    a(world, i, j, k, i1, block);
                    --itemstack.count;
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public static void a(World world, int i, int j, int k, int l, Block block) {
        byte b0 = 0;
        byte b1 = 0;

        if (l == 0) {
            b1 = 1;
        }

        if (l == 1) {
            b0 = -1;
        }

        if (l == 2) {
            b1 = -1;
        }

        if (l == 3) {
            b0 = 1;
        }

        int i1 = (world.e(i - b0, j, k - b1) ? 1 : 0) + (world.e(i - b0, j + 1, k - b1) ? 1 : 0);
        int j1 = (world.e(i + b0, j, k + b1) ? 1 : 0) + (world.e(i + b0, j + 1, k + b1) ? 1 : 0);
        boolean flag = world.getTypeId(i - b0, j, k - b1) == block.id || world.getTypeId(i - b0, j + 1, k - b1) == block.id;
        boolean flag1 = world.getTypeId(i + b0, j, k + b1) == block.id || world.getTypeId(i + b0, j + 1, k + b1) == block.id;
        boolean flag2 = false;

        if (flag && !flag1) {
            flag2 = true;
        } else if (j1 > i1) {
            flag2 = true;
        }

        if (flag2) {
            l = l - 1 & 3;
            l += 4;
        }

        world.suppressPhysics = true;
        world.setTypeIdAndData(i, j, k, block.id, l);
        world.setTypeIdAndData(i, j + 1, k, block.id, l + 8);
        world.suppressPhysics = false;
        world.applyPhysics(i, j, k, block.id);
        world.applyPhysics(i, j + 1, k, block.id);
    }
}
