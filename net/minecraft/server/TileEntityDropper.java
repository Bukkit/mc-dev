package net.minecraft.server;

public class TileEntityDropper extends TileEntityDispenser {

    public TileEntityDropper() {}

    public String getInventoryName() {
        return this.k_() ? this.a : "container.dropper";
    }
}
