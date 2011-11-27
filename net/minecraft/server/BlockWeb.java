package net.minecraft.server;

import java.util.Random;

public class BlockWeb extends Block {

    public BlockWeb(int i, int j) {
        super(i, j, Material.WEB);
    }

    public void a(World world, int i, int j, int k, Entity entity) {
        entity.s();
    }

    public boolean a() {
        return false;
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        return null;
    }

    public int c() {
        return 1;
    }

    public boolean b() {
        return false;
    }

    public int getDropType(int i, Random random, int j) {
        return Item.STRING.id;
    }
}
