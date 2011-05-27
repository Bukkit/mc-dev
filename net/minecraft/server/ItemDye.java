package net.minecraft.server;

public class ItemDye extends Item {

    public static final String[] a = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white"};

    public ItemDye(int i) {
        super(i);
        this.a(true);
        this.d(0);
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (itemstack.h() == 15) {
            int i1 = world.getTypeId(i, j, k);

            if (i1 == Block.SAPLING.id) {
                ((BlockSapling) Block.SAPLING).b(world, i, j, k, world.k);
                --itemstack.count;
                return true;
            }

            if (i1 == Block.CROPS.id) {
                ((BlockCrops) Block.CROPS).c_(world, i, j, k);
                --itemstack.count;
                return true;
            }
        }

        return false;
    }

    public void b(ItemStack itemstack, EntityLiving entityliving) {
        if (entityliving instanceof EntitySheep) {
            EntitySheep entitysheep = (EntitySheep) entityliving;
            int i = BlockCloth.c(itemstack.h());

            if (!entitysheep.j_() && entitysheep.n() != i) {
                entitysheep.a_(i);
                --itemstack.count;
            }
        }
    }
}
