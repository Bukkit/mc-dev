package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;

public class WorldGenFactory {

    private static Map a = new HashMap();
    private static Map b = new HashMap();
    private static Map c = new HashMap();
    private static Map d = new HashMap();

    private static void b(Class oclass, String s) {
        a.put(s, oclass);
        b.put(oclass, s);
    }

    static void a(Class oclass, String s) {
        c.put(s, oclass);
        d.put(oclass, s);
    }

    public static String a(StructureStart structurestart) {
        return (String) b.get(structurestart.getClass());
    }

    public static String a(StructurePiece structurepiece) {
        return (String) d.get(structurepiece.getClass());
    }

    public static StructureStart a(NBTTagCompound nbttagcompound, World world) {
        StructureStart structurestart = null;

        try {
            Class oclass = (Class) a.get(nbttagcompound.getString("id"));

            if (oclass != null) {
                structurestart = (StructureStart) oclass.newInstance();
            }
        } catch (Exception exception) {
            world.getLogger().warning("Failed Start with id " + nbttagcompound.getString("id"));
            exception.printStackTrace();
        }

        if (structurestart != null) {
            structurestart.a(world, nbttagcompound);
        } else {
            world.getLogger().warning("Skipping Structure with id " + nbttagcompound.getString("id"));
        }

        return structurestart;
    }

    public static StructurePiece b(NBTTagCompound nbttagcompound, World world) {
        StructurePiece structurepiece = null;

        try {
            Class oclass = (Class) c.get(nbttagcompound.getString("id"));

            if (oclass != null) {
                structurepiece = (StructurePiece) oclass.newInstance();
            }
        } catch (Exception exception) {
            world.getLogger().warning("Failed Piece with id " + nbttagcompound.getString("id"));
            exception.printStackTrace();
        }

        if (structurepiece != null) {
            structurepiece.a(world, nbttagcompound);
        } else {
            world.getLogger().warning("Skipping Piece with id " + nbttagcompound.getString("id"));
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
