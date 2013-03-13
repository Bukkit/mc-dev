package net.minecraft.server;

public class BlockHalfTransparant extends Block {

    private boolean a;
    private String b;

    protected BlockHalfTransparant(int i, String s, Material material, boolean flag) {
        super(i, material);
        this.a = flag;
        this.b = s;
    }

    public boolean c() {
        return false;
    }
}
