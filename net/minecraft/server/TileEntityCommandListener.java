package net.minecraft.server;

class TileEntityCommandListener extends CommandBlockListenerAbstract {

    final TileEntityCommand a;

    TileEntityCommandListener(TileEntityCommand tileentitycommand) {
        this.a = tileentitycommand;
    }

    public ChunkCoordinates getChunkCoordinates() {
        return new ChunkCoordinates(this.a.x, this.a.y, this.a.z);
    }

    public World getWorld() {
        return this.a.getWorld();
    }

    public void a(String s) {
        super.a(s);
        this.a.update();
    }

    public void e() {
        this.a.getWorld().notify(this.a.x, this.a.y, this.a.z);
    }
}
