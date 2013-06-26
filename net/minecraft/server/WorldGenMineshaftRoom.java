package net.minecraft.server;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WorldGenMineshaftRoom extends StructurePiece {

    private List a = new LinkedList();

    public WorldGenMineshaftRoom(int i, Random random, int j, int k) {
        super(i);
        this.f = new StructureBoundingBox(j, 50, k, j + 7 + random.nextInt(6), 54 + random.nextInt(6), k + 7 + random.nextInt(6));
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        int i = this.c();
        int j = this.f.c() - 3 - 1;

        if (j <= 0) {
            j = 1;
        }

        int k;
        StructurePiece structurepiece1;
        StructureBoundingBox structureboundingbox;

        for (k = 0; k < this.f.b(); k += 4) {
            k += random.nextInt(this.f.b());
            if (k + 3 > this.f.b()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a + k, this.f.b + random.nextInt(j) + 1, this.f.c - 1, 2, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.b();
                this.a.add(new StructureBoundingBox(structureboundingbox.a, structureboundingbox.b, this.f.c, structureboundingbox.d, structureboundingbox.e, this.f.c + 1));
            }
        }

        for (k = 0; k < this.f.b(); k += 4) {
            k += random.nextInt(this.f.b());
            if (k + 3 > this.f.b()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a + k, this.f.b + random.nextInt(j) + 1, this.f.f + 1, 0, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.b();
                this.a.add(new StructureBoundingBox(structureboundingbox.a, structureboundingbox.b, this.f.f - 1, structureboundingbox.d, structureboundingbox.e, this.f.f));
            }
        }

        for (k = 0; k < this.f.d(); k += 4) {
            k += random.nextInt(this.f.d());
            if (k + 3 > this.f.d()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.a - 1, this.f.b + random.nextInt(j) + 1, this.f.c + k, 1, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.b();
                this.a.add(new StructureBoundingBox(this.f.a, structureboundingbox.b, structureboundingbox.c, this.f.a + 1, structureboundingbox.e, structureboundingbox.f));
            }
        }

        for (k = 0; k < this.f.d(); k += 4) {
            k += random.nextInt(this.f.d());
            if (k + 3 > this.f.d()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.f.d + 1, this.f.b + random.nextInt(j) + 1, this.f.c + k, 3, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.b();
                this.a.add(new StructureBoundingBox(this.f.d - 1, structureboundingbox.b, structureboundingbox.c, this.f.d, structureboundingbox.e, structureboundingbox.f));
            }
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, this.f.a, this.f.b, this.f.c, this.f.d, this.f.b, this.f.f, Block.DIRT.id, 0, true);
            this.a(world, structureboundingbox, this.f.a, this.f.b + 1, this.f.c, this.f.d, Math.min(this.f.b + 3, this.f.e), this.f.f, 0, 0, false);
            Iterator iterator = this.a.iterator();

            while (iterator.hasNext()) {
                StructureBoundingBox structureboundingbox1 = (StructureBoundingBox) iterator.next();

                this.a(world, structureboundingbox, structureboundingbox1.a, structureboundingbox1.e - 2, structureboundingbox1.c, structureboundingbox1.d, structureboundingbox1.e, structureboundingbox1.f, 0, 0, false);
            }

            this.a(world, structureboundingbox, this.f.a, this.f.b + 4, this.f.c, this.f.d, this.f.e, this.f.f, 0, false);
            return true;
        }
    }
}
