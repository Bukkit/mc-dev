package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;

public class TileEntity {

    private static Map a = new HashMap();
    private static Map b = new HashMap();
    public World world;
    public int e;
    public int f;
    public int g;

    public TileEntity() {}

    private static void a(Class oclass, String s) {
        if (b.containsKey(s)) {
            throw new IllegalArgumentException("Duplicate id: " + s);
        } else {
            a.put(s, oclass);
            b.put(oclass, s);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.e = nbttagcompound.e("x");
        this.f = nbttagcompound.e("y");
        this.g = nbttagcompound.e("z");
    }

    public void b(NBTTagCompound nbttagcompound) {
        String s = (String) b.get(this.getClass());

        if (s == null) {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        } else {
            nbttagcompound.setString("id", s);
            nbttagcompound.a("x", this.e);
            nbttagcompound.a("y", this.f);
            nbttagcompound.a("z", this.g);
        }
    }

    public void i_() {}

    public static TileEntity c(NBTTagCompound nbttagcompound) {
        TileEntity tileentity = null;

        try {
            Class oclass = (Class) a.get(nbttagcompound.getString("id"));

            if (oclass != null) {
                tileentity = (TileEntity) oclass.newInstance();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (tileentity != null) {
            tileentity.a(nbttagcompound);
        } else {
            System.out.println("Skipping TileEntity with id " + nbttagcompound.getString("id"));
        }

        return tileentity;
    }

    public void update() {
        if (this.world != null) {
            this.world.b(this.e, this.f, this.g, this);
        }
    }

    public Packet e() {
        return null;
    }

    static {
        a(TileEntityFurnace.class, "Furnace");
        a(TileEntityChest.class, "Chest");
        a(TileEntityDispenser.class, "Trap");
        a(TileEntitySign.class, "Sign");
        a(TileEntityMobSpawner.class, "MobSpawner");
        a(TileEntityNote.class, "Music");
    }
}
