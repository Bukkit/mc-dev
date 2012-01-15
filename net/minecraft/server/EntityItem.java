package net.minecraft.server;

public class EntityItem extends Entity {

    public ItemStack itemStack;
    private int e;
    public int age = 0;
    public int pickupDelay;
    private int f = 5;
    public float d = (float) (Math.random() * 3.141592653589793D * 2.0D);

    public EntityItem(World world, double d0, double d1, double d2, ItemStack itemstack) {
        super(world);
        this.b(0.25F, 0.25F);
        this.height = this.length / 2.0F;
        this.setPosition(d0, d1, d2);
        this.itemStack = itemstack;
        this.yaw = (float) (Math.random() * 360.0D);
        this.motX = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.motY = 0.20000000298023224D;
        this.motZ = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
    }

    protected boolean g_() {
        return false;
    }

    public EntityItem(World world) {
        super(world);
        this.b(0.25F, 0.25F);
        this.height = this.length / 2.0F;
    }

    protected void b() {}

    public void y_() {
        super.y_();
        if (this.pickupDelay > 0) {
            --this.pickupDelay;
        }

        this.lastX = this.locX;
        this.lastY = this.locY;
        this.lastZ = this.locZ;
        this.motY -= 0.03999999910593033D;
        if (this.world.getMaterial(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)) == Material.LAVA) {
            this.motY = 0.20000000298023224D;
            this.motX = (double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            this.motZ = (double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            this.world.makeSound(this, "random.fizz", 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
        }

        this.g(this.locX, (this.boundingBox.b + this.boundingBox.e) / 2.0D, this.locZ);
        this.move(this.motX, this.motY, this.motZ);
        float f = 0.98F;

        if (this.onGround) {
            f = 0.58800006F;
            int i = this.world.getTypeId(MathHelper.floor(this.locX), MathHelper.floor(this.boundingBox.b) - 1, MathHelper.floor(this.locZ));

            if (i > 0) {
                f = Block.byId[i].frictionFactor * 0.98F;
            }
        }

        this.motX *= (double) f;
        this.motY *= 0.9800000190734863D;
        this.motZ *= (double) f;
        if (this.onGround) {
            this.motY *= -0.5D;
        }

        ++this.e;
        ++this.age;
        if (this.age >= 6000) {
            this.die();
        }
    }

    public boolean i_() {
        return this.world.a(this.boundingBox, Material.WATER, this);
    }

    protected void burn(int i) {
        this.damageEntity(DamageSource.FIRE, i);
    }

    public boolean damageEntity(DamageSource damagesource, int i) {
        this.aM();
        this.f -= i;
        if (this.f <= 0) {
            this.die();
        }

        return false;
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.setShort("Health", (short) ((byte) this.f));
        nbttagcompound.setShort("Age", (short) this.age);
        nbttagcompound.setCompound("Item", this.itemStack.b(new NBTTagCompound()));
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.f = nbttagcompound.getShort("Health") & 255;
        this.age = nbttagcompound.getShort("Age");
        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Item");

        this.itemStack = ItemStack.a(nbttagcompound1);
        if (this.itemStack == null) {
            this.die();
        }
    }

    public void a_(EntityHuman entityhuman) {
        if (!this.world.isStatic) {
            int i = this.itemStack.count;

            if (this.pickupDelay == 0 && entityhuman.inventory.pickup(this.itemStack)) {
                if (this.itemStack.id == Block.LOG.id) {
                    entityhuman.a((Statistic) AchievementList.g);
                }

                if (this.itemStack.id == Item.LEATHER.id) {
                    entityhuman.a((Statistic) AchievementList.t);
                }

                if (this.itemStack.id == Item.DIAMOND.id) {
                    entityhuman.a((Statistic) AchievementList.w);
                }

                if (this.itemStack.id == Item.BLAZE_ROD.id) {
                    entityhuman.a((Statistic) AchievementList.z);
                }

                this.world.makeSound(this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                entityhuman.receive(this, i);
                if (this.itemStack.count <= 0) {
                    this.die();
                }
            }
        }
    }

    public String getLocalizedName() {
        return LocaleI18n.a("item." + this.itemStack.k());
    }
}
