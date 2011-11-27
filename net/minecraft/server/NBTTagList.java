package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NBTTagList extends NBTBase {

    private List list = new ArrayList();
    private byte type;

    public NBTTagList() {
        super("");
    }

    public NBTTagList(String s) {
        super(s);
    }

    void write(DataOutput dataoutput) {
        if (this.list.size() > 0) {
            this.type = ((NBTBase) this.list.get(0)).getTypeId();
        } else {
            this.type = 1;
        }

        dataoutput.writeByte(this.type);
        dataoutput.writeInt(this.list.size());

        for (int i = 0; i < this.list.size(); ++i) {
            ((NBTBase) this.list.get(i)).write(dataoutput);
        }
    }

    void load(DataInput datainput) {
        this.type = datainput.readByte();
        int i = datainput.readInt();

        this.list = new ArrayList();

        for (int j = 0; j < i; ++j) {
            NBTBase nbtbase = NBTBase.createTag(this.type, (String) null);

            nbtbase.load(datainput);
            this.list.add(nbtbase);
        }
    }

    public byte getTypeId() {
        return (byte) 9;
    }

    public String toString() {
        return "" + this.list.size() + " entries of type " + NBTBase.getTagName(this.type);
    }

    public void add(NBTBase nbtbase) {
        this.type = nbtbase.getTypeId();
        this.list.add(nbtbase);
    }

    public NBTBase get(int i) {
        return (NBTBase) this.list.get(i);
    }

    public int size() {
        return this.list.size();
    }

    public NBTBase clone() {
        NBTTagList nbttaglist = new NBTTagList(this.getName());

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
}
