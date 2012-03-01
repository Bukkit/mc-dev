package net.minecraft.server;

public class BlockRedstoneLamp extends Block {

    private final boolean a;

    public BlockRedstoneLamp(int i, boolean flag) {
        super(i, 211, Material.BUILDABLE_GLASS);
        this.a = flag;
        if (flag) {
            this.a(1.0F);
            ++this.textureId;
        }
    }

    public void onPlace(World world, int i, int j, int k) {
        if (!world.isStatic) {
            if (this.a && !world.isBlockIndirectlyPowered(i, j, k)) {
                world.setTypeId(i, j, k, Block.REDSTONE_LAMP_OFF.id);
            } else if (!this.a && world.isBlockIndirectlyPowered(i, j, k)) {
                world.setTypeId(i, j, k, Block.REDSTONE_LAMP_ON.id);
            }
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (!world.isStatic) {
            if (this.a && !world.isBlockIndirectlyPowered(i, j, k)) {
                world.setTypeId(i, j, k, Block.REDSTONE_LAMP_OFF.id);
            } else if (!this.a && world.isBlockIndirectlyPowered(i, j, k)) {
                world.setTypeId(i, j, k, Block.REDSTONE_LAMP_ON.id);
            }
        }
    }
}
