package net.minecraft.server;

public class BlockSponge extends Block {

    protected BlockSponge(int i) {
        super(i, Material.SPONGE);
        this.textureId = 48;
    }

    public void onPlace(World world, int i, int j, int k) {}

    public void remove(World world, int i, int j, int k) {}
}
