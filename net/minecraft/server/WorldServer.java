package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class WorldServer extends World {

    public ChunkProviderServer chunkProviderServer;
    public boolean weirdIsOpCache = false;
    public boolean savingDisabled;
    private MinecraftServer server;
    private IntHashMap N = new IntHashMap();

    public WorldServer(MinecraftServer minecraftserver, IDataManager idatamanager, String s, int i, WorldSettings worldsettings) {
        super(idatamanager, s, worldsettings, WorldProvider.byDimension(i));
        this.server = minecraftserver;
    }

    public void entityJoinedWorld(Entity entity, boolean flag) {
        if (!this.server.spawnAnimals && (entity instanceof EntityAnimal || entity instanceof EntityWaterAnimal)) {
            entity.die();
        }

        if (entity.passenger == null || !(entity.passenger instanceof EntityHuman)) {
            super.entityJoinedWorld(entity, flag);
        }
    }

    public void vehicleEnteredWorld(Entity entity, boolean flag) {
        super.entityJoinedWorld(entity, flag);
    }

    protected IChunkProvider b() {
        IChunkLoader ichunkloader = this.dataManager.createChunkLoader(this.worldProvider);

        this.chunkProviderServer = new ChunkProviderServer(this, ichunkloader, this.worldProvider.getChunkProvider());
        return this.chunkProviderServer;
    }

    public List getTileEntities(int i, int j, int k, int l, int i1, int j1) {
        ArrayList arraylist = new ArrayList();

        for (int k1 = 0; k1 < this.h.size(); ++k1) {
            TileEntity tileentity = (TileEntity) this.h.get(k1);

            if (tileentity.x >= i && tileentity.y >= j && tileentity.z >= k && tileentity.x < l && tileentity.y < i1 && tileentity.z < j1) {
                arraylist.add(tileentity);
            }
        }

        return arraylist;
    }

    public boolean a(EntityHuman entityhuman, int i, int j, int k) {
        int l = MathHelper.a(i - this.worldData.c());
        int i1 = MathHelper.a(k - this.worldData.e());

        if (l > i1) {
            i1 = l;
        }

        return i1 > 16 || this.server.serverConfigurationManager.isOp(entityhuman.name);
    }

    protected void c(Entity entity) {
        super.c(entity);
        this.N.a(entity.id, entity);
        Entity[] aentity = entity.aG();

        if (aentity != null) {
            for (int i = 0; i < aentity.length; ++i) {
                this.N.a(aentity[i].id, aentity[i]);
            }
        }
    }

    protected void d(Entity entity) {
        super.d(entity);
        this.N.d(entity.id);
        Entity[] aentity = entity.aG();

        if (aentity != null) {
            for (int i = 0; i < aentity.length; ++i) {
                this.N.d(aentity[i].id);
            }
        }
    }

    public Entity getEntity(int i) {
        return (Entity) this.N.a(i);
    }

    public boolean strikeLightning(Entity entity) {
        if (super.strikeLightning(entity)) {
            this.server.serverConfigurationManager.sendPacketNearby(entity.locX, entity.locY, entity.locZ, 512.0D, this.worldProvider.dimension, new Packet71Weather(entity));
            return true;
        } else {
            return false;
        }
    }

    public void a(Entity entity, byte b0) {
        Packet38EntityStatus packet38entitystatus = new Packet38EntityStatus(entity.id, b0);

        this.server.getTracker(this.worldProvider.dimension).sendPacketToEntity(entity, packet38entitystatus);
    }

    public Explosion createExplosion(Entity entity, double d0, double d1, double d2, float f, boolean flag) {
        Explosion explosion = new Explosion(this, entity, d0, d1, d2, f);

        explosion.a = flag;
        explosion.a();
        explosion.a(false);
        this.server.serverConfigurationManager.sendPacketNearby(d0, d1, d2, 64.0D, this.worldProvider.dimension, new Packet60Explosion(d0, d1, d2, f, explosion.blocks));
        return explosion;
    }

    public void playNote(int i, int j, int k, int l, int i1) {
        super.playNote(i, j, k, l, i1);
        this.server.serverConfigurationManager.sendPacketNearby((double) i, (double) j, (double) k, 64.0D, this.worldProvider.dimension, new Packet54PlayNoteBlock(i, j, k, l, i1));
    }

    public void saveLevel() {
        this.dataManager.e();
    }

    protected void i() {
        boolean flag = this.w();

        super.i();
        if (flag != this.w()) {
            if (flag) {
                this.server.serverConfigurationManager.sendAll(new Packet70Bed(2, 0));
            } else {
                this.server.serverConfigurationManager.sendAll(new Packet70Bed(1, 0));
            }
        }
    }
}
