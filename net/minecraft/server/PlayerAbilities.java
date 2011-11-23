package net.minecraft.server;

public class PlayerAbilities {

    public boolean isInvulnerable = false;
    public boolean isFlying = false;
    public boolean canFly = false;
    public boolean canInstantlyBuild = false;

    public PlayerAbilities() {}

    public void a(NBTTagCompound nbttagcompound) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();

        nbttagcompound1.a("invulnerable", this.isInvulnerable);
        nbttagcompound1.a("flying", this.isInvulnerable);
        nbttagcompound1.a("mayfly", this.canFly);
        nbttagcompound1.a("instabuild", this.canInstantlyBuild);
        nbttagcompound.a("abilities", (NBTBase) nbttagcompound1);
    }

    public void b(NBTTagCompound nbttagcompound) {
        if (nbttagcompound.hasKey("abilities")) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.l("abilities");

            this.isInvulnerable = nbttagcompound1.n("invulnerable");
            this.isFlying = nbttagcompound1.n("flying");
            this.canFly = nbttagcompound1.n("mayfly");
            this.canInstantlyBuild = nbttagcompound1.n("instabuild");
        }
    }
}
