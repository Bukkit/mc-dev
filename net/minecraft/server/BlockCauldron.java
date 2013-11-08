package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockCauldron extends Block {

    public BlockCauldron() {
        super(Material.ORE);
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

    public int b() {
        return 24;
    }

    public boolean d() {
        return false;
    }

    public void a(World world, int i, int j, int k, Entity entity) {
        int l = b(world.getData(i, j, k));
        float f = (float) j + (6.0F + (float) (3 * l)) / 16.0F;

        if (!world.isStatic && entity.isBurning() && l > 0 && entity.boundingBox.b <= (double) f) {
            entity.extinguish();
            this.a(world, i, j, k, l - 1);
        }
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
                int j1 = b(i1);

                if (itemstack.getItem() == Items.WATER_BUCKET) {
                    if (j1 < 3) {
                        if (!entityhuman.abilities.canInstantlyBuild) {
                            entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, new ItemStack(Items.BUCKET));
                        }

                        this.a(world, i, j, k, 3);
                    }

                    return true;
                } else {
                    if (itemstack.getItem() == Items.GLASS_BOTTLE) {
                        if (j1 > 0) {
                            if (!entityhuman.abilities.canInstantlyBuild) {
                                ItemStack itemstack1 = new ItemStack(Items.POTION, 1, 0);

                                if (!entityhuman.inventory.pickup(itemstack1)) {
                                    world.addEntity(new EntityItem(world, (double) i + 0.5D, (double) j + 1.5D, (double) k + 0.5D, itemstack1));
                                } else if (entityhuman instanceof EntityPlayer) {
                                    ((EntityPlayer) entityhuman).updateInventory(entityhuman.defaultContainer);
                                }

                                --itemstack.count;
                                if (itemstack.count <= 0) {
                                    entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
                                }
                            }

                            this.a(world, i, j, k, j1 - 1);
                        }
                    } else if (j1 > 0 && itemstack.getItem() instanceof ItemArmor && ((ItemArmor) itemstack.getItem()).m_() == EnumArmorMaterial.CLOTH) {
                        ItemArmor itemarmor = (ItemArmor) itemstack.getItem();

                        itemarmor.c(itemstack);
                        this.a(world, i, j, k, j1 - 1);
                        return true;
                    }

                    return false;
                }
            }
        }
    }

    public void a(World world, int i, int j, int k, int l) {
        world.setData(i, j, k, MathHelper.a(l, 0, 3), 2);
        world.f(i, j, k, this);
    }

    public void l(World world, int i, int j, int k) {
        if (world.random.nextInt(20) == 1) {
            int l = world.getData(i, j, k);

            if (l < 3) {
                world.setData(i, j, k, l + 1, 2);
            }
        }
    }

    public Item getDropType(int i, Random random, int j) {
        return Items.CAULDRON;
    }

    public boolean M() {
        return true;
    }

    public int g(World world, int i, int j, int k, int l) {
        int i1 = world.getData(i, j, k);

        return b(i1);
    }

    public static int b(int i) {
        return i;
    }
}
