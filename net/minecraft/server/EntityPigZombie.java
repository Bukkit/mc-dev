package net.minecraft.server;

import java.util.List;
import java.util.UUID;

public class EntityPigZombie extends EntityZombie {

    private static final UUID bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
    private static final AttributeModifier br = (new AttributeModifier(bq, "Attacking speed boost", 0.45D, 0)).a(false);
    private int angerLevel;
    private int soundDelay;
    private Entity bu;

    public EntityPigZombie(World world) {
        super(world);
        this.fireProof = true;
    }

    protected void az() {
        super.az();
        this.getAttributeInstance(bp).setValue(0.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.5D);
        this.getAttributeInstance(GenericAttributes.e).setValue(5.0D);
    }

    protected boolean bf() {
        return false;
    }

    public void l_() {
        if (this.bu != this.target && !this.world.isStatic) {
            AttributeInstance attributeinstance = this.getAttributeInstance(GenericAttributes.d);

            attributeinstance.b(br);
            if (this.target != null) {
                attributeinstance.a(br);
            }
        }

        this.bu = this.target;
        if (this.soundDelay > 0 && --this.soundDelay == 0) {
            this.makeSound("mob.zombiepig.zpigangry", this.ba() * 2.0F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.l_();
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

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (this.isInvulnerable()) {
            return false;
        } else {
            Entity entity = damagesource.getEntity();

            if (entity instanceof EntityHuman) {
                List list = this.world.getEntities(this, this.boundingBox.grow(32.0D, 32.0D, 32.0D));

                for (int i = 0; i < list.size(); ++i) {
                    Entity entity1 = (Entity) list.get(i);

                    if (entity1 instanceof EntityPigZombie) {
                        EntityPigZombie entitypigzombie = (EntityPigZombie) entity1;

                        entitypigzombie.c(entity);
                    }
                }

                this.c(entity);
            }

            return super.damageEntity(damagesource, f);
        }
    }

    private void c(Entity entity) {
        this.target = entity;
        this.angerLevel = 400 + this.random.nextInt(400);
        this.soundDelay = this.random.nextInt(40);
    }

    protected String r() {
        return "mob.zombiepig.zpig";
    }

    protected String aO() {
        return "mob.zombiepig.zpighurt";
    }

    protected String aP() {
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

    protected void bw() {
        this.setEquipment(0, new ItemStack(Item.GOLD_SWORD));
    }

    public GroupDataEntity a(GroupDataEntity groupdataentity) {
        super.a(groupdataentity);
        this.setVillager(false);
        return groupdataentity;
    }
}
