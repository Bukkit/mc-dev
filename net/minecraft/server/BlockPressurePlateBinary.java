package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class BlockPressurePlateBinary extends BlockPressurePlateAbstract {

    private EnumMobType a;

    protected BlockPressurePlateBinary(String s, Material material, EnumMobType enummobtype) {
        super(s, material);
        this.a = enummobtype;
    }

    protected int d(int i) {
        return i > 0 ? 1 : 0;
    }

    protected int c(int i) {
        return i == 1 ? 15 : 0;
    }

    protected int e(World world, int i, int j, int k) {
        List list = null;

        if (this.a == EnumMobType.EVERYTHING) {
            list = world.getEntities((Entity) null, this.a(i, j, k));
        }

        if (this.a == EnumMobType.MOBS) {
            list = world.a(EntityLiving.class, this.a(i, j, k));
        }

        if (this.a == EnumMobType.PLAYERS) {
            list = world.a(EntityHuman.class, this.a(i, j, k));
        }

        if (list != null && !list.isEmpty()) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                if (!entity.az()) {
                    return 15;
                }
            }
        }

        return 0;
    }
}
