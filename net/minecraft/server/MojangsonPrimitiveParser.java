package net.minecraft.server;

class MojangsonPrimitiveParser extends MojangsonTypeParser {

    protected String b;

    public MojangsonPrimitiveParser(String s, String s1) {
        this.a = s;
        this.b = s1;
    }

    public NBTBase a() {
        try {
            if (this.b.matches("[-+]?[0-9]*\\.?[0-9]+[d|D]")) {
                return new NBTTagDouble(Double.parseDouble(this.b.substring(0, this.b.length() - 1)));
            } else if (this.b.matches("[-+]?[0-9]*\\.?[0-9]+[f|F]")) {
                return new NBTTagFloat(Float.parseFloat(this.b.substring(0, this.b.length() - 1)));
            } else if (this.b.matches("[-+]?[0-9]+[b|B]")) {
                return new NBTTagByte(Byte.parseByte(this.b.substring(0, this.b.length() - 1)));
            } else if (this.b.matches("[-+]?[0-9]+[l|L]")) {
                return new NBTTagLong(Long.parseLong(this.b.substring(0, this.b.length() - 1)));
            } else if (this.b.matches("[-+]?[0-9]+[s|S]")) {
                return new NBTTagShort(Short.parseShort(this.b.substring(0, this.b.length() - 1)));
            } else if (this.b.matches("[-+]?[0-9]+")) {
                return new NBTTagInt(Integer.parseInt(this.b.substring(0, this.b.length())));
            } else if (this.b.matches("[-+]?[0-9]*\\.?[0-9]+")) {
                return new NBTTagDouble(Double.parseDouble(this.b.substring(0, this.b.length())));
            } else if (!this.b.equalsIgnoreCase("true") && !this.b.equalsIgnoreCase("false")) {
                if (this.b.startsWith("[") && this.b.endsWith("]")) {
                    if (this.b.length() > 2) {
                        String s = this.b.substring(1, this.b.length() - 1);
                        String[] astring = s.split(",");

                        try {
                            if (astring.length <= 1) {
                                return new NBTTagIntArray(new int[] { Integer.parseInt(s.trim())});
                            } else {
                                int[] aint = new int[astring.length];

                                for (int i = 0; i < astring.length; ++i) {
                                    aint[i] = Integer.parseInt(astring[i].trim());
                                }

                                return new NBTTagIntArray(aint);
                            }
                        } catch (NumberFormatException numberformatexception) {
                            return new NBTTagString(this.b);
                        }
                    } else {
                        return new NBTTagIntArray();
                    }
                } else {
                    if (this.b.startsWith("\"") && this.b.endsWith("\"") && this.b.length() > 2) {
                        this.b = this.b.substring(1, this.b.length() - 1);
                    }

                    this.b = this.b.replaceAll("\\\\\"", "\"");
                    return new NBTTagString(this.b);
                }
            } else {
                return new NBTTagByte((byte) (Boolean.parseBoolean(this.b) ? 1 : 0));
            }
        } catch (NumberFormatException numberformatexception1) {
            this.b = this.b.replaceAll("\\\\\"", "\"");
            return new NBTTagString(this.b);
        }
    }
}
