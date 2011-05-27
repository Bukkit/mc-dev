package net.minecraft.server;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class EntityPlayer extends EntityHuman implements ICrafting {

    public NetServerHandler a;
    public MinecraftServer b;
    public ItemInWorldManager c;
    public double d;
    public double e;
    public List f = new LinkedList();
    public Set aj = new HashSet();
    public double ak;
    public boolean al = false;
    private int bE = -99999999;
    private int bF = 60;
    private int[] bG = new int[] { -1, -1, -1, -1, -1};
    private int bH = 0;
    public boolean am;

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
        this.aw = s;
        this.c = iteminworldmanager;
        this.H = 0.0F;
    }

    public void k() {
        this.ap.a((ICrafting) this);
    }

    public int[] E() {
        return this.bG;
    }

    public void b_() {
        --this.bF;
        this.ap.a();

        for (int i = 0; i < 5; ++i) {
            int j = this.a(i);

            if (j != this.bG[i]) {
                this.b.k.a(this, new Packet5PlayerInventory(this.g, i, j));
                this.bG[i] = j;
            }
        }
    }

    public int a(int i) {
        return i == 0 ? this.c(this.an.e()) : this.c(this.an.b[i - 1]);
    }

    private int c(ItemStack itemstack) {
        return itemstack == null ? -1 : itemstack.c;
    }

    public void f(Entity entity) {
        this.an.h();
    }

    public boolean a(Entity entity, int i) {
        if (this.bF > 0) {
            return false;
        } else {
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
    }

    public void c(int i) {
        super.c(i);
    }

    public void F() {
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
                    this.a((TileEntity) list.get(j));
                }
            }
        }

        if (this.ba != this.bE) {
            this.a.b((Packet) (new Packet8(this.ba)));
            this.bE = this.ba;
        }
    }

    private void a(TileEntity tileentity) {
        if (tileentity != null) {
            Packet packet = tileentity.f();

            if (packet != null) {
                this.a.b(packet);
            }
        }
    }

    public void G() {
        this.s = this.t = this.u = 0.0D;
        this.bB = false;
        super.G();
    }

    public void c(Entity entity, int i) {
        if (!entity.G) {
            if (entity instanceof EntityItem) {
                this.b.k.a(entity, new Packet22Collect(entity.g, this.g));
            }

            if (entity instanceof EntityArrow) {
                this.b.k.a(entity, new Packet22Collect(entity.g, this.g));
            }
        }

        super.c(entity, i);
        this.ap.a();
    }

    public void H() {
        if (!this.au) {
            this.av = -1;
            this.au = true;
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

    private void R() {
        this.bH = this.bH % 100 + 1;
    }

    public void a(int i, int j, int k) {
        this.R();
        this.a.b((Packet) (new Packet100(this.bH, 1, "Crafting", 9)));
        this.ap = new ContainerWorkbench(this.an, this.l, i, j, k);
        this.ap.f = this.bH;
        this.ap.a((ICrafting) this);
    }

    public void a(IInventory iinventory) {
        this.R();
        this.a.b((Packet) (new Packet100(this.bH, 0, iinventory.b(), iinventory.a())));
        this.ap = new ContainerChest(this.an, iinventory);
        this.ap.f = this.bH;
        this.ap.a((ICrafting) this);
    }

    public void a(TileEntityFurnace tileentityfurnace) {
        this.R();
        this.a.b((Packet) (new Packet100(this.bH, 2, tileentityfurnace.b(), tileentityfurnace.a())));
        this.ap = new ContainerFurnace(this.an, tileentityfurnace);
        this.ap.f = this.bH;
        this.ap.a((ICrafting) this);
    }

    public void a(Container container, int i, ItemStack itemstack) {
        if (!(container.a(i) instanceof SlotResult)) {
            if (!this.am) {
                this.a.b((Packet) (new Packet103(container.f, i, itemstack)));
            }
        }
    }

    public void a(Container container, List list) {
        this.a.b((Packet) (new Packet104(container.f, list)));
        this.a.b((Packet) (new Packet103(-1, -1, this.an.i())));
    }

    public void a(Container container, int i, int j) {
        this.a.b((Packet) (new Packet105(container.f, i, j)));
    }

    public void a(ItemStack itemstack) {}

    public void I() {
        this.a.b((Packet) (new Packet101(this.ap.f)));
        this.K();
    }

    public void J() {
        if (!this.am) {
            this.a.b((Packet) (new Packet103(-1, -1, this.an.i())));
        }
    }

    public void K() {
        this.ap.a((EntityHuman) this);
        this.ap = this.ao;
    }
}
