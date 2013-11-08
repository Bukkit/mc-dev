package net.minecraft.server;

public class EntityMinecartRideable extends EntityMinecartAbstract {

    public EntityMinecartRideable(World world) {
        super(world);
    }

    public EntityMinecartRideable(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    public boolean c(EntityHuman entityhuman) {
        if (this.passenger != null && this.passenger instanceof EntityHuman && this.passenger != entityhuman) {
            return true;
        } else if (this.passenger != null && this.passenger != entityhuman) {
            return false;
        } else {
            if (!this.world.isStatic) {
                entityhuman.mount(this);
            }

            return true;
        }
    }

    public int m() {
        return 0;
    }
}
