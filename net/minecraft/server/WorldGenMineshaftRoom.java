package net.minecraft.server;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WorldGenMineshaftRoom extends StructurePiece {

    private LinkedList a = new LinkedList();

    public WorldGenMineshaftRoom(int i, Random random, int j, int k) {
        super(i);
        this.g = new StructureBoundingBox(j, 50, k, j + 7 + random.nextInt(6), 54 + random.nextInt(6), k + 7 + random.nextInt(6));
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        int i = this.c();
        int j = this.g.c() - 3 - 1;

        if (j <= 0) {
            j = 1;
        }

        int k;
        StructurePiece structurepiece1;
        StructureBoundingBox structureboundingbox;

        for (k = 0; k < this.g.b(); k += 4) {
            k += random.nextInt(this.g.b());
            if (k + 3 > this.g.b()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a + k, this.g.b + random.nextInt(j) + 1, this.g.c - 1, 2, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.b();
                this.a.add(new StructureBoundingBox(structureboundingbox.a, structureboundingbox.b, this.g.c, structureboundingbox.d, structureboundingbox.e, this.g.c + 1));
            }
        }

        for (k = 0; k < this.g.b(); k += 4) {
            k += random.nextInt(this.g.b());
            if (k + 3 > this.g.b()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a + k, this.g.b + random.nextInt(j) + 1, this.g.f + 1, 0, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.b();
                this.a.add(new StructureBoundingBox(structureboundingbox.a, structureboundingbox.b, this.g.f - 1, structureboundingbox.d, structureboundingbox.e, this.g.f));
            }
        }

        for (k = 0; k < this.g.d(); k += 4) {
            k += random.nextInt(this.g.d());
            if (k + 3 > this.g.d()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.a - 1, this.g.b + random.nextInt(j) + 1, this.g.c + k, 1, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.b();
                this.a.add(new StructureBoundingBox(this.g.a, structureboundingbox.b, structureboundingbox.c, this.g.a + 1, structureboundingbox.e, structureboundingbox.f));
            }
        }

        for (k = 0; k < this.g.d(); k += 4) {
            k += random.nextInt(this.g.d());
            if (k + 3 > this.g.d()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.g.d + 1, this.g.b + random.nextInt(j) + 1, this.g.c + k, 3, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.b();
                this.a.add(new StructureBoundingBox(this.g.d - 1, structureboundingbox.b, structureboundingbox.c, this.g.d, structureboundingbox.e, structureboundingbox.f));
            }
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, this.g.a, this.g.b, this.g.c, this.g.d, this.g.b, this.g.f, Block.DIRT.id, 0, true);
            this.a(world, structureboundingbox, this.g.a, this.g.b + 1, this.g.c, this.g.d, Math.min(this.g.b + 3, this.g.e), this.g.f, 0, 0, false);
            Iterator iterator = this.a.iterator();

            while (iterator.hasNext()) {
                StructureBoundingBox structureboundingbox1 = (StructureBoundingBox) iterator.next();

                this.a(world, structureboundingbox, structureboundingbox1.a, structureboundingbox1.e - 2, structureboundingbox1.c, structureboundingbox1.d, structureboundingbox1.e, structureboundingbox1.f, 0, 0, false);
            }

            this.a(world, structureboundingbox, this.g.a, this.g.b + 4, this.g.c, this.g.d, this.g.e, this.g.f, 0, false);
            return true;
        }
    }
}
