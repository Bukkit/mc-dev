package net.minecraft.server;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class EntityPlayer extends EntityHuman implements ICrafting {

    public NetServerHandler netServerHandler;
    public MinecraftServer server;
    public ItemInWorldManager itemInWorldManager;
    public double d;
    public double e;
    public List chunkCoordIntPairQueue = new LinkedList();
    public Set playerChunkCoordIntPairs = new HashSet();
    private int cf = -99999999;
    private int cg = -99999999;
    private boolean ch = true;
    private int lastSentExp = -99999999;
    private int invulnerableTicks = 60;
    private ItemStack[] ck = new ItemStack[] { null, null, null, null, null};
    private int containerCounter = 0;
    public boolean h;
    public int ping;
    public boolean viewingCredits = false;

    public EntityPlayer(MinecraftServer minecraftserver, World world, String s, ItemInWorldManager iteminworldmanager) {
        super(world);
        iteminworldmanager.player = this;
        this.itemInWorldManager = iteminworldmanager;
        ChunkCoordinates chunkcoordinates = world.getSpawn();
        int i = chunkcoordinates.x;
        int j = chunkcoordinates.z;
        int k = chunkcoordinates.y;

        if (!world.worldProvider.e) {
            i += this.random.nextInt(20) - 10;
            k = world.g(i, j);
            j += this.random.nextInt(20) - 10;
        }

        this.setPositionRotation((double) i + 0.5D, (double) k, (double) j + 0.5D, 0.0F, 0.0F);
        this.server = minecraftserver;
        this.bP = 0.0F;
        this.name = s;
        this.height = 0.0F;
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.hasKey("playerGameType")) {
            this.itemInWorldManager.setGameMode(nbttagcompound.getInt("playerGameType"));
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("playerGameType", this.itemInWorldManager.getGameMode());
    }

    public void spawnIn(World world) {
        super.spawnIn(world);
    }

    public void levelDown(int i) {
        super.levelDown(i);
        this.lastSentExp = -1;
    }

    public void syncInventory() {
        this.activeContainer.addSlotListener(this);
    }

    public ItemStack[] getEquipment() {
        return this.ck;
    }

    protected void A() {
        this.height = 0.0F;
    }

    public float getHeadHeight() {
        return 1.62F;
    }

    public void F_() {
        this.itemInWorldManager.c();
        --this.invulnerableTicks;
        this.activeContainer.a();

        for (int i = 0; i < 5; ++i) {
            ItemStack itemstack = this.c(i);

            if (itemstack != this.ck[i]) {
                this.server.getTracker(this.dimension).a(this, new Packet5EntityEquipment(this.id, i, itemstack));
                this.ck[i] = itemstack;
            }
        }
    }

    public ItemStack c(int i) {
        return i == 0 ? this.inventory.getItemInHand() : this.inventory.armor[i - 1];
    }

    public void die(DamageSource damagesource) {
        this.server.serverConfigurationManager.sendAll(new Packet3Chat(damagesource.getLocalizedDeathMessage(this)));
        this.inventory.k();
    }

    public boolean damageEntity(DamageSource damagesource, int i) {
        if (this.invulnerableTicks > 0) {
            return false;
        } else {
            if (!this.server.pvpMode && damagesource instanceof EntityDamageSource) {
                Entity entity = damagesource.getEntity();

                if (entity instanceof EntityHuman) {
                    return false;
                }

                if (entity instanceof EntityArrow) {
                    EntityArrow entityarrow = (EntityArrow) entity;

                    if (entityarrow.shooter instanceof EntityHuman) {
                        return false;
                    }
                }
            }

            return super.damageEntity(damagesource, i);
        }
    }

    protected boolean C() {
        return this.server.pvpMode;
    }

    public void heal(int i) {
        super.heal(i);
    }

    public void a(boolean flag) {
        super.F_();

        for (int i = 0; i < this.inventory.getSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);

            if (itemstack != null && Item.byId[itemstack.id].t_() && this.netServerHandler.lowPriorityCount() <= 2) {
                Packet packet = ((ItemWorldMapBase) Item.byId[itemstack.id]).c(itemstack, this.world, this);

                if (packet != null) {
                    this.netServerHandler.sendPacket(packet);
                }
            }
        }

        if (flag && !this.chunkCoordIntPairQueue.isEmpty()) {
            ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) this.chunkCoordIntPairQueue.get(0);
            double d0 = chunkcoordintpair.a(this);

            for (int j = 0; j < this.chunkCoordIntPairQueue.size(); ++j) {
                ChunkCoordIntPair chunkcoordintpair1 = (ChunkCoordIntPair) this.chunkCoordIntPairQueue.get(j);
                double d1 = chunkcoordintpair1.a(this);

                if (d1 < d0) {
                    chunkcoordintpair = chunkcoordintpair1;
                    d0 = d1;
                }
            }

            if (chunkcoordintpair != null) {
                boolean flag1 = false;

                if (this.netServerHandler.lowPriorityCount() < 4) {
                    flag1 = true;
                }

                if (flag1) {
                    WorldServer worldserver = this.server.getWorldServer(this.dimension);

                    if (worldserver.isLoaded(chunkcoordintpair.x << 4, 0, chunkcoordintpair.z << 4)) {
                        Chunk chunk = worldserver.getChunkAt(chunkcoordintpair.x, chunkcoordintpair.z);

                        if (chunk.done) {
                            this.chunkCoordIntPairQueue.remove(chunkcoordintpair);
                            this.netServerHandler.sendPacket(new Packet51MapChunk(worldserver.getChunkAt(chunkcoordintpair.x, chunkcoordintpair.z), true, 0));
                            List list = worldserver.getTileEntities(chunkcoordintpair.x * 16, 0, chunkcoordintpair.z * 16, chunkcoordintpair.x * 16 + 16, 256, chunkcoordintpair.z * 16 + 16);

                            for (int k = 0; k < list.size(); ++k) {
                                this.a((TileEntity) list.get(k));
                            }
                        }
                    }
                }
            }
        }

        if (this.J) {
            if (this.server.propertyManager.getBoolean("allow-nether", true)) {
                if (this.activeContainer != this.defaultContainer) {
                    this.closeInventory();
                }

                if (this.vehicle != null) {
                    this.mount(this.vehicle);
                } else {
                    this.K += 0.0125F;
                    if (this.K >= 1.0F) {
                        this.K = 1.0F;
                        this.I = 10;
                        boolean flag2 = false;
                        byte b0;

                        if (this.dimension == -1) {
                            b0 = 0;
                        } else {
                            b0 = -1;
                        }

                        this.server.serverConfigurationManager.changeDimension(this, b0);
                        this.lastSentExp = -1;
                        this.cf = -1;
                        this.cg = -1;
                        this.a((Statistic) AchievementList.x);
                    }
                }

                this.J = false;
            }
        } else {
            if (this.K > 0.0F) {
                this.K -= 0.05F;
            }

            if (this.K < 0.0F) {
                this.K = 0.0F;
            }
        }

        if (this.I > 0) {
            --this.I;
        }

        if (this.getHealth() != this.cf || this.cg != this.foodData.a() || this.foodData.c() == 0.0F != this.ch) {
            this.netServerHandler.sendPacket(new Packet8UpdateHealth(this.getHealth(), this.foodData.a(), this.foodData.c()));
            this.cf = this.getHealth();
            this.cg = this.foodData.a();
            this.ch = this.foodData.c() == 0.0F;
        }

        if (this.expTotal != this.lastSentExp) {
            this.lastSentExp = this.expTotal;
            this.netServerHandler.sendPacket(new Packet43SetExperience(this.exp, this.expTotal, this.expLevel));
        }
    }

    public void e(int i) {
        if (this.dimension == 1 && i == 1) {
            this.a((Statistic) AchievementList.C);
            this.world.kill(this);
            this.viewingCredits = true;
            this.netServerHandler.sendPacket(new Packet70Bed(4, 0));
        } else {
            this.a((Statistic) AchievementList.B);
            ChunkCoordinates chunkcoordinates = this.server.getWorldServer(i).getDimensionSpawn();

            if (chunkcoordinates != null) {
                this.netServerHandler.a((double) chunkcoordinates.x, (double) chunkcoordinates.y, (double) chunkcoordinates.z, 0.0F, 0.0F);
            }

            this.server.serverConfigurationManager.changeDimension(this, 1);
            this.lastSentExp = -1;
            this.cf = -1;
            this.cg = -1;
        }
    }

    private void a(TileEntity tileentity) {
        if (tileentity != null) {
            Packet packet = tileentity.d();

            if (packet != null) {
                this.netServerHandler.sendPacket(packet);
            }
        }
    }

    public void receive(Entity entity, int i) {
        if (!entity.dead) {
            EntityTracker entitytracker = this.server.getTracker(this.dimension);

            if (entity instanceof EntityItem) {
                entitytracker.a(entity, new Packet22Collect(entity.id, this.id));
            }

            if (entity instanceof EntityArrow) {
                entitytracker.a(entity, new Packet22Collect(entity.id, this.id));
            }

            if (entity instanceof EntityExperienceOrb) {
                entitytracker.a(entity, new Packet22Collect(entity.id, this.id));
            }
        }

        super.receive(entity, i);
        this.activeContainer.a();
    }

    public void C_() {
        if (!this.t) {
            this.u = -1;
            this.t = true;
            EntityTracker entitytracker = this.server.getTracker(this.dimension);

            entitytracker.a(this, new Packet18ArmAnimation(this, 1));
        }
    }

    public void E() {}

    public EnumBedResult a(int i, int j, int k) {
        EnumBedResult enumbedresult = super.a(i, j, k);

        if (enumbedresult == EnumBedResult.OK) {
            EntityTracker entitytracker = this.server.getTracker(this.dimension);
            Packet17EntityLocationAction packet17entitylocationaction = new Packet17EntityLocationAction(this, 0, i, j, k);

            entitytracker.a(this, packet17entitylocationaction);
            this.netServerHandler.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
            this.netServerHandler.sendPacket(packet17entitylocationaction);
        }

        return enumbedresult;
    }

    public void a(boolean flag, boolean flag1, boolean flag2) {
        if (this.isSleeping()) {
            EntityTracker entitytracker = this.server.getTracker(this.dimension);

            entitytracker.sendPacketToEntity(this, new Packet18ArmAnimation(this, 3));
        }

        super.a(flag, flag1, flag2);
        if (this.netServerHandler != null) {
            this.netServerHandler.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
        }
    }

    public void mount(Entity entity) {
        super.mount(entity);
        this.netServerHandler.sendPacket(new Packet39AttachEntity(this, this.vehicle));
        this.netServerHandler.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
    }

    protected void a(double d0, boolean flag) {}

    public void b(double d0, boolean flag) {
        super.a(d0, flag);
    }

    private void nextContainerCounter() {
        this.containerCounter = this.containerCounter % 100 + 1;
    }

    public void startCrafting(int i, int j, int k) {
        this.nextContainerCounter();
        this.netServerHandler.sendPacket(new Packet100OpenWindow(this.containerCounter, 1, "Crafting", 9));
        this.activeContainer = new ContainerWorkbench(this.inventory, this.world, i, j, k);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void startEnchanting(int i, int j, int k) {
        this.nextContainerCounter();
        this.netServerHandler.sendPacket(new Packet100OpenWindow(this.containerCounter, 4, "Enchanting", 9));
        this.activeContainer = new ContainerEnchantTable(this.inventory, this.world, i, j, k);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void openContainer(IInventory iinventory) {
        this.nextContainerCounter();
        this.netServerHandler.sendPacket(new Packet100OpenWindow(this.containerCounter, 0, iinventory.getName(), iinventory.getSize()));
        this.activeContainer = new ContainerChest(this.inventory, iinventory);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void openFurnace(TileEntityFurnace tileentityfurnace) {
        this.nextContainerCounter();
        this.netServerHandler.sendPacket(new Packet100OpenWindow(this.containerCounter, 2, tileentityfurnace.getName(), tileentityfurnace.getSize()));
        this.activeContainer = new ContainerFurnace(this.inventory, tileentityfurnace);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void openDispenser(TileEntityDispenser tileentitydispenser) {
        this.nextContainerCounter();
        this.netServerHandler.sendPacket(new Packet100OpenWindow(this.containerCounter, 3, tileentitydispenser.getName(), tileentitydispenser.getSize()));
        this.activeContainer = new ContainerDispenser(this.inventory, tileentitydispenser);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void openBrewingStand(TileEntityBrewingStand tileentitybrewingstand) {
        this.nextContainerCounter();
        this.netServerHandler.sendPacket(new Packet100OpenWindow(this.containerCounter, 5, tileentitybrewingstand.getName(), tileentitybrewingstand.getSize()));
        this.activeContainer = new ContainerBrewingStand(this.inventory, tileentitybrewingstand);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void a(Container container, int i, ItemStack itemstack) {
        if (!(container.getSlot(i) instanceof SlotResult)) {
            if (!this.h) {
                this.netServerHandler.sendPacket(new Packet103SetSlot(container.windowId, i, itemstack));
            }
        }
    }

    public void updateInventory(Container container) {
        this.a(container, container.b());
    }

    public void a(Container container, List list) {
        this.netServerHandler.sendPacket(new Packet104WindowItems(container.windowId, list));
        this.netServerHandler.sendPacket(new Packet103SetSlot(-1, -1, this.inventory.getCarried()));
    }

    public void setContainerData(Container container, int i, int j) {
        this.netServerHandler.sendPacket(new Packet105CraftProgressBar(container.windowId, i, j));
    }

    public void carriedChanged(ItemStack itemstack) {}

    public void closeInventory() {
        this.netServerHandler.sendPacket(new Packet101CloseWindow(this.activeContainer.windowId));
        this.H();
    }

    public void broadcastCarriedItem() {
        if (!this.h) {
            this.netServerHandler.sendPacket(new Packet103SetSlot(-1, -1, this.inventory.getCarried()));
        }
    }

    public void H() {
        this.activeContainer.a((EntityHuman) this);
        this.activeContainer = this.defaultContainer;
    }

    public void a(Statistic statistic, int i) {
        if (statistic != null) {
            if (!statistic.f) {
                while (i > 100) {
                    this.netServerHandler.sendPacket(new Packet200Statistic(statistic.e, 100));
                    i -= 100;
                }

                this.netServerHandler.sendPacket(new Packet200Statistic(statistic.e, i));
            }
        }
    }

    public void I() {
        if (this.vehicle != null) {
            this.mount(this.vehicle);
        }

        if (this.passenger != null) {
            this.passenger.mount(this);
        }

        if (this.sleeping) {
            this.a(true, false, false);
        }
    }

    public void D_() {
        this.cf = -99999999;
    }

    public void a(String s) {
        LocaleLanguage localelanguage = LocaleLanguage.a();
        String s1 = localelanguage.b(s);

        this.netServerHandler.sendPacket(new Packet3Chat(s1));
    }

    protected void K() {
        this.netServerHandler.sendPacket(new Packet38EntityStatus(this.id, (byte) 9));
        super.K();
    }

    public void a(ItemStack itemstack, int i) {
        super.a(itemstack, i);
        if (itemstack != null && itemstack.getItem() != null && itemstack.getItem().d(itemstack) == EnumAnimation.b) {
            EntityTracker entitytracker = this.server.getTracker(this.dimension);

            entitytracker.sendPacketToEntity(this, new Packet18ArmAnimation(this, 5));
        }
    }

    protected void b(MobEffect mobeffect) {
        super.b(mobeffect);
        this.netServerHandler.sendPacket(new Packet41MobEffect(this.id, mobeffect));
    }

    protected void c(MobEffect mobeffect) {
        super.c(mobeffect);
        this.netServerHandler.sendPacket(new Packet41MobEffect(this.id, mobeffect));
    }

    protected void d(MobEffect mobeffect) {
        super.d(mobeffect);
        this.netServerHandler.sendPacket(new Packet42RemoveMobEffect(this.id, mobeffect));
    }

    public void enderTeleportTo(double d0, double d1, double d2) {
        this.netServerHandler.a(d0, d1, d2, this.yaw, this.pitch);
    }

    public void c(Entity entity) {
        EntityTracker entitytracker = this.server.getTracker(this.dimension);

        entitytracker.sendPacketToEntity(this, new Packet18ArmAnimation(entity, 6));
    }

    public void d(Entity entity) {
        EntityTracker entitytracker = this.server.getTracker(this.dimension);

        entitytracker.sendPacketToEntity(this, new Packet18ArmAnimation(entity, 7));
    }

    public void updateAbilities() {
        if (this.netServerHandler != null) {
            this.netServerHandler.sendPacket(new Packet202Abilities(this.abilities));
        }
    }
}
