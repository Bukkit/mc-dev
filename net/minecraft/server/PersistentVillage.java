package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PersistentVillage extends PersistentBase {

    private World world;
    private final List b = new ArrayList();
    private final List c = new ArrayList();
    private final List villages = new ArrayList();
    private int time;

    public PersistentVillage(String s) {
        super(s);
    }

    public PersistentVillage(World world) {
        super("villages");
        this.world = world;
        this.c();
    }

    public void a(World world) {
        this.world = world;
        Iterator iterator = this.villages.iterator();

        while (iterator.hasNext()) {
            Village village = (Village) iterator.next();

            village.a(world);
        }
    }

    public void a(int i, int j, int k) {
        if (this.b.size() <= 64) {
            if (!this.d(i, j, k)) {
                this.b.add(new ChunkCoordinates(i, j, k));
            }
        }
    }

    public void tick() {
        ++this.time;
        Iterator iterator = this.villages.iterator();

        while (iterator.hasNext()) {
            Village village = (Village) iterator.next();

            village.tick(this.time);
        }

        this.e();
        this.f();
        this.g();
        if (this.time % 400 == 0) {
            this.c();
        }
    }

    private void e() {
        Iterator iterator = this.villages.iterator();

        while (iterator.hasNext()) {
            Village village = (Village) iterator.next();

            if (village.isAbandoned()) {
                iterator.remove();
                this.c();
            }
        }
    }

    public List getVillages() {
        return this.villages;
    }

    public Village getClosestVillage(int i, int j, int k, int l) {
        Village village = null;
        float f = Float.MAX_VALUE;
        Iterator iterator = this.villages.iterator();

        while (iterator.hasNext()) {
            Village village1 = (Village) iterator.next();
            float f1 = village1.getCenter().e(i, j, k);

            if (f1 < f) {
                float f2 = (float) (l + village1.getSize());

                if (f1 <= f2 * f2) {
                    village = village1;
                    f = f1;
                }
            }
        }

        return village;
    }

    private void f() {
        if (!this.b.isEmpty()) {
            this.a((ChunkCoordinates) this.b.remove(0));
        }
    }

    private void g() {
        int i = 0;

        while (i < this.c.size()) {
            VillageDoor villagedoor = (VillageDoor) this.c.get(i);
            boolean flag = false;
            Iterator iterator = this.villages.iterator();

            while (true) {
                if (iterator.hasNext()) {
                    Village village = (Village) iterator.next();
                    int j = (int) village.getCenter().e(villagedoor.locX, villagedoor.locY, villagedoor.locZ);
                    int k = 32 + village.getSize();

                    if (j > k * k) {
                        continue;
                    }

                    village.addDoor(villagedoor);
                    flag = true;
                }

                if (!flag) {
                    Village village1 = new Village(this.world);

                    village1.addDoor(villagedoor);
                    this.villages.add(village1);
                    this.c();
                }

                ++i;
                break;
            }
        }

        this.c.clear();
    }

    private void a(ChunkCoordinates chunkcoordinates) {
        byte b0 = 16;
        byte b1 = 4;
        byte b2 = 16;

        for (int i = chunkcoordinates.x - b0; i < chunkcoordinates.x + b0; ++i) {
            for (int j = chunkcoordinates.y - b1; j < chunkcoordinates.y + b1; ++j) {
                for (int k = chunkcoordinates.z - b2; k < chunkcoordinates.z + b2; ++k) {
                    if (this.e(i, j, k)) {
                        VillageDoor villagedoor = this.b(i, j, k);

                        if (villagedoor == null) {
                            this.c(i, j, k);
                        } else {
                            villagedoor.addedTime = this.time;
                        }
                    }
                }
            }
        }
    }

    private VillageDoor b(int i, int j, int k) {
        Iterator iterator = this.c.iterator();

        VillageDoor villagedoor;

        do {
            if (!iterator.hasNext()) {
                iterator = this.villages.iterator();

                VillageDoor villagedoor1;

                do {
                    if (!iterator.hasNext()) {
                        return null;
                    }

                    Village village = (Village) iterator.next();

                    villagedoor1 = village.e(i, j, k);
                } while (villagedoor1 == null);

                return villagedoor1;
            }

            villagedoor = (VillageDoor) iterator.next();
        } while (villagedoor.locX != i || villagedoor.locZ != k || Math.abs(villagedoor.locY - j) > 1);

        return villagedoor;
    }

    private void c(int i, int j, int k) {
        int l = ((BlockDoor) Blocks.WOODEN_DOOR).e(this.world, i, j, k);
        int i1;
        int j1;

        if (l != 0 && l != 2) {
            i1 = 0;

            for (j1 = -5; j1 < 0; ++j1) {
                if (this.world.i(i, j, k + j1)) {
                    --i1;
                }
            }

            for (j1 = 1; j1 <= 5; ++j1) {
                if (this.world.i(i, j, k + j1)) {
                    ++i1;
                }
            }

            if (i1 != 0) {
                this.c.add(new VillageDoor(i, j, k, 0, i1 > 0 ? -2 : 2, this.time));
            }
        } else {
            i1 = 0;

            for (j1 = -5; j1 < 0; ++j1) {
                if (this.world.i(i + j1, j, k)) {
                    --i1;
                }
            }

            for (j1 = 1; j1 <= 5; ++j1) {
                if (this.world.i(i + j1, j, k)) {
                    ++i1;
                }
            }

            if (i1 != 0) {
                this.c.add(new VillageDoor(i, j, k, i1 > 0 ? -2 : 2, 0, this.time));
            }
        }
    }

    private boolean d(int i, int j, int k) {
        Iterator iterator = this.b.iterator();

        ChunkCoordinates chunkcoordinates;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            chunkcoordinates = (ChunkCoordinates) iterator.next();
        } while (chunkcoordinates.x != i || chunkcoordinates.y != j || chunkcoordinates.z != k);

        return true;
    }

    private boolean e(int i, int j, int k) {
        return this.world.getType(i, j, k) == Blocks.WOODEN_DOOR;
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.time = nbttagcompound.getInt("Tick");
        NBTTagList nbttaglist = nbttagcompound.getList("Villages", 10);

        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.get(i);
            Village village = new Village();

            village.a(nbttagcompound1);
            this.villages.add(village);
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.setInt("Tick", this.time);
        NBTTagList nbttaglist = new NBTTagList();
        Iterator iterator = this.villages.iterator();

        while (iterator.hasNext()) {
            Village village = (Village) iterator.next();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

            village.b(nbttagcompound1);
            nbttaglist.add(nbttagcompound1);
        }

        nbttagcompound.set("Villages", nbttaglist);
    }
}
