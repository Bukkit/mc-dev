package net.minecraft.server;

public class EntityVillager extends EntityCreature implements NPC {

    private int profession;

    public EntityVillager(World world) {
        this(world, 0);
    }

    public EntityVillager(World world, int i) {
        super(world);
        this.profession = i;
        this.y();
        this.aY = 0.5F;
    }

    public int getMaxHealth() {
        return 20;
    }

    public void d() {
        super.d();
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Profession", this.profession);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.profession = nbttagcompound.getInt("Profession");
        this.y();
    }

    private void y() {
        this.texture = "/mob/villager/villager.png";
        if (this.profession == 0) {
            this.texture = "/mob/villager/farmer.png";
        }

        if (this.profession == 1) {
            this.texture = "/mob/villager/librarian.png";
        }

        if (this.profession == 2) {
            this.texture = "/mob/villager/priest.png";
        }

        if (this.profession == 3) {
            this.texture = "/mob/villager/smith.png";
        }

        if (this.profession == 4) {
            this.texture = "/mob/villager/butcher.png";
        }
    }

    protected boolean d_() {
        return false;
    }

    protected String c_() {
        return "mob.villager.default";
    }

    protected String m() {
        return "mob.villager.defaulthurt";
    }

    protected String n() {
        return "mob.villager.defaultdeath";
    }
}
