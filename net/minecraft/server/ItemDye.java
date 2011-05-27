package net.minecraft.server;

public class ItemDye extends Item {

    public static final String[] a = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white"};

    public ItemDye(int i) {
        super(i);
        this.a(true);
        this.d(0);
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (itemstack.getData() == 15) {
            int i1 = world.getTypeId(i, j, k);

            if (i1 == Block.SAPLING.id) {
                ((BlockSapling) Block.SAPLING).b(world, i, j, k, world.random);
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

    public void a(ItemStack itemstack, EntityLiving entityliving) {
        if (entityliving instanceof EntitySheep) {
            EntitySheep entitysheep = (EntitySheep) entityliving;
            int i = BlockCloth.c(itemstack.getData());

            if (!entitysheep.isSheared() && entitysheep.getColor() != i) {
                entitysheep.setColor(i);
                --itemstack.count;
            }
        }
    }
}
