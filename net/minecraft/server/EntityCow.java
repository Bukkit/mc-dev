package net.minecraft.server;

public class EntityCow extends EntityAnimal {

    public EntityCow(World world) {
        super(world);
        this.texture = "/mob/cow.png";
        this.b(0.9F, 1.3F);
    }

    public int getMaxHealth() {
        return 10;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    protected String c_() {
        return "mob.cow";
    }

    protected String m() {
        return "mob.cowhurt";
    }

    protected String n() {
        return "mob.cowhurt";
    }

    protected float o() {
        return 0.4F;
    }

    protected int e() {
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
            if (this.z()) {
                this.b(Item.COOKED_BEEF.id, 1);
            } else {
                this.b(Item.RAW_BEEF.id, 1);
            }
        }
    }

    public boolean b(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (itemstack != null && itemstack.id == Item.BUCKET.id) {
            entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, new ItemStack(Item.MILK_BUCKET));
            return true;
        } else {
            return super.b(entityhuman);
        }
    }

    protected EntityAnimal createChild(EntityAnimal entityanimal) {
        return new EntityCow(this.world);
    }
}
