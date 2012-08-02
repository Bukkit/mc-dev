package net.minecraft.server;

public class BlockSponge extends Block {

    protected BlockSponge(int i) {
        super(i, Material.SPONGE);
        this.textureId = 48;
        this.a(CreativeModeTab.b);
    }
}
