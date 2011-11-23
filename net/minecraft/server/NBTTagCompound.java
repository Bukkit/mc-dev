package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NBTTagCompound extends NBTBase {

    private Map a = new HashMap();

    public NBTTagCompound() {
        super("");
    }

    public NBTTagCompound(String s) {
        super(s);
    }

    void a(DataOutput dataoutput) {
        Iterator iterator = this.a.values().iterator();

        while (iterator.hasNext()) {
            NBTBase nbtbase = (NBTBase) iterator.next();

            NBTBase.a(nbtbase, dataoutput);
        }

        dataoutput.writeByte(0);
    }

    void a(DataInput datainput) {
        this.a.clear();

        NBTBase nbtbase;

        while ((nbtbase = NBTBase.b(datainput)).a() != 0) {
            this.a.put(nbtbase.c(), nbtbase);
        }
    }

    public Collection d() {
        return this.a.values();
    }

    public byte a() {
        return (byte) 10;
    }

    public void a(String s, NBTBase nbtbase) {
        this.a.put(s, nbtbase.a(s));
    }

    public void a(String s, byte b0) {
        this.a.put(s, new NBTTagByte(s, b0));
    }

    public void a(String s, short short1) {
        this.a.put(s, new NBTTagShort(s, short1));
    }

    public void a(String s, int i) {
        this.a.put(s, new NBTTagInt(s, i));
    }

    public void setLong(String s, long i) {
        this.a.put(s, new NBTTagLong(s, i));
    }

    public void a(String s, float f) {
        this.a.put(s, new NBTTagFloat(s, f));
    }

    public void a(String s, double d0) {
        this.a.put(s, new NBTTagDouble(s, d0));
    }

    public void setString(String s, String s1) {
        this.a.put(s, new NBTTagString(s, s1));
    }

    public void a(String s, byte[] abyte) {
        this.a.put(s, new NBTTagByteArray(s, abyte));
    }

    public void a(String s, NBTTagCompound nbttagcompound) {
        this.a.put(s, nbttagcompound.a(s));
    }

    public void a(String s, boolean flag) {
        this.a(s, (byte) (flag ? 1 : 0));
    }

    public NBTBase b(String s) {
        return (NBTBase) this.a.get(s);
    }

    public boolean hasKey(String s) {
        return this.a.containsKey(s);
    }

    public byte d(String s) {
        return !this.a.containsKey(s) ? 0 : ((NBTTagByte) this.a.get(s)).a;
    }

    public short e(String s) {
        return !this.a.containsKey(s) ? 0 : ((NBTTagShort) this.a.get(s)).a;
    }

    public int f(String s) {
        return !this.a.containsKey(s) ? 0 : ((NBTTagInt) this.a.get(s)).a;
    }

    public long getLong(String s) {
        return !this.a.containsKey(s) ? 0L : ((NBTTagLong) this.a.get(s)).a;
    }

    public float h(String s) {
        return !this.a.containsKey(s) ? 0.0F : ((NBTTagFloat) this.a.get(s)).a;
    }

    public double i(String s) {
        return !this.a.containsKey(s) ? 0.0D : ((NBTTagDouble) this.a.get(s)).a;
    }

    public String getString(String s) {
        return !this.a.containsKey(s) ? "" : ((NBTTagString) this.a.get(s)).a;
    }

    public byte[] k(String s) {
        return !this.a.containsKey(s) ? new byte[0] : ((NBTTagByteArray) this.a.get(s)).a;
    }

    public NBTTagCompound l(String s) {
        return !this.a.containsKey(s) ? new NBTTagCompound(s) : (NBTTagCompound) this.a.get(s);
    }

    public NBTTagList m(String s) {
        return !this.a.containsKey(s) ? new NBTTagList(s) : (NBTTagList) this.a.get(s);
    }

    public boolean n(String s) {
        return this.d(s) != 0;
    }

    public String toString() {
        return "" + this.a.size() + " entries";
    }

    public NBTBase b() {
        NBTTagCompound nbttagcompound = new NBTTagCompound(this.c());
        Iterator iterator = this.a.keySet().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            nbttagcompound.a(s, ((NBTBase) this.a.get(s)).b());
        }

        return nbttagcompound;
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) object;

            return this.a.entrySet().equals(nbttagcompound.a.entrySet());
        } else {
            return false;
        }
    }
}
