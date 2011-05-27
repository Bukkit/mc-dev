package net.minecraft.server;

public class EntityCow extends EntityAnimal {

    public EntityCow(World world) {
        super(world);
        this.texture = "/mob/cow.png";
        this.b(0.9F, 1.3F);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    protected String g() {
        return "mob.cow";
    }

    protected String h() {
        return "mob.cowhurt";
    }

    protected String i() {
        return "mob.cowhurt";
    }

    protected float k() {
        return 0.4F;
    }

    protected int j() {
        return Item.LEATHER.id;
    }

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (itemstack != null && itemstack.id == Item.BUCKET.id) {
            entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, new ItemStack(Item.MILK_BUCKET));
            return true;
        } else {
            return false;
        }
    }
}
