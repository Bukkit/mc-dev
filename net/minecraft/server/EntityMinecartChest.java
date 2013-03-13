package net.minecraft.server;

public class EntityMinecartChest extends EntityMinecartContainer {

    public EntityMinecartChest(World world) {
        super(world);
    }

    public EntityMinecartChest(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    public void a(DamageSource damagesource) {
        super.a(damagesource);
        this.a(Block.CHEST.id, 1, 0.0F);
    }

    public int getSize() {
        return 27;
    }

    public int getType() {
        return 1;
    }

    public Block n() {
        return Block.CHEST;
    }

    public int r() {
        return 8;
    }
}
