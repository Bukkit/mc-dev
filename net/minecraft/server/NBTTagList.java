package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NBTTagList extends NBTBase {

    private List list = new ArrayList();
    private byte type = 0;

    public NBTTagList() {}

    void write(DataOutput dataoutput) {
        if (!this.list.isEmpty()) {
            this.type = ((NBTBase) this.list.get(0)).getTypeId();
        } else {
            this.type = 0;
        }

        dataoutput.writeByte(this.type);
        dataoutput.writeInt(this.list.size());

        for (int i = 0; i < this.list.size(); ++i) {
            ((NBTBase) this.list.get(i)).write(dataoutput);
        }
    }

    void load(DataInput datainput, int i) {
        if (i > 512) {
            throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
        } else {
            this.type = datainput.readByte();
            int j = datainput.readInt();

            this.list = new ArrayList();

            for (int k = 0; k < j; ++k) {
                NBTBase nbtbase = NBTBase.createTag(this.type);

                nbtbase.load(datainput, i + 1);
                this.list.add(nbtbase);
            }
        }
    }

    public byte getTypeId() {
        return (byte) 9;
    }

    public String toString() {
        String s = "[";
        int i = 0;

        for (Iterator iterator = this.list.iterator(); iterator.hasNext(); ++i) {
            NBTBase nbtbase = (NBTBase) iterator.next();

            s = s + "" + i + ':' + nbtbase + ',';
        }

        return s + "]";
    }

    public void add(NBTBase nbtbase) {
        if (this.type == 0) {
            this.type = nbtbase.getTypeId();
        } else if (this.type != nbtbase.getTypeId()) {
            System.err.println("WARNING: Adding mismatching tag types to tag list");
            return;
        }

        this.list.add(nbtbase);
    }

    public NBTTagCompound get(int i) {
        if (i >= 0 && i < this.list.size()) {
            NBTBase nbtbase = (NBTBase) this.list.get(i);

            return nbtbase.getTypeId() == 10 ? (NBTTagCompound) nbtbase : new NBTTagCompound();
        } else {
            return new NBTTagCompound();
        }
    }

    public int[] c(int i) {
        if (i >= 0 && i < this.list.size()) {
            NBTBase nbtbase = (NBTBase) this.list.get(i);

            return nbtbase.getTypeId() == 11 ? ((NBTTagIntArray) nbtbase).c() : new int[0];
        } else {
            return new int[0];
        }
    }

    public double d(int i) {
        if (i >= 0 && i < this.list.size()) {
            NBTBase nbtbase = (NBTBase) this.list.get(i);

            return nbtbase.getTypeId() == 6 ? ((NBTTagDouble) nbtbase).g() : 0.0D;
        } else {
            return 0.0D;
        }
    }

    public float e(int i) {
        if (i >= 0 && i < this.list.size()) {
            NBTBase nbtbase = (NBTBase) this.list.get(i);

            return nbtbase.getTypeId() == 5 ? ((NBTTagFloat) nbtbase).h() : 0.0F;
        } else {
            return 0.0F;
        }
    }

    public String f(int i) {
        if (i >= 0 && i < this.list.size()) {
            NBTBase nbtbase = (NBTBase) this.list.get(i);

            return nbtbase.getTypeId() == 8 ? nbtbase.a_() : nbtbase.toString();
        } else {
            return "";
        }
    }

    public int size() {
        return this.list.size();
    }

    public NBTBase clone() {
        NBTTagList nbttaglist = new NBTTagList();

        nbttaglist.type = this.type;
        Iterator iterator = this.list.iterator();

        while (iterator.hasNext()) {
            NBTBase nbtbase = (NBTBase) iterator.next();
            NBTBase nbtbase1 = nbtbase.clone();

            nbttaglist.list.add(nbtbase1);
        }

        return nbttaglist;
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagList nbttaglist = (NBTTagList) object;

            if (this.type == nbttaglist.type) {
                return this.list.equals(nbttaglist.list);
            }
        }

        return false;
    }

    public int hashCode() {
        return super.hashCode() ^ this.list.hashCode();
    }

    public int d() {
        return this.type;
    }
}
