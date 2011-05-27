package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;

public class TileEntity {

    private static Map e = new HashMap();
    private static Map f = new HashMap();
    public World a;
    public int b;
    public int c;
    public int d;

    public TileEntity() {}

    private static void a(Class oclass, String s) {
        if (f.containsKey(s)) {
            throw new IllegalArgumentException("Duplicate id: " + s);
        } else {
            e.put(s, oclass);
            f.put(oclass, s);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.b = nbttagcompound.d("x");
        this.c = nbttagcompound.d("y");
        this.d = nbttagcompound.d("z");
    }

    public void b(NBTTagCompound nbttagcompound) {
        String s = (String) f.get(this.getClass());

        if (s == null) {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        } else {
            nbttagcompound.a("id", s);
            nbttagcompound.a("x", this.b);
            nbttagcompound.a("y", this.c);
            nbttagcompound.a("z", this.d);
        }
    }

    public void b() {}

    public static TileEntity c(NBTTagCompound nbttagcompound) {
        TileEntity tileentity = null;

        try {
            Class oclass = (Class) e.get(nbttagcompound.h("id"));

            if (oclass != null) {
                tileentity = (TileEntity) oclass.newInstance();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (tileentity != null) {
            tileentity.a(nbttagcompound);
        } else {
            System.out.println("Skipping TileEntity with id " + nbttagcompound.h("id"));
        }

        return tileentity;
    }

    public void c() {
        this.a.b(this.b, this.c, this.d, this);
    }

    static {
        a(TileEntityFurnace.class, "Furnace");
        a(TileEntityChest.class, "Chest");
        a(TileEntitySign.class, "Sign");
        a(TileEntityMobSpawner.class, "MobSpawner");
    }
}
