package net.minecraft.server;

public class BlockTransparent extends Block {

    protected boolean P;

    protected BlockTransparent(Material material, boolean flag) {
        super(material);
        this.P = flag;
    }

    public boolean c() {
        return false;
    }
}
