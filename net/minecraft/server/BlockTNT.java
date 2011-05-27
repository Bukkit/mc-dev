package net.minecraft.server;

import java.util.Random;

public class BlockTNT extends Block {

    public BlockTNT(int i, int j) {
        super(i, j, Material.p);
    }

    public int a(int i) {
        return i == 0 ? this.bg + 2 : (i == 1 ? this.bg + 1 : this.bg);
    }

    public void b(World world, int i, int j, int k, int l) {
        if (l > 0 && Block.m[l].c() && world.o(i, j, k)) {
            this.a(world, i, j, k, 0);
            world.d(i, j, k, 0);
        }
    }

    public int a(Random random) {
        return 0;
    }

    public void c(World world, int i, int j, int k) {
        EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F));

        entitytntprimed.a = world.l.nextInt(entitytntprimed.a / 4) + entitytntprimed.a / 8;
        world.a((Entity) entitytntprimed);
    }

    public void a(World world, int i, int j, int k, int l) {
        if (!world.z) {
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F));

            world.a((Entity) entitytntprimed);
            world.a(entitytntprimed, "random.fuse", 1.0F, 1.0F);
        }
    }
}
