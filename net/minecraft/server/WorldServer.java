package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldServer extends World {

    public ChunkProviderServer u;
    public boolean v = false;
    public boolean w;
    private MinecraftServer x;
    private EntityList y = new EntityList();

    public WorldServer(MinecraftServer minecraftserver, IDataManager idatamanager, String s, int i) {
        super(idatamanager, s, (new Random()).nextLong(), WorldProvider.a(i));
        this.x = minecraftserver;
    }

    public void a(Entity entity, boolean flag) {
        if (!this.x.m && (entity instanceof EntityAnimal || entity instanceof EntityWaterAnimal)) {
            entity.C();
        }

        if (entity.passenger == null || !(entity.passenger instanceof EntityHuman)) {
            super.a(entity, flag);
        }
    }

    public void b(Entity entity, boolean flag) {
        super.a(entity, flag);
    }

    protected IChunkProvider b() {
        IChunkLoader ichunkloader = this.p.a(this.m);

        this.u = new ChunkProviderServer(this, ichunkloader, this.m.c());
        return this.u;
    }

    public List d(int i, int j, int k, int l, int i1, int j1) {
        ArrayList arraylist = new ArrayList();

        for (int k1 = 0; k1 < this.c.size(); ++k1) {
            TileEntity tileentity = (TileEntity) this.c.get(k1);

            if (tileentity.e >= i && tileentity.f >= j && tileentity.g >= k && tileentity.e < l && tileentity.f < i1 && tileentity.g < j1) {
                arraylist.add(tileentity);
            }
        }

        return arraylist;
    }

    public boolean a(EntityHuman entityhuman, int i, int j, int k) {
        int l = (int) MathHelper.e((float) (i - this.q.c()));
        int i1 = (int) MathHelper.e((float) (k - this.q.e()));

        if (l > i1) {
            i1 = l;
        }

        return i1 > 16 || this.x.f.h(entityhuman.name);
    }

    protected void b(Entity entity) {
        super.b(entity);
        this.y.a(entity.id, entity);
    }

    protected void c(Entity entity) {
        super.c(entity);
        this.y.d(entity.id);
    }

    public Entity a(int i) {
        return (Entity) this.y.a(i);
    }

    public void a(Entity entity, byte b0) {
        Packet38EntityStatus packet38entitystatus = new Packet38EntityStatus(entity.id, b0);

        this.x.k.b(entity, packet38entitystatus);
    }

    public Explosion a(Entity entity, double d0, double d1, double d2, float f, boolean flag) {
        Explosion explosion = super.a(entity, d0, d1, d2, f, flag);

        this.x.f.a(d0, d1, d2, 64.0D, new Packet60Explosion(d0, d1, d2, f, explosion.g));
        return explosion;
    }

    public void d(int i, int j, int k, int l, int i1) {
        super.d(i, j, k, l, i1);
        this.x.f.a((double) i, (double) j, (double) k, 64.0D, new Packet54PlayNoteBlock(i, j, k, l, i1));
    }

    public void r() {
        this.p.e();
    }
}
