package net.minecraft.server;

public class TileEntityMobSpawnerData extends WeightedRandomChoice {

    public final NBTTagCompound b;
    public final String c;
    final MobSpawnerAbstract d;

    public TileEntityMobSpawnerData(MobSpawnerAbstract mobspawnerabstract, NBTTagCompound nbttagcompound) {
        super(nbttagcompound.getInt("Weight"));
        this.d = mobspawnerabstract;
        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Properties");
        String s = nbttagcompound.getString("Type");

        if (s.equals("Minecart")) {
            if (nbttagcompound1 != null) {
                switch (nbttagcompound1.getInt("Type")) {
                case 0:
                    s = "MinecartRideable";
                    break;

                case 1:
                    s = "MinecartChest";
                    break;

                case 2:
                    s = "MinecartFurnace";
                }
            } else {
                s = "MinecartRideable";
            }
        }

        this.b = nbttagcompound1;
        this.c = s;
    }

    public TileEntityMobSpawnerData(MobSpawnerAbstract mobspawnerabstract, NBTTagCompound nbttagcompound, String s) {
        super(1);
        this.d = mobspawnerabstract;
        if (s.equals("Minecart")) {
            if (nbttagcompound != null) {
                switch (nbttagcompound.getInt("Type")) {
                case 0:
                    s = "MinecartRideable";
                    break;

                case 1:
                    s = "MinecartChest";
                    break;

                case 2:
                    s = "MinecartFurnace";
                }
            } else {
                s = "MinecartRideable";
            }
        }

        this.b = nbttagcompound;
        this.c = s;
    }

    public NBTTagCompound a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.set("Properties", this.b);
        nbttagcompound.setString("Type", this.c);
        nbttagcompound.setInt("Weight", this.a);
        return nbttagcompound;
    }
}
