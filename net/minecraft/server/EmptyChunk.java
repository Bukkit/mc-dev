package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class EmptyChunk extends Chunk {

    public EmptyChunk(World world, int i, int j) {
        super(world, i, j);
    }

    public boolean a(int i, int j) {
        return i == this.x && j == this.z;
    }

    public int b(int i, int j) {
        return 0;
    }

    public void initLighting() {}

    public int getTypeId(int i, int j, int k) {
        return 0;
    }

    public int b(int i, int j, int k) {
        return 255;
    }

    public boolean a(int i, int j, int k, int l, int i1) {
        return true;
    }

    public int getData(int i, int j, int k) {
        return 0;
    }

    public boolean b(int i, int j, int k, int l) {
        return false;
    }

    public int getBrightness(EnumSkyBlock enumskyblock, int i, int j, int k) {
        return 0;
    }

    public void a(EnumSkyBlock enumskyblock, int i, int j, int k, int l) {}

    public int c(int i, int j, int k, int l) {
        return 0;
    }

    public void a(Entity entity) {}

    public void b(Entity entity) {}

    public void a(Entity entity, int i) {}

    public boolean d(int i, int j, int k) {
        return false;
    }

    public TileEntity e(int i, int j, int k) {
        return null;
    }

    public void a(TileEntity tileentity) {}

    public void a(int i, int j, int k, TileEntity tileentity) {}

    public void f(int i, int j, int k) {}

    public void addEntities() {}

    public void removeEntities() {}

    public void e() {}

    public void a(Entity entity, AxisAlignedBB axisalignedbb, List list, IEntitySelector ientityselector) {}

    public void a(Class oclass, AxisAlignedBB axisalignedbb, List list, IEntitySelector ientityselector) {}

    public boolean a(boolean flag) {
        return false;
    }

    public Random a(long i) {
        return new Random(this.world.getSeed() + (long) (this.x * this.x * 4987142) + (long) (this.x * 5947611) + (long) (this.z * this.z) * 4392871L + (long) (this.z * 389711) ^ i);
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean c(int i, int j) {
        return true;
    }
}
