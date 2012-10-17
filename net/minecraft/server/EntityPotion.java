package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class EntityPotion extends EntityProjectile {

    private ItemStack d;

    public EntityPotion(World world) {
        super(world);
    }

    public EntityPotion(World world, EntityLiving entityliving, int i) {
        this(world, entityliving, new ItemStack(Item.POTION, 1, i));
    }

    public EntityPotion(World world, EntityLiving entityliving, ItemStack itemstack) {
        super(world, entityliving);
        this.d = itemstack;
    }

    public EntityPotion(World world, double d0, double d1, double d2, ItemStack itemstack) {
        super(world, d0, d1, d2);
        this.d = itemstack;
    }

    protected float g() {
        return 0.05F;
    }

    protected float c() {
        return 0.5F;
    }

    protected float d() {
        return -20.0F;
    }

    public void setPotionValue(int i) {
        if (this.d == null) {
            this.d = new ItemStack(Item.POTION, 1, 0);
        }

        this.d.setData(i);
    }

    public int getPotionValue() {
        if (this.d == null) {
            this.d = new ItemStack(Item.POTION, 1, 0);
        }

        return this.d.getData();
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.world.isStatic) {
            List list = Item.POTION.l(this.d);

            if (list != null && !list.isEmpty()) {
                AxisAlignedBB axisalignedbb = this.boundingBox.grow(4.0D, 2.0D, 4.0D);
                List list1 = this.world.a(EntityLiving.class, axisalignedbb);

                if (list1 != null && !list1.isEmpty()) {
                    Iterator iterator = list1.iterator();

                    while (iterator.hasNext()) {
                        EntityLiving entityliving = (EntityLiving) iterator.next();
                        double d0 = this.e(entityliving);

                        if (d0 < 16.0D) {
                            double d1 = 1.0D - Math.sqrt(d0) / 4.0D;

                            if (entityliving == movingobjectposition.entity) {
                                d1 = 1.0D;
                            }

                            Iterator iterator1 = list.iterator();

                            while (iterator1.hasNext()) {
                                MobEffect mobeffect = (MobEffect) iterator1.next();
                                int i = mobeffect.getEffectId();

                                if (MobEffectList.byId[i].isInstant()) {
                                    MobEffectList.byId[i].applyInstantEffect(this.shooter, entityliving, mobeffect.getAmplifier(), d1);
                                } else {
                                    int j = (int) (d1 * (double) mobeffect.getDuration() + 0.5D);

                                    if (j > 20) {
                                        entityliving.addEffect(new MobEffect(i, j, mobeffect.getAmplifier()));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            this.world.triggerEffect(2002, (int) Math.round(this.locX), (int) Math.round(this.locY), (int) Math.round(this.locZ), this.getPotionValue());
            this.die();
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.hasKey("Potion")) {
            this.d = ItemStack.a(nbttagcompound.getCompound("Potion"));
        } else {
            this.setPotionValue(nbttagcompound.getInt("potionValue"));
        }

        if (this.d == null) {
            this.die();
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.d != null) {
            nbttagcompound.setCompound("Potion", this.d.save(new NBTTagCompound()));
        }
    }
}
