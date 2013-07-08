package net.minecraft.server;

public class EntityCow extends EntityAnimal {

    public EntityCow(World world) {
        super(world);
        this.a(0.9F, 1.3F);
        this.getNavigation().a(true);
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalPanic(this, 2.0D));
        this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D));
        this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.25D, Item.WHEAT.id, false));
        this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.25D));
        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
        this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
    }

    public boolean be() {
        return true;
    }

    protected void ay() {
        super.ay();
        this.getAttributeInstance(GenericAttributes.a).setValue(10.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.20000000298023224D);
    }

    protected String r() {
        return "mob.cow.say";
    }

    protected String aN() {
        return "mob.cow.hurt";
    }

    protected String aO() {
        return "mob.cow.hurt";
    }

    protected void a(int i, int j, int k, int l) {
        this.makeSound("mob.cow.step", 0.15F, 1.0F);
    }

    protected float aZ() {
        return 0.4F;
    }

    protected int getLootId() {
        return Item.LEATHER.id;
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(3) + this.random.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(Item.LEATHER.id, 1);
        }

        j = this.random.nextInt(3) + 1 + this.random.nextInt(1 + i);

        for (k = 0; k < j; ++k) {
            if (this.isBurning()) {
                this.b(Item.COOKED_BEEF.id, 1);
            } else {
                this.b(Item.RAW_BEEF.id, 1);
            }
        }
    }

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (itemstack != null && itemstack.id == Item.BUCKET.id && !entityhuman.abilities.canInstantlyBuild) {
            if (itemstack.count-- == 1) {
                entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, new ItemStack(Item.MILK_BUCKET));
            } else if (!entityhuman.inventory.pickup(new ItemStack(Item.MILK_BUCKET))) {
                entityhuman.drop(new ItemStack(Item.MILK_BUCKET.id, 1, 0));
            }

            return true;
        } else {
            return super.a(entityhuman);
        }
    }

    public EntityCow b(EntityAgeable entityageable) {
        return new EntityCow(this.world);
    }

    public EntityAgeable createChild(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
