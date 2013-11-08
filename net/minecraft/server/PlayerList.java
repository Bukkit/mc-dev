package net.minecraft.server;

import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.minecraft.util.com.google.common.base.Charsets;
import net.minecraft.util.com.google.common.collect.Maps;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class PlayerList {

    private static final Logger d = LogManager.getLogger();
    private static final SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    private final MinecraftServer server;
    public final List players = new ArrayList();
    private final BanList banByName = new BanList(new File("banned-players.txt"));
    private final BanList banByIP = new BanList(new File("banned-ips.txt"));
    private final Set operators = new HashSet();
    private final Set whitelist = new HashSet();
    private final Map k = Maps.newHashMap();
    private IPlayerFileData playerFileData;
    private boolean hasWhitelist;
    protected int maxPlayers;
    protected int c;
    private EnumGamemode n;
    private boolean o;
    private int p;

    public PlayerList(MinecraftServer minecraftserver) {
        this.server = minecraftserver;
        this.banByName.setEnabled(false);
        this.banByIP.setEnabled(false);
        this.maxPlayers = 8;
    }

    public void a(NetworkManager networkmanager, EntityPlayer entityplayer) {
        NBTTagCompound nbttagcompound = this.a(entityplayer);

        entityplayer.spawnIn(this.server.getWorldServer(entityplayer.dimension));
        entityplayer.playerInteractManager.a((WorldServer) entityplayer.world);
        String s = "local";

        if (networkmanager.getSocketAddress() != null) {
            s = networkmanager.getSocketAddress().toString();
        }

        d.info(entityplayer.getName() + "[" + s + "] logged in with entity id " + entityplayer.getId() + " at (" + entityplayer.locX + ", " + entityplayer.locY + ", " + entityplayer.locZ + ")");
        WorldServer worldserver = this.server.getWorldServer(entityplayer.dimension);
        ChunkCoordinates chunkcoordinates = worldserver.getSpawn();

        this.a(entityplayer, (EntityPlayer) null, worldserver);
        PlayerConnection playerconnection = new PlayerConnection(this.server, networkmanager, entityplayer);

        playerconnection.sendPacket(new PacketPlayOutLogin(entityplayer.getId(), entityplayer.playerInteractManager.getGameMode(), worldserver.getWorldData().isHardcore(), worldserver.worldProvider.dimension, worldserver.difficulty, this.getMaxPlayers(), worldserver.getWorldData().getType()));
        playerconnection.sendPacket(new PacketPlayOutCustomPayload("MC|Brand", this.getServer().getServerModName().getBytes(Charsets.UTF_8)));
        playerconnection.sendPacket(new PacketPlayOutSpawnPosition(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z));
        playerconnection.sendPacket(new PacketPlayOutAbilities(entityplayer.abilities));
        playerconnection.sendPacket(new PacketPlayOutHeldItemSlot(entityplayer.inventory.itemInHandIndex));
        entityplayer.x().d();
        entityplayer.x().b(entityplayer);
        this.a((ScoreboardServer) worldserver.getScoreboard(), entityplayer);
        this.server.au();
        ChatMessage chatmessage = new ChatMessage("multiplayer.player.joined", new Object[] { entityplayer.getScoreboardDisplayName()});

        chatmessage.b().setColor(EnumChatFormat.YELLOW);
        this.sendMessage(chatmessage);
        this.c(entityplayer);
        playerconnection.a(entityplayer.locX, entityplayer.locY, entityplayer.locZ, entityplayer.yaw, entityplayer.pitch);
        this.b(entityplayer, worldserver);
        if (this.server.getResourcePack().length() > 0) {
            entityplayer.a(this.server.getResourcePack());
        }

        Iterator iterator = entityplayer.getEffects().iterator();

        while (iterator.hasNext()) {
            MobEffect mobeffect = (MobEffect) iterator.next();

            playerconnection.sendPacket(new PacketPlayOutEntityEffect(entityplayer.getId(), mobeffect));
        }

        entityplayer.syncInventory();
        if (nbttagcompound != null && nbttagcompound.hasKeyOfType("Riding", 10)) {
            Entity entity = EntityTypes.a(nbttagcompound.getCompound("Riding"), worldserver);

            if (entity != null) {
                entity.o = true;
                worldserver.addEntity(entity);
                entityplayer.mount(entity);
                entity.o = false;
            }
        }
    }

    protected void a(ScoreboardServer scoreboardserver, EntityPlayer entityplayer) {
        HashSet hashset = new HashSet();
        Iterator iterator = scoreboardserver.getTeams().iterator();

        while (iterator.hasNext()) {
            ScoreboardTeam scoreboardteam = (ScoreboardTeam) iterator.next();

            entityplayer.playerConnection.sendPacket(new PacketPlayOutScoreboardTeam(scoreboardteam, 0));
        }

        for (int i = 0; i < 3; ++i) {
            ScoreboardObjective scoreboardobjective = scoreboardserver.getObjectiveForSlot(i);

            if (scoreboardobjective != null && !hashset.contains(scoreboardobjective)) {
                List list = scoreboardserver.getScoreboardScorePacketsForObjective(scoreboardobjective);
                Iterator iterator1 = list.iterator();

                while (iterator1.hasNext()) {
                    Packet packet = (Packet) iterator1.next();

                    entityplayer.playerConnection.sendPacket(packet);
                }

                hashset.add(scoreboardobjective);
            }
        }
    }

    public void setPlayerFileData(WorldServer[] aworldserver) {
        this.playerFileData = aworldserver[0].getDataManager().getPlayerFileData();
    }

    public void a(EntityPlayer entityplayer, WorldServer worldserver) {
        WorldServer worldserver1 = entityplayer.r();

        if (worldserver != null) {
            worldserver.getPlayerChunkMap().removePlayer(entityplayer);
        }

        worldserver1.getPlayerChunkMap().addPlayer(entityplayer);
        worldserver1.chunkProviderServer.getChunkAt((int) entityplayer.locX >> 4, (int) entityplayer.locZ >> 4);
    }

    public int a() {
        return PlayerChunkMap.getFurthestViewableBlock(this.o());
    }

    public NBTTagCompound a(EntityPlayer entityplayer) {
        NBTTagCompound nbttagcompound = this.server.worldServer[0].getWorldData().i();
        NBTTagCompound nbttagcompound1;

        if (entityplayer.getName().equals(this.server.K()) && nbttagcompound != null) {
            entityplayer.f(nbttagcompound);
            nbttagcompound1 = nbttagcompound;
            d.debug("loading single player");
        } else {
            nbttagcompound1 = this.playerFileData.load(entityplayer);
        }

        return nbttagcompound1;
    }

    protected void b(EntityPlayer entityplayer) {
        this.playerFileData.save(entityplayer);
        ServerStatisticManager serverstatisticmanager = (ServerStatisticManager) this.k.get(entityplayer.getName());

        if (serverstatisticmanager != null) {
            serverstatisticmanager.b();
        }
    }

    public void c(EntityPlayer entityplayer) {
        this.sendAll(new PacketPlayOutPlayerInfo(entityplayer.getName(), true, 1000));
        this.players.add(entityplayer);
        WorldServer worldserver = this.server.getWorldServer(entityplayer.dimension);

        worldserver.addEntity(entityplayer);
        this.a(entityplayer, (WorldServer) null);

        for (int i = 0; i < this.players.size(); ++i) {
            EntityPlayer entityplayer1 = (EntityPlayer) this.players.get(i);

            entityplayer.playerConnection.sendPacket(new PacketPlayOutPlayerInfo(entityplayer1.getName(), true, entityplayer1.ping));
        }
    }

    public void d(EntityPlayer entityplayer) {
        entityplayer.r().getPlayerChunkMap().movePlayer(entityplayer);
    }

    public void disconnect(EntityPlayer entityplayer) {
        entityplayer.a(StatisticList.f);
        this.b(entityplayer);
        WorldServer worldserver = entityplayer.r();

        if (entityplayer.vehicle != null) {
            worldserver.removeEntity(entityplayer.vehicle);
            d.debug("removing player mount");
        }

        worldserver.kill(entityplayer);
        worldserver.getPlayerChunkMap().removePlayer(entityplayer);
        this.players.remove(entityplayer);
        this.k.remove(entityplayer.getName());
        this.sendAll(new PacketPlayOutPlayerInfo(entityplayer.getName(), false, 9999));
    }

    public String attemptLogin(SocketAddress socketaddress, GameProfile gameprofile) {
        if (this.banByName.isBanned(gameprofile.getName())) {
            BanEntry banentry = (BanEntry) this.banByName.getEntries().get(gameprofile.getName());
            String s = "You are banned from this server!\nReason: " + banentry.getReason();

            if (banentry.getExpires() != null) {
                s = s + "\nYour ban will be removed on " + e.format(banentry.getExpires());
            }

            return s;
        } else if (!this.isWhitelisted(gameprofile.getName())) {
            return "You are not white-listed on this server!";
        } else {
            String s1 = socketaddress.toString();

            s1 = s1.substring(s1.indexOf("/") + 1);
            s1 = s1.substring(0, s1.indexOf(":"));
            if (this.banByIP.isBanned(s1)) {
                BanEntry banentry1 = (BanEntry) this.banByIP.getEntries().get(s1);
                String s2 = "Your IP address is banned from this server!\nReason: " + banentry1.getReason();

                if (banentry1.getExpires() != null) {
                    s2 = s2 + "\nYour ban will be removed on " + e.format(banentry1.getExpires());
                }

                return s2;
            } else {
                return this.players.size() >= this.maxPlayers ? "The server is full!" : null;
            }
        }
    }

    public EntityPlayer processLogin(GameProfile gameprofile) {
        ArrayList arraylist = new ArrayList();

        EntityPlayer entityplayer;

        for (int i = 0; i < this.players.size(); ++i) {
            entityplayer = (EntityPlayer) this.players.get(i);
            if (entityplayer.getName().equalsIgnoreCase(gameprofile.getName())) {
                arraylist.add(entityplayer);
            }
        }

        Iterator iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            entityplayer = (EntityPlayer) iterator.next();
            entityplayer.playerConnection.disconnect("You logged in from another location");
        }

        Object object;

        if (this.server.P()) {
            object = new DemoPlayerInteractManager(this.server.getWorldServer(0));
        } else {
            object = new PlayerInteractManager(this.server.getWorldServer(0));
        }

        return new EntityPlayer(this.server, this.server.getWorldServer(0), gameprofile, (PlayerInteractManager) object);
    }

    public EntityPlayer moveToWorld(EntityPlayer entityplayer, int i, boolean flag) {
        entityplayer.r().getTracker().untrackPlayer(entityplayer);
        entityplayer.r().getTracker().untrackEntity(entityplayer);
        entityplayer.r().getPlayerChunkMap().removePlayer(entityplayer);
        this.players.remove(entityplayer);
        this.server.getWorldServer(entityplayer.dimension).removeEntity(entityplayer);
        ChunkCoordinates chunkcoordinates = entityplayer.getBed();
        boolean flag1 = entityplayer.isRespawnForced();

        entityplayer.dimension = i;
        Object object;

        if (this.server.P()) {
            object = new DemoPlayerInteractManager(this.server.getWorldServer(entityplayer.dimension));
        } else {
            object = new PlayerInteractManager(this.server.getWorldServer(entityplayer.dimension));
        }

        EntityPlayer entityplayer1 = new EntityPlayer(this.server, this.server.getWorldServer(entityplayer.dimension), entityplayer.getProfile(), (PlayerInteractManager) object);

        entityplayer1.playerConnection = entityplayer.playerConnection;
        entityplayer1.copyTo(entityplayer, flag);
        entityplayer1.d(entityplayer.getId());
        WorldServer worldserver = this.server.getWorldServer(entityplayer.dimension);

        this.a(entityplayer1, entityplayer, worldserver);
        ChunkCoordinates chunkcoordinates1;

        if (chunkcoordinates != null) {
            chunkcoordinates1 = EntityHuman.getBed(this.server.getWorldServer(entityplayer.dimension), chunkcoordinates, flag1);
            if (chunkcoordinates1 != null) {
                entityplayer1.setPositionRotation((double) ((float) chunkcoordinates1.x + 0.5F), (double) ((float) chunkcoordinates1.y + 0.1F), (double) ((float) chunkcoordinates1.z + 0.5F), 0.0F, 0.0F);
                entityplayer1.setRespawnPosition(chunkcoordinates, flag1);
            } else {
                entityplayer1.playerConnection.sendPacket(new PacketPlayOutGameStateChange(0, 0.0F));
            }
        }

        worldserver.chunkProviderServer.getChunkAt((int) entityplayer1.locX >> 4, (int) entityplayer1.locZ >> 4);

        while (!worldserver.getCubes(entityplayer1, entityplayer1.boundingBox).isEmpty()) {
            entityplayer1.setPosition(entityplayer1.locX, entityplayer1.locY + 1.0D, entityplayer1.locZ);
        }

        entityplayer1.playerConnection.sendPacket(new PacketPlayOutRespawn(entityplayer1.dimension, entityplayer1.world.difficulty, entityplayer1.world.getWorldData().getType(), entityplayer1.playerInteractManager.getGameMode()));
        chunkcoordinates1 = worldserver.getSpawn();
        entityplayer1.playerConnection.a(entityplayer1.locX, entityplayer1.locY, entityplayer1.locZ, entityplayer1.yaw, entityplayer1.pitch);
        entityplayer1.playerConnection.sendPacket(new PacketPlayOutSpawnPosition(chunkcoordinates1.x, chunkcoordinates1.y, chunkcoordinates1.z));
        entityplayer1.playerConnection.sendPacket(new PacketPlayOutExperience(entityplayer1.exp, entityplayer1.expTotal, entityplayer1.expLevel));
        this.b(entityplayer1, worldserver);
        worldserver.getPlayerChunkMap().addPlayer(entityplayer1);
        worldserver.addEntity(entityplayer1);
        this.players.add(entityplayer1);
        entityplayer1.syncInventory();
        entityplayer1.setHealth(entityplayer1.getHealth());
        return entityplayer1;
    }

    public void changeDimension(EntityPlayer entityplayer, int i) {
        int j = entityplayer.dimension;
        WorldServer worldserver = this.server.getWorldServer(entityplayer.dimension);

        entityplayer.dimension = i;
        WorldServer worldserver1 = this.server.getWorldServer(entityplayer.dimension);

        entityplayer.playerConnection.sendPacket(new PacketPlayOutRespawn(entityplayer.dimension, entityplayer.world.difficulty, entityplayer.world.getWorldData().getType(), entityplayer.playerInteractManager.getGameMode()));
        worldserver.removeEntity(entityplayer);
        entityplayer.dead = false;
        this.a(entityplayer, j, worldserver, worldserver1);
        this.a(entityplayer, worldserver);
        entityplayer.playerConnection.a(entityplayer.locX, entityplayer.locY, entityplayer.locZ, entityplayer.yaw, entityplayer.pitch);
        entityplayer.playerInteractManager.a(worldserver1);
        this.b(entityplayer, worldserver1);
        this.updateClient(entityplayer);
        Iterator iterator = entityplayer.getEffects().iterator();

        while (iterator.hasNext()) {
            MobEffect mobeffect = (MobEffect) iterator.next();

            entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityEffect(entityplayer.getId(), mobeffect));
        }
    }

    public void a(Entity entity, int i, WorldServer worldserver, WorldServer worldserver1) {
        double d0 = entity.locX;
        double d1 = entity.locZ;
        double d2 = 8.0D;
        double d3 = entity.locX;
        double d4 = entity.locY;
        double d5 = entity.locZ;
        float f = entity.yaw;

        worldserver.methodProfiler.a("moving");
        if (entity.dimension == -1) {
            d0 /= d2;
            d1 /= d2;
            entity.setPositionRotation(d0, entity.locY, d1, entity.yaw, entity.pitch);
            if (entity.isAlive()) {
                worldserver.entityJoinedWorld(entity, false);
            }
        } else if (entity.dimension == 0) {
            d0 *= d2;
            d1 *= d2;
            entity.setPositionRotation(d0, entity.locY, d1, entity.yaw, entity.pitch);
            if (entity.isAlive()) {
                worldserver.entityJoinedWorld(entity, false);
            }
        } else {
            ChunkCoordinates chunkcoordinates;

            if (i == 1) {
                chunkcoordinates = worldserver1.getSpawn();
            } else {
                chunkcoordinates = worldserver1.getDimensionSpawn();
            }

            d0 = (double) chunkcoordinates.x;
            entity.locY = (double) chunkcoordinates.y;
            d1 = (double) chunkcoordinates.z;
            entity.setPositionRotation(d0, entity.locY, d1, 90.0F, 0.0F);
            if (entity.isAlive()) {
                worldserver.entityJoinedWorld(entity, false);
            }
        }

        worldserver.methodProfiler.b();
        if (i != 1) {
            worldserver.methodProfiler.a("placing");
            d0 = (double) MathHelper.a((int) d0, -29999872, 29999872);
            d1 = (double) MathHelper.a((int) d1, -29999872, 29999872);
            if (entity.isAlive()) {
                entity.setPositionRotation(d0, entity.locY, d1, entity.yaw, entity.pitch);
                worldserver1.t().a(entity, d3, d4, d5, f);
                worldserver1.addEntity(entity);
                worldserver1.entityJoinedWorld(entity, false);
            }

            worldserver.methodProfiler.b();
        }

        entity.spawnIn(worldserver1);
    }

    public void tick() {
        if (++this.p > 600) {
            this.p = 0;
        }

        if (this.p < this.players.size()) {
            EntityPlayer entityplayer = (EntityPlayer) this.players.get(this.p);

            this.sendAll(new PacketPlayOutPlayerInfo(entityplayer.getName(), true, entityplayer.ping));
        }
    }

    public void sendAll(Packet packet) {
        for (int i = 0; i < this.players.size(); ++i) {
            ((EntityPlayer) this.players.get(i)).playerConnection.sendPacket(packet);
        }
    }

    public void a(Packet packet, int i) {
        for (int j = 0; j < this.players.size(); ++j) {
            EntityPlayer entityplayer = (EntityPlayer) this.players.get(j);

            if (entityplayer.dimension == i) {
                entityplayer.playerConnection.sendPacket(packet);
            }
        }
    }

    public String c() {
        String s = "";

        for (int i = 0; i < this.players.size(); ++i) {
            if (i > 0) {
                s = s + ", ";
            }

            s = s + ((EntityPlayer) this.players.get(i)).getName();
        }

        return s;
    }

    public String[] d() {
        String[] astring = new String[this.players.size()];

        for (int i = 0; i < this.players.size(); ++i) {
            astring[i] = ((EntityPlayer) this.players.get(i)).getName();
        }

        return astring;
    }

    public BanList getNameBans() {
        return this.banByName;
    }

    public BanList getIPBans() {
        return this.banByIP;
    }

    public void addOp(String s) {
        this.operators.add(s.toLowerCase());
    }

    public void removeOp(String s) {
        this.operators.remove(s.toLowerCase());
    }

    public boolean isWhitelisted(String s) {
        s = s.trim().toLowerCase();
        return !this.hasWhitelist || this.operators.contains(s) || this.whitelist.contains(s);
    }

    public boolean isOp(String s) {
        return this.operators.contains(s.trim().toLowerCase()) || this.server.L() && this.server.worldServer[0].getWorldData().allowCommands() && this.server.K().equalsIgnoreCase(s) || this.o;
    }

    public EntityPlayer getPlayer(String s) {
        Iterator iterator = this.players.iterator();

        EntityPlayer entityplayer;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            entityplayer = (EntityPlayer) iterator.next();
        } while (!entityplayer.getName().equalsIgnoreCase(s));

        return entityplayer;
    }

    public List a(ChunkCoordinates chunkcoordinates, int i, int j, int k, int l, int i1, int j1, Map map, String s, String s1, World world) {
        if (this.players.isEmpty()) {
            return null;
        } else {
            Object object = new ArrayList();
            boolean flag = k < 0;
            boolean flag1 = s != null && s.startsWith("!");
            boolean flag2 = s1 != null && s1.startsWith("!");
            int k1 = i * i;
            int l1 = j * j;

            k = MathHelper.a(k);
            if (flag1) {
                s = s.substring(1);
            }

            if (flag2) {
                s1 = s1.substring(1);
            }

            for (int i2 = 0; i2 < this.players.size(); ++i2) {
                EntityPlayer entityplayer = (EntityPlayer) this.players.get(i2);

                if ((world == null || entityplayer.world == world) && (s == null || flag1 != s.equalsIgnoreCase(entityplayer.getName()))) {
                    if (s1 != null) {
                        ScoreboardTeamBase scoreboardteambase = entityplayer.getScoreboardTeam();
                        String s2 = scoreboardteambase == null ? "" : scoreboardteambase.getName();

                        if (flag2 == s1.equalsIgnoreCase(s2)) {
                            continue;
                        }
                    }

                    if (chunkcoordinates != null && (i > 0 || j > 0)) {
                        float f = chunkcoordinates.e(entityplayer.getChunkCoordinates());

                        if (i > 0 && f < (float) k1 || j > 0 && f > (float) l1) {
                            continue;
                        }
                    }

                    if (this.a((EntityHuman) entityplayer, map) && (l == EnumGamemode.NONE.a() || l == entityplayer.playerInteractManager.getGameMode().a()) && (i1 <= 0 || entityplayer.expLevel >= i1) && entityplayer.expLevel <= j1) {
                        ((List) object).add(entityplayer);
                    }
                }
            }

            if (chunkcoordinates != null) {
                Collections.sort((List) object, new PlayerDistanceComparator(chunkcoordinates));
            }

            if (flag) {
                Collections.reverse((List) object);
            }

            if (k > 0) {
                object = ((List) object).subList(0, Math.min(k, ((List) object).size()));
            }

            return (List) object;
        }
    }

    private boolean a(EntityHuman entityhuman, Map map) {
        if (map != null && map.size() != 0) {
            Iterator iterator = map.entrySet().iterator();

            Entry entry;
            boolean flag;
            int i;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                entry = (Entry) iterator.next();
                String s = (String) entry.getKey();

                flag = false;
                if (s.endsWith("_min") && s.length() > 4) {
                    flag = true;
                    s = s.substring(0, s.length() - 4);
                }

                Scoreboard scoreboard = entityhuman.getScoreboard();
                ScoreboardObjective scoreboardobjective = scoreboard.getObjective(s);

                if (scoreboardobjective == null) {
                    return false;
                }

                ScoreboardScore scoreboardscore = entityhuman.getScoreboard().getPlayerScoreForObjective(entityhuman.getName(), scoreboardobjective);

                i = scoreboardscore.getScore();
                if (i < ((Integer) entry.getValue()).intValue() && flag) {
                    return false;
                }
            } while (i <= ((Integer) entry.getValue()).intValue() || flag);

            return false;
        } else {
            return true;
        }
    }

    public void sendPacketNearby(double d0, double d1, double d2, double d3, int i, Packet packet) {
        this.sendPacketNearby((EntityHuman) null, d0, d1, d2, d3, i, packet);
    }

    public void sendPacketNearby(EntityHuman entityhuman, double d0, double d1, double d2, double d3, int i, Packet packet) {
        for (int j = 0; j < this.players.size(); ++j) {
            EntityPlayer entityplayer = (EntityPlayer) this.players.get(j);

            if (entityplayer != entityhuman && entityplayer.dimension == i) {
                double d4 = d0 - entityplayer.locX;
                double d5 = d1 - entityplayer.locY;
                double d6 = d2 - entityplayer.locZ;

                if (d4 * d4 + d5 * d5 + d6 * d6 < d3 * d3) {
                    entityplayer.playerConnection.sendPacket(packet);
                }
            }
        }
    }

    public void savePlayers() {
        for (int i = 0; i < this.players.size(); ++i) {
            this.b((EntityPlayer) this.players.get(i));
        }
    }

    public void addWhitelist(String s) {
        this.whitelist.add(s);
    }

    public void removeWhitelist(String s) {
        this.whitelist.remove(s);
    }

    public Set getWhitelisted() {
        return this.whitelist;
    }

    public Set getOPs() {
        return this.operators;
    }

    public void reloadWhitelist() {}

    public void b(EntityPlayer entityplayer, WorldServer worldserver) {
        entityplayer.playerConnection.sendPacket(new PacketPlayOutUpdateTime(worldserver.getTime(), worldserver.getDayTime(), worldserver.getGameRules().getBoolean("doDaylightCycle")));
        if (worldserver.P()) {
            entityplayer.playerConnection.sendPacket(new PacketPlayOutGameStateChange(1, 0.0F));
            entityplayer.playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, worldserver.j(1.0F)));
            entityplayer.playerConnection.sendPacket(new PacketPlayOutGameStateChange(8, worldserver.h(1.0F)));
        }
    }

    public void updateClient(EntityPlayer entityplayer) {
        entityplayer.updateInventory(entityplayer.defaultContainer);
        entityplayer.triggerHealthUpdate();
        entityplayer.playerConnection.sendPacket(new PacketPlayOutHeldItemSlot(entityplayer.inventory.itemInHandIndex));
    }

    public int getPlayerCount() {
        return this.players.size();
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public String[] getSeenPlayers() {
        return this.server.worldServer[0].getDataManager().getPlayerFileData().getSeenPlayers();
    }

    public boolean getHasWhitelist() {
        return this.hasWhitelist;
    }

    public void setHasWhitelist(boolean flag) {
        this.hasWhitelist = flag;
    }

    public List h(String s) {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.players.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            if (entityplayer.s().equals(s)) {
                arraylist.add(entityplayer);
            }
        }

        return arraylist;
    }

    public int o() {
        return this.c;
    }

    public MinecraftServer getServer() {
        return this.server;
    }

    public NBTTagCompound q() {
        return null;
    }

    private void a(EntityPlayer entityplayer, EntityPlayer entityplayer1, World world) {
        if (entityplayer1 != null) {
            entityplayer.playerInteractManager.setGameMode(entityplayer1.playerInteractManager.getGameMode());
        } else if (this.n != null) {
            entityplayer.playerInteractManager.setGameMode(this.n);
        }

        entityplayer.playerInteractManager.b(world.getWorldData().getGameType());
    }

    public void r() {
        for (int i = 0; i < this.players.size(); ++i) {
            ((EntityPlayer) this.players.get(i)).playerConnection.disconnect("Server closed");
        }
    }

    public void sendMessage(IChatBaseComponent ichatbasecomponent, boolean flag) {
        this.server.sendMessage(ichatbasecomponent);
        this.sendAll(new PacketPlayOutChat(ichatbasecomponent, flag));
    }

    public void sendMessage(IChatBaseComponent ichatbasecomponent) {
        this.sendMessage(ichatbasecomponent, true);
    }

    public ServerStatisticManager i(String s) {
        ServerStatisticManager serverstatisticmanager = (ServerStatisticManager) this.k.get(s);

        if (serverstatisticmanager == null) {
            serverstatisticmanager = new ServerStatisticManager(this.server, new File(this.server.getWorldServer(0).getDataManager().getDirectory(), "stats/" + s + ".json"));
            serverstatisticmanager.a();
            this.k.put(s, serverstatisticmanager);
        }

        return serverstatisticmanager;
    }
}
