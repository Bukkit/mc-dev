package net.minecraft.server;

public class TileEntityLightDetector extends TileEntity {

    public TileEntityLightDetector() {}

    public void h() {
        if (this.world != null && !this.world.isStatic && this.world.getTime() % 20L == 0L) {
            this.q = this.q();
            if (this.q != null && this.q instanceof BlockDaylightDetector) {
                ((BlockDaylightDetector) this.q).i_(this.world, this.x, this.y, this.z);
            }
        }
    }
}
