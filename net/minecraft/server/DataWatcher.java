package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.minecraft.util.org.apache.commons.lang3.ObjectUtils;

public class DataWatcher {

    private final Entity a;
    private boolean b = true;
    private static final HashMap c = new HashMap();
    private final Map d = new HashMap();
    private boolean e;
    private ReadWriteLock f = new ReentrantReadWriteLock();

    public DataWatcher(Entity entity) {
        this.a = entity;
    }

    public void a(int i, Object object) {
        Integer integer = (Integer) c.get(object.getClass());

        if (integer == null) {
            throw new IllegalArgumentException("Unknown data type: " + object.getClass());
        } else if (i > 31) {
            throw new IllegalArgumentException("Data value id is too big with " + i + "! (Max is " + 31 + ")");
        } else if (this.d.containsKey(Integer.valueOf(i))) {
            throw new IllegalArgumentException("Duplicate id value for " + i + "!");
        } else {
            WatchableObject watchableobject = new WatchableObject(integer.intValue(), i, object);

            this.f.writeLock().lock();
            this.d.put(Integer.valueOf(i), watchableobject);
            this.f.writeLock().unlock();
            this.b = false;
        }
    }

    public void a(int i, int j) {
        WatchableObject watchableobject = new WatchableObject(j, i, null);

        this.f.writeLock().lock();
        this.d.put(Integer.valueOf(i), watchableobject);
        this.f.writeLock().unlock();
        this.b = false;
    }

    public byte getByte(int i) {
        return ((Byte) this.i(i).b()).byteValue();
    }

    public short getShort(int i) {
        return ((Short) this.i(i).b()).shortValue();
    }

    public int getInt(int i) {
        return ((Integer) this.i(i).b()).intValue();
    }

    public float getFloat(int i) {
        return ((Float) this.i(i).b()).floatValue();
    }

    public String getString(int i) {
        return (String) this.i(i).b();
    }

    public ItemStack getItemStack(int i) {
        return (ItemStack) this.i(i).b();
    }

    private WatchableObject i(int i) {
        this.f.readLock().lock();

        WatchableObject watchableobject;

        try {
            watchableobject = (WatchableObject) this.d.get(Integer.valueOf(i));
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Getting synched entity data");
            CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Synched entity data");

            crashreportsystemdetails.a("Data ID", Integer.valueOf(i));
            throw new ReportedException(crashreport);
        }

        this.f.readLock().unlock();
        return watchableobject;
    }

    public void watch(int i, Object object) {
        WatchableObject watchableobject = this.i(i);

        if (ObjectUtils.notEqual(object, watchableobject.b())) {
            watchableobject.a(object);
            this.a.i(i);
            watchableobject.a(true);
            this.e = true;
        }
    }

    public void h(int i) {
        WatchableObject.a(this.i(i), true);
        this.e = true;
    }

    public boolean a() {
        return this.e;
    }

    public static void a(List list, PacketDataSerializer packetdataserializer) {
        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                WatchableObject watchableobject = (WatchableObject) iterator.next();

                a(packetdataserializer, watchableobject);
            }
        }

        packetdataserializer.writeByte(127);
    }

    public List b() {
        ArrayList arraylist = null;

        if (this.e) {
            this.f.readLock().lock();
            Iterator iterator = this.d.values().iterator();

            while (iterator.hasNext()) {
                WatchableObject watchableobject = (WatchableObject) iterator.next();

                if (watchableobject.d()) {
                    watchableobject.a(false);
                    if (arraylist == null) {
                        arraylist = new ArrayList();
                    }

                    arraylist.add(watchableobject);
                }
            }

            this.f.readLock().unlock();
        }

        this.e = false;
        return arraylist;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.f.readLock().lock();
        Iterator iterator = this.d.values().iterator();

        while (iterator.hasNext()) {
            WatchableObject watchableobject = (WatchableObject) iterator.next();

            a(packetdataserializer, watchableobject);
        }

        this.f.readLock().unlock();
        packetdataserializer.writeByte(127);
    }

    public List c() {
        ArrayList arraylist = null;

        this.f.readLock().lock();

        WatchableObject watchableobject;

        for (Iterator iterator = this.d.values().iterator(); iterator.hasNext(); arraylist.add(watchableobject)) {
            watchableobject = (WatchableObject) iterator.next();
            if (arraylist == null) {
                arraylist = new ArrayList();
            }
        }

        this.f.readLock().unlock();
        return arraylist;
    }

    private static void a(PacketDataSerializer packetdataserializer, WatchableObject watchableobject) {
        int i = (watchableobject.c() << 5 | watchableobject.a() & 31) & 255;

        packetdataserializer.writeByte(i);
        switch (watchableobject.c()) {
        case 0:
            packetdataserializer.writeByte(((Byte) watchableobject.b()).byteValue());
            break;

        case 1:
            packetdataserializer.writeShort(((Short) watchableobject.b()).shortValue());
            break;

        case 2:
            packetdataserializer.writeInt(((Integer) watchableobject.b()).intValue());
            break;

        case 3:
            packetdataserializer.writeFloat(((Float) watchableobject.b()).floatValue());
            break;

        case 4:
            packetdataserializer.a((String) watchableobject.b());
            break;

        case 5:
            ItemStack itemstack = (ItemStack) watchableobject.b();

            packetdataserializer.a(itemstack);
            break;

        case 6:
            ChunkCoordinates chunkcoordinates = (ChunkCoordinates) watchableobject.b();

            packetdataserializer.writeInt(chunkcoordinates.x);
            packetdataserializer.writeInt(chunkcoordinates.y);
            packetdataserializer.writeInt(chunkcoordinates.z);
        }
    }

    public static List b(PacketDataSerializer packetdataserializer) {
        ArrayList arraylist = null;

        for (byte b0 = packetdataserializer.readByte(); b0 != 127; b0 = packetdataserializer.readByte()) {
            if (arraylist == null) {
                arraylist = new ArrayList();
            }

            int i = (b0 & 224) >> 5;
            int j = b0 & 31;
            WatchableObject watchableobject = null;

            switch (i) {
            case 0:
                watchableobject = new WatchableObject(i, j, Byte.valueOf(packetdataserializer.readByte()));
                break;

            case 1:
                watchableobject = new WatchableObject(i, j, Short.valueOf(packetdataserializer.readShort()));
                break;

            case 2:
                watchableobject = new WatchableObject(i, j, Integer.valueOf(packetdataserializer.readInt()));
                break;

            case 3:
                watchableobject = new WatchableObject(i, j, Float.valueOf(packetdataserializer.readFloat()));
                break;

            case 4:
                watchableobject = new WatchableObject(i, j, packetdataserializer.c(32767));
                break;

            case 5:
                watchableobject = new WatchableObject(i, j, packetdataserializer.c());
                break;

            case 6:
                int k = packetdataserializer.readInt();
                int l = packetdataserializer.readInt();
                int i1 = packetdataserializer.readInt();

                watchableobject = new WatchableObject(i, j, new ChunkCoordinates(k, l, i1));
            }

            arraylist.add(watchableobject);
        }

        return arraylist;
    }

    public boolean d() {
        return this.b;
    }

    public void e() {
        this.e = false;
    }

    static {
        c.put(Byte.class, Integer.valueOf(0));
        c.put(Short.class, Integer.valueOf(1));
        c.put(Integer.class, Integer.valueOf(2));
        c.put(Float.class, Integer.valueOf(3));
        c.put(String.class, Integer.valueOf(4));
        c.put(ItemStack.class, Integer.valueOf(5));
        c.put(ChunkCoordinates.class, Integer.valueOf(6));
    }
}
