package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataWatcher {

    private static final HashMap a = new HashMap();
    private final Map b = new HashMap();
    private boolean c;

    public DataWatcher() {}

    public void a(int i, Object object) {
        Integer integer = (Integer) a.get(object.getClass());

        if (integer == null) {
            throw new IllegalArgumentException("Unknown data type: " + object.getClass());
        } else if (i > 31) {
            throw new IllegalArgumentException("Data value id is too big with " + i + "! (Max is " + 31 + ")");
        } else if (this.b.containsKey(Integer.valueOf(i))) {
            throw new IllegalArgumentException("Duplicate id value for " + i + "!");
        } else {
            WatchableObject watchableobject = new WatchableObject(integer.intValue(), i, object);

            this.b.put(Integer.valueOf(i), watchableobject);
        }
    }

    public byte getByte(int i) {
        return ((Byte) ((WatchableObject) this.b.get(Integer.valueOf(i))).b()).byteValue();
    }

    public short b(int i) {
        return ((Short) ((WatchableObject) this.b.get(Integer.valueOf(i))).b()).shortValue();
    }

    public int getInt(int i) {
        return ((Integer) ((WatchableObject) this.b.get(Integer.valueOf(i))).b()).intValue();
    }

    public String getString(int i) {
        return (String) ((WatchableObject) this.b.get(Integer.valueOf(i))).b();
    }

    public void watch(int i, Object object) {
        WatchableObject watchableobject = (WatchableObject) this.b.get(Integer.valueOf(i));

        if (!object.equals(watchableobject.b())) {
            watchableobject.a(object);
            watchableobject.a(true);
            this.c = true;
        }
    }

    public boolean a() {
        return this.c;
    }

    public static void a(List list, DataOutputStream dataoutputstream) {
        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                WatchableObject watchableobject = (WatchableObject) iterator.next();

                a(dataoutputstream, watchableobject);
            }
        }

        dataoutputstream.writeByte(127);
    }

    public ArrayList b() {
        ArrayList arraylist = null;

        if (this.c) {
            Iterator iterator = this.b.values().iterator();

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
        }

        this.c = false;
        return arraylist;
    }

    public void a(DataOutputStream dataoutputstream) {
        Iterator iterator = this.b.values().iterator();

        while (iterator.hasNext()) {
            WatchableObject watchableobject = (WatchableObject) iterator.next();

            a(dataoutputstream, watchableobject);
        }

        dataoutputstream.writeByte(127);
    }

    private static void a(DataOutputStream dataoutputstream, WatchableObject watchableobject) {
        int i = (watchableobject.c() << 5 | watchableobject.a() & 31) & 255;

        dataoutputstream.writeByte(i);
        switch (watchableobject.c()) {
        case 0:
            dataoutputstream.writeByte(((Byte) watchableobject.b()).byteValue());
            break;

        case 1:
            dataoutputstream.writeShort(((Short) watchableobject.b()).shortValue());
            break;

        case 2:
            dataoutputstream.writeInt(((Integer) watchableobject.b()).intValue());
            break;

        case 3:
            dataoutputstream.writeFloat(((Float) watchableobject.b()).floatValue());
            break;

        case 4:
            Packet.a((String) watchableobject.b(), dataoutputstream);
            break;

        case 5:
            ItemStack itemstack = (ItemStack) watchableobject.b();

            dataoutputstream.writeShort(itemstack.getItem().id);
            dataoutputstream.writeByte(itemstack.count);
            dataoutputstream.writeShort(itemstack.getData());
            break;

        case 6:
            ChunkCoordinates chunkcoordinates = (ChunkCoordinates) watchableobject.b();

            dataoutputstream.writeInt(chunkcoordinates.x);
            dataoutputstream.writeInt(chunkcoordinates.y);
            dataoutputstream.writeInt(chunkcoordinates.z);
        }
    }

    public static List a(DataInputStream datainputstream) {
        ArrayList arraylist = null;

        for (byte b0 = datainputstream.readByte(); b0 != 127; b0 = datainputstream.readByte()) {
            if (arraylist == null) {
                arraylist = new ArrayList();
            }

            int i = (b0 & 224) >> 5;
            int j = b0 & 31;
            WatchableObject watchableobject = null;

            switch (i) {
            case 0:
                watchableobject = new WatchableObject(i, j, Byte.valueOf(datainputstream.readByte()));
                break;

            case 1:
                watchableobject = new WatchableObject(i, j, Short.valueOf(datainputstream.readShort()));
                break;

            case 2:
                watchableobject = new WatchableObject(i, j, Integer.valueOf(datainputstream.readInt()));
                break;

            case 3:
                watchableobject = new WatchableObject(i, j, Float.valueOf(datainputstream.readFloat()));
                break;

            case 4:
                watchableobject = new WatchableObject(i, j, Packet.a(datainputstream, 64));
                break;

            case 5:
                short short1 = datainputstream.readShort();
                byte b1 = datainputstream.readByte();
                short short2 = datainputstream.readShort();

                watchableobject = new WatchableObject(i, j, new ItemStack(short1, b1, short2));
                break;

            case 6:
                int k = datainputstream.readInt();
                int l = datainputstream.readInt();
                int i1 = datainputstream.readInt();

                watchableobject = new WatchableObject(i, j, new ChunkCoordinates(k, l, i1));
            }

            arraylist.add(watchableobject);
        }

        return arraylist;
    }

    static {
        a.put(Byte.class, Integer.valueOf(0));
        a.put(Short.class, Integer.valueOf(1));
        a.put(Integer.class, Integer.valueOf(2));
        a.put(Float.class, Integer.valueOf(3));
        a.put(String.class, Integer.valueOf(4));
        a.put(ItemStack.class, Integer.valueOf(5));
        a.put(ChunkCoordinates.class, Integer.valueOf(6));
    }
}
