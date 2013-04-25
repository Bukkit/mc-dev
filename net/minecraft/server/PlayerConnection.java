package net.minecraft.server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Callable;

public class PlayerConnection extends Connection {

    public final INetworkManager networkManager;
    private final MinecraftServer minecraftServer;
    public boolean disconnected = false;
    public EntityPlayer player;
    private int e;
    private int f;
    private boolean g;
    private int h;
    private long i;
    private static Random j = new Random();
    private long k;
    private int chatThrottle = 0;
    private int x = 0;
    private double y;
    private double z;
    private double p;
    private boolean checkMovement = true;
    private IntHashMap r = new IntHashMap();

    public PlayerConnection(MinecraftServer minecraftserver, INetworkManager inetworkmanager, EntityPlayer entityplayer) {
        this.minecraftServer = minecraftserver;
        this.networkManager = inetworkmanager;
        inetworkmanager.a(this);
        this.player = entityplayer;
        entityplayer.playerConnection = this;
    }

    public void d() {
        this.g = false;
        ++this.e;
        this.minecraftServer.methodProfiler.a("packetflow");
        this.networkManager.b();
        this.minecraftServer.methodProfiler.c("keepAlive");
        if ((long) this.e - this.k > 20L) {
            this.k = (long) this.e;
            this.i = System.nanoTime() / 1000000L;
            this.h = j.nextInt();
            this.sendPacket(new Packet0KeepAlive(this.h));
        }

        if (this.chatThrottle > 0) {
            --this.chatThrottle;
        }

        if (this.x > 0) {
            --this.x;
        }

        this.minecraftServer.methodProfiler.c("playerTick");
        this.minecraftServer.methodProfiler.b();
    }

    public void disconnect(String s) {
        if (!this.disconnected) {
            this.player.k();
            this.sendPacket(new Packet255KickDisconnect(s));
            this.networkManager.d();
            this.minecraftServer.getPlayerList().sendAll(new Packet3Chat(EnumChatFormat.YELLOW + this.player.name + " left the game."));
            this.minecraftServer.getPlayerList().disconnect(this.player);
            this.disconnected = true;
        }
    }

    public void a(Packet10Flying packet10flying) {
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);

        this.g = true;
        if (!this.player.viewingCredits) {
            double d0;

            if (!this.checkMovement) {
                d0 = packet10flying.y - this.z;
                if (packet10flying.x == this.y && d0 * d0 < 0.01D && packet10flying.z == this.p) {
                    this.checkMovement = true;
                }
            }

            if (this.checkMovement) {
                double d1;
                double d2;
                double d3;
                double d4;

                if (this.player.vehicle != null) {
                    float f = this.player.yaw;
                    float f1 = this.player.pitch;

                    this.player.vehicle.U();
                    d1 = this.player.locX;
                    d2 = this.player.locY;
                    d3 = this.player.locZ;
                    double d5 = 0.0D;

                    d4 = 0.0D;
                    if (packet10flying.hasLook) {
                        f = packet10flying.yaw;
                        f1 = packet10flying.pitch;
                    }

                    if (packet10flying.hasPos && packet10flying.y == -999.0D && packet10flying.stance == -999.0D) {
                        if (Math.abs(packet10flying.x) > 1.0D || Math.abs(packet10flying.z) > 1.0D) {
                            System.err.println(this.player.name + " was caught trying to crash the server with an invalid position.");
                            this.disconnect("Nope!");
                            return;
                        }

                        d5 = packet10flying.x;
                        d4 = packet10flying.z;
                    }

                    this.player.onGround = packet10flying.g;
                    this.player.g();
                    this.player.move(d5, 0.0D, d4);
                    this.player.setLocation(d1, d2, d3, f, f1);
                    this.player.motX = d5;
                    this.player.motZ = d4;
                    if (this.player.vehicle != null) {
                        worldserver.vehicleEnteredWorld(this.player.vehicle, true);
                    }

                    if (this.player.vehicle != null) {
                        this.player.vehicle.U();
                    }

                    this.minecraftServer.getPlayerList().d(this.player);
                    this.y = this.player.locX;
                    this.z = this.player.locY;
                    this.p = this.player.locZ;
                    worldserver.playerJoinedWorld(this.player);
                    return;
                }

                if (this.player.isSleeping()) {
                    this.player.g();
                    this.player.setLocation(this.y, this.z, this.p, this.player.yaw, this.player.pitch);
                    worldserver.playerJoinedWorld(this.player);
                    return;
                }

                d0 = this.player.locY;
                this.y = this.player.locX;
                this.z = this.player.locY;
                this.p = this.player.locZ;
                d1 = this.player.locX;
                d2 = this.player.locY;
                d3 = this.player.locZ;
                float f2 = this.player.yaw;
                float f3 = this.player.pitch;

                if (packet10flying.hasPos && packet10flying.y == -999.0D && packet10flying.stance == -999.0D) {
                    packet10flying.hasPos = false;
                }

                if (packet10flying.hasPos) {
                    d1 = packet10flying.x;
                    d2 = packet10flying.y;
                    d3 = packet10flying.z;
                    d4 = packet10flying.stance - packet10flying.y;
                    if (!this.player.isSleeping() && (d4 > 1.65D || d4 < 0.1D)) {
                        this.disconnect("Illegal stance");
                        this.minecraftServer.getLogger().warning(this.player.name + " had an illegal stance: " + d4);
                        return;
                    }

                    if (Math.abs(packet10flying.x) > 3.2E7D || Math.abs(packet10flying.z) > 3.2E7D) {
                        this.disconnect("Illegal position");
                        return;
                    }
                }

                if (packet10flying.hasLook) {
                    f2 = packet10flying.yaw;
                    f3 = packet10flying.pitch;
                }

                this.player.g();
                this.player.X = 0.0F;
                this.player.setLocation(this.y, this.z, this.p, f2, f3);
                if (!this.checkMovement) {
                    return;
                }

                d4 = d1 - this.player.locX;
                double d6 = d2 - this.player.locY;
                double d7 = d3 - this.player.locZ;
                double d8 = Math.min(Math.abs(d4), Math.abs(this.player.motX));
                double d9 = Math.min(Math.abs(d6), Math.abs(this.player.motY));
                double d10 = Math.min(Math.abs(d7), Math.abs(this.player.motZ));
                double d11 = d8 * d8 + d9 * d9 + d10 * d10;

                if (d11 > 100.0D && (!this.minecraftServer.I() || !this.minecraftServer.H().equals(this.player.name))) {
                    this.minecraftServer.getLogger().warning(this.player.name + " moved too quickly! " + d4 + "," + d6 + "," + d7 + " (" + d8 + ", " + d9 + ", " + d10 + ")");
                    this.a(this.y, this.z, this.p, this.player.yaw, this.player.pitch);
                    return;
                }

                float f4 = 0.0625F;
                boolean flag = worldserver.getCubes(this.player, this.player.boundingBox.clone().shrink((double) f4, (double) f4, (double) f4)).isEmpty();

                if (this.player.onGround && !packet10flying.g && d6 > 0.0D) {
                    this.player.j(0.2F);
                }

                this.player.move(d4, d6, d7);
                this.player.onGround = packet10flying.g;
                this.player.checkMovement(d4, d6, d7);
                double d12 = d6;

                d4 = d1 - this.player.locX;
                d6 = d2 - this.player.locY;
                if (d6 > -0.5D || d6 < 0.5D) {
                    d6 = 0.0D;
                }

                d7 = d3 - this.player.locZ;
                d11 = d4 * d4 + d6 * d6 + d7 * d7;
                boolean flag1 = false;

                if (d11 > 0.0625D && !this.player.isSleeping() && !this.player.playerInteractManager.isCreative()) {
                    flag1 = true;
                    this.minecraftServer.getLogger().warning(this.player.name + " moved wrongly!");
                }

                this.player.setLocation(d1, d2, d3, f2, f3);
                boolean flag2 = worldserver.getCubes(this.player, this.player.boundingBox.clone().shrink((double) f4, (double) f4, (double) f4)).isEmpty();

                if (flag && (flag1 || !flag2) && !this.player.isSleeping()) {
                    this.a(this.y, this.z, this.p, f2, f3);
                    return;
                }

                AxisAlignedBB axisalignedbb = this.player.boundingBox.clone().grow((double) f4, (double) f4, (double) f4).a(0.0D, -0.55D, 0.0D);

                if (!this.minecraftServer.getAllowFlight() && !this.player.playerInteractManager.isCreative() && !worldserver.c(axisalignedbb)) {
                    if (d12 >= -0.03125D) {
                        ++this.f;
                        if (this.f > 80) {
                            this.minecraftServer.getLogger().warning(this.player.name + " was kicked for floating too long!");
                            this.disconnect("Flying is not enabled on this server");
                            return;
                        }
                    }
                } else {
                    this.f = 0;
                }

                this.player.onGround = packet10flying.g;
                this.minecraftServer.getPlayerList().d(this.player);
                this.player.b(this.player.locY - d0, packet10flying.g);
            }
        }
    }

    public void a(double d0, double d1, double d2, float f, float f1) {
        this.checkMovement = false;
        this.y = d0;
        this.z = d1;
        this.p = d2;
        this.player.setLocation(d0, d1, d2, f, f1);
        this.player.playerConnection.sendPacket(new Packet13PlayerLookMove(d0, d1 + 1.6200000047683716D, d1, d2, f, f1, false));
    }

    public void a(Packet14BlockDig packet14blockdig) {
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);

        if (packet14blockdig.e == 4) {
            this.player.a(false);
        } else if (packet14blockdig.e == 3) {
            this.player.a(true);
        } else if (packet14blockdig.e == 5) {
            this.player.bZ();
        } else {
            boolean flag = false;

            if (packet14blockdig.e == 0) {
                flag = true;
            }

            if (packet14blockdig.e == 1) {
                flag = true;
            }

            if (packet14blockdig.e == 2) {
                flag = true;
            }

            int i = packet14blockdig.a;
            int j = packet14blockdig.b;
            int k = packet14blockdig.c;

            if (flag) {
                double d0 = this.player.locX - ((double) i + 0.5D);
                double d1 = this.player.locY - ((double) j + 0.5D) + 1.5D;
                double d2 = this.player.locZ - ((double) k + 0.5D);
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (d3 > 36.0D) {
                    return;
                }

                if (j >= this.minecraftServer.getMaxBuildHeight()) {
                    return;
                }
            }

            if (packet14blockdig.e == 0) {
                if (!this.minecraftServer.a(worldserver, i, j, k, this.player)) {
                    this.player.playerInteractManager.dig(i, j, k, packet14blockdig.face);
                } else {
                    this.player.playerConnection.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
                }
            } else if (packet14blockdig.e == 2) {
                this.player.playerInteractManager.a(i, j, k);
                if (worldserver.getTypeId(i, j, k) != 0) {
                    this.player.playerConnection.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
                }
            } else if (packet14blockdig.e == 1) {
                this.player.playerInteractManager.c(i, j, k);
                if (worldserver.getTypeId(i, j, k) != 0) {
                    this.player.playerConnection.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
                }
            }
        }
    }

    public void a(Packet15Place packet15place) {
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
        ItemStack itemstack = this.player.inventory.getItemInHand();
        boolean flag = false;
        int i = packet15place.d();
        int j = packet15place.f();
        int k = packet15place.g();
        int l = packet15place.getFace();

        if (packet15place.getFace() == 255) {
            if (itemstack == null) {
                return;
            }

            this.player.playerInteractManager.useItem(this.player, worldserver, itemstack);
        } else if (packet15place.f() >= this.minecraftServer.getMaxBuildHeight() - 1 && (packet15place.getFace() == 1 || packet15place.f() >= this.minecraftServer.getMaxBuildHeight())) {
            this.player.playerConnection.sendPacket(new Packet3Chat("" + EnumChatFormat.GRAY + "Height limit for building is " + this.minecraftServer.getMaxBuildHeight()));
            flag = true;
        } else {
            if (this.checkMovement && this.player.e((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D) < 64.0D && !this.minecraftServer.a(worldserver, i, j, k, this.player)) {
                this.player.playerInteractManager.interact(this.player, worldserver, itemstack, i, j, k, l, packet15place.j(), packet15place.k(), packet15place.l());
            }

            flag = true;
        }

        if (flag) {
            this.player.playerConnection.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
            if (l == 0) {
                --j;
            }

            if (l == 1) {
                ++j;
            }

            if (l == 2) {
                --k;
            }

            if (l == 3) {
                ++k;
            }

            if (l == 4) {
                --i;
            }

            if (l == 5) {
                ++i;
            }

            this.player.playerConnection.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
        }

        itemstack = this.player.inventory.getItemInHand();
        if (itemstack != null && itemstack.count == 0) {
            this.player.inventory.items[this.player.inventory.itemInHandIndex] = null;
            itemstack = null;
        }

        if (itemstack == null || itemstack.n() == 0) {
            this.player.h = true;
            this.player.inventory.items[this.player.inventory.itemInHandIndex] = ItemStack.b(this.player.inventory.items[this.player.inventory.itemInHandIndex]);
            Slot slot = this.player.activeContainer.a((IInventory) this.player.inventory, this.player.inventory.itemInHandIndex);

            this.player.activeContainer.b();
            this.player.h = false;
            if (!ItemStack.matches(this.player.inventory.getItemInHand(), packet15place.getItemStack())) {
                this.sendPacket(new Packet103SetSlot(this.player.activeContainer.windowId, slot.g, this.player.inventory.getItemInHand()));
            }
        }
    }

    public void a(String s, Object[] aobject) {
        this.minecraftServer.getLogger().info(this.player.name + " lost connection: " + s);
        this.minecraftServer.getPlayerList().sendAll(new Packet3Chat(EnumChatFormat.YELLOW + this.player.getScoreboardDisplayName() + " left the game."));
        this.minecraftServer.getPlayerList().disconnect(this.player);
        this.disconnected = true;
        if (this.minecraftServer.I() && this.player.name.equals(this.minecraftServer.H())) {
            this.minecraftServer.getLogger().info("Stopping singleplayer server as player logged out");
            this.minecraftServer.safeShutdown();
        }
    }

    public void onUnhandledPacket(Packet packet) {
        this.minecraftServer.getLogger().warning(this.getClass() + " wasn\'t prepared to deal with a " + packet.getClass());
        this.disconnect("Protocol error, unexpected packet");
    }

    public void sendPacket(Packet packet) {
        if (packet instanceof Packet3Chat) {
            Packet3Chat packet3chat = (Packet3Chat) packet;
            int i = this.player.getChatFlags();

            if (i == 2) {
                return;
            }

            if (i == 1 && !packet3chat.isServer()) {
                return;
            }
        }

        try {
            this.networkManager.queue(packet);
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Sending packet");
            CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Packet being sent");

            crashreportsystemdetails.a("Packet ID", (Callable) (new CrashReportConnectionPacketID(this, packet)));
            crashreportsystemdetails.a("Packet class", (Callable) (new CrashReportConnectionPacketClass(this, packet)));
            throw new ReportedException(crashreport);
        }
    }

    public void a(Packet16BlockItemSwitch packet16blockitemswitch) {
        if (packet16blockitemswitch.itemInHandIndex >= 0 && packet16blockitemswitch.itemInHandIndex < PlayerInventory.getHotbarSize()) {
            this.player.inventory.itemInHandIndex = packet16blockitemswitch.itemInHandIndex;
        } else {
            this.minecraftServer.getLogger().warning(this.player.name + " tried to set an invalid carried item");
        }
    }

    public void a(Packet3Chat packet3chat) {
        if (this.player.getChatFlags() == 2) {
            this.sendPacket(new Packet3Chat("Cannot send chat message."));
        } else {
            String s = packet3chat.message;

            if (s.length() > 100) {
                this.disconnect("Chat message too long");
            } else {
                s = s.trim();

                for (int i = 0; i < s.length(); ++i) {
                    if (!SharedConstants.isAllowedChatCharacter(s.charAt(i))) {
                        this.disconnect("Illegal characters in chat");
                        return;
                    }
                }

                if (s.startsWith("/")) {
                    this.handleCommand(s);
                } else {
                    if (this.player.getChatFlags() == 1) {
                        this.sendPacket(new Packet3Chat("Cannot send chat message."));
                        return;
                    }

                    s = "<" + this.player.getScoreboardDisplayName() + "> " + s;
                    this.minecraftServer.getLogger().info(s);
                    this.minecraftServer.getPlayerList().sendAll(new Packet3Chat(s, false));
                }

                this.chatThrottle += 20;
                if (this.chatThrottle > 200 && !this.minecraftServer.getPlayerList().isOp(this.player.name)) {
                    this.disconnect("disconnect.spam");
                }
            }
        }
    }

    private void handleCommand(String s) {
        this.minecraftServer.getCommandHandler().a(this.player, s);
    }

    public void a(Packet18ArmAnimation packet18armanimation) {
        if (packet18armanimation.b == 1) {
            this.player.bK();
        }
    }

    public void a(Packet19EntityAction packet19entityaction) {
        if (packet19entityaction.animation == 1) {
            this.player.setSneaking(true);
        } else if (packet19entityaction.animation == 2) {
            this.player.setSneaking(false);
        } else if (packet19entityaction.animation == 4) {
            this.player.setSprinting(true);
        } else if (packet19entityaction.animation == 5) {
            this.player.setSprinting(false);
        } else if (packet19entityaction.animation == 3) {
            this.player.a(false, true, true);
            this.checkMovement = false;
        }
    }

    public void a(Packet255KickDisconnect packet255kickdisconnect) {
        this.networkManager.a("disconnect.quitting", new Object[0]);
    }

    public int lowPriorityCount() {
        return this.networkManager.e();
    }

    public void a(Packet7UseEntity packet7useentity) {
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
        Entity entity = worldserver.getEntity(packet7useentity.target);

        if (entity != null) {
            boolean flag = this.player.n(entity);
            double d0 = 36.0D;

            if (!flag) {
                d0 = 9.0D;
            }

            if (this.player.e(entity) < d0) {
                if (packet7useentity.action == 0) {
                    this.player.p(entity);
                } else if (packet7useentity.action == 1) {
                    this.player.attack(entity);
                }
            }
        }
    }

    public void a(Packet205ClientCommand packet205clientcommand) {
        if (packet205clientcommand.a == 1) {
            if (this.player.viewingCredits) {
                this.player = this.minecraftServer.getPlayerList().moveToWorld(this.player, 0, true);
            } else if (this.player.o().getWorldData().isHardcore()) {
                if (this.minecraftServer.I() && this.player.name.equals(this.minecraftServer.H())) {
                    this.player.playerConnection.disconnect("You have died. Game over, man, it\'s game over!");
                    this.minecraftServer.P();
                } else {
                    BanEntry banentry = new BanEntry(this.player.name);

                    banentry.setReason("Death in Hardcore");
                    this.minecraftServer.getPlayerList().getNameBans().add(banentry);
                    this.player.playerConnection.disconnect("You have died. Game over, man, it\'s game over!");
                }
            } else {
                if (this.player.getHealth() > 0) {
                    return;
                }

                this.player = this.minecraftServer.getPlayerList().moveToWorld(this.player, 0, false);
            }
        }
    }

    public boolean b() {
        return true;
    }

    public void a(Packet9Respawn packet9respawn) {}

    public void handleContainerClose(Packet101CloseWindow packet101closewindow) {
        this.player.j();
    }

    public void a(Packet102WindowClick packet102windowclick) {
        if (this.player.activeContainer.windowId == packet102windowclick.a && this.player.activeContainer.c(this.player)) {
            ItemStack itemstack = this.player.activeContainer.clickItem(packet102windowclick.slot, packet102windowclick.button, packet102windowclick.shift, this.player);

            if (ItemStack.matches(packet102windowclick.item, itemstack)) {
                this.player.playerConnection.sendPacket(new Packet106Transaction(packet102windowclick.a, packet102windowclick.d, true));
                this.player.h = true;
                this.player.activeContainer.b();
                this.player.broadcastCarriedItem();
                this.player.h = false;
            } else {
                this.r.a(this.player.activeContainer.windowId, Short.valueOf(packet102windowclick.d));
                this.player.playerConnection.sendPacket(new Packet106Transaction(packet102windowclick.a, packet102windowclick.d, false));
                this.player.activeContainer.a(this.player, false);
                ArrayList arraylist = new ArrayList();

                for (int i = 0; i < this.player.activeContainer.c.size(); ++i) {
                    arraylist.add(((Slot) this.player.activeContainer.c.get(i)).getItem());
                }

                this.player.a(this.player.activeContainer, arraylist);
            }
        }
    }

    public void a(Packet108ButtonClick packet108buttonclick) {
        if (this.player.activeContainer.windowId == packet108buttonclick.a && this.player.activeContainer.c(this.player)) {
            this.player.activeContainer.a((EntityHuman) this.player, packet108buttonclick.b);
            this.player.activeContainer.b();
        }
    }

    public void a(Packet107SetCreativeSlot packet107setcreativeslot) {
        if (this.player.playerInteractManager.isCreative()) {
            boolean flag = packet107setcreativeslot.slot < 0;
            ItemStack itemstack = packet107setcreativeslot.b;
            boolean flag1 = packet107setcreativeslot.slot >= 1 && packet107setcreativeslot.slot < 36 + PlayerInventory.getHotbarSize();
            boolean flag2 = itemstack == null || itemstack.id < Item.byId.length && itemstack.id >= 0 && Item.byId[itemstack.id] != null;
            boolean flag3 = itemstack == null || itemstack.getData() >= 0 && itemstack.getData() >= 0 && itemstack.count <= 64 && itemstack.count > 0;

            if (flag1 && flag2 && flag3) {
                if (itemstack == null) {
                    this.player.defaultContainer.setItem(packet107setcreativeslot.slot, (ItemStack) null);
                } else {
                    this.player.defaultContainer.setItem(packet107setcreativeslot.slot, itemstack);
                }

                this.player.defaultContainer.a(this.player, true);
            } else if (flag && flag2 && flag3 && this.x < 200) {
                this.x += 20;
                EntityItem entityitem = this.player.drop(itemstack);

                if (entityitem != null) {
                    entityitem.c();
                }
            }
        }
    }

    public void a(Packet106Transaction packet106transaction) {
        Short oshort = (Short) this.r.get(this.player.activeContainer.windowId);

        if (oshort != null && packet106transaction.b == oshort.shortValue() && this.player.activeContainer.windowId == packet106transaction.a && !this.player.activeContainer.c(this.player)) {
            this.player.activeContainer.a(this.player, true);
        }
    }

    public void a(Packet130UpdateSign packet130updatesign) {
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);

        if (worldserver.isLoaded(packet130updatesign.x, packet130updatesign.y, packet130updatesign.z)) {
            TileEntity tileentity = worldserver.getTileEntity(packet130updatesign.x, packet130updatesign.y, packet130updatesign.z);

            if (tileentity instanceof TileEntitySign) {
                TileEntitySign tileentitysign = (TileEntitySign) tileentity;

                if (!tileentitysign.a()) {
                    this.minecraftServer.warning("Player " + this.player.name + " just tried to change non-editable sign");
                    return;
                }
            }

            int i;
            int j;

            for (j = 0; j < 4; ++j) {
                boolean flag = true;

                if (packet130updatesign.lines[j].length() > 15) {
                    flag = false;
                } else {
                    for (i = 0; i < packet130updatesign.lines[j].length(); ++i) {
                        if (SharedConstants.allowedCharacters.indexOf(packet130updatesign.lines[j].charAt(i)) < 0) {
                            flag = false;
                        }
                    }
                }

                if (!flag) {
                    packet130updatesign.lines[j] = "!?";
                }
            }

            if (tileentity instanceof TileEntitySign) {
                j = packet130updatesign.x;
                int k = packet130updatesign.y;

                i = packet130updatesign.z;
                TileEntitySign tileentitysign1 = (TileEntitySign) tileentity;

                System.arraycopy(packet130updatesign.lines, 0, tileentitysign1.lines, 0, 4);
                tileentitysign1.update();
                worldserver.notify(j, k, i);
            }
        }
    }

    public void a(Packet0KeepAlive packet0keepalive) {
        if (packet0keepalive.a == this.h) {
            int i = (int) (System.nanoTime() / 1000000L - this.i);

            this.player.ping = (this.player.ping * 3 + i) / 4;
        }
    }

    public boolean a() {
        return true;
    }

    public void a(Packet202Abilities packet202abilities) {
        this.player.abilities.isFlying = packet202abilities.f() && this.player.abilities.canFly;
    }

    public void a(Packet203TabComplete packet203tabcomplete) {
        StringBuilder stringbuilder = new StringBuilder();

        String s;

        for (Iterator iterator = this.minecraftServer.a((ICommandListener) this.player, packet203tabcomplete.d()).iterator(); iterator.hasNext(); stringbuilder.append(s)) {
            s = (String) iterator.next();
            if (stringbuilder.length() > 0) {
                stringbuilder.append(");
            }
        }

        this.player.playerConnection.sendPacket(new Packet203TabComplete(stringbuilder.toString()));
    }

    public void a(Packet204LocaleAndViewDistance packet204localeandviewdistance) {
        this.player.a(packet204localeandviewdistance);
    }

    public void a(Packet250CustomPayload packet250custompayload) {
        DataInputStream datainputstream;
        ItemStack itemstack;
        ItemStack itemstack1;

        if ("MC|BEdit".equals(packet250custompayload.tag)) {
            try {
                datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.data));
                itemstack = Packet.c(datainputstream);
                if (!ItemBookAndQuill.a(itemstack.getTag())) {
                    throw new IOException("Invalid book tag!");
                }

                itemstack1 = this.player.inventory.getItemInHand();
                if (itemstack != null && itemstack.id == Item.BOOK_AND_QUILL.id && itemstack.id == itemstack1.id) {
                    itemstack1.a("pages", (NBTBase) itemstack.getTag().getList("pages"));
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if ("MC|BSign".equals(packet250custompayload.tag)) {
            try {
                datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.data));
                itemstack = Packet.c(datainputstream);
                if (!ItemWrittenBook.a(itemstack.getTag())) {
                    throw new IOException("Invalid book tag!");
                }

                itemstack1 = this.player.inventory.getItemInHand();
                if (itemstack != null && itemstack.id == Item.WRITTEN_BOOK.id && itemstack1.id == Item.BOOK_AND_QUILL.id) {
                    itemstack1.a("author", (NBTBase) (new NBTTagString("author", this.player.name)));
                    itemstack1.a("title", (NBTBase) (new NBTTagString("title", itemstack.getTag().getString("title"))));
                    itemstack1.a("pages", (NBTBase) itemstack.getTag().getList("pages"));
                    itemstack1.id = Item.WRITTEN_BOOK.id;
                }
            } catch (Exception exception1) {
                exception1.printStackTrace();
            }
        } else {
            int i;

            if ("MC|TrSel".equals(packet250custompayload.tag)) {
                try {
                    datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.data));
                    i = datainputstream.readInt();
                    Container container = this.player.activeContainer;

                    if (container instanceof ContainerMerchant) {
                        ((ContainerMerchant) container).e(i);
                    }
                } catch (Exception exception2) {
                    exception2.printStackTrace();
                }
            } else {
                int j;

                if ("MC|AdvCdm".equals(packet250custompayload.tag)) {
                    if (!this.minecraftServer.getEnableCommandBlock()) {
                        this.player.sendMessage(this.player.a("advMode.notEnabled", new Object[0]));
                    } else if (this.player.a(2, "") && this.player.abilities.canInstantlyBuild) {
                        try {
                            datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.data));
                            i = datainputstream.readInt();
                            j = datainputstream.readInt();
                            int k = datainputstream.readInt();
                            String s = Packet.a(datainputstream, 256);
                            TileEntity tileentity = this.player.world.getTileEntity(i, j, k);

                            if (tileentity != null && tileentity instanceof TileEntityCommand) {
                                ((TileEntityCommand) tileentity).b(s);
                                this.player.world.notify(i, j, k);
                                this.player.sendMessage("Command set: " + s);
                            }
                        } catch (Exception exception3) {
                            exception3.printStackTrace();
                        }
                    } else {
                        this.player.sendMessage(this.player.a("advMode.notAllowed", new Object[0]));
                    }
                } else if ("MC|Beacon".equals(packet250custompayload.tag)) {
                    if (this.player.activeContainer instanceof ContainerBeacon) {
                        try {
                            datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.data));
                            i = datainputstream.readInt();
                            j = datainputstream.readInt();
                            ContainerBeacon containerbeacon = (ContainerBeacon) this.player.activeContainer;
                            Slot slot = containerbeacon.getSlot(0);

                            if (slot.d()) {
                                slot.a(1);
                                TileEntityBeacon tileentitybeacon = containerbeacon.e();

                                tileentitybeacon.d(i);
                                tileentitybeacon.e(j);
                                tileentitybeacon.update();
                            }
                        } catch (Exception exception4) {
                            exception4.printStackTrace();
                        }
                    }
                } else if ("MC|ItemName".equals(packet250custompayload.tag) && this.player.activeContainer instanceof ContainerAnvil) {
                    ContainerAnvil containeranvil = (ContainerAnvil) this.player.activeContainer;

                    if (packet250custompayload.data != null && packet250custompayload.data.length >= 1) {
                        String s1 = SharedConstants.a(new String(packet250custompayload.data));

                        if (s1.length() <= 30) {
                            containeranvil.a(s1);
                        }
                    } else {
                        containeranvil.a("");
                    }
                }
            }
        }
    }
}
