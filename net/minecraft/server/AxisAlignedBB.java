package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class AxisAlignedBB {

    private static List g = new ArrayList();
    private static int h = 0;
    public double a;
    public double b;
    public double c;
    public double d;
    public double e;
    public double f;

    public static AxisAlignedBB a(double d0, double d1, double d2, double d3, double d4, double d5) {
        return new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
    }

    public static void a() {
        h = 0;
    }

    public static AxisAlignedBB b(double d0, double d1, double d2, double d3, double d4, double d5) {
        if (h >= g.size()) {
            g.add(a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D));
        }

        return ((AxisAlignedBB) g.get(h++)).c(d0, d1, d2, d3, d4, d5);
    }

    private AxisAlignedBB(double d0, double d1, double d2, double d3, double d4, double d5) {
        this.a = d0;
        this.b = d1;
        this.c = d2;
        this.d = d3;
        this.e = d4;
        this.f = d5;
    }

    public AxisAlignedBB c(double d0, double d1, double d2, double d3, double d4, double d5) {
        this.a = d0;
        this.b = d1;
        this.c = d2;
        this.d = d3;
        this.e = d4;
        this.f = d5;
        return this;
    }

    public AxisAlignedBB a(double d0, double d1, double d2) {
        double d3 = this.a;
        double d4 = this.b;
        double d5 = this.c;
        double d6 = this.d;
        double d7 = this.e;
        double d8 = this.f;

        if (d0 < 0.0D) {
            d3 += d0;
        }

        if (d0 > 0.0D) {
            d6 += d0;
        }

        if (d1 < 0.0D) {
            d4 += d1;
        }

        if (d1 > 0.0D) {
            d7 += d1;
        }

        if (d2 < 0.0D) {
            d5 += d2;
        }

        if (d2 > 0.0D) {
            d8 += d2;
        }

        return b(d3, d4, d5, d6, d7, d8);
    }

    public AxisAlignedBB b(double d0, double d1, double d2) {
        double d3 = this.a - d0;
        double d4 = this.b - d1;
        double d5 = this.c - d2;
        double d6 = this.d + d0;
        double d7 = this.e + d1;
        double d8 = this.f + d2;

        return b(d3, d4, d5, d6, d7, d8);
    }

    public AxisAlignedBB c(double d0, double d1, double d2) {
        return b(this.a + d0, this.b + d1, this.c + d2, this.d + d0, this.e + d1, this.f + d2);
    }

    public double a(AxisAlignedBB axisalignedbb, double d0) {
        if (axisalignedbb.e > this.b && axisalignedbb.b < this.e) {
            if (axisalignedbb.f > this.c && axisalignedbb.c < this.f) {
                double d1;

                if (d0 > 0.0D && axisalignedbb.d <= this.a) {
                    d1 = this.a - axisalignedbb.d;
                    if (d1 < d0) {
                        d0 = d1;
                    }
                }

                if (d0 < 0.0D && axisalignedbb.a >= this.d) {
                    d1 = this.d - axisalignedbb.a;
                    if (d1 > d0) {
                        d0 = d1;
                    }
                }

                return d0;
            } else {
                return d0;
            }
        } else {
            return d0;
        }
    }

    public double b(AxisAlignedBB axisalignedbb, double d0) {
        if (axisalignedbb.d > this.a && axisalignedbb.a < this.d) {
            if (axisalignedbb.f > this.c && axisalignedbb.c < this.f) {
                double d1;

                if (d0 > 0.0D && axisalignedbb.e <= this.b) {
                    d1 = this.b - axisalignedbb.e;
                    if (d1 < d0) {
                        d0 = d1;
                    }
                }

                if (d0 < 0.0D && axisalignedbb.b >= this.e) {
                    d1 = this.e - axisalignedbb.b;
                    if (d1 > d0) {
                        d0 = d1;
                    }
                }

                return d0;
            } else {
                return d0;
            }
        } else {
            return d0;
        }
    }

    public double c(AxisAlignedBB axisalignedbb, double d0) {
        if (axisalignedbb.d > this.a && axisalignedbb.a < this.d) {
            if (axisalignedbb.e > this.b && axisalignedbb.b < this.e) {
                double d1;

                if (d0 > 0.0D && axisalignedbb.f <= this.c) {
                    d1 = this.c - axisalignedbb.f;
                    if (d1 < d0) {
                        d0 = d1;
                    }
                }

                if (d0 < 0.0D && axisalignedbb.c >= this.f) {
                    d1 = this.f - axisalignedbb.c;
                    if (d1 > d0) {
                        d0 = d1;
                    }
                }

                return d0;
            } else {
                return d0;
            }
        } else {
            return d0;
        }
    }

    public boolean a(AxisAlignedBB axisalignedbb) {
        return axisalignedbb.d > this.a && axisalignedbb.a < this.d ? (axisalignedbb.e > this.b && axisalignedbb.b < this.e ? axisalignedbb.f > this.c && axisalignedbb.c < this.f : false) : false;
    }

    public AxisAlignedBB d(double d0, double d1, double d2) {
        this.a += d0;
        this.b += d1;
        this.c += d2;
        this.d += d0;
        this.e += d1;
        this.f += d2;
        return this;
    }

    public boolean a(Vec3D vec3d) {
        return vec3d.a > this.a && vec3d.a < this.d ? (vec3d.b > this.b && vec3d.b < this.e ? vec3d.c > this.c && vec3d.c < this.f : false) : false;
    }

    public AxisAlignedBB shrink(double d0, double d1, double d2) {
        double d3 = this.a + d0;
        double d4 = this.b + d1;
        double d5 = this.c + d2;
        double d6 = this.d - d0;
        double d7 = this.e - d1;
        double d8 = this.f - d2;

        return b(d3, d4, d5, d6, d7, d8);
    }

    public AxisAlignedBB clone() {
        return b(this.a, this.b, this.c, this.d, this.e, this.f);
    }

    public MovingObjectPosition a(Vec3D vec3d, Vec3D vec3d1) {
        Vec3D vec3d2 = vec3d.a(vec3d1, this.a);
        Vec3D vec3d3 = vec3d.a(vec3d1, this.d);
        Vec3D vec3d4 = vec3d.b(vec3d1, this.b);
        Vec3D vec3d5 = vec3d.b(vec3d1, this.e);
        Vec3D vec3d6 = vec3d.c(vec3d1, this.c);
        Vec3D vec3d7 = vec3d.c(vec3d1, this.f);

        if (!this.b(vec3d2)) {
            vec3d2 = null;
        }

        if (!this.b(vec3d3)) {
            vec3d3 = null;
        }

        if (!this.c(vec3d4)) {
            vec3d4 = null;
        }

        if (!this.c(vec3d5)) {
            vec3d5 = null;
        }

        if (!this.d(vec3d6)) {
            vec3d6 = null;
        }

        if (!this.d(vec3d7)) {
            vec3d7 = null;
        }

        Vec3D vec3d8 = null;

        if (vec3d2 != null && (vec3d8 == null || vec3d.b(vec3d2) < vec3d.b(vec3d8))) {
            vec3d8 = vec3d2;
        }

        if (vec3d3 != null && (vec3d8 == null || vec3d.b(vec3d3) < vec3d.b(vec3d8))) {
            vec3d8 = vec3d3;
        }

        if (vec3d4 != null && (vec3d8 == null || vec3d.b(vec3d4) < vec3d.b(vec3d8))) {
            vec3d8 = vec3d4;
        }

        if (vec3d5 != null && (vec3d8 == null || vec3d.b(vec3d5) < vec3d.b(vec3d8))) {
            vec3d8 = vec3d5;
        }

        if (vec3d6 != null && (vec3d8 == null || vec3d.b(vec3d6) < vec3d.b(vec3d8))) {
            vec3d8 = vec3d6;
        }

        if (vec3d7 != null && (vec3d8 == null || vec3d.b(vec3d7) < vec3d.b(vec3d8))) {
            vec3d8 = vec3d7;
        }

        if (vec3d8 == null) {
            return null;
        } else {
            byte b0 = -1;

            if (vec3d8 == vec3d2) {
                b0 = 4;
            }

            if (vec3d8 == vec3d3) {
                b0 = 5;
            }

            if (vec3d8 == vec3d4) {
                b0 = 0;
            }

            if (vec3d8 == vec3d5) {
                b0 = 1;
            }

            if (vec3d8 == vec3d6) {
                b0 = 2;
            }

            if (vec3d8 == vec3d7) {
                b0 = 3;
            }

            return new MovingObjectPosition(0, 0, 0, b0, vec3d8);
        }
    }

    private boolean b(Vec3D vec3d) {
        return vec3d == null ? false : vec3d.b >= this.b && vec3d.b <= this.e && vec3d.c >= this.c && vec3d.c <= this.f;
    }

    private boolean c(Vec3D vec3d) {
        return vec3d == null ? false : vec3d.a >= this.a && vec3d.a <= this.d && vec3d.c >= this.c && vec3d.c <= this.f;
    }

    private boolean d(Vec3D vec3d) {
        return vec3d == null ? false : vec3d.a >= this.a && vec3d.a <= this.d && vec3d.b >= this.b && vec3d.b <= this.e;
    }

    public void b(AxisAlignedBB axisalignedbb) {
        this.a = axisalignedbb.a;
        this.b = axisalignedbb.b;
        this.c = axisalignedbb.c;
        this.d = axisalignedbb.d;
        this.e = axisalignedbb.e;
        this.f = axisalignedbb.f;
    }

    public String toString() {
        return "box[" + this.a + ", " + this.b + ", " + this.c + " -> " + this.d + ", " + this.e + ", " + this.f + "]";
    }
}
