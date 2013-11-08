package net.minecraft.server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PersistentCollection {

    private IDataManager a;
    private Map b = new HashMap();
    private List c = new ArrayList();
    private Map d = new HashMap();

    public PersistentCollection(IDataManager idatamanager) {
        this.a = idatamanager;
        this.b();
    }

    public PersistentBase get(Class oclass, String s) {
        PersistentBase persistentbase = (PersistentBase) this.b.get(s);

        if (persistentbase != null) {
            return persistentbase;
        } else {
            if (this.a != null) {
                try {
                    File file1 = this.a.getDataFile(s);

                    if (file1 != null && file1.exists()) {
                        try {
                            persistentbase = (PersistentBase) oclass.getConstructor(new Class[] { String.class}).newInstance(new Object[] { s});
                        } catch (Exception exception) {
                            throw new RuntimeException("Failed to instantiate " + oclass.toString(), exception);
                        }

                        FileInputStream fileinputstream = new FileInputStream(file1);
                        NBTTagCompound nbttagcompound = NBTCompressedStreamTools.a((InputStream) fileinputstream);

                        fileinputstream.close();
                        persistentbase.a(nbttagcompound.getCompound("data"));
                    }
                } catch (Exception exception1) {
                    exception1.printStackTrace();
                }
            }

            if (persistentbase != null) {
                this.b.put(s, persistentbase);
                this.c.add(persistentbase);
            }

            return persistentbase;
        }
    }

    public void a(String s, PersistentBase persistentbase) {
        if (persistentbase == null) {
            throw new RuntimeException("Can\'t set null data");
        } else {
            if (this.b.containsKey(s)) {
                this.c.remove(this.b.remove(s));
            }

            this.b.put(s, persistentbase);
            this.c.add(persistentbase);
        }
    }

    public void a() {
        for (int i = 0; i < this.c.size(); ++i) {
            PersistentBase persistentbase = (PersistentBase) this.c.get(i);

            if (persistentbase.d()) {
                this.a(persistentbase);
                persistentbase.a(false);
            }
        }
    }

    private void a(PersistentBase persistentbase) {
        if (this.a != null) {
            try {
                File file1 = this.a.getDataFile(persistentbase.id);

                if (file1 != null) {
                    NBTTagCompound nbttagcompound = new NBTTagCompound();

                    persistentbase.b(nbttagcompound);
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                    nbttagcompound1.set("data", nbttagcompound);
                    FileOutputStream fileoutputstream = new FileOutputStream(file1);

                    NBTCompressedStreamTools.a(nbttagcompound1, (OutputStream) fileoutputstream);
                    fileoutputstream.close();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void b() {
        try {
            this.d.clear();
            if (this.a == null) {
                return;
            }

            File file1 = this.a.getDataFile("idcounts");

            if (file1 != null && file1.exists()) {
                DataInputStream datainputstream = new DataInputStream(new FileInputStream(file1));
                NBTTagCompound nbttagcompound = NBTCompressedStreamTools.a((DataInput) datainputstream);

                datainputstream.close();
                Iterator iterator = nbttagcompound.c().iterator();

                while (iterator.hasNext()) {
                    String s = (String) iterator.next();
                    NBTBase nbtbase = nbttagcompound.get(s);

                    if (nbtbase instanceof NBTTagShort) {
                        NBTTagShort nbttagshort = (NBTTagShort) nbtbase;
                        short short1 = nbttagshort.e();

                        this.d.put(s, Short.valueOf(short1));
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public int a(String s) {
        Short oshort = (Short) this.d.get(s);

        if (oshort == null) {
            oshort = Short.valueOf((short) 0);
        } else {
            oshort = Short.valueOf((short) (oshort.shortValue() + 1));
        }

        this.d.put(s, oshort);
        if (this.a == null) {
            return oshort.shortValue();
        } else {
            try {
                File file1 = this.a.getDataFile("idcounts");

                if (file1 != null) {
                    NBTTagCompound nbttagcompound = new NBTTagCompound();
                    Iterator iterator = this.d.keySet().iterator();

                    while (iterator.hasNext()) {
                        String s1 = (String) iterator.next();
                        short short1 = ((Short) this.d.get(s1)).shortValue();

                        nbttagcompound.setShort(s1, short1);
                    }

                    DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(file1));

                    NBTCompressedStreamTools.a(nbttagcompound, (DataOutput) dataoutputstream);
                    dataoutputstream.close();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return oshort.shortValue();
        }
    }
}
