package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public abstract class NBTBase {

    private String a;

    abstract void a(DataOutput dataoutput);

    abstract void a(DataInput datainput);

    public abstract byte a();

    protected NBTBase(String s) {
        if (s == null) {
            this.a = "";
        } else {
            this.a = s;
        }
    }

    public boolean equals(Object object) {
        if (object != null && object instanceof NBTBase) {
            NBTBase nbtbase = (NBTBase) object;

            return this.a() != nbtbase.a() ? false : ((this.a != null || nbtbase.a == null) && (this.a == null || nbtbase.a != null) ? this.a == null || this.a.equals(nbtbase.a) : false);
        } else {
            return false;
        }
    }

    public NBTBase a(String s) {
        if (s == null) {
            this.a = "";
        } else {
            this.a = s;
        }

        return this;
    }

    public String c() {
        return this.a == null ? "" : this.a;
    }

    public static NBTBase b(DataInput datainput) {
        byte b0 = datainput.readByte();

        if (b0 == 0) {
            return new NBTTagEnd();
        } else {
            String s = datainput.readUTF();
            NBTBase nbtbase = a(b0, s);

            nbtbase.a(datainput);
            return nbtbase;
        }
    }

    public static void a(NBTBase nbtbase, DataOutput dataoutput) {
        dataoutput.writeByte(nbtbase.a());
        if (nbtbase.a() != 0) {
            dataoutput.writeUTF(nbtbase.c());
            nbtbase.a(dataoutput);
        }
    }

    public static NBTBase a(byte b0, String s) {
        switch (b0) {
        case 0:
            return new NBTTagEnd();

        case 1:
            return new NBTTagByte(s);

        case 2:
            return new NBTTagShort(s);

        case 3:
            return new NBTTagInt(s);

        case 4:
            return new NBTTagLong(s);

        case 5:
            return new NBTTagFloat(s);

        case 6:
            return new NBTTagDouble(s);

        case 7:
            return new NBTTagByteArray(s);

        case 8:
            return new NBTTagString(s);

        case 9:
            return new NBTTagList(s);

        case 10:
            return new NBTTagCompound(s);

        default:
            return null;
        }
    }

    public static String a(byte b0) {
        switch (b0) {
        case 0:
            return "TAG_End";

        case 1:
            return "TAG_Byte";

        case 2:
            return "TAG_Short";

        case 3:
            return "TAG_Int";

        case 4:
            return "TAG_Long";

        case 5:
            return "TAG_Float";

        case 6:
            return "TAG_Double";

        case 7:
            return "TAG_Byte_Array";

        case 8:
            return "TAG_String";

        case 9:
            return "TAG_List";

        case 10:
            return "TAG_Compound";

        default:
            return "UNKNOWN";
        }
    }

    public abstract NBTBase b();
}
