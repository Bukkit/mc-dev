package net.minecraft.server;

public class BlockMinecartTrack extends BlockMinecartTrackAbstract {

    protected BlockMinecartTrack() {
        super(false);
    }

    protected void a(World world, int i, int j, int k, int l, int i1, Block block) {
        if (block.isPowerSource() && (new MinecartTrackLogic(this, world, i, j, k)).a() == 3) {
            this.a(world, i, j, k, false);
        }
    }
}
