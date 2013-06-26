package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataWatcher {

    private boolean a = true;
    private static final HashMap b = new HashMap();
    private final Map c = new HashMap();
    private boolean d;
    private ReadWriteLock e = new ReentrantReadWriteLock();

    public DataWatcher() {}

    public void a(int i, Object object) {
        Integer integer = (Integer) b.get(object.getClass());

        if (integer == null) {
            throw new IllegalArgumentException("Unknown data type: " + object.getClass());
        } else if (i > 31) {
            throw new IllegalArgumentException("Data value id is too big with " + i + "! (Max is " + 31 + ")");
        } else if (this.c.containsKey(Integer.valueOf(i))) {
            throw new IllegalArgumentException("Duplicate id value for " + i + "!");
        } else {
            WatchableObject watchableobject = new WatchableObject(integer.intValue(), i, object);

            this.e.writeLock().lock();
            this.c.put(Integer.valueOf(i), watchableobject);
            this.e.writeLock().unlock();
            this.a = false;
        }
    }

    public void a(int i, int j) {
        WatchableObject watchableobject = new WatchableObject(j, i, null);

        this.e.writeLock().lock();
        this.c.put(Integer.valueOf(i), watchableobject);
        this.e.writeLock().unlock();
        this.a = false;
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
        this.e.readLock().lock();

        WatchableObject watchableobject;

        try {
            watchableobject = (WatchableObject) this.c.get(Integer.valueOf(i));
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Getting synched entity data");
            CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Synched entity data");

            crashreportsystemdetails.a("Data ID", Integer.valueOf(i));
            throw new ReportedException(crashreport);
        }

        this.e.readLock().unlock();
        return watchableobject;
    }

    public void watch(int i, Object object) {
        WatchableObject watchableobject = this.i(i);

        if (!object.equals(watchableobject.b())) {
            watchableobject.a(object);
            watchableobject.a(true);
            this.d = true;
        }
    }

    public void h(int i) {
        WatchableObject.a(this.i(i), true);
        this.d = true;
    }

    public boolean a() {
        return this.d;
    }

    public static void a(List list, DataOutput dataoutput) {
        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                WatchableObject watchableobject = (WatchableObject) iterator.next();

                a(dataoutput, watchableobject);
            }
        }

        dataoutput.writeByte(127);
    }

    public List b() {
        ArrayList arraylist = null;

        if (this.d) {
            this.e.readLock().lock();
            Iterator iterator = this.c.values().iterator();

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

            this.e.readLock().unlock();
        }

        this.d = false;
        return arraylist;
    }

    public void a(DataOutput dataoutput) {
        this.e.readLock().lock();
        Iterator iterator = this.c.values().iterator();

        while (iterator.hasNext()) {
            WatchableObject watchableobject = (WatchableObject) iterator.next();

            a(dataoutput, watchableobject);
        }

        this.e.readLock().unlock();
        dataoutput.writeByte(127);
    }

    public List c() {
        ArrayList arraylist = null;

        this.e.readLock().lock();

        WatchableObject watchableobject;

        for (Iterator iterator = this.c.values().iterator(); iterator.hasNext(); arraylist.add(watchableobject)) {
            watchableobject = (WatchableObject) iterator.next();
            if (arraylist == null) {
                arraylist = new ArrayList();
            }
        }

        this.e.readLock().unlock();
        return arraylist;
    }

    private static void a(DataOutput dataoutput, WatchableObject watchableobject) {
        int i = (watchableobject.c() << 5 | watchableobject.a() & 31) & 255;

        dataoutput.writeByte(i);
        switch (watchableobject.c()) {
        case 0:
            dataoutput.writeByte(((Byte) watchableobject.b()).byteValue());
            break;

        case 1:
            dataoutput.writeShort(((Short) watchableobject.b()).shortValue());
            break;

        case 2:
            dataoutput.writeInt(((Integer) watchableobject.b()).intValue());
            break;

        case 3:
            dataoutput.writeFloat(((Float) watchableobject.b()).floatValue());
            break;

        case 4:
            Packet.a((String) watchableobject.b(), dataoutput);
            break;

        case 5:
            ItemStack itemstack = (ItemStack) watchableobject.b();

            Packet.a(itemstack, dataoutput);
            break;

        case 6:
            ChunkCoordinates chunkcoordinates = (ChunkCoordinates) watchableobject.b();

            dataoutput.writeInt(chunkcoordinates.x);
            dataoutput.writeInt(chunkcoordinates.y);
            dataoutput.writeInt(chunkcoordinates.z);
        }
    }

    public static List a(DataInput datainput) {
        ArrayList arraylist = null;

        for (byte b0 = datainput.readByte(); b0 != 127; b0 = datainput.readByte()) {
            if (arraylist == null) {
                arraylist = new ArrayList();
            }

            int i = (b0 & 224) >> 5;
            int j = b0 & 31;
            WatchableObject watchableobject = null;

            switch (i) {
            case 0:
                watchableobject = new WatchableObject(i, j, Byte.valueOf(datainput.readByte()));
                break;

            case 1:
                watchableobject = new WatchableObject(i, j, Short.valueOf(datainput.readShort()));
                break;

            case 2:
                watchableobject = new WatchableObject(i, j, Integer.valueOf(datainput.readInt()));
                break;

            case 3:
                watchableobject = new WatchableObject(i, j, Float.valueOf(datainput.readFloat()));
                break;

            case 4:
                watchableobject = new WatchableObject(i, j, Packet.a(datainput, 64));
                break;

            case 5:
                watchableobject = new WatchableObject(i, j, Packet.c(datainput));
                break;

            case 6:
                int k = datainput.readInt();
                int l = datainput.readInt();
                int i1 = datainput.readInt();

                watchableobject = new WatchableObject(i, j, new ChunkCoordinates(k, l, i1));
            }

            arraylist.add(watchableobject);
        }

        return arraylist;
    }

    public boolean d() {
        return this.a;
    }

    public void e() {
        this.d = false;
    }

    static {
        b.put(Byte.class, Integer.valueOf(0));
        b.put(Short.class, Integer.valueOf(1));
        b.put(Integer.class, Integer.valueOf(2));
        b.put(Float.class, Integer.valueOf(3));
        b.put(String.class, Integer.valueOf(4));
        b.put(ItemStack.class, Integer.valueOf(5));
        b.put(ChunkCoordinates.class, Integer.valueOf(6));
    }
}
