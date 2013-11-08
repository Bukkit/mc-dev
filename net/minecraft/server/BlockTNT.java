package net.minecraft.server;

import java.util.Random;

public class BlockTNT extends Block {

    public BlockTNT() {
        super(Material.TNT);
        this.a(CreativeModeTab.d);
    }

    public void onPlace(World world, int i, int j, int k) {
        super.onPlace(world, i, j, k);
        if (world.isBlockIndirectlyPowered(i, j, k)) {
            this.postBreak(world, i, j, k, 1);
            world.setAir(i, j, k);
        }
    }

    public void doPhysics(World world, int i, int j, int k, Block block) {
        if (world.isBlockIndirectlyPowered(i, j, k)) {
            this.postBreak(world, i, j, k, 1);
            world.setAir(i, j, k);
        }
    }

    public int a(Random random) {
        return 1;
    }

    public void wasExploded(World world, int i, int j, int k, Explosion explosion) {
        if (!world.isStatic) {
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), explosion.c());

            entitytntprimed.fuseTicks = world.random.nextInt(entitytntprimed.fuseTicks / 4) + entitytntprimed.fuseTicks / 8;
            world.addEntity(entitytntprimed);
        }
    }

    public void postBreak(World world, int i, int j, int k, int l) {
        this.a(world, i, j, k, l, (EntityLiving) null);
    }

    public void a(World world, int i, int j, int k, int l, EntityLiving entityliving) {
        if (!world.isStatic) {
            if ((l & 1) == 1) {
                EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), entityliving);

                world.addEntity(entitytntprimed);
                world.makeSound(entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
            }
        }
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (entityhuman.bD() != null && entityhuman.bD().getItem() == Items.FLINT_AND_STEEL) {
            this.a(world, i, j, k, 1, entityhuman);
            world.setAir(i, j, k);
            entityhuman.bD().damage(1, entityhuman);
            return true;
        } else {
            return super.interact(world, i, j, k, entityhuman, l, f, f1, f2);
        }
    }

    public void a(World world, int i, int j, int k, Entity entity) {
        if (entity instanceof EntityArrow && !world.isStatic) {
            EntityArrow entityarrow = (EntityArrow) entity;

            if (entityarrow.isBurning()) {
                this.a(world, i, j, k, 1, entityarrow.shooter instanceof EntityLiving ? (EntityLiving) entityarrow.shooter : null);
                world.setAir(i, j, k);
            }
        }
    }

    public boolean a(Explosion explosion) {
        return false;
    }
}
