package net.minecraft.server;

public class BlockHalfTransparent extends Block {

    private boolean a;
    private String b;

    protected BlockHalfTransparent(String s, Material material, boolean flag) {
        super(material);
        this.a = flag;
        this.b = s;
    }

    public boolean c() {
        return false;
    }
}
