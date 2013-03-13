package net.minecraft.server;

public class TileEntityDropper extends TileEntityDispenser {

    public TileEntityDropper() {}

    public String getName() {
        return this.c() ? this.a : "container.dropper";
    }
}
