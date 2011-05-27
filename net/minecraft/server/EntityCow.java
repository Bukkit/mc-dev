package net.minecraft.server;

public class EntityCow extends EntityAnimal {

    public boolean a = false;

    public EntityCow(World world) {
        super(world);
        this.aH = "/mob/cow.png";
        this.a(0.9F, 1.3F);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    protected String d() {
        return "mob.cow";
    }

    protected String e() {
        return "mob.cowhurt";
    }

    protected String f() {
        return "mob.cowhurt";
    }

    protected float h() {
        return 0.4F;
    }

    protected int g() {
        return Item.LEATHER.aW;
    }

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.am.b();

        if (itemstack != null && itemstack.c == Item.BUCKET.aW) {
            entityhuman.am.a(entityhuman.am.d, new ItemStack(Item.MILK_BUCKET));
            return true;
        } else {
            return false;
        }
    }
}
