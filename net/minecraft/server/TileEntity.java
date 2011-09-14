package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;

public class TileEntity {

    private static Map a = new HashMap();
    private static Map b = new HashMap();
    public World world;
    public int x;
    public int y;
    public int z;
    protected boolean m;
    public int n = -1;
    public Block o;

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
        this.x = nbttagcompound.e("x");
        this.y = nbttagcompound.e("y");
        this.z = nbttagcompound.e("z");
    }

    public void b(NBTTagCompound nbttagcompound) {
        String s = (String) b.get(this.getClass());

        if (s == null) {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        } else {
            nbttagcompound.setString("id", s);
            nbttagcompound.a("x", this.x);
            nbttagcompound.a("y", this.y);
            nbttagcompound.a("z", this.z);
        }
    }

    public void h_() {}

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

    public int j() {
        if (this.n == -1) {
            this.n = this.world.getData(this.x, this.y, this.z);
        }

        return this.n;
    }

    public void update() {
        if (this.world != null) {
            this.n = this.world.getData(this.x, this.y, this.z);
            this.world.b(this.x, this.y, this.z, this);
        }
    }

    public Packet l() {
        return null;
    }

    public boolean m() {
        return this.m;
    }

    public void i() {
        this.m = true;
    }

    public void n() {
        this.m = false;
    }

    public void b(int i, int j) {}

    public void g() {
        this.o = null;
        this.n = -1;
    }

    static {
        a(TileEntityFurnace.class, "Furnace");
        a(TileEntityChest.class, "Chest");
        a(TileEntityRecordPlayer.class, "RecordPlayer");
        a(TileEntityDispenser.class, "Trap");
        a(TileEntitySign.class, "Sign");
        a(TileEntityMobSpawner.class, "MobSpawner");
        a(TileEntityNote.class, "Music");
        a(TileEntityPiston.class, "Piston");
    }
}
