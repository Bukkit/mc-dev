package net.minecraft.server;

public class EntityCow extends EntityAnimal {

    public EntityCow(World world) {
        super(world);
        this.aP = "/mob/cow.png";
        this.a(0.9F, 1.3F);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    protected String e() {
        return "mob.cow";
    }

    protected String f() {
        return "mob.cowhurt";
    }

    protected String g() {
        return "mob.cowhurt";
    }

    protected float i() {
        return 0.4F;
    }

    protected int h() {
        return Item.LEATHER.ba;
    }

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.an.e();

        if (itemstack != null && itemstack.c == Item.BUCKET.ba) {
            entityhuman.an.a(entityhuman.an.c, new ItemStack(Item.MILK_BUCKET));
            return true;
        } else {
            return false;
        }
    }
}
