package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockCauldron extends Block {

    public BlockCauldron(int i) {
        super(i, Material.ORE);
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
        super.a(world, i, j, k, axisalignedbb, list, entity);
        float f = 0.125F;

        this.a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        super.a(world, i, j, k, axisalignedbb, list, entity);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        super.a(world, i, j, k, axisalignedbb, list, entity);
        this.a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.a(world, i, j, k, axisalignedbb, list, entity);
        this.a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        super.a(world, i, j, k, axisalignedbb, list, entity);
        this.g();
    }

    public void g() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public boolean c() {
        return false;
    }

    public int d() {
        return 24;
    }

    public boolean b() {
        return false;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            ItemStack itemstack = entityhuman.inventory.getItemInHand();

            if (itemstack == null) {
                return true;
            } else {
                int i1 = world.getData(i, j, k);
                int j1 = h_(i1);

                if (itemstack.id == Item.WATER_BUCKET.id) {
                    if (j1 < 3) {
                        if (!entityhuman.abilities.canInstantlyBuild) {
                            entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, new ItemStack(Item.BUCKET));
                        }

                        world.setData(i, j, k, 3, 2);
                        world.m(i, j, k, this.id);
                    }

                    return true;
                } else {
                    if (itemstack.id == Item.GLASS_BOTTLE.id) {
                        if (j1 > 0) {
                            ItemStack itemstack1 = new ItemStack(Item.POTION, 1, 0);

                            if (!entityhuman.inventory.pickup(itemstack1)) {
                                world.addEntity(new EntityItem(world, (double) i + 0.5D, (double) j + 1.5D, (double) k + 0.5D, itemstack1));
                            } else if (entityhuman instanceof EntityPlayer) {
                                ((EntityPlayer) entityhuman).updateInventory(entityhuman.defaultContainer);
                            }

                            --itemstack.count;
                            if (itemstack.count <= 0) {
                                entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
                            }

                            world.setData(i, j, k, j1 - 1, 2);
                            world.m(i, j, k, this.id);
                        }
                    } else if (j1 > 0 && itemstack.getItem() instanceof ItemArmor && ((ItemArmor) itemstack.getItem()).d() == EnumArmorMaterial.CLOTH) {
                        ItemArmor itemarmor = (ItemArmor) itemstack.getItem();

                        itemarmor.c(itemstack);
                        world.setData(i, j, k, j1 - 1, 2);
                        world.m(i, j, k, this.id);
                        return true;
                    }

                    return true;
                }
            }
        }
    }

    public void g(World world, int i, int j, int k) {
        if (world.random.nextInt(20) == 1) {
            int l = world.getData(i, j, k);

            if (l < 3) {
                world.setData(i, j, k, l + 1, 2);
            }
        }
    }

    public int getDropType(int i, Random random, int j) {
        return Item.CAULDRON.id;
    }

    public boolean q_() {
        return true;
    }

    public int b_(World world, int i, int j, int k, int l) {
        int i1 = world.getData(i, j, k);

        return h_(i1);
    }

    public static int h_(int i) {
        return i;
    }
}
