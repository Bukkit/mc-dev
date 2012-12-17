package net.minecraft.server;

class TileEntityMobSpawnerData extends WeightedRandomChoice {

    public final NBTTagCompound b;
    public final String c;

    final TileEntityMobSpawner d;

    public TileEntityMobSpawnerData(TileEntityMobSpawner tileentitymobspawner, NBTTagCompound nbttagcompound) {
        super(nbttagcompound.getInt("Weight"));
        this.d = tileentitymobspawner;
        this.b = nbttagcompound.getCompound("Properties");
        this.c = nbttagcompound.getString("Type");
    }

    public TileEntityMobSpawnerData(TileEntityMobSpawner tileentitymobspawner, NBTTagCompound nbttagcompound, String s) {
        super(1);
        this.d = tileentitymobspawner;
        this.b = nbttagcompound;
        this.c = s;
    }

    public NBTTagCompound a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.setCompound("Properties", this.b);
        nbttagcompound.setString("Type", this.c);
        nbttagcompound.setInt("Weight", this.a);
        return nbttagcompound;
    }
}
