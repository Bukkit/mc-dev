package net.minecraft.server;

import java.util.List;

public class EntityPigZombie extends EntityZombie {

    private int angerLevel = 0;
    private int soundDelay = 0;

    public EntityPigZombie(World world) {
        super(world);
        this.texture = "/mob/pigzombie.png";
        this.bH = 0.5F;
        this.fireProof = true;
    }

    protected boolean be() {
        return false;
    }

    public void j_() {
        this.bH = this.target != null ? 0.95F : 0.5F;
        if (this.soundDelay > 0 && --this.soundDelay == 0) {
            this.makeSound("mob.zombiepig.zpigangry", this.aX() * 2.0F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.j_();
    }

    public boolean canSpawn() {
        return this.world.difficulty > 0 && this.world.b(this.boundingBox) && this.world.getCubes(this, this.boundingBox).isEmpty() && !this.world.containsLiquid(this.boundingBox);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setShort("Anger", (short) this.angerLevel);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.angerLevel = nbttagcompound.getShort("Anger");
    }

    protected Entity findTarget() {
        return this.angerLevel == 0 ? null : super.findTarget();
    }

    public boolean damageEntity(DamageSource damagesource, int i) {
        if (this.isInvulnerable()) {
            return false;
        } else {
            Entity entity = damagesource.getEntity();

            if (entity instanceof EntityHuman) {
                List list = this.world.getEntities(this, this.boundingBox.grow(32.0D, 32.0D, 32.0D));

                for (int j = 0; j < list.size(); ++j) {
                    Entity entity1 = (Entity) list.get(j);

                    if (entity1 instanceof EntityPigZombie) {
                        EntityPigZombie entitypigzombie = (EntityPigZombie) entity1;

                        entitypigzombie.p(entity);
                    }
                }

                this.p(entity);
            }

            return super.damageEntity(damagesource, i);
        }
    }

    private void p(Entity entity) {
        this.target = entity;
        this.angerLevel = 400 + this.random.nextInt(400);
        this.soundDelay = this.random.nextInt(40);
    }

    protected String aY() {
        return "mob.zombiepig.zpig";
    }

    protected String aZ() {
        return "mob.zombiepig.zpighurt";
    }

    protected String ba() {
        return "mob.zombiepig.zpigdeath";
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(2 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(Item.ROTTEN_FLESH.id, 1);
        }

        j = this.random.nextInt(2 + i);

        for (k = 0; k < j; ++k) {
            this.b(Item.GOLD_NUGGET.id, 1);
        }
    }

    public boolean a(EntityHuman entityhuman) {
        return false;
    }

    protected void l(int i) {
        this.b(Item.GOLD_INGOT.id, 1);
    }

    protected int getLootId() {
        return Item.ROTTEN_FLESH.id;
    }

    protected void bE() {
        this.setEquipment(0, new ItemStack(Item.GOLD_SWORD));
    }

    public void bG() {
        super.bG();
        this.setVillager(false);
    }

    public int c(Entity entity) {
        ItemStack itemstack = this.bD();
        int i = 5;

        if (itemstack != null) {
            i += itemstack.a((Entity) this);
        }

        return i;
    }
}
