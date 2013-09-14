package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenMineshaftStart extends StructureStart {

    public WorldGenMineshaftStart() {}

    public WorldGenMineshaftStart(World world, Random random, int i, int j) {
        super(i, j);
        WorldGenMineshaftRoom worldgenmineshaftroom = new WorldGenMineshaftRoom(0, random, (i << 4) + 2, (j << 4) + 2);

        this.a.add(worldgenmineshaftroom);
        worldgenmineshaftroom.a((StructurePiece) worldgenmineshaftroom, (List) this.a, random);
        this.c();
        this.a(world, random, 10);
    }
}
