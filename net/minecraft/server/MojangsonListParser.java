package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;

class MojangsonListParser extends MojangsonTypeParser {

    protected ArrayList b = new ArrayList();

    public MojangsonListParser(String s) {
        this.a = s;
    }

    public NBTBase a() {
        NBTTagList nbttaglist = new NBTTagList();
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            MojangsonTypeParser mojangsontypeparser = (MojangsonTypeParser) iterator.next();

            nbttaglist.add(mojangsontypeparser.a());
        }

        return nbttaglist;
    }
}
