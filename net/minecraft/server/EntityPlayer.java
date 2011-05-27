package net.minecraft.server;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class EntityPlayer extends EntityHuman {

    public NetServerHandler a;
    public MinecraftServer b;
    public ItemInWorldManager c;
    public double d;
    public double e;
    public List f = new LinkedList();
    public Set aj = new HashSet();
    public double ak;
    public boolean al = false;
    private int bv = -99999999;

    public EntityPlayer(MinecraftServer minecraftserver, World world, String s, ItemInWorldManager iteminworldmanager) {
        super(world);
        int i = world.m;
        int j = world.o;
        int k = world.n;

        if (!world.q.e) {
            i += this.W.nextInt(20) - 10;
            k = world.e(i, j);
            j += this.W.nextInt(20) - 10;
        }

        this.c((double) i + 0.5D, (double) k, (double) j + 0.5D, 0.0F, 0.0F);
        this.b = minecraftserver;
        this.S = 0.0F;
        iteminworldmanager.a = this;
        this.at = s;
        this.c = iteminworldmanager;
        this.H = 0.0F;
    }

    public void b_() {}

    public void f(Entity entity) {
        this.am.f();
    }

    public boolean a(Entity entity, int i) {
        if (!this.b.n) {
            if (entity instanceof EntityHuman) {
                return false;
            }

            if (entity instanceof EntityArrow) {
                EntityArrow entityarrow = (EntityArrow) entity;

                if (entityarrow.b instanceof EntityHuman) {
                    return false;
                }
            }
        }

        return super.a(entity, i);
    }

    public void a(int i) {
        super.a(i);
    }

    public void k() {
        super.b_();
        ChunkCoordIntPair chunkcoordintpair = null;
        double d0 = 0.0D;

        for (int i = 0; i < this.f.size(); ++i) {
            ChunkCoordIntPair chunkcoordintpair1 = (ChunkCoordIntPair) this.f.get(i);
            double d1 = chunkcoordintpair1.a(this);

            if (i == 0 || d1 < d0) {
                chunkcoordintpair = chunkcoordintpair1;
                d0 = chunkcoordintpair1.a(this);
            }
        }

        if (chunkcoordintpair != null) {
            boolean flag = false;

            if (d0 < 1024.0D) {
                flag = true;
            }

            if (this.a.b() < 2) {
                flag = true;
            }

            if (flag) {
                this.f.remove(chunkcoordintpair);
                this.a.b((Packet) (new Packet51MapChunk(chunkcoordintpair.a * 16, 0, chunkcoordintpair.b * 16, 16, 128, 16, this.b.e)));
                List list = this.b.e.d(chunkcoordintpair.a * 16, 0, chunkcoordintpair.b * 16, chunkcoordintpair.a * 16 + 16, 128, chunkcoordintpair.b * 16 + 16);

                for (int j = 0; j < list.size(); ++j) {
                    TileEntity tileentity = (TileEntity) list.get(j);

                    this.a.b((Packet) (new Packet59ComplexEntity(tileentity.b, tileentity.c, tileentity.d, tileentity)));
                }
            }
        }

        if (this.aR != this.bv) {
            this.a.b((Packet) (new Packet8(this.aR)));
            this.bv = this.aR;
        }
    }

    public void E() {
        this.s = this.t = this.u = 0.0D;
        this.bs = false;
        super.E();
    }

    public void c(Entity entity, int i) {
        if (!entity.G) {
            if (entity instanceof EntityItem) {
                this.a.b((Packet) (new Packet17AddToInventory(((EntityItem) entity).a, i)));
                this.b.k.a(entity, new Packet22Collect(entity.g, this.g));
            }

            if (entity instanceof EntityArrow) {
                this.a.b((Packet) (new Packet17AddToInventory(new ItemStack(Item.ARROW), 1)));
                this.b.k.a(entity, new Packet22Collect(entity.g, this.g));
            }
        }

        super.c(entity, i);
    }

    public void F() {
        if (!this.ar) {
            this.as = -1;
            this.ar = true;
            this.b.k.a(this, new Packet18ArmAnimation(this, 1));
        }
    }

    public float s() {
        return 1.62F;
    }

    public void e(Entity entity) {
        super.e(entity);
        this.a.b((Packet) (new Packet39(this, this.k)));
        this.a.a(this.p, this.q, this.r, this.v, this.w);
    }

    protected void a(double d0, boolean flag) {}

    public void b(double d0, boolean flag) {
        super.a(d0, flag);
    }

    public boolean p() {
        return this.al;
    }
}
