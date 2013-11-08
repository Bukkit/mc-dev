package net.minecraft.server;

import java.util.Random;

public class BlockCommand extends BlockContainer {

    public BlockCommand() {
        super(Material.ORE);
    }

    public TileEntity a(World world, int i) {
        return new TileEntityCommand();
    }

    public void doPhysics(World world, int i, int j, int k, Block block) {
        if (!world.isStatic) {
            boolean flag = world.isBlockIndirectlyPowered(i, j, k);
            int l = world.getData(i, j, k);
            boolean flag1 = (l & 1) != 0;

            if (flag && !flag1) {
                world.setData(i, j, k, l | 1, 4);
                world.a(i, j, k, this, this.a(world));
            } else if (!flag && flag1) {
                world.setData(i, j, k, l & -2, 4);
            }
        }
    }

    public void a(World world, int i, int j, int k, Random random) {
        TileEntity tileentity = world.getTileEntity(i, j, k);

        if (tileentity != null && tileentity instanceof TileEntityCommand) {
            CommandBlockListenerAbstract commandblocklistenerabstract = ((TileEntityCommand) tileentity).a();

            commandblocklistenerabstract.a(world);
            world.f(i, j, k, this);
        }
    }

    public int a(World world) {
        return 1;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        TileEntityCommand tileentitycommand = (TileEntityCommand) world.getTileEntity(i, j, k);

        if (tileentitycommand != null) {
            entityhuman.a((TileEntity) tileentitycommand);
        }

        return true;
    }

    public boolean M() {
        return true;
    }

    public int g(World world, int i, int j, int k, int l) {
        TileEntity tileentity = world.getTileEntity(i, j, k);

        return tileentity != null && tileentity instanceof TileEntityCommand ? ((TileEntityCommand) tileentity).a().g() : 0;
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemstack) {
        TileEntityCommand tileentitycommand = (TileEntityCommand) world.getTileEntity(i, j, k);

        if (itemstack.hasName()) {
            tileentitycommand.a().b(itemstack.getName());
        }
    }

    public int a(Random random) {
        return 0;
    }
}
