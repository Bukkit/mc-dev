package net.minecraft.server;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WorldGenMineshaftRoom extends StructurePiece {

    private List a = new LinkedList();

    public WorldGenMineshaftRoom(int i, Random random, int j, int k) {
        super(i);
        this.e = new StructureBoundingBox(j, 50, k, j + 7 + random.nextInt(6), 54 + random.nextInt(6), k + 7 + random.nextInt(6));
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        int i = this.c();
        int j = this.e.c() - 3 - 1;

        if (j <= 0) {
            j = 1;
        }

        int k;
        StructurePiece structurepiece1;
        StructureBoundingBox structureboundingbox;

        for (k = 0; k < this.e.b(); k += 4) {
            k += random.nextInt(this.e.b());
            if (k + 3 > this.e.b()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a + k, this.e.b + random.nextInt(j) + 1, this.e.c - 1, 2, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.b();
                this.a.add(new StructureBoundingBox(structureboundingbox.a, structureboundingbox.b, this.e.c, structureboundingbox.d, structureboundingbox.e, this.e.c + 1));
            }
        }

        for (k = 0; k < this.e.b(); k += 4) {
            k += random.nextInt(this.e.b());
            if (k + 3 > this.e.b()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a + k, this.e.b + random.nextInt(j) + 1, this.e.f + 1, 0, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.b();
                this.a.add(new StructureBoundingBox(structureboundingbox.a, structureboundingbox.b, this.e.f - 1, structureboundingbox.d, structureboundingbox.e, this.e.f));
            }
        }

        for (k = 0; k < this.e.d(); k += 4) {
            k += random.nextInt(this.e.d());
            if (k + 3 > this.e.d()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.a - 1, this.e.b + random.nextInt(j) + 1, this.e.c + k, 1, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.b();
                this.a.add(new StructureBoundingBox(this.e.a, structureboundingbox.b, structureboundingbox.c, this.e.a + 1, structureboundingbox.e, structureboundingbox.f));
            }
        }

        for (k = 0; k < this.e.d(); k += 4) {
            k += random.nextInt(this.e.d());
            if (k + 3 > this.e.d()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.e.d + 1, this.e.b + random.nextInt(j) + 1, this.e.c + k, 3, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.b();
                this.a.add(new StructureBoundingBox(this.e.d - 1, structureboundingbox.b, structureboundingbox.c, this.e.d, structureboundingbox.e, structureboundingbox.f));
            }
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, this.e.a, this.e.b, this.e.c, this.e.d, this.e.b, this.e.f, Block.DIRT.id, 0, true);
            this.a(world, structureboundingbox, this.e.a, this.e.b + 1, this.e.c, this.e.d, Math.min(this.e.b + 3, this.e.e), this.e.f, 0, 0, false);
            Iterator iterator = this.a.iterator();

            while (iterator.hasNext()) {
                StructureBoundingBox structureboundingbox1 = (StructureBoundingBox) iterator.next();

                this.a(world, structureboundingbox, structureboundingbox1.a, structureboundingbox1.e - 2, structureboundingbox1.c, structureboundingbox1.d, structureboundingbox1.e, structureboundingbox1.f, 0, 0, false);
            }

            this.a(world, structureboundingbox, this.e.a, this.e.b + 4, this.e.c, this.e.d, this.e.e, this.e.f, 0, false);
            return true;
        }
    }
}
