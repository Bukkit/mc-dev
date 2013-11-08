package net.minecraft.server;

public class TileEntityLightDetector extends TileEntity {

    public TileEntityLightDetector() {}

    public void h() {
        if (this.world != null && !this.world.isStatic && this.world.getTime() % 20L == 0L) {
            this.h = this.q();
            if (this.h instanceof BlockDaylightDetector) {
                ((BlockDaylightDetector) this.h).e(this.world, this.x, this.y, this.z);
            }
        }
    }
}
