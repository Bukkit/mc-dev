package net.minecraft.server;

import java.util.Iterator;

public class BlockPressurePlateWeighted extends BlockPressurePlateAbstract {

    private final int a;

    protected BlockPressurePlateWeighted(int i, String s, Material material, int j) {
        super(i, s, material);
        this.a = j;
    }

    protected int e(World world, int i, int j, int k) {
        int l = 0;
        Iterator iterator = world.a(EntityItem.class, this.a(i, j, k)).iterator();

        while (iterator.hasNext()) {
            EntityItem entityitem = (EntityItem) iterator.next();

            l += entityitem.getItemStack().count;
            if (l >= this.a) {
                break;
            }
        }

        if (l <= 0) {
            return 0;
        } else {
            float f = (float) Math.min(this.a, l) / (float) this.a;

            return MathHelper.f(f * 15.0F);
        }
    }

    protected int c(int i) {
        return i;
    }

    protected int d(int i) {
        return i;
    }

    public int a(World world) {
        return 10;
    }
}
