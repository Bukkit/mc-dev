package net.minecraft.server;

import java.util.Random;

public class BlockWeb extends Block {

    public BlockWeb() {
        super(Material.WEB);
        this.a(CreativeModeTab.c);
    }

    public void a(World world, int i, int j, int k, Entity entity) {
        entity.as();
    }

    public boolean c() {
        return false;
    }

    public AxisAlignedBB a(World world, int i, int j, int k) {
        return null;
    }

    public int b() {
        return 1;
    }

    public boolean d() {
        return false;
    }

    public Item getDropType(int i, Random random, int j) {
        return Items.STRING;
    }

    protected boolean E() {
        return true;
    }
}
