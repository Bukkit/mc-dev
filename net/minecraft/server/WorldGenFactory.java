package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldGenFactory {

    private static final Logger a = LogManager.getLogger();
    private static Map b = new HashMap();
    private static Map c = new HashMap();
    private static Map d = new HashMap();
    private static Map e = new HashMap();

    private static void b(Class oclass, String s) {
        b.put(s, oclass);
        c.put(oclass, s);
    }

    static void a(Class oclass, String s) {
        d.put(s, oclass);
        e.put(oclass, s);
    }

    public static String a(StructureStart structurestart) {
        return (String) c.get(structurestart.getClass());
    }

    public static String a(StructurePiece structurepiece) {
        return (String) e.get(structurepiece.getClass());
    }

    public static StructureStart a(NBTTagCompound nbttagcompound, World world) {
        StructureStart structurestart = null;

        try {
            Class oclass = (Class) b.get(nbttagcompound.getString("id"));

            if (oclass != null) {
                structurestart = (StructureStart) oclass.newInstance();
            }
        } catch (Exception exception) {
            a.warn("Failed Start with id " + nbttagcompound.getString("id"));
            exception.printStackTrace();
        }

        if (structurestart != null) {
            structurestart.a(world, nbttagcompound);
        } else {
            a.warn("Skipping Structure with id " + nbttagcompound.getString("id"));
        }

        return structurestart;
    }

    public static StructurePiece b(NBTTagCompound nbttagcompound, World world) {
        StructurePiece structurepiece = null;

        try {
            Class oclass = (Class) d.get(nbttagcompound.getString("id"));

            if (oclass != null) {
                structurepiece = (StructurePiece) oclass.newInstance();
            }
        } catch (Exception exception) {
            a.warn("Failed Piece with id " + nbttagcompound.getString("id"));
            exception.printStackTrace();
        }

        if (structurepiece != null) {
            structurepiece.a(world, nbttagcompound);
        } else {
            a.warn("Skipping Piece with id " + nbttagcompound.getString("id"));
        }

        return structurepiece;
    }

    static {
        b(WorldGenMineshaftStart.class, "Mineshaft");
        b(WorldGenVillageStart.class, "Village");
        b(WorldGenNetherStart.class, "Fortress");
        b(WorldGenStronghold2Start.class, "Stronghold");
        b(WorldGenLargeFeatureStart.class, "Temple");
        WorldGenMineshaftPieces.a();
        WorldGenVillagePieces.a();
        WorldGenNetherPieces.a();
        WorldGenStrongholdPieces.a();
        WorldGenRegistration.a();
    }
}
