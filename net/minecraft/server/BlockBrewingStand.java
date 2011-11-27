package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class BlockBrewingStand extends BlockContainer {

    private Random a = new Random();

    public BlockBrewingStand(int i) {
        super(i, Material.ORE);
        this.textureId = 157;
    }

    public boolean a() {
        return false;
    }

    public int c() {
        return 25;
    }

    public TileEntity a_() {
        return new TileEntityBrewingStand();
    }

    public boolean b() {
        return false;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist) {
        this.a(0.4375F, 0.0F, 0.4375F, 0.5625F, 0.875F, 0.5625F);
        super.a(world, i, j, k, axisalignedbb, arraylist);
        this.f();
        super.a(world, i, j, k, axisalignedbb, arraylist);
    }

    public void f() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
        if (world.isStatic) {
            return true;
        } else {
            TileEntityBrewingStand tileentitybrewingstand = (TileEntityBrewingStand) world.getTileEntity(i, j, k);

            if (tileentitybrewingstand != null) {
                entityhuman.a(tileentitybrewingstand);
            }

            return true;
        }
    }

    public void remove(World world, int i, int j, int k) {
        TileEntity tileentity = world.getTileEntity(i, j, k);

        if (tileentity != null && tileentity instanceof TileEntityBrewingStand) {
            TileEntityBrewingStand tileentitybrewingstand = (TileEntityBrewingStand) tileentity;

            for (int l = 0; l < tileentitybrewingstand.getSize(); ++l) {
                ItemStack itemstack = tileentitybrewingstand.getItem(l);

                if (itemstack != null) {
                    float f = this.a.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.a.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.a.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.count > 0) {
                        int i1 = this.a.nextInt(21) + 10;

                        if (i1 > itemstack.count) {
                            i1 = itemstack.count;
                        }

                        itemstack.count -= i1;
                        EntityItem entityitem = new EntityItem(world, (double) ((float) i + f), (double) ((float) j + f1), (double) ((float) k + f2), new ItemStack(itemstack.id, i1, itemstack.getData()));
                        float f3 = 0.05F;

                        entityitem.motX = (double) ((float) this.a.nextGaussian() * f3);
                        entityitem.motY = (double) ((float) this.a.nextGaussian() * f3 + 0.2F);
                        entityitem.motZ = (double) ((float) this.a.nextGaussian() * f3);
                        world.addEntity(entityitem);
                    }
                }
            }
        }

        super.remove(world, i, j, k);
    }

    public int getDropType(int i, Random random, int j) {
        return Item.BREWING_STAND.id;
    }
}
