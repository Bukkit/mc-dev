package net.minecraft.server;

public class BlockJukeBox extends Block {

    protected BlockJukeBox(int i, int j) {
        super(i, j, Material.c);
    }

    public int a(int i) {
        return this.bh + (i == 1 ? 1 : 0);
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        int l = world.b(i, j, k);

        if (l > 0) {
            this.f(world, i, j, k, l);
            return true;
        } else {
            return false;
        }
    }

    public void f(World world, int i, int j, int k, int l) {
        world.a((String) null, i, j, k);
        world.b(i, j, k, 0);
        int i1 = Item.GOLD_RECORD.aW + l - 1;
        float f = 0.7F;
        double d0 = (double) (world.l.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
        double d1 = (double) (world.l.nextFloat() * f) + (double) (1.0F - f) * 0.2D + 0.6D;
        double d2 = (double) (world.l.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
        EntityItem entityitem = new EntityItem(world, (double) i + d0, (double) j + d1, (double) k + d2, new ItemStack(i1));

        entityitem.c = 10;
        world.a((Entity) entityitem);
    }

    public void a(World world, int i, int j, int k, int l, float f) {
        if (!world.z) {
            if (l > 0) {
                this.f(world, i, j, k, l);
            }

            super.a(world, i, j, k, l, f);
        }
    }
}
