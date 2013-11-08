package net.minecraft.server;

public abstract class EntityTameableAnimal extends EntityAnimal implements EntityOwnable {

    protected PathfinderGoalSit bp = new PathfinderGoalSit(this);

    public EntityTameableAnimal(World world) {
        super(world);
    }

    protected void c() {
        super.c();
        this.datawatcher.a(16, Byte.valueOf((byte) 0));
        this.datawatcher.a(17, "");
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.getOwnerName() == null) {
            nbttagcompound.setString("Owner", "");
        } else {
            nbttagcompound.setString("Owner", this.getOwnerName());
        }

        nbttagcompound.setBoolean("Sitting", this.isSitting());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        String s = nbttagcompound.getString("Owner");

        if (s.length() > 0) {
            this.setOwnerName(s);
            this.setTamed(true);
        }

        this.bp.setSitting(nbttagcompound.getBoolean("Sitting"));
        this.setSitting(nbttagcompound.getBoolean("Sitting"));
    }

    protected void i(boolean flag) {
        String s = "heart";

        if (!flag) {
            s = "smoke";
        }

        for (int i = 0; i < 7; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;

            this.world.addParticle(s, this.locX + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.locY + 0.5D + (double) (this.random.nextFloat() * this.length), this.locZ + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
        }
    }

    public boolean isTamed() {
        return (this.datawatcher.getByte(16) & 4) != 0;
    }

    public void setTamed(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 | 4)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & -5)));
        }
    }

    public boolean isSitting() {
        return (this.datawatcher.getByte(16) & 1) != 0;
    }

    public void setSitting(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    public String getOwnerName() {
        return this.datawatcher.getString(17);
    }

    public void setOwnerName(String s) {
        this.datawatcher.watch(17, s);
    }

    public EntityLiving getOwner() {
        return this.world.a(this.getOwnerName());
    }

    public PathfinderGoalSit getGoalSit() {
        return this.bp;
    }

    public boolean a(EntityLiving entityliving, EntityLiving entityliving1) {
        return true;
    }

    public ScoreboardTeamBase getScoreboardTeam() {
        if (this.isTamed()) {
            EntityLiving entityliving = this.getOwner();

            if (entityliving != null) {
                return entityliving.getScoreboardTeam();
            }
        }

        return super.getScoreboardTeam();
    }

    public boolean c(EntityLiving entityliving) {
        if (this.isTamed()) {
            EntityLiving entityliving1 = this.getOwner();

            if (entityliving == entityliving1) {
                return true;
            }

            if (entityliving1 != null) {
                return entityliving1.c(entityliving);
            }
        }

        return super.c(entityliving);
    }

    public Entity getOwner() {
        return this.getOwner();
    }
}
