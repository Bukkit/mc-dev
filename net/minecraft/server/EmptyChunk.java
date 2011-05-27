package net.minecraft.server;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EmptyChunk extends Chunk {

    public EmptyChunk(World world, int i, int j) {
        super(world, i, j);
        this.p = true;
    }

    public EmptyChunk(World world, byte[] abyte, int i, int j) {
        super(world, abyte, i, j);
        this.p = true;
    }

    public boolean a(int i, int j) {
        return i == this.j && j == this.k;
    }

    public int b(int i, int j) {
        return 0;
    }

    public void a() {}

    public void b() {}

    public void c() {}

    public int a(int i, int j, int k) {
        return 0;
    }

    public boolean a(int i, int j, int k, int l, int i1) {
        return true;
    }

    public boolean a(int i, int j, int k, int l) {
        return true;
    }

    public int b(int i, int j, int k) {
        return 0;
    }

    public void b(int i, int j, int k, int l) {}

    public int a(EnumSkyBlock enumskyblock, int i, int j, int k) {
        return 0;
    }

    public void a(EnumSkyBlock enumskyblock, int i, int j, int k, int l) {}

    public int c(int i, int j, int k, int l) {
        return 0;
    }

    public void a(Entity entity) {}

    public void b(Entity entity) {}

    public void a(Entity entity, int i) {}

    public boolean c(int i, int j, int k) {
        return false;
    }

    public TileEntity d(int i, int j, int k) {
        return null;
    }

    public void a(TileEntity tileentity) {}

    public void a(int i, int j, int k, TileEntity tileentity) {}

    public void e(int i, int j, int k) {}

    public void d() {}

    public void e() {}

    public void f() {}

    public void a(Entity entity, AxisAlignedBB axisalignedbb, List list) {}

    public void a(Class oclass, AxisAlignedBB axisalignedbb, List list) {}

    public boolean a(boolean flag) {
        return false;
    }

    public int a(byte[] abyte, int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1 = l - i;
        int i2 = i1 - j;
        int j2 = j1 - k;
        int k2 = l1 * i2 * j2;
        int l2 = k2 + k2 / 2 * 3;

        Arrays.fill(abyte, k1, k1 + l2, (byte) 0);
        return l2;
    }

    public Random a(long i) {
        return new Random(this.d.j() + (long) (this.j * this.j * 4987142) + (long) (this.j * 5947611) + (long) (this.k * this.k) * 4392871L + (long) (this.k * 389711) ^ i);
    }

    public boolean g() {
        return true;
    }
}
