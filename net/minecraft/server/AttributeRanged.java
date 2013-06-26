package net.minecraft.server;

public class AttributeRanged extends AttributeBase {

    private final double a;
    private final double b;
    private String c;

    public AttributeRanged(String s, double d0, double d1, double d2) {
        super(s, d0);
        this.a = d1;
        this.b = d2;
        if (d1 > d2) {
            throw new IllegalArgumentException("Minimum value cannot be bigger than maximum value!");
        } else if (d0 < d1) {
            throw new IllegalArgumentException("Default value cannot be lower than minimum value!");
        } else if (d0 > d2) {
            throw new IllegalArgumentException("Default value cannot be bigger than maximum value!");
        }
    }

    public AttributeRanged a(String s) {
        this.c = s;
        return this;
    }

    public String f() {
        return this.c;
    }

    public double a(double d0) {
        if (d0 < this.a) {
            d0 = this.a;
        }

        if (d0 > this.b) {
            d0 = this.b;
        }

        return d0;
    }
}
