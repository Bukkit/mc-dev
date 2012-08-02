package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldMap extends WorldMapBase {

    public int centerX;
    public int centerZ;
    public byte map;
    public byte scale;
    public byte[] colors = new byte[16384];
    public int f;
    public List g = new ArrayList();
    private Map j = new HashMap();
    public List decorations = new ArrayList();

    public WorldMap(String s) {
        super(s);
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.map = nbttagcompound.getByte("dimension");
        this.centerX = nbttagcompound.getInt("xCenter");
        this.centerZ = nbttagcompound.getInt("zCenter");
        this.scale = nbttagcompound.getByte("scale");
        if (this.scale < 0) {
            this.scale = 0;
        }

        if (this.scale > 4) {
            this.scale = 4;
        }

        short short1 = nbttagcompound.getShort("width");
        short short2 = nbttagcompound.getShort("height");

        if (short1 == 128 && short2 == 128) {
            this.colors = nbttagcompound.getByteArray("colors");
        } else {
            byte[] abyte = nbttagcompound.getByteArray("colors");

            this.colors = new byte[16384];
            int i = (128 - short1) / 2;
            int j = (128 - short2) / 2;

            for (int k = 0; k < short2; ++k) {
                int l = k + j;

                if (l >= 0 || l < 128) {
                    for (int i1 = 0; i1 < short1; ++i1) {
                        int j1 = i1 + i;

                        if (j1 >= 0 || j1 < 128) {
                            this.colors[j1 + l * 128] = abyte[i1 + k * short1];
                        }
                    }
                }
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.setByte("dimension", this.map);
        nbttagcompound.setInt("xCenter", this.centerX);
        nbttagcompound.setInt("zCenter", this.centerZ);
        nbttagcompound.setByte("scale", this.scale);
        nbttagcompound.setShort("width", (short) 128);
        nbttagcompound.setShort("height", (short) 128);
        nbttagcompound.setByteArray("colors", this.colors);
    }

    public void a(EntityHuman entityhuman, ItemStack itemstack) {
        if (!this.j.containsKey(entityhuman)) {
            WorldMapHumanTracker worldmaphumantracker = new WorldMapHumanTracker(this, entityhuman);

            this.j.put(entityhuman, worldmaphumantracker);
            this.g.add(worldmaphumantracker);
        }

        this.decorations.clear();

        for (int i = 0; i < this.g.size(); ++i) {
            WorldMapHumanTracker worldmaphumantracker1 = (WorldMapHumanTracker) this.g.get(i);

            if (!worldmaphumantracker1.trackee.dead && worldmaphumantracker1.trackee.inventory.c(itemstack)) {
                float f = (float) (worldmaphumantracker1.trackee.locX - (double) this.centerX) / (float) (1 << this.scale);
                float f1 = (float) (worldmaphumantracker1.trackee.locZ - (double) this.centerZ) / (float) (1 << this.scale);
                byte b0 = 64;
                byte b1 = 64;

                if (f >= (float) (-b0) && f1 >= (float) (-b1) && f <= (float) b0 && f1 <= (float) b1) {
                    byte b2 = 0;
                    byte b3 = (byte) ((int) ((double) (f * 2.0F) + 0.5D));
                    byte b4 = (byte) ((int) ((double) (f1 * 2.0F) + 0.5D));
                    byte b5 = (byte) ((int) ((double) worldmaphumantracker1.trackee.yaw * 16.0D / 360.0D));

                    if (this.map < 0) {
                        int j = this.f / 10;

                        b5 = (byte) (j * j * 34187121 + j * 121 >> 15 & 15);
                    }

                    if (worldmaphumantracker1.trackee.dimension == this.map) {
                        this.decorations.add(new WorldMapDecoration(this, b2, b3, b4, b5));
                    }
                }
            } else {
                this.j.remove(worldmaphumantracker1.trackee);
                this.g.remove(worldmaphumantracker1);
            }
        }
    }

    public byte[] getUpdatePacket(ItemStack itemstack, World world, EntityHuman entityhuman) {
        WorldMapHumanTracker worldmaphumantracker = (WorldMapHumanTracker) this.j.get(entityhuman);

        return worldmaphumantracker == null ? null : worldmaphumantracker.a(itemstack);
    }

    public void flagDirty(int i, int j, int k) {
        super.a();

        for (int l = 0; l < this.g.size(); ++l) {
            WorldMapHumanTracker worldmaphumantracker = (WorldMapHumanTracker) this.g.get(l);

            if (worldmaphumantracker.b[i] < 0 || worldmaphumantracker.b[i] > j) {
                worldmaphumantracker.b[i] = j;
            }

            if (worldmaphumantracker.c[i] < 0 || worldmaphumantracker.c[i] < k) {
                worldmaphumantracker.c[i] = k;
            }
        }
    }
}
