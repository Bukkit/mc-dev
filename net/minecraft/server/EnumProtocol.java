package net.minecraft.server;

import java.util.Iterator;
import java.util.Map;

import net.minecraft.util.com.google.common.collect.BiMap;
import net.minecraft.util.com.google.common.collect.HashBiMap;
import net.minecraft.util.com.google.common.collect.Iterables;
import net.minecraft.util.com.google.common.collect.Maps;
import net.minecraft.util.gnu.trove.map.TIntObjectMap;
import net.minecraft.util.gnu.trove.map.hash.TIntObjectHashMap;
import org.apache.logging.log4j.LogManager;

public enum EnumProtocol {

    HANDSHAKING("HANDSHAKING", 0, -1), PLAY("PLAY", 1, 0), STATUS("STATUS", 2, 1), LOGIN("LOGIN", 3, 2);
    private static final TIntObjectMap e = new TIntObjectHashMap();
    private static final Map f = Maps.newHashMap();
    private final int g;
    private final BiMap h;
    private final BiMap i;
    private static final EnumProtocol[] ENUM$VALUES = new EnumProtocol[] { HANDSHAKING, PLAY, STATUS, LOGIN};

    private EnumProtocol(String s, int i, int j) {
        this.h = HashBiMap.create();
        this.i = HashBiMap.create();
        this.g = j;
    }

    protected EnumProtocol a(int i, Class oclass) {
        String s;

        if (this.h.containsKey(Integer.valueOf(i))) {
            s = "Serverbound packet ID " + i + " is already assigned to " + this.h.get(Integer.valueOf(i)) + "; cannot re-assign to " + oclass;
            LogManager.getLogger().fatal(s);
            throw new IllegalArgumentException(s);
        } else if (this.h.containsValue(oclass)) {
            s = "Serverbound packet " + oclass + " is already assigned to ID " + this.h.inverse().get(oclass) + "; cannot re-assign to " + i;
            LogManager.getLogger().fatal(s);
            throw new IllegalArgumentException(s);
        } else {
            this.h.put(Integer.valueOf(i), oclass);
            return this;
        }
    }

    protected EnumProtocol b(int i, Class oclass) {
        String s;

        if (this.i.containsKey(Integer.valueOf(i))) {
            s = "Clientbound packet ID " + i + " is already assigned to " + this.i.get(Integer.valueOf(i)) + "; cannot re-assign to " + oclass;
            LogManager.getLogger().fatal(s);
            throw new IllegalArgumentException(s);
        } else if (this.i.containsValue(oclass)) {
            s = "Clientbound packet " + oclass + " is already assigned to ID " + this.i.inverse().get(oclass) + "; cannot re-assign to " + i;
            LogManager.getLogger().fatal(s);
            throw new IllegalArgumentException(s);
        } else {
            this.i.put(Integer.valueOf(i), oclass);
            return this;
        }
    }

    public BiMap a() {
        return this.h;
    }

    public BiMap b() {
        return this.i;
    }

    public BiMap a(boolean flag) {
        return flag ? this.b() : this.a();
    }

    public BiMap b(boolean flag) {
        return flag ? this.a() : this.b();
    }

    public int c() {
        return this.g;
    }

    public static EnumProtocol a(int i) {
        return (EnumProtocol) e.get(i);
    }

    public static EnumProtocol a(Packet packet) {
        return (EnumProtocol) f.get(packet.getClass());
    }

    EnumProtocol(String s, int i, int j, EnumProtocol$1 enumprotocol$1) {
        this(s, i, j);
    }

    static {
        EnumProtocol[] aenumprotocol = values();
        int i = aenumprotocol.length;

        for (int j = 0; j < i; ++j) {
            EnumProtocol enumprotocol = aenumprotocol[j];

            e.put(enumprotocol.c(), enumprotocol);
            Iterator iterator = Iterables.concat(enumprotocol.b().values(), enumprotocol.a().values()).iterator();

            while (iterator.hasNext()) {
                Class oclass = (Class) iterator.next();

                if (f.containsKey(oclass) && f.get(oclass) != enumprotocol) {
                    throw new Error("Packet " + oclass + " is already assigned to protocol " + f.get(oclass) + " - can\'t reassign to " + enumprotocol);
                }

                f.put(oclass, enumprotocol);
            }
        }
    }
}
