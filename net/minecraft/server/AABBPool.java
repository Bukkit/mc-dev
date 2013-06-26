package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class AABBPool {

    private final int a;
    private final int b;
    private final List pool = new ArrayList();
    private int d;
    private int largestSize;
    private int resizeTime;

    public AABBPool(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public AxisAlignedBB a(double d0, double d1, double d2, double d3, double d4, double d5) {
        AxisAlignedBB axisalignedbb;

        if (this.d >= this.pool.size()) {
            axisalignedbb = new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
            this.pool.add(axisalignedbb);
        } else {
            axisalignedbb = (AxisAlignedBB) this.pool.get(this.d);
            axisalignedbb.b(d0, d1, d2, d3, d4, d5);
        }

        ++this.d;
        return axisalignedbb;
    }

    public void a() {
        if (this.d > this.largestSize) {
            this.largestSize = this.d;
        }

        if (this.resizeTime++ == this.a) {
            int i = Math.max(this.largestSize, this.pool.size() - this.b);

            while (this.pool.size() > i) {
                this.pool.remove(i);
            }

            this.largestSize = 0;
            this.resizeTime = 0;
        }

        this.d = 0;
    }

    public int c() {
        return this.pool.size();
    }

    public int d() {
        return this.d;
    }
}
