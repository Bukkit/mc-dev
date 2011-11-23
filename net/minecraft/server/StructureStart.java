package net.minecraft.server;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public abstract class StructureStart {

    protected LinkedList a = new LinkedList();
    protected StructureBoundingBox b;

    protected StructureStart() {}

    public StructureBoundingBox b() {
        return this.b;
    }

    public LinkedList c() {
        return this.a;
    }

    public void a(World world, Random random, StructureBoundingBox structureboundingbox) {
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            StructurePiece structurepiece = (StructurePiece) iterator.next();

            if (structurepiece.b().a(structureboundingbox) && !structurepiece.a(world, random, structureboundingbox)) {
                iterator.remove();
            }
        }
    }

    protected void d() {
        this.b = StructureBoundingBox.a();
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            StructurePiece structurepiece = (StructurePiece) iterator.next();

            this.b.b(structurepiece.b());
        }
    }

    protected void a(World world, Random random, int i) {
        int j = world.seaLevel - i;
        int k = this.b.c() + 1;

        if (k < j) {
            k += random.nextInt(j - k);
        }

        int l = k - this.b.e;

        this.b.a(0, l, 0);
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            StructurePiece structurepiece = (StructurePiece) iterator.next();

            structurepiece.b().a(0, l, 0);
        }
    }

    protected void a(World world, Random random, int i, int j) {
        int k = j - i + 1 - this.b.c();
        boolean flag = true;
        int l;

        if (k > 1) {
            l = i + random.nextInt(k);
        } else {
            l = i;
        }

        int i1 = l - this.b.b;

        this.b.a(0, i1, 0);
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            StructurePiece structurepiece = (StructurePiece) iterator.next();

            structurepiece.b().a(0, i1, 0);
        }
    }

    public boolean a() {
        return true;
    }
}
