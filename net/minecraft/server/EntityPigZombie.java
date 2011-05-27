package net.minecraft.server;

import java.util.List;

public class EntityPigZombie extends EntityZombie {

    private int angerLevel = 0;
    private int soundDelay = 0;
    private static final ItemStack f = new ItemStack(Item.GOLD_SWORD, 1);

    public EntityPigZombie(World world) {
        super(world);
        this.texture = "/mob/pigzombie.png";
        this.az = 0.5F;
        this.damage = 5;
        this.by = true;
    }

    public void f_() {
        this.az = this.target != null ? 0.95F : 0.5F;
        if (this.soundDelay > 0 && --this.soundDelay == 0) {
            this.world.makeSound(this, "mob.zombiepig.zpigangry", this.i() * 2.0F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.f_();
    }

    public boolean b() {
        return this.world.spawnMonsters > 0 && this.world.containsEntity(this.boundingBox) && this.world.getEntities(this, this.boundingBox).size() == 0 && !this.world.b(this.boundingBox);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Anger", (short) this.angerLevel);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.angerLevel = nbttagcompound.d("Anger");
    }

    protected Entity findTarget() {
        return this.angerLevel == 0 ? null : super.findTarget();
    }

    public void r() {
        super.r();
    }

    public boolean damageEntity(Entity entity, int i) {
        if (entity instanceof EntityHuman) {
            List list = this.world.b((Entity) this, this.boundingBox.b(32.0D, 32.0D, 32.0D));

            for (int j = 0; j < list.size(); ++j) {
                Entity entity1 = (Entity) list.get(j);

                if (entity1 instanceof EntityPigZombie) {
                    EntityPigZombie entitypigzombie = (EntityPigZombie) entity1;

                    entitypigzombie.d(entity);
                }
            }

            this.d(entity);
        }

        return super.damageEntity(entity, i);
    }

    private void d(Entity entity) {
        this.target = entity;
        this.angerLevel = 400 + this.random.nextInt(400);
        this.soundDelay = this.random.nextInt(40);
    }

    protected String e() {
        return "mob.zombiepig.zpig";
    }

    protected String f() {
        return "mob.zombiepig.zpighurt";
    }

    protected String g() {
        return "mob.zombiepig.zpigdeath";
    }

    protected int h() {
        return Item.GRILLED_PORK.id;
    }
}
