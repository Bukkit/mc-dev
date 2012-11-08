package net.minecraft.server;

import java.util.Random;

public class BlockWeb extends Block {

    public BlockWeb(int i, int j) {
        super(i, j, Material.WEB);
        this.a(CreativeModeTab.c);
    }

    public void a(World world, int i, int j, int k, Entity entity) {
        entity.am();
    }

    public boolean c() {
        return false;
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        return null;
    }

    public int d() {
        return 1;
    }

    public boolean b() {
        return false;
    }

    public int getDropType(int i, Random random, int j) {
        return Item.STRING.id;
    }

    protected boolean s_() {
        return true;
    }
}
