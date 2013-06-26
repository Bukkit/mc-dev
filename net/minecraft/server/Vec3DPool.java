package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class Vec3DPool {

    private final int a;
    private final int b;
    private final List pool = new ArrayList();
    private int position;
    private int largestSize;
    private int resizeTime;

    public Vec3DPool(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public Vec3D create(double d0, double d1, double d2) {
        if (this.e()) {
            return new Vec3D(this, d0, d1, d2);
        } else {
            Vec3D vec3d;

            if (this.position >= this.pool.size()) {
                vec3d = new Vec3D(this, d0, d1, d2);
                this.pool.add(vec3d);
            } else {
                vec3d = (Vec3D) this.pool.get(this.position);
                vec3d.b(d0, d1, d2);
            }

            ++this.position;
            return vec3d;
        }
    }

    public void a() {
        if (!this.e()) {
            if (this.position > this.largestSize) {
                this.largestSize = this.position;
            }

            if (this.resizeTime++ == this.a) {
                int i = Math.max(this.largestSize, this.pool.size() - this.b);

                while (this.pool.size() > i) {
                    this.pool.remove(i);
                }

                this.largestSize = 0;
                this.resizeTime = 0;
            }

            this.position = 0;
        }
    }

    public int c() {
        return this.pool.size();
    }

    public int d() {
        return this.position;
    }

    private boolean e() {
        return this.b < 0 || this.a < 0;
    }
}
