package net.minecraft.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.util.com.google.common.collect.Sets;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.io.netty.buffer.Unpooled;
import net.minecraft.util.org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityPlayer extends EntityHuman implements ICrafting {

    private static final Logger bM = LogManager.getLogger();
    private String locale = "en_US";
    public PlayerConnection playerConnection;
    public final MinecraftServer server;
    public final PlayerInteractManager playerInteractManager;
    public double d;
    public double e;
    public final List chunkCoordIntPairQueue = new LinkedList();
    public final List removeQueue = new LinkedList();
    private final ServerStatisticManager bO;
    private float bP = Float.MIN_VALUE;
    private float bQ = -1.0E8F;
    private int bR = -99999999;
    private boolean bS = true;
    private int lastSentExp = -99999999;
    private int invulnerableTicks = 60;
    private int bV;
    private EnumChatVisibility bW;
    private boolean bX = true;
    private long bY = 0L;
    private int containerCounter;
    public boolean h;
    public int ping;
    public boolean viewingCredits;

    public EntityPlayer(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile, PlayerInteractManager playerinteractmanager) {
        super(worldserver, gameprofile);
        playerinteractmanager.player = this;
        this.playerInteractManager = playerinteractmanager;
        this.bV = minecraftserver.getPlayerList().o();
        ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
        int i = chunkcoordinates.x;
        int j = chunkcoordinates.z;
        int k = chunkcoordinates.y;

        if (!worldserver.worldProvider.g && worldserver.getWorldData().getGameType() != EnumGamemode.ADVENTURE) {
            int l = Math.max(5, minecraftserver.getSpawnProtection() - 6);

            i += this.random.nextInt(l * 2) - l;
            j += this.random.nextInt(l * 2) - l;
            k = worldserver.i(i, j);
        }

        this.server = minecraftserver;
        this.bO = minecraftserver.getPlayerList().i(this.getName());
        this.X = 0.0F;
        this.height = 0.0F;
        this.setPositionRotation((double) i + 0.5D, (double) k, (double) j + 0.5D, 0.0F, 0.0F);

        while (!worldserver.getCubes(this, this.boundingBox).isEmpty()) {
            this.setPosition(this.locX, this.locY + 1.0D, this.locZ);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.hasKeyOfType("playerGameType", 99)) {
            if (MinecraftServer.getServer().getForceGamemode()) {
                this.playerInteractManager.setGameMode(MinecraftServer.getServer().getGamemode());
            } else {
                this.playerInteractManager.setGameMode(EnumGamemode.a(nbttagcompound.getInt("playerGameType")));
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("playerGameType", this.playerInteractManager.getGameMode().a());
    }

    public void levelDown(int i) {
        super.levelDown(i);
        this.lastSentExp = -1;
    }

    public void syncInventory() {
        this.activeContainer.addSlotListener(this);
    }

    protected void e_() {
        this.height = 0.0F;
    }

    public float getHeadHeight() {
        return 1.62F;
    }

    public void h() {
        this.playerInteractManager.a();
        --this.invulnerableTicks;
        if (this.noDamageTicks > 0) {
            --this.noDamageTicks;
        }

        this.activeContainer.b();
        if (!this.world.isStatic && !this.activeContainer.a((EntityHuman) this)) {
            this.closeInventory();
            this.activeContainer = this.defaultContainer;
        }

        while (!this.removeQueue.isEmpty()) {
            int i = Math.min(this.removeQueue.size(), 127);
            int[] aint = new int[i];
            Iterator iterator = this.removeQueue.iterator();
            int j = 0;

            while (iterator.hasNext() && j < i) {
                aint[j++] = ((Integer) iterator.next()).intValue();
                iterator.remove();
            }

            this.playerConnection.sendPacket(new PacketPlayOutEntityDestroy(aint));
        }

        if (!this.chunkCoordIntPairQueue.isEmpty()) {
            ArrayList arraylist = new ArrayList();
            Iterator iterator1 = this.chunkCoordIntPairQueue.iterator();
            ArrayList arraylist1 = new ArrayList();

            Chunk chunk;

            while (iterator1.hasNext() && arraylist.size() < PacketPlayOutMapChunkBulk.c()) {
                ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) iterator1.next();

                if (chunkcoordintpair != null) {
                    if (this.world.isLoaded(chunkcoordintpair.x << 4, 0, chunkcoordintpair.z << 4)) {
                        chunk = this.world.getChunkAt(chunkcoordintpair.x, chunkcoordintpair.z);
                        if (chunk.k()) {
                            arraylist.add(chunk);
                            arraylist1.addAll(((WorldServer) this.world).getTileEntities(chunkcoordintpair.x * 16, 0, chunkcoordintpair.z * 16, chunkcoordintpair.x * 16 + 16, 256, chunkcoordintpair.z * 16 + 16));
                            iterator1.remove();
                        }
                    }
                } else {
                    iterator1.remove();
                }
            }

            if (!arraylist.isEmpty()) {
                this.playerConnection.sendPacket(new PacketPlayOutMapChunkBulk(arraylist));
                Iterator iterator2 = arraylist1.iterator();

                while (iterator2.hasNext()) {
                    TileEntity tileentity = (TileEntity) iterator2.next();

                    this.b(tileentity);
                }

                iterator2 = arraylist.iterator();

                while (iterator2.hasNext()) {
                    chunk = (Chunk) iterator2.next();
                    this.r().getTracker().a(this, chunk);
                }
            }
        }

        if (this.bY > 0L && this.server.aq() > 0 && MinecraftServer.ap() - this.bY > (long) (this.server.aq() * 1000 * 60)) {
            this.playerConnection.disconnect("You have been idle for too long!");
        }
    }

    public void i() {
        try {
            super.h();

            for (int i = 0; i < this.inventory.getSize(); ++i) {
                ItemStack itemstack = this.inventory.getItem(i);

                if (itemstack != null && itemstack.getItem().h()) {
                    Packet packet = ((ItemWorldMapBase) itemstack.getItem()).c(itemstack, this.world, this);

                    if (packet != null) {
                        this.playerConnection.sendPacket(packet);
                    }
                }
            }

            if (this.getHealth() != this.bQ || this.bR != this.foodData.a() || this.foodData.e() == 0.0F != this.bS) {
                this.playerConnection.sendPacket(new PacketPlayOutUpdateHealth(this.getHealth(), this.foodData.a(), this.foodData.e()));
                this.bQ = this.getHealth();
                this.bR = this.foodData.a();
                this.bS = this.foodData.e() == 0.0F;
            }

            if (this.getHealth() + this.bs() != this.bP) {
                this.bP = this.getHealth() + this.bs();
                Collection collection = this.getScoreboard().getObjectivesForCriteria(IScoreboardCriteria.f);
                Iterator iterator = collection.iterator();

                while (iterator.hasNext()) {
                    ScoreboardObjective scoreboardobjective = (ScoreboardObjective) iterator.next();

                    this.getScoreboard().getPlayerScoreForObjective(this.getName(), scoreboardobjective).updateForList(Arrays.asList(new EntityHuman[] { this}));
                }
            }

            if (this.expTotal != this.lastSentExp) {
                this.lastSentExp = this.expTotal;
                this.playerConnection.sendPacket(new PacketPlayOutExperience(this.exp, this.expTotal, this.expLevel));
            }

            if (this.ticksLived % 20 * 5 == 0 && !this.x().a(AchievementList.L)) {
                this.j();
            }
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Ticking player");
            CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Player being ticked");

            this.a(crashreportsystemdetails);
            throw new ReportedException(crashreport);
        }
    }

    protected void j() {
        BiomeBase biomebase = this.world.getBiome(MathHelper.floor(this.locX), MathHelper.floor(this.locZ));

        if (biomebase != null) {
            String s = biomebase.af;
            AchievementSet achievementset = (AchievementSet) this.x().b(AchievementList.L);

            if (achievementset == null) {
                achievementset = (AchievementSet) this.x().a(AchievementList.L, new AchievementSet());
            }

            achievementset.add(s);
            if (this.x().b(AchievementList.L) && achievementset.size() == BiomeBase.n.size()) {
                HashSet hashset = Sets.newHashSet(BiomeBase.n);
                Iterator iterator = achievementset.iterator();

                while (iterator.hasNext()) {
                    String s1 = (String) iterator.next();
                    Iterator iterator1 = hashset.iterator();

                    while (iterator1.hasNext()) {
                        BiomeBase biomebase1 = (BiomeBase) iterator1.next();

                        if (biomebase1.af.equals(s1)) {
                            iterator1.remove();
                        }
                    }

                    if (hashset.isEmpty()) {
                        break;
                    }
                }

                if (hashset.isEmpty()) {
                    this.a((Statistic) AchievementList.L);
                }
            }
        }
    }

    public void die(DamageSource damagesource) {
        this.server.getPlayerList().sendMessage(this.aW().b());
        if (!this.world.getGameRules().getBoolean("keepInventory")) {
            this.inventory.m();
        }

        Collection collection = this.world.getScoreboard().getObjectivesForCriteria(IScoreboardCriteria.c);
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreboardObjective scoreboardobjective = (ScoreboardObjective) iterator.next();
            ScoreboardScore scoreboardscore = this.getScoreboard().getPlayerScoreForObjective(this.getName(), scoreboardobjective);

            scoreboardscore.incrementScore();
        }

        EntityLiving entityliving = this.aX();

        if (entityliving != null) {
            int i = EntityTypes.a(entityliving);
            MonsterEggInfo monsteregginfo = (MonsterEggInfo) EntityTypes.a.get(Integer.valueOf(i));

            if (monsteregginfo != null) {
                this.a(monsteregginfo.e, 1);
            }

            entityliving.b(this, this.bb);
        }

        this.a(StatisticList.v, 1);
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (this.isInvulnerable()) {
            return false;
        } else {
            boolean flag = this.server.V() && this.server.getPvP() && "fall".equals(damagesource.translationIndex);

            if (!flag && this.invulnerableTicks > 0 && damagesource != DamageSource.OUT_OF_WORLD) {
                return false;
            } else {
                if (damagesource instanceof EntityDamageSource) {
                    Entity entity = damagesource.getEntity();

                    if (entity instanceof EntityHuman && !this.a((EntityHuman) entity)) {
                        return false;
                    }

                    if (entity instanceof EntityArrow) {
                        EntityArrow entityarrow = (EntityArrow) entity;

                        if (entityarrow.shooter instanceof EntityHuman && !this.a((EntityHuman) entityarrow.shooter)) {
                            return false;
                        }
                    }
                }

                return super.damageEntity(damagesource, f);
            }
        }
    }

    public boolean a(EntityHuman entityhuman) {
        return !this.server.getPvP() ? false : super.a(entityhuman);
    }

    public void b(int i) {
        if (this.dimension == 1 && i == 1) {
            this.a((Statistic) AchievementList.D);
            this.world.kill(this);
            this.viewingCredits = true;
            this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(4, 0.0F));
        } else {
            if (this.dimension == 0 && i == 1) {
                this.a((Statistic) AchievementList.C);
                ChunkCoordinates chunkcoordinates = this.server.getWorldServer(i).getDimensionSpawn();

                if (chunkcoordinates != null) {
                    this.playerConnection.a((double) chunkcoordinates.x, (double) chunkcoordinates.y, (double) chunkcoordinates.z, 0.0F, 0.0F);
                }

                i = 1;
            } else {
                this.a((Statistic) AchievementList.y);
            }

            this.server.getPlayerList().changeDimension(this, i);
            this.lastSentExp = -1;
            this.bQ = -1.0F;
            this.bR = -1;
        }
    }

    private void b(TileEntity tileentity) {
        if (tileentity != null) {
            Packet packet = tileentity.getUpdatePacket();

            if (packet != null) {
                this.playerConnection.sendPacket(packet);
            }
        }
    }

    public void receive(Entity entity, int i) {
        super.receive(entity, i);
        this.activeContainer.b();
    }

    public EnumBedResult a(int i, int j, int k) {
        EnumBedResult enumbedresult = super.a(i, j, k);

        if (enumbedresult == EnumBedResult.OK) {
            PacketPlayOutBed packetplayoutbed = new PacketPlayOutBed(this, i, j, k);

            this.r().getTracker().a((Entity) this, (Packet) packetplayoutbed);
            this.playerConnection.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
            this.playerConnection.sendPacket(packetplayoutbed);
        }

        return enumbedresult;
    }

    public void a(boolean flag, boolean flag1, boolean flag2) {
        if (this.isSleeping()) {
            this.r().getTracker().sendPacketToEntity(this, new PacketPlayOutAnimation(this, 2));
        }

        super.a(flag, flag1, flag2);
        if (this.playerConnection != null) {
            this.playerConnection.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
        }
    }

    public void mount(Entity entity) {
        super.mount(entity);
        this.playerConnection.sendPacket(new PacketPlayOutAttachEntity(0, this, this.vehicle));
        this.playerConnection.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
    }

    protected void a(double d0, boolean flag) {}

    public void b(double d0, boolean flag) {
        super.a(d0, flag);
    }

    public void a(TileEntity tileentity) {
        if (tileentity instanceof TileEntitySign) {
            ((TileEntitySign) tileentity).a((EntityHuman) this);
            this.playerConnection.sendPacket(new PacketPlayOutOpenSignEditor(tileentity.x, tileentity.y, tileentity.z));
        }
    }

    private void nextContainerCounter() {
        this.containerCounter = this.containerCounter % 100 + 1;
    }

    public void startCrafting(int i, int j, int k) {
        this.nextContainerCounter();
        this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, 1, "Crafting", 9, true));
        this.activeContainer = new ContainerWorkbench(this.inventory, this.world, i, j, k);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void startEnchanting(int i, int j, int k, String s) {
        this.nextContainerCounter();
        this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, 4, s == null ? "" : s, 9, s != null));
        this.activeContainer = new ContainerEnchantTable(this.inventory, this.world, i, j, k);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void openAnvil(int i, int j, int k) {
        this.nextContainerCounter();
        this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, 8, "Repairing", 9, true));
        this.activeContainer = new ContainerAnvil(this.inventory, this.world, i, j, k, this);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void openContainer(IInventory iinventory) {
        if (this.activeContainer != this.defaultContainer) {
            this.closeInventory();
        }

        this.nextContainerCounter();
        this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, 0, iinventory.getInventoryName(), iinventory.getSize(), iinventory.k_()));
        this.activeContainer = new ContainerChest(this.inventory, iinventory);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void openHopper(TileEntityHopper tileentityhopper) {
        this.nextContainerCounter();
        this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, 9, tileentityhopper.getInventoryName(), tileentityhopper.getSize(), tileentityhopper.k_()));
        this.activeContainer = new ContainerHopper(this.inventory, tileentityhopper);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void openMinecartHopper(EntityMinecartHopper entityminecarthopper) {
        this.nextContainerCounter();
        this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, 9, entityminecarthopper.getInventoryName(), entityminecarthopper.getSize(), entityminecarthopper.k_()));
        this.activeContainer = new ContainerHopper(this.inventory, entityminecarthopper);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void openFurnace(TileEntityFurnace tileentityfurnace) {
        this.nextContainerCounter();
        this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, 2, tileentityfurnace.getInventoryName(), tileentityfurnace.getSize(), tileentityfurnace.k_()));
        this.activeContainer = new ContainerFurnace(this.inventory, tileentityfurnace);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void openDispenser(TileEntityDispenser tileentitydispenser) {
        this.nextContainerCounter();
        this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, tileentitydispenser instanceof TileEntityDropper ? 10 : 3, tileentitydispenser.getInventoryName(), tileentitydispenser.getSize(), tileentitydispenser.k_()));
        this.activeContainer = new ContainerDispenser(this.inventory, tileentitydispenser);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void openBrewingStand(TileEntityBrewingStand tileentitybrewingstand) {
        this.nextContainerCounter();
        this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, 5, tileentitybrewingstand.getInventoryName(), tileentitybrewingstand.getSize(), tileentitybrewingstand.k_()));
        this.activeContainer = new ContainerBrewingStand(this.inventory, tileentitybrewingstand);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void openBeacon(TileEntityBeacon tileentitybeacon) {
        this.nextContainerCounter();
        this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, 7, tileentitybeacon.getInventoryName(), tileentitybeacon.getSize(), tileentitybeacon.k_()));
        this.activeContainer = new ContainerBeacon(this.inventory, tileentitybeacon);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void openTrade(IMerchant imerchant, String s) {
        this.nextContainerCounter();
        this.activeContainer = new ContainerMerchant(this.inventory, imerchant, this.world);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
        InventoryMerchant inventorymerchant = ((ContainerMerchant) this.activeContainer).getMerchantInventory();

        this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, 6, s == null ? "" : s, inventorymerchant.getSize(), s != null));
        MerchantRecipeList merchantrecipelist = imerchant.getOffers(this);

        if (merchantrecipelist != null) {
            try {
                PacketDataSerializer packetdataserializer = new PacketDataSerializer(Unpooled.buffer());

                packetdataserializer.writeInt(this.containerCounter);
                merchantrecipelist.a(packetdataserializer);
                this.playerConnection.sendPacket(new PacketPlayOutCustomPayload("MC|TrList", packetdataserializer));
            } catch (IOException ioexception) {
                bM.error("Couldn\'t send trade list", ioexception);
            }
        }
    }

    public void openHorseInventory(EntityHorse entityhorse, IInventory iinventory) {
        if (this.activeContainer != this.defaultContainer) {
            this.closeInventory();
        }

        this.nextContainerCounter();
        this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, 11, iinventory.getInventoryName(), iinventory.getSize(), iinventory.k_(), entityhorse.getId()));
        this.activeContainer = new ContainerHorse(this.inventory, iinventory, entityhorse);
        this.activeContainer.windowId = this.containerCounter;
        this.activeContainer.addSlotListener(this);
    }

    public void a(Container container, int i, ItemStack itemstack) {
        if (!(container.getSlot(i) instanceof SlotResult)) {
            if (!this.h) {
                this.playerConnection.sendPacket(new PacketPlayOutSetSlot(container.windowId, i, itemstack));
            }
        }
    }

    public void updateInventory(Container container) {
        this.a(container, container.a());
    }

    public void a(Container container, List list) {
        this.playerConnection.sendPacket(new PacketPlayOutWindowItems(container.windowId, list));
        this.playerConnection.sendPacket(new PacketPlayOutSetSlot(-1, -1, this.inventory.getCarried()));
    }

    public void setContainerData(Container container, int i, int j) {
        this.playerConnection.sendPacket(new PacketPlayOutCraftProgressBar(container.windowId, i, j));
    }

    public void closeInventory() {
        this.playerConnection.sendPacket(new PacketPlayOutCloseWindow(this.activeContainer.windowId));
        this.m();
    }

    public void broadcastCarriedItem() {
        if (!this.h) {
            this.playerConnection.sendPacket(new PacketPlayOutSetSlot(-1, -1, this.inventory.getCarried()));
        }
    }

    public void m() {
        this.activeContainer.b((EntityHuman) this);
        this.activeContainer = this.defaultContainer;
    }

    public void a(float f, float f1, boolean flag, boolean flag1) {
        if (this.vehicle != null) {
            if (f >= -1.0F && f <= 1.0F) {
                this.be = f;
            }

            if (f1 >= -1.0F && f1 <= 1.0F) {
                this.bf = f1;
            }

            this.bd = flag;
            this.setSneaking(flag1);
        }
    }

    public void a(Statistic statistic, int i) {
        if (statistic != null) {
            this.bO.b(this, statistic, i);
            Iterator iterator = this.getScoreboard().getObjectivesForCriteria(statistic.k()).iterator();

            while (iterator.hasNext()) {
                ScoreboardObjective scoreboardobjective = (ScoreboardObjective) iterator.next();

                this.getScoreboard().getPlayerScoreForObjective(this.getName(), scoreboardobjective).incrementScore();
            }

            if (this.bO.e()) {
                this.bO.a(this);
            }
        }
    }

    public void n() {
        if (this.passenger != null) {
            this.passenger.mount(this);
        }

        if (this.sleeping) {
            this.a(true, false, false);
        }
    }

    public void triggerHealthUpdate() {
        this.bQ = -1.0E8F;
    }

    public void b(IChatBaseComponent ichatbasecomponent) {
        this.playerConnection.sendPacket(new PacketPlayOutChat(ichatbasecomponent));
    }

    protected void p() {
        this.playerConnection.sendPacket(new PacketPlayOutEntityStatus(this, (byte) 9));
        super.p();
    }

    public void a(ItemStack itemstack, int i) {
        super.a(itemstack, i);
        if (itemstack != null && itemstack.getItem() != null && itemstack.getItem().d(itemstack) == EnumAnimation.EAT) {
            this.r().getTracker().sendPacketToEntity(this, new PacketPlayOutAnimation(this, 3));
        }
    }

    public void copyTo(EntityHuman entityhuman, boolean flag) {
        super.copyTo(entityhuman, flag);
        this.lastSentExp = -1;
        this.bQ = -1.0F;
        this.bR = -1;
        this.removeQueue.addAll(((EntityPlayer) entityhuman).removeQueue);
    }

    protected void a(MobEffect mobeffect) {
        super.a(mobeffect);
        this.playerConnection.sendPacket(new PacketPlayOutEntityEffect(this.getId(), mobeffect));
    }

    protected void a(MobEffect mobeffect, boolean flag) {
        super.a(mobeffect, flag);
        this.playerConnection.sendPacket(new PacketPlayOutEntityEffect(this.getId(), mobeffect));
    }

    protected void b(MobEffect mobeffect) {
        super.b(mobeffect);
        this.playerConnection.sendPacket(new PacketPlayOutRemoveEntityEffect(this.getId(), mobeffect));
    }

    public void enderTeleportTo(double d0, double d1, double d2) {
        this.playerConnection.a(d0, d1, d2, this.yaw, this.pitch);
    }

    public void b(Entity entity) {
        this.r().getTracker().sendPacketToEntity(this, new PacketPlayOutAnimation(entity, 4));
    }

    public void c(Entity entity) {
        this.r().getTracker().sendPacketToEntity(this, new PacketPlayOutAnimation(entity, 5));
    }

    public void updateAbilities() {
        if (this.playerConnection != null) {
            this.playerConnection.sendPacket(new PacketPlayOutAbilities(this.abilities));
        }
    }

    public WorldServer r() {
        return (WorldServer) this.world;
    }

    public void a(EnumGamemode enumgamemode) {
        this.playerInteractManager.setGameMode(enumgamemode);
        this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(3, (float) enumgamemode.a()));
    }

    public void sendMessage(IChatBaseComponent ichatbasecomponent) {
        this.playerConnection.sendPacket(new PacketPlayOutChat(ichatbasecomponent));
    }

    public boolean a(int i, String s) {
        return "seed".equals(s) && !this.server.V() ? true : (!"tell".equals(s) && !"help".equals(s) && !"me".equals(s) ? (this.server.getPlayerList().isOp(this.getName()) ? this.server.l() >= i : false) : true);
    }

    public String s() {
        String s = this.playerConnection.networkManager.getSocketAddress().toString();

        s = s.substring(s.indexOf("/") + 1);
        s = s.substring(0, s.indexOf(":"));
        return s;
    }

    public void a(PacketPlayInSettings packetplayinsettings) {
        this.locale = packetplayinsettings.c();
        int i = 256 >> packetplayinsettings.d();

        if (i > 3 && i < 15) {
            this.bV = i;
        }

        this.bW = packetplayinsettings.e();
        this.bX = packetplayinsettings.f();
        if (this.server.L() && this.server.K().equals(this.getName())) {
            this.server.a(packetplayinsettings.g());
        }

        this.b(1, !packetplayinsettings.h());
    }

    public EnumChatVisibility getChatFlags() {
        return this.bW;
    }

    public void a(String s) {
        this.playerConnection.sendPacket(new PacketPlayOutCustomPayload("MC|RPack", s.getBytes(Charsets.UTF_8)));
    }

    public ChunkCoordinates getChunkCoordinates() {
        return new ChunkCoordinates(MathHelper.floor(this.locX), MathHelper.floor(this.locY + 0.5D), MathHelper.floor(this.locZ));
    }

    public void w() {
        this.bY = MinecraftServer.ap();
    }

    public ServerStatisticManager x() {
        return this.bO;
    }
}
