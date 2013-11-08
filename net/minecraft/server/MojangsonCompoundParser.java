package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;

class MojangsonCompoundParser extends MojangsonTypeParser {

    protected ArrayList b = new ArrayList();

    public MojangsonCompoundParser(String s) {
        this.a = s;
    }

    public NBTBase a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            MojangsonTypeParser mojangsontypeparser = (MojangsonTypeParser) iterator.next();

            nbttagcompound.set(mojangsontypeparser.a, mojangsontypeparser.a());
        }

        return nbttagcompound;
    }
}
