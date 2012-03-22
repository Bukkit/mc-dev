package net.minecraft.server;

public class EntityVillager extends EntityAgeable implements NPC {

    private int profession;
    private boolean c;
    private boolean g;
    Village village;

    public EntityVillager(World world) {
        this(world, 0);
    }

    public EntityVillager(World world, int i) {
        super(world);
        this.profession = 0;
        this.c = false;
        this.g = false;
        this.village = null;
        this.setProfession(i);
        this.texture = "/mob/villager/villager.png";
        this.bb = 0.5F;
        this.al().b(true);
        this.al().a(true);
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalAvoidPlayer(this, EntityZombie.class, 8.0F, 0.3F, 0.35F));
        this.goalSelector.a(2, new PathfinderGoalMoveIndoors(this));
        this.goalSelector.a(3, new PathfinderGoalRestrictOpenDoor(this));
        this.goalSelector.a(4, new PathfinderGoalOpenDoor(this, true));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.3F));
        this.goalSelector.a(6, new PathfinderGoalMakeLove(this));
        this.goalSelector.a(7, new PathfinderGoalTakeFlower(this));
        this.goalSelector.a(8, new PathfinderGoalPlay(this, 0.32F));
        this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityHuman.class, 3.0F, 1.0F));
        this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityVillager.class, 5.0F, 0.02F));
        this.goalSelector.a(9, new PathfinderGoalRandomStroll(this, 0.3F));
        this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityLiving.class, 8.0F));
    }

    public boolean c_() {
        return true;
    }

    protected void g() {
        if (--this.profession <= 0) {
            this.world.villages.a(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ));
            this.profession = 70 + this.random.nextInt(50);
            this.village = this.world.villages.getClosestVillage(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ), 32);
            if (this.village == null) {
                this.ax();
            } else {
                ChunkCoordinates chunkcoordinates = this.village.getCenter();

                this.b(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, this.village.getSize());
            }
        }

        super.g();
    }

    protected void b() {
        super.b();
        this.datawatcher.a(16, Integer.valueOf(0));
    }

    public int getMaxHealth() {
        return 20;
    }

    public void e() {
        super.e();
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Profession", this.getProfession());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setProfession(nbttagcompound.getInt("Profession"));
    }

    protected boolean n() {
        return false;
    }

    protected String i() {
        return "mob.villager.default";
    }

    protected String j() {
        return "mob.villager.defaulthurt";
    }

    protected String k() {
        return "mob.villager.defaultdeath";
    }

    public void setProfession(int i) {
        this.datawatcher.watch(16, Integer.valueOf(i));
    }

    public int getProfession() {
        return this.datawatcher.getInt(16);
    }

    public boolean A() {
        return this.c;
    }

    public void a(boolean flag) {
        this.c = flag;
    }

    public void b(boolean flag) {
        this.g = flag;
    }

    public boolean C() {
        return this.g;
    }

    public void a(EntityLiving entityliving) {
        super.a(entityliving);
        if (this.village != null && entityliving != null) {
            this.village.a(entityliving);
        }
    }
}
