package net.minecraft.server;

public class BlockFlowers extends BlockPlant {

    private static final String[][] M = new String[][] { { "flower_dandelion"}, { "flower_rose", "flower_blue_orchid", "flower_allium", "flower_houstonia", "flower_tulip_red", "flower_tulip_orange", "flower_tulip_white", "flower_tulip_pink", "flower_oxeye_daisy"}};
    public static final String[] a = new String[] { "poppy", "blueOrchid", "allium", "houstonia", "tulipRed", "tulipOrange", "tulipWhite", "tulipPink", "oxeyeDaisy"};
    public static final String[] b = new String[] { "dandelion"};
    private int O;

    protected BlockFlowers(int i) {
        super(Material.PLANT);
        this.O = i;
    }

    public int getDropData(int i) {
        return i;
    }

    public static BlockFlowers e(String s) {
        String[] astring = b;
        int i = astring.length;

        int j;
        String s1;

        for (j = 0; j < i; ++j) {
            s1 = astring[j];
            if (s1.equals(s)) {
                return Blocks.YELLOW_FLOWER;
            }
        }

        astring = a;
        i = astring.length;

        for (j = 0; j < i; ++j) {
            s1 = astring[j];
            if (s1.equals(s)) {
                return Blocks.RED_ROSE;
            }
        }

        return null;
    }

    public static int f(String s) {
        int i;

        for (i = 0; i < b.length; ++i) {
            if (b[i].equals(s)) {
                return i;
            }
        }

        for (i = 0; i < a.length; ++i) {
            if (a[i].equals(s)) {
                return i;
            }
        }

        return 0;
    }
}
