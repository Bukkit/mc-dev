package net.minecraft.server;

public class BlockMinecartTrack extends BlockMinecartTrackAbstract {

    protected BlockMinecartTrack(int i) {
        super(i, false);
    }

    protected void a(World world, int i, int j, int k, int l, int i1, int j1) {
        if (j1 > 0 && Block.byId[j1].isPowerSource() && (new MinecartTrackLogic(this, world, i, j, k)).a() == 3) {
            this.a(world, i, j, k, false);
        }
    }
}
