package net.minecraft.server;

import java.util.List;

public class EntityPigZombie extends EntityZombie {

    private int a = 0;
    private int b = 0;
    private static final ItemStack c = new ItemStack(Item.GOLD_SWORD, 1);

    public EntityPigZombie(World world) {
        super(world);
        this.aQ = "/mob/pigzombie.png";
        this.bD = 0.5F;
        this.f = 5;
        this.ae = true;
    }

    public void b_() {
        this.bD = this.aj != null ? 0.95F : 0.5F;
        if (this.b > 0 && --this.b == 0) {
            this.l.a(this, "mob.zombiepig.zpigangry", this.h() * 2.0F, ((this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.b_();
    }

    public boolean a() {
        return this.l.k > 0 && this.l.a(this.z) && this.l.a((Entity) this, this.z).size() == 0 && !this.l.b(this.z);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Anger", (short) this.a);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.c("Anger");
    }

    protected Entity k() {
        return this.a == 0 ? null : super.k();
    }

    public void G() {
        super.G();
    }

    public boolean a(Entity entity, int i) {
        if (entity instanceof EntityHuman) {
            List list = this.l.b((Entity) this, this.z.b(32.0D, 32.0D, 32.0D));

            for (int j = 0; j < list.size(); ++j) {
                Entity entity1 = (Entity) list.get(j);

                if (entity1 instanceof EntityPigZombie) {
                    EntityPigZombie entitypigzombie = (EntityPigZombie) entity1;

                    entitypigzombie.g(entity);
                }
            }

            this.g(entity);
        }

        return super.a(entity, i);
    }

    private void g(Entity entity) {
        this.aj = entity;
        this.a = 400 + this.W.nextInt(400);
        this.b = this.W.nextInt(40);
    }

    protected String d() {
        return "mob.zombiepig.zpig";
    }

    protected String e() {
        return "mob.zombiepig.zpighurt";
    }

    protected String f() {
        return "mob.zombiepig.zpigdeath";
    }

    protected int g() {
        return Item.GRILLED_PORK.aW;
    }
}
