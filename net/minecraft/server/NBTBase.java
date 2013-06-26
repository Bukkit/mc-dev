package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class NBTBase {

    public static final String[] b = new String[] { "END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]"};
    private String name;

    abstract void write(DataOutput dataoutput);

    abstract void load(DataInput datainput, int i);

    public abstract byte getTypeId();

    protected NBTBase(String s) {
        if (s == null) {
            this.name = "";
        } else {
            this.name = s;
        }
    }

    public NBTBase setName(String s) {
        if (s == null) {
            this.name = "";
        } else {
            this.name = s;
        }

        return this;
    }

    public String getName() {
        return this.name == null ? "" : this.name;
    }

    public static NBTBase a(DataInput datainput) {
        return b(datainput, 0);
    }

    public static NBTBase b(DataInput datainput, int i) {
        byte b0 = datainput.readByte();

        if (b0 == 0) {
            return new NBTTagEnd();
        } else {
            String s = datainput.readUTF();
            NBTBase nbtbase = createTag(b0, s);

            try {
                nbtbase.load(datainput, i);
                return nbtbase;
            } catch (IOException ioexception) {
                CrashReport crashreport = CrashReport.a((Throwable) ioexception, "Loading NBT data");
                CrashReportSystemDetails crashreportsystemdetails = crashreport.a("NBT Tag");

                crashreportsystemdetails.a("Tag name", s);
                crashreportsystemdetails.a("Tag type", Byte.valueOf(b0));
                throw new ReportedException(crashreport);
            }
        }
    }

    public static void a(NBTBase nbtbase, DataOutput dataoutput) {
        dataoutput.writeByte(nbtbase.getTypeId());
        if (nbtbase.getTypeId() != 0) {
            dataoutput.writeUTF(nbtbase.getName());
            nbtbase.write(dataoutput);
        }
    }

    public static NBTBase createTag(byte b0, String s) {
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

        case 11:
            return new NBTTagIntArray(s);

        default:
            return null;
        }
    }

    public static String getTagName(byte b0) {
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

        case 11:
            return "TAG_Int_Array";

        default:
            return "UNKNOWN";
        }
    }

    public abstract NBTBase clone();

    public boolean equals(Object object) {
        if (!(object instanceof NBTBase)) {
            return false;
        } else {
            NBTBase nbtbase = (NBTBase) object;

            return this.getTypeId() != nbtbase.getTypeId() ? false : ((this.name != null || nbtbase.name == null) && (this.name == null || nbtbase.name != null) ? this.name == null || this.name.equals(nbtbase.name) : false);
        }
    }

    public int hashCode() {
        return this.name.hashCode() ^ this.getTypeId();
    }
}
