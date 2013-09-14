package net.minecraft.server;

public class WorldGenRegistration {

    public static void a() {
        WorldGenFactory.a(WorldGenPyramidPiece.class, "TeDP");
        WorldGenFactory.a(WorldGenJungleTemple.class, "TeJP");
        WorldGenFactory.a(WorldGenWitchHut.class, "TeSH");
    }
}
