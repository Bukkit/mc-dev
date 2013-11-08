package net.minecraft.server;

public class MaterialMapColor {

    public static final MaterialMapColor[] a = new MaterialMapColor[64];
    public static final MaterialMapColor b = new MaterialMapColor(0, 0);
    public static final MaterialMapColor c = new MaterialMapColor(1, 8368696);
    public static final MaterialMapColor d = new MaterialMapColor(2, 16247203);
    public static final MaterialMapColor e = new MaterialMapColor(3, 10987431);
    public static final MaterialMapColor f = new MaterialMapColor(4, 16711680);
    public static final MaterialMapColor g = new MaterialMapColor(5, 10526975);
    public static final MaterialMapColor h = new MaterialMapColor(6, 10987431);
    public static final MaterialMapColor i = new MaterialMapColor(7, 31744);
    public static final MaterialMapColor j = new MaterialMapColor(8, 16777215);
    public static final MaterialMapColor k = new MaterialMapColor(9, 10791096);
    public static final MaterialMapColor l = new MaterialMapColor(10, 12020271);
    public static final MaterialMapColor m = new MaterialMapColor(11, 7368816);
    public static final MaterialMapColor n = new MaterialMapColor(12, 4210943);
    public static final MaterialMapColor o = new MaterialMapColor(13, 6837042);
    public static final MaterialMapColor p = new MaterialMapColor(14, 16776437);
    public static final MaterialMapColor q = new MaterialMapColor(15, 14188339);
    public static final MaterialMapColor r = new MaterialMapColor(16, 11685080);
    public static final MaterialMapColor s = new MaterialMapColor(17, 6724056);
    public static final MaterialMapColor t = new MaterialMapColor(18, 15066419);
    public static final MaterialMapColor u = new MaterialMapColor(19, 8375321);
    public static final MaterialMapColor v = new MaterialMapColor(20, 15892389);
    public static final MaterialMapColor w = new MaterialMapColor(21, 5000268);
    public static final MaterialMapColor x = new MaterialMapColor(22, 10066329);
    public static final MaterialMapColor y = new MaterialMapColor(23, 5013401);
    public static final MaterialMapColor z = new MaterialMapColor(24, 8339378);
    public static final MaterialMapColor A = new MaterialMapColor(25, 3361970);
    public static final MaterialMapColor B = new MaterialMapColor(26, 6704179);
    public static final MaterialMapColor C = new MaterialMapColor(27, 6717235);
    public static final MaterialMapColor D = new MaterialMapColor(28, 10040115);
    public static final MaterialMapColor E = new MaterialMapColor(29, 1644825);
    public static final MaterialMapColor F = new MaterialMapColor(30, 16445005);
    public static final MaterialMapColor G = new MaterialMapColor(31, 6085589);
    public static final MaterialMapColor H = new MaterialMapColor(32, 4882687);
    public static final MaterialMapColor I = new MaterialMapColor(33, '\ud93a');
    public static final MaterialMapColor J = new MaterialMapColor(34, 1381407);
    public static final MaterialMapColor K = new MaterialMapColor(35, 7340544);
    public final int L;
    public final int M;

    private MaterialMapColor(int i, int j) {
        if (i >= 0 && i <= 63) {
            this.M = i;
            this.L = j;
            a[i] = this;
        } else {
            throw new IndexOutOfBoundsException("Map colour ID must be between 0 and 63 (inclusive)");
        }
    }

    public static MaterialMapColor a(int i) {
        switch (BlockCloth.c(i)) {
        case 0:
            return E;

        case 1:
            return D;

        case 2:
            return C;

        case 3:
            return B;

        case 4:
            return A;

        case 5:
            return z;

        case 6:
            return y;

        case 7:
            return x;

        case 8:
            return w;

        case 9:
            return v;

        case 10:
            return u;

        case 11:
            return t;

        case 12:
            return s;

        case 13:
            return r;

        case 14:
            return q;

        case 15:
            return j;

        default:
            return b;
        }
    }
}
