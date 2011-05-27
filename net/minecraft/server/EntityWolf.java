package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class EntityWolf extends EntityAnimal {

    private boolean a = false;
    private float b;
    private float c;
    private boolean f;
    private boolean g;
    private float h;
    private float i;

    public EntityWolf(World world) {
        super(world);
        this.texture = "/mob/wolf.png";
        this.b(0.8F, 0.8F);
        this.az = 1.1F;
        this.health = 8;
    }

    protected void a() {
        super.a();
        this.datawatcher.a(16, Byte.valueOf((byte) 0));
        this.datawatcher.a(17, "");
        this.datawatcher.a(18, new Integer(this.health));
    }

    protected boolean l() {
        return false;
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Angry", this.x());
        nbttagcompound.a("Sitting", this.w());
        if (this.v() == null) {
            nbttagcompound.setString("Owner", "");
        } else {
            nbttagcompound.setString("Owner", this.v());
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.c(nbttagcompound.m("Angry"));
        this.b(nbttagcompound.m("Sitting"));
        String s = nbttagcompound.getString("Owner");

        if (s.length() > 0) {
            this.a(s);
            this.d(true);
        }
    }

    protected boolean s() {
        return !this.y();
    }

    protected String e() {
        return this.x() ? "mob.wolf.growl" : (this.random.nextInt(3) == 0 ? (this.y() && this.health < 10 ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    protected String f() {
        return "mob.wolf.hurt";
    }

    protected String g() {
        return "mob.wolf.death";
    }

    protected float i() {
        return 0.4F;
    }

    protected int h() {
        return -1;
    }

    protected void c_() {
        super.c_();
        if (!this.e && !this.z() && this.y()) {
            EntityHuman entityhuman = this.world.a(this.v());

            if (entityhuman != null) {
                float f = entityhuman.f(this);

                if (f > 5.0F) {
                    this.b(entityhuman, f);
                }
            } else if (!this.g_()) {
                this.b(true);
            }
        } else if (this.target == null && !this.z() && !this.y() && this.world.random.nextInt(100) == 0) {
            List list = this.world.a(EntitySheep.class, AxisAlignedBB.b(this.locX, this.locY, this.locZ, this.locX + 1.0D, this.locY + 1.0D, this.locZ + 1.0D).b(16.0D, 4.0D, 16.0D));

            if (!list.isEmpty()) {
                this.c((Entity) list.get(this.world.random.nextInt(list.size())));
            }
        }

        if (this.g_()) {
            this.b(false);
        }

        if (!this.world.isStatic) {
            this.datawatcher.b(18, Integer.valueOf(this.health));
        }
    }

    public void r() {
        super.r();
        this.a = false;
        if (this.N() && !this.z() && !this.x()) {
            Entity entity = this.O();

            if (entity instanceof EntityHuman) {
                EntityHuman entityhuman = (EntityHuman) entity;
                ItemStack itemstack = entityhuman.inventory.getItemInHand();

                if (itemstack != null) {
                    if (!this.y() && itemstack.id == Item.BONE.id) {
                        this.a = true;
                    } else if (this.y() && Item.byId[itemstack.id] instanceof ItemFood) {
                        this.a = ((ItemFood) Item.byId[itemstack.id]).k();
                    }
                }
            }
        }

        if (!this.T && this.f && !this.g && !this.z()) {
            this.g = true;
            this.h = 0.0F;
            this.i = 0.0F;
            this.world.a(this, (byte) 8);
        }
    }

    public void f_() {
        super.f_();
        this.c = this.b;
        if (this.a) {
            this.b += (1.0F - this.b) * 0.4F;
        } else {
            this.b += (0.0F - this.b) * 0.4F;
        }

        if (this.a) {
            this.aA = 10;
        }

        if (this.g_()) {
            this.f = true;
            this.g = false;
            this.h = 0.0F;
            this.i = 0.0F;
        } else if ((this.f || this.g) && this.g) {
            if (this.h == 0.0F) {
                this.world.makeSound(this, "mob.wolf.shake", this.i(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            }

            this.i = this.h;
            this.h += 0.05F;
            if (this.i >= 2.0F) {
                this.f = false;
                this.g = false;
                this.i = 0.0F;
                this.h = 0.0F;
            }

            if (this.h > 0.4F) {
                float f = (float) this.boundingBox.b;
                int i = (int) (MathHelper.sin((this.h - 0.4F) * 3.1415927F) * 7.0F);

                for (int j = 0; j < i; ++j) {
                    float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * this.length * 0.5F;
                    float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.length * 0.5F;

                    this.world.a("splash", this.locX + (double) f1, (double) (f + 0.8F), this.locZ + (double) f2, this.motX, this.motY, this.motZ);
                }
            }
        }
    }

    public float q() {
        return this.width * 0.8F;
    }

    protected int n_() {
        return this.w() ? 20 : super.n_();
    }

    private void b(Entity entity, float f) {
        PathEntity pathentity = this.world.findPath(this, entity, 16.0F);

        if (pathentity == null && f > 12.0F) {
            int i = MathHelper.floor(entity.locX) - 2;
            int j = MathHelper.floor(entity.locZ) - 2;
            int k = MathHelper.floor(entity.boundingBox.b);

            for (int l = 0; l <= 4; ++l) {
                for (int i1 = 0; i1 <= 4; ++i1) {
                    if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.world.d(i + l, k - 1, j + i1) && !this.world.d(i + l, k, j + i1) && !this.world.d(i + l, k + 1, j + i1)) {
                        this.setPositionRotation((double) ((float) (i + l) + 0.5F), (double) k, (double) ((float) (j + i1) + 0.5F), this.yaw, this.pitch);
                        return;
                    }
                }
            }
        } else {
            this.a(pathentity);
        }
    }

    protected boolean u() {
        return this.w() || this.g;
    }

    public boolean damageEntity(Entity entity, int i) {
        this.b(false);
        if (entity != null && !(entity instanceof EntityHuman) && !(entity instanceof EntityArrow)) {
            i = (i + 1) / 2;
        }

        if (!super.damageEntity((Entity) entity, i)) {
            return false;
        } else {
            if (!this.y() && !this.x()) {
                if (entity instanceof EntityHuman) {
                    this.c(true);
                    this.target = (Entity) entity;
                }

                if (entity instanceof EntityArrow && ((EntityArrow) entity).shooter != null) {
                    entity = ((EntityArrow) entity).shooter;
                }

                if (entity instanceof EntityLiving) {
                    List list = this.world.a(EntityWolf.class, AxisAlignedBB.b(this.locX, this.locY, this.locZ, this.locX + 1.0D, this.locY + 1.0D, this.locZ + 1.0D).b(16.0D, 4.0D, 16.0D));
                    Iterator iterator = list.iterator();

                    while (iterator.hasNext()) {
                        Entity entity1 = (Entity) iterator.next();
                        EntityWolf entitywolf = (EntityWolf) entity1;

                        if (!entitywolf.y() && entitywolf.target == null) {
                            entitywolf.target = (Entity) entity;
                            if (entity instanceof EntityHuman) {
                                entitywolf.c(true);
                            }
                        }
                    }
                }
            } else if (entity != this && entity != null) {
                if (this.y() && entity instanceof EntityHuman && ((EntityHuman) entity).name.equals(this.v())) {
                    return true;
                }

                this.target = (Entity) entity;
            }

            return true;
        }
    }

    protected Entity findTarget() {
        return this.x() ? this.world.a(this, 16.0D) : null;
    }

    protected void a(Entity entity, float f) {
        if (f > 2.0F && f < 6.0F && this.random.nextInt(10) == 0) {
            if (this.onGround) {
                double d0 = entity.locX - this.locX;
                double d1 = entity.locZ - this.locZ;
                float f1 = MathHelper.a(d0 * d0 + d1 * d1);

                this.motX = d0 / (double) f1 * 0.5D * 0.800000011920929D + this.motX * 0.20000000298023224D;
                this.motZ = d1 / (double) f1 * 0.5D * 0.800000011920929D + this.motZ * 0.20000000298023224D;
                this.motY = 0.4000000059604645D;
            }
        } else if ((double) f < 1.5D && entity.boundingBox.e > this.boundingBox.b && entity.boundingBox.b < this.boundingBox.e) {
            this.attackTicks = 20;
            byte b0 = 2;

            if (this.y()) {
                b0 = 4;
            }

            entity.damageEntity(this, b0);
        }
    }

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (!this.y()) {
            if (itemstack != null && itemstack.id == Item.BONE.id && !this.x()) {
                --itemstack.count;
                if (itemstack.count <= 0) {
                    entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
                }

                if (!this.world.isStatic) {
                    if (this.random.nextInt(3) == 0) {
                        this.d(true);
                        this.a((PathEntity) null);
                        this.b(true);
                        this.health = 20;
                        this.a(entityhuman.name);
                        this.a(true);
                        this.world.a(this, (byte) 7);
                    } else {
                        this.a(false);
                        this.world.a(this, (byte) 6);
                    }
                }

                return true;
            }
        } else {
            if (itemstack != null && Item.byId[itemstack.id] instanceof ItemFood) {
                ItemFood itemfood = (ItemFood) Item.byId[itemstack.id];

                if (itemfood.k() && this.datawatcher.b(18) < 20) {
                    --itemstack.count;
                    if (itemstack.count <= 0) {
                        entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
                    }

                    this.b(((ItemFood) Item.PORK).j());
                    return true;
                }
            }

            if (entityhuman.name.equals(this.v())) {
                if (!this.world.isStatic) {
                    this.b(!this.w());
                    this.ax = false;
                    this.a((PathEntity) null);
                }

                return true;
            }
        }

        return false;
    }

    void a(boolean flag) {
        String s = "heart";

        if (!flag) {
            s = "smoke";
        }

        for (int i = 0; i < 7; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;

            this.world.a(s, this.locX + (double) (this.random.nextFloat() * this.length * 2.0F) - (double) this.length, this.locY + 0.5D + (double) (this.random.nextFloat() * this.width), this.locZ + (double) (this.random.nextFloat() * this.length * 2.0F) - (double) this.length, d0, d1, d2);
        }
    }

    public int j() {
        return 8;
    }

    public String v() {
        return this.datawatcher.c(17);
    }

    public void a(String s) {
        this.datawatcher.b(17, s);
    }

    public boolean w() {
        return (this.datawatcher.a(16) & 1) != 0;
    }

    public void b(boolean flag) {
        byte b0 = this.datawatcher.a(16);

        if (flag) {
            this.datawatcher.b(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.datawatcher.b(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    public boolean x() {
        return (this.datawatcher.a(16) & 2) != 0;
    }

    public void c(boolean flag) {
        byte b0 = this.datawatcher.a(16);

        if (flag) {
            this.datawatcher.b(16, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.datawatcher.b(16, Byte.valueOf((byte) (b0 & -3)));
        }
    }

    public boolean y() {
        return (this.datawatcher.a(16) & 4) != 0;
    }

    public void d(boolean flag) {
        byte b0 = this.datawatcher.a(16);

        if (flag) {
            this.datawatcher.b(16, Byte.valueOf((byte) (b0 | 4)));
        } else {
            this.datawatcher.b(16, Byte.valueOf((byte) (b0 & -5)));
        }
    }
}
