package net.minecraft.server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Callable;

import net.minecraft.util.com.google.common.base.Charsets;
import net.minecraft.util.com.google.common.collect.Lists;
import net.minecraft.util.io.netty.buffer.Unpooled;
import net.minecraft.util.io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerConnection implements PacketPlayInListener {

    private static final Logger c = LogManager.getLogger();
    public final NetworkManager networkManager;
    private final MinecraftServer minecraftServer;
    public EntityPlayer player;
    private int e;
    private int f;
    private boolean g;
    private int h;
    private long i;
    private static Random j = new Random();
    private long k;
    private int chatThrottle;
    private int x;
    private IntHashMap n = new IntHashMap();
    private double y;
    private double z;
    private double q;
    private boolean checkMovement = true;

    public PlayerConnection(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        this.minecraftServer = minecraftserver;
        this.networkManager = networkmanager;
        networkmanager.a((PacketListener) this);
        this.player = entityplayer;
        entityplayer.playerConnection = this;
    }

    public void a() {
        this.g = false;
        ++this.e;
        this.minecraftServer.methodProfiler.a("keepAlive");
        if ((long) this.e - this.k > 40L) {
            this.k = (long) this.e;
            this.i = this.d();
            this.h = (int) this.i;
            this.sendPacket(new PacketPlayOutKeepAlive(this.h));
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

    public NetworkManager b() {
        return this.networkManager;
    }

    public void disconnect(String s) {
        ChatComponentText chatcomponenttext = new ChatComponentText(s);

        this.networkManager.handle(new PacketPlayOutKickDisconnect(chatcomponenttext), new GenericFutureListener[] { new PlayerConnectionFuture(this, chatcomponenttext)});
        this.networkManager.g();
    }

    public void a(PacketPlayInSteerVehicle packetplayinsteervehicle) {
        this.player.a(packetplayinsteervehicle.c(), packetplayinsteervehicle.d(), packetplayinsteervehicle.e(), packetplayinsteervehicle.f());
    }

    public void a(PacketPlayInFlying packetplayinflying) {
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);

        this.g = true;
        if (!this.player.viewingCredits) {
            double d0;

            if (!this.checkMovement) {
                d0 = packetplayinflying.d() - this.z;
                if (packetplayinflying.c() == this.y && d0 * d0 < 0.01D && packetplayinflying.e() == this.q) {
                    this.checkMovement = true;
                }
            }

            if (this.checkMovement) {
                double d1;
                double d2;
                double d3;

                if (this.player.vehicle != null) {
                    float f = this.player.yaw;
                    float f1 = this.player.pitch;

                    this.player.vehicle.ac();
                    d1 = this.player.locX;
                    d2 = this.player.locY;
                    d3 = this.player.locZ;
                    if (packetplayinflying.k()) {
                        f = packetplayinflying.g();
                        f1 = packetplayinflying.h();
                    }

                    this.player.onGround = packetplayinflying.i();
                    this.player.i();
                    this.player.W = 0.0F;
                    this.player.setLocation(d1, d2, d3, f, f1);
                    if (this.player.vehicle != null) {
                        this.player.vehicle.ac();
                    }

                    this.minecraftServer.getPlayerList().d(this.player);
                    if (this.checkMovement) {
                        this.y = this.player.locX;
                        this.z = this.player.locY;
                        this.q = this.player.locZ;
                    }

                    worldserver.playerJoinedWorld(this.player);
                    return;
                }

                if (this.player.isSleeping()) {
                    this.player.i();
                    this.player.setLocation(this.y, this.z, this.q, this.player.yaw, this.player.pitch);
                    worldserver.playerJoinedWorld(this.player);
                    return;
                }

                d0 = this.player.locY;
                this.y = this.player.locX;
                this.z = this.player.locY;
                this.q = this.player.locZ;
                d1 = this.player.locX;
                d2 = this.player.locY;
                d3 = this.player.locZ;
                float f2 = this.player.yaw;
                float f3 = this.player.pitch;

                if (packetplayinflying.j() && packetplayinflying.d() == -999.0D && packetplayinflying.f() == -999.0D) {
                    packetplayinflying.a(false);
                }

                double d4;

                if (packetplayinflying.j()) {
                    d1 = packetplayinflying.c();
                    d2 = packetplayinflying.d();
                    d3 = packetplayinflying.e();
                    d4 = packetplayinflying.f() - packetplayinflying.d();
                    if (!this.player.isSleeping() && (d4 > 1.65D || d4 < 0.1D)) {
                        this.disconnect("Illegal stance");
                        c.warn(this.player.getName() + " had an illegal stance: " + d4);
                        return;
                    }

                    if (Math.abs(packetplayinflying.c()) > 3.2E7D || Math.abs(packetplayinflying.e()) > 3.2E7D) {
                        this.disconnect("Illegal position");
                        return;
                    }
                }

                if (packetplayinflying.k()) {
                    f2 = packetplayinflying.g();
                    f3 = packetplayinflying.h();
                }

                this.player.i();
                this.player.W = 0.0F;
                this.player.setLocation(this.y, this.z, this.q, f2, f3);
                if (!this.checkMovement) {
                    return;
                }

                d4 = d1 - this.player.locX;
                double d5 = d2 - this.player.locY;
                double d6 = d3 - this.player.locZ;
                double d7 = Math.min(Math.abs(d4), Math.abs(this.player.motX));
                double d8 = Math.min(Math.abs(d5), Math.abs(this.player.motY));
                double d9 = Math.min(Math.abs(d6), Math.abs(this.player.motZ));
                double d10 = d7 * d7 + d8 * d8 + d9 * d9;

                if (d10 > 100.0D && (!this.minecraftServer.L() || !this.minecraftServer.K().equals(this.player.getName()))) {
                    c.warn(this.player.getName() + " moved too quickly! " + d4 + "," + d5 + "," + d6 + " (" + d7 + ", " + d8 + ", " + d9 + ")");
                    this.a(this.y, this.z, this.q, this.player.yaw, this.player.pitch);
                    return;
                }

                float f4 = 0.0625F;
                boolean flag = worldserver.getCubes(this.player, this.player.boundingBox.clone().shrink((double) f4, (double) f4, (double) f4)).isEmpty();

                if (this.player.onGround && !packetplayinflying.i() && d5 > 0.0D) {
                    this.player.bj();
                }

                this.player.move(d4, d5, d6);
                this.player.onGround = packetplayinflying.i();
                this.player.checkMovement(d4, d5, d6);
                double d11 = d5;

                d4 = d1 - this.player.locX;
                d5 = d2 - this.player.locY;
                if (d5 > -0.5D || d5 < 0.5D) {
                    d5 = 0.0D;
                }

                d6 = d3 - this.player.locZ;
                d10 = d4 * d4 + d5 * d5 + d6 * d6;
                boolean flag1 = false;

                if (d10 > 0.0625D && !this.player.isSleeping() && !this.player.playerInteractManager.isCreative()) {
                    flag1 = true;
                    c.warn(this.player.getName() + " moved wrongly!");
                }

                this.player.setLocation(d1, d2, d3, f2, f3);
                boolean flag2 = worldserver.getCubes(this.player, this.player.boundingBox.clone().shrink((double) f4, (double) f4, (double) f4)).isEmpty();

                if (flag && (flag1 || !flag2) && !this.player.isSleeping()) {
                    this.a(this.y, this.z, this.q, f2, f3);
                    return;
                }

                AxisAlignedBB axisalignedbb = this.player.boundingBox.clone().grow((double) f4, (double) f4, (double) f4).a(0.0D, -0.55D, 0.0D);

                if (!this.minecraftServer.getAllowFlight() && !this.player.playerInteractManager.isCreative() && !worldserver.c(axisalignedbb)) {
                    if (d11 >= -0.03125D) {
                        ++this.f;
                        if (this.f > 80) {
                            c.warn(this.player.getName() + " was kicked for floating too long!");
                            this.disconnect("Flying is not enabled on this server");
                            return;
                        }
                    }
                } else {
                    this.f = 0;
                }

                this.player.onGround = packetplayinflying.i();
                this.minecraftServer.getPlayerList().d(this.player);
                this.player.b(this.player.locY - d0, packetplayinflying.i());
            } else if (this.e % 20 == 0) {
                this.a(this.y, this.z, this.q, this.player.yaw, this.player.pitch);
            }
        }
    }

    public void a(double d0, double d1, double d2, float f, float f1) {
        this.checkMovement = false;
        this.y = d0;
        this.z = d1;
        this.q = d2;
        this.player.setLocation(d0, d1, d2, f, f1);
        this.player.playerConnection.sendPacket(new PacketPlayOutPosition(d0, d1 + 1.6200000047683716D, d2, f, f1, false));
    }

    public void a(PacketPlayInBlockDig packetplayinblockdig) {
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);

        this.player.w();
        if (packetplayinblockdig.g() == 4) {
            this.player.a(false);
        } else if (packetplayinblockdig.g() == 3) {
            this.player.a(true);
        } else if (packetplayinblockdig.g() == 5) {
            this.player.by();
        } else {
            boolean flag = false;

            if (packetplayinblockdig.g() == 0) {
                flag = true;
            }

            if (packetplayinblockdig.g() == 1) {
                flag = true;
            }

            if (packetplayinblockdig.g() == 2) {
                flag = true;
            }

            int i = packetplayinblockdig.c();
            int j = packetplayinblockdig.d();
            int k = packetplayinblockdig.e();

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

            if (packetplayinblockdig.g() == 0) {
                if (!this.minecraftServer.a(worldserver, i, j, k, this.player)) {
                    this.player.playerInteractManager.dig(i, j, k, packetplayinblockdig.f());
                } else {
                    this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(i, j, k, worldserver));
                }
            } else if (packetplayinblockdig.g() == 2) {
                this.player.playerInteractManager.a(i, j, k);
                if (worldserver.getType(i, j, k).getMaterial() != Material.AIR) {
                    this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(i, j, k, worldserver));
                }
            } else if (packetplayinblockdig.g() == 1) {
                this.player.playerInteractManager.c(i, j, k);
                if (worldserver.getType(i, j, k).getMaterial() != Material.AIR) {
                    this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(i, j, k, worldserver));
                }
            }
        }
    }

    public void a(PacketPlayInBlockPlace packetplayinblockplace) {
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
        ItemStack itemstack = this.player.inventory.getItemInHand();
        boolean flag = false;
        int i = packetplayinblockplace.c();
        int j = packetplayinblockplace.d();
        int k = packetplayinblockplace.e();
        int l = packetplayinblockplace.getFace();

        this.player.w();
        if (packetplayinblockplace.getFace() == 255) {
            if (itemstack == null) {
                return;
            }

            this.player.playerInteractManager.useItem(this.player, worldserver, itemstack);
        } else if (packetplayinblockplace.d() >= this.minecraftServer.getMaxBuildHeight() - 1 && (packetplayinblockplace.getFace() == 1 || packetplayinblockplace.d() >= this.minecraftServer.getMaxBuildHeight())) {
            ChatMessage chatmessage = new ChatMessage("build.tooHigh", new Object[] { Integer.valueOf(this.minecraftServer.getMaxBuildHeight())});

            chatmessage.b().setColor(EnumChatFormat.RED);
            this.player.playerConnection.sendPacket(new PacketPlayOutChat(chatmessage));
            flag = true;
        } else {
            if (this.checkMovement && this.player.e((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D) < 64.0D && !this.minecraftServer.a(worldserver, i, j, k, this.player)) {
                this.player.playerInteractManager.interact(this.player, worldserver, itemstack, i, j, k, l, packetplayinblockplace.h(), packetplayinblockplace.i(), packetplayinblockplace.j());
            }

            flag = true;
        }

        if (flag) {
            this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(i, j, k, worldserver));
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

            this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(i, j, k, worldserver));
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
            if (!ItemStack.matches(this.player.inventory.getItemInHand(), packetplayinblockplace.getItemStack())) {
                this.sendPacket(new PacketPlayOutSetSlot(this.player.activeContainer.windowId, slot.rawSlotIndex, this.player.inventory.getItemInHand()));
            }
        }
    }

    public void a(IChatBaseComponent ichatbasecomponent) {
        c.info(this.player.getName() + " lost connection: " + ichatbasecomponent);
        this.minecraftServer.au();
        ChatMessage chatmessage = new ChatMessage("multiplayer.player.left", new Object[] { this.player.getScoreboardDisplayName()});

        chatmessage.b().setColor(EnumChatFormat.YELLOW);
        this.minecraftServer.getPlayerList().sendMessage(chatmessage);
        this.player.n();
        this.minecraftServer.getPlayerList().disconnect(this.player);
        if (this.minecraftServer.L() && this.player.getName().equals(this.minecraftServer.K())) {
            c.info("Stopping singleplayer server as player logged out");
            this.minecraftServer.safeShutdown();
        }
    }

    public void sendPacket(Packet packet) {
        if (packet instanceof PacketPlayOutChat) {
            PacketPlayOutChat packetplayoutchat = (PacketPlayOutChat) packet;
            EnumChatVisibility enumchatvisibility = this.player.getChatFlags();

            if (enumchatvisibility == EnumChatVisibility.HIDDEN) {
                return;
            }

            if (enumchatvisibility == EnumChatVisibility.SYSTEM && !packetplayoutchat.d()) {
                return;
            }
        }

        try {
            this.networkManager.handle(packet, new GenericFutureListener[0]);
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Sending packet");
            CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Packet being sent");

            crashreportsystemdetails.a("Packet class", (Callable) (new CrashReportConnectionPacketClass(this, packet)));
            throw new ReportedException(crashreport);
        }
    }

    public void a(PacketPlayInHeldItemSlot packetplayinhelditemslot) {
        if (packetplayinhelditemslot.c() >= 0 && packetplayinhelditemslot.c() < PlayerInventory.getHotbarSize()) {
            this.player.inventory.itemInHandIndex = packetplayinhelditemslot.c();
            this.player.w();
        } else {
            c.warn(this.player.getName() + " tried to set an invalid carried item");
        }
    }

    public void a(PacketPlayInChat packetplayinchat) {
        if (this.player.getChatFlags() == EnumChatVisibility.HIDDEN) {
            ChatMessage chatmessage = new ChatMessage("chat.cannotSend", new Object[0]);

            chatmessage.b().setColor(EnumChatFormat.RED);
            this.sendPacket(new PacketPlayOutChat(chatmessage));
        } else {
            this.player.w();
            String s = packetplayinchat.c();

            s = StringUtils.normalizeSpace(s);

            for (int i = 0; i < s.length(); ++i) {
                if (!SharedConstants.isAllowedChatCharacter(s.charAt(i))) {
                    this.disconnect("Illegal characters in chat");
                    return;
                }
            }

            if (s.startsWith("/")) {
                this.handleCommand(s);
            } else {
                ChatMessage chatmessage1 = new ChatMessage("chat.type.text", new Object[] { this.player.getScoreboardDisplayName(), s});

                this.minecraftServer.getPlayerList().sendMessage(chatmessage1, false);
            }

            this.chatThrottle += 20;
            if (this.chatThrottle > 200 && !this.minecraftServer.getPlayerList().isOp(this.player.getName())) {
                this.disconnect("disconnect.spam");
            }
        }
    }

    private void handleCommand(String s) {
        this.minecraftServer.getCommandHandler().a(this.player, s);
    }

    public void a(PacketPlayInArmAnimation packetplayinarmanimation) {
        this.player.w();
        if (packetplayinarmanimation.d() == 1) {
            this.player.ba();
        }
    }

    public void a(PacketPlayInEntityAction packetplayinentityaction) {
        this.player.w();
        if (packetplayinentityaction.d() == 1) {
            this.player.setSneaking(true);
        } else if (packetplayinentityaction.d() == 2) {
            this.player.setSneaking(false);
        } else if (packetplayinentityaction.d() == 4) {
            this.player.setSprinting(true);
        } else if (packetplayinentityaction.d() == 5) {
            this.player.setSprinting(false);
        } else if (packetplayinentityaction.d() == 3) {
            this.player.a(false, true, true);
            this.checkMovement = false;
        } else if (packetplayinentityaction.d() == 6) {
            if (this.player.vehicle != null && this.player.vehicle instanceof EntityHorse) {
                ((EntityHorse) this.player.vehicle).w(packetplayinentityaction.e());
            }
        } else if (packetplayinentityaction.d() == 7 && this.player.vehicle != null && this.player.vehicle instanceof EntityHorse) {
            ((EntityHorse) this.player.vehicle).g(this.player);
        }
    }

    public void a(PacketPlayInUseEntity packetplayinuseentity) {
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
        Entity entity = packetplayinuseentity.a((World) worldserver);

        this.player.w();
        if (entity != null) {
            boolean flag = this.player.o(entity);
            double d0 = 36.0D;

            if (!flag) {
                d0 = 9.0D;
            }

            if (this.player.e(entity) < d0) {
                if (packetplayinuseentity.c() == EnumEntityUseAction.INTERACT) {
                    this.player.p(entity);
                } else if (packetplayinuseentity.c() == EnumEntityUseAction.ATTACK) {
                    if (entity instanceof EntityItem || entity instanceof EntityExperienceOrb || entity instanceof EntityArrow || entity == this.player) {
                        this.disconnect("Attempting to attack an invalid entity");
                        this.minecraftServer.warning("Player " + this.player.getName() + " tried to attack an invalid entity");
                        return;
                    }

                    this.player.attack(entity);
                }
            }
        }
    }

    public void a(PacketPlayInClientCommand packetplayinclientcommand) {
        this.player.w();
        EnumClientCommand enumclientcommand = packetplayinclientcommand.c();

        switch (ClientCommandOrdinalWrapper.a[enumclientcommand.ordinal()]) {
        case 1:
            if (this.player.viewingCredits) {
                this.player = this.minecraftServer.getPlayerList().moveToWorld(this.player, 0, true);
            } else if (this.player.r().getWorldData().isHardcore()) {
                if (this.minecraftServer.L() && this.player.getName().equals(this.minecraftServer.K())) {
                    this.player.playerConnection.disconnect("You have died. Game over, man, it\'s game over!");
                    this.minecraftServer.S();
                } else {
                    BanEntry banentry = new BanEntry(this.player.getName());

                    banentry.setReason("Death in Hardcore");
                    this.minecraftServer.getPlayerList().getNameBans().add(banentry);
                    this.player.playerConnection.disconnect("You have died. Game over, man, it\'s game over!");
                }
            } else {
                if (this.player.getHealth() > 0.0F) {
                    return;
                }

                this.player = this.minecraftServer.getPlayerList().moveToWorld(this.player, 0, false);
            }
            break;

        case 2:
            this.player.x().a(this.player);
            break;

        case 3:
            this.player.a((Statistic) AchievementList.f);
        }
    }

    public void a(PacketPlayInCloseWindow packetplayinclosewindow) {
        this.player.m();
    }

    public void a(PacketPlayInWindowClick packetplayinwindowclick) {
        this.player.w();
        if (this.player.activeContainer.windowId == packetplayinwindowclick.c() && this.player.activeContainer.c(this.player)) {
            ItemStack itemstack = this.player.activeContainer.clickItem(packetplayinwindowclick.d(), packetplayinwindowclick.e(), packetplayinwindowclick.h(), this.player);

            if (ItemStack.matches(packetplayinwindowclick.g(), itemstack)) {
                this.player.playerConnection.sendPacket(new PacketPlayOutTransaction(packetplayinwindowclick.c(), packetplayinwindowclick.f(), true));
                this.player.h = true;
                this.player.activeContainer.b();
                this.player.broadcastCarriedItem();
                this.player.h = false;
            } else {
                this.n.a(this.player.activeContainer.windowId, Short.valueOf(packetplayinwindowclick.f()));
                this.player.playerConnection.sendPacket(new PacketPlayOutTransaction(packetplayinwindowclick.c(), packetplayinwindowclick.f(), false));
                this.player.activeContainer.a(this.player, false);
                ArrayList arraylist = new ArrayList();

                for (int i = 0; i < this.player.activeContainer.c.size(); ++i) {
                    arraylist.add(((Slot) this.player.activeContainer.c.get(i)).getItem());
                }

                this.player.a(this.player.activeContainer, arraylist);
            }
        }
    }

    public void a(PacketPlayInEnchantItem packetplayinenchantitem) {
        this.player.w();
        if (this.player.activeContainer.windowId == packetplayinenchantitem.c() && this.player.activeContainer.c(this.player)) {
            this.player.activeContainer.a((EntityHuman) this.player, packetplayinenchantitem.d());
            this.player.activeContainer.b();
        }
    }

    public void a(PacketPlayInSetCreativeSlot packetplayinsetcreativeslot) {
        if (this.player.playerInteractManager.isCreative()) {
            boolean flag = packetplayinsetcreativeslot.c() < 0;
            ItemStack itemstack = packetplayinsetcreativeslot.d();
            boolean flag1 = packetplayinsetcreativeslot.c() >= 1 && packetplayinsetcreativeslot.c() < 36 + PlayerInventory.getHotbarSize();
            boolean flag2 = itemstack == null || itemstack.getItem() != null;
            boolean flag3 = itemstack == null || itemstack.getData() >= 0 && itemstack.count <= 64 && itemstack.count > 0;

            if (flag1 && flag2 && flag3) {
                if (itemstack == null) {
                    this.player.defaultContainer.setItem(packetplayinsetcreativeslot.c(), (ItemStack) null);
                } else {
                    this.player.defaultContainer.setItem(packetplayinsetcreativeslot.c(), itemstack);
                }

                this.player.defaultContainer.a(this.player, true);
            } else if (flag && flag2 && flag3 && this.x < 200) {
                this.x += 20;
                EntityItem entityitem = this.player.drop(itemstack, true);

                if (entityitem != null) {
                    entityitem.e();
                }
            }
        }
    }

    public void a(PacketPlayInTransaction packetplayintransaction) {
        Short oshort = (Short) this.n.get(this.player.activeContainer.windowId);

        if (oshort != null && packetplayintransaction.d() == oshort.shortValue() && this.player.activeContainer.windowId == packetplayintransaction.c() && !this.player.activeContainer.c(this.player)) {
            this.player.activeContainer.a(this.player, true);
        }
    }

    public void a(PacketPlayInUpdateSign packetplayinupdatesign) {
        this.player.w();
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);

        if (worldserver.isLoaded(packetplayinupdatesign.c(), packetplayinupdatesign.d(), packetplayinupdatesign.e())) {
            TileEntity tileentity = worldserver.getTileEntity(packetplayinupdatesign.c(), packetplayinupdatesign.d(), packetplayinupdatesign.e());

            if (tileentity instanceof TileEntitySign) {
                TileEntitySign tileentitysign = (TileEntitySign) tileentity;

                if (!tileentitysign.a() || tileentitysign.b() != this.player) {
                    this.minecraftServer.warning("Player " + this.player.getName() + " just tried to change non-editable sign");
                    return;
                }
            }

            int i;
            int j;

            for (j = 0; j < 4; ++j) {
                boolean flag = true;

                if (packetplayinupdatesign.f()[j].length() > 15) {
                    flag = false;
                } else {
                    for (i = 0; i < packetplayinupdatesign.f()[j].length(); ++i) {
                        if (!SharedConstants.isAllowedChatCharacter(packetplayinupdatesign.f()[j].charAt(i))) {
                            flag = false;
                        }
                    }
                }

                if (!flag) {
                    packetplayinupdatesign.f()[j] = "!?";
                }
            }

            if (tileentity instanceof TileEntitySign) {
                j = packetplayinupdatesign.c();
                int k = packetplayinupdatesign.d();

                i = packetplayinupdatesign.e();
                TileEntitySign tileentitysign1 = (TileEntitySign) tileentity;

                System.arraycopy(packetplayinupdatesign.f(), 0, tileentitysign1.lines, 0, 4);
                tileentitysign1.update();
                worldserver.notify(j, k, i);
            }
        }
    }

    public void a(PacketPlayInKeepAlive packetplayinkeepalive) {
        if (packetplayinkeepalive.c() == this.h) {
            int i = (int) (this.d() - this.i);

            this.player.ping = (this.player.ping * 3 + i) / 4;
        }
    }

    private long d() {
        return System.nanoTime() / 1000000L;
    }

    public void a(PacketPlayInAbilities packetplayinabilities) {
        this.player.abilities.isFlying = packetplayinabilities.d() && this.player.abilities.canFly;
    }

    public void a(PacketPlayInTabComplete packetplayintabcomplete) {
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = this.minecraftServer.a(this.player, packetplayintabcomplete.c()).iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            arraylist.add(s);
        }

        this.player.playerConnection.sendPacket(new PacketPlayOutTabComplete((String[]) arraylist.toArray(new String[arraylist.size()])));
    }

    public void a(PacketPlayInSettings packetplayinsettings) {
        this.player.a(packetplayinsettings);
    }

    public void a(PacketPlayInCustomPayload packetplayincustompayload) {
        ItemStack itemstack;
        ItemStack itemstack1;

        if ("MC|BEdit".equals(packetplayincustompayload.c())) {
            try {
                itemstack = (new PacketDataSerializer(Unpooled.wrappedBuffer(packetplayincustompayload.e()))).c();
                if (!ItemBookAndQuill.a(itemstack.getTag())) {
                    throw new IOException("Invalid book tag!");
                }

                itemstack1 = this.player.inventory.getItemInHand();
                if (itemstack.getItem() == Items.BOOK_AND_QUILL && itemstack.getItem() == itemstack1.getItem()) {
                    itemstack1.a("pages", (NBTBase) itemstack.getTag().getList("pages", 8));
                }
            } catch (Exception exception) {
                c.error("Couldn\'t handle book info", exception);
            }
        } else if ("MC|BSign".equals(packetplayincustompayload.c())) {
            try {
                itemstack = (new PacketDataSerializer(Unpooled.wrappedBuffer(packetplayincustompayload.e()))).c();
                if (!ItemWrittenBook.a(itemstack.getTag())) {
                    throw new IOException("Invalid book tag!");
                }

                itemstack1 = this.player.inventory.getItemInHand();
                if (itemstack.getItem() == Items.WRITTEN_BOOK && itemstack1.getItem() == Items.BOOK_AND_QUILL) {
                    itemstack1.a("author", (NBTBase) (new NBTTagString(this.player.getName())));
                    itemstack1.a("title", (NBTBase) (new NBTTagString(itemstack.getTag().getString("title"))));
                    itemstack1.a("pages", (NBTBase) itemstack.getTag().getList("pages", 8));
                    itemstack1.setItem(Items.WRITTEN_BOOK);
                }
            } catch (Exception exception1) {
                c.error("Couldn\'t sign book", exception1);
            }
        } else {
            DataInputStream datainputstream;
            int i;

            if ("MC|TrSel".equals(packetplayincustompayload.c())) {
                try {
                    datainputstream = new DataInputStream(new ByteArrayInputStream(packetplayincustompayload.e()));
                    i = datainputstream.readInt();
                    Container container = this.player.activeContainer;

                    if (container instanceof ContainerMerchant) {
                        ((ContainerMerchant) container).e(i);
                    }
                } catch (Exception exception2) {
                    c.error("Couldn\'t select trade", exception2);
                }
            } else if ("MC|AdvCdm".equals(packetplayincustompayload.c())) {
                if (!this.minecraftServer.getEnableCommandBlock()) {
                    this.player.sendMessage(new ChatMessage("advMode.notEnabled", new Object[0]));
                } else if (this.player.a(2, "") && this.player.abilities.canInstantlyBuild) {
                    try {
                        PacketDataSerializer packetdataserializer = new PacketDataSerializer(Unpooled.wrappedBuffer(packetplayincustompayload.e()));
                        byte b0 = packetdataserializer.readByte();
                        CommandBlockListenerAbstract commandblocklistenerabstract = null;

                        if (b0 == 0) {
                            TileEntity tileentity = this.player.world.getTileEntity(packetdataserializer.readInt(), packetdataserializer.readInt(), packetdataserializer.readInt());

                            if (tileentity instanceof TileEntityCommand) {
                                commandblocklistenerabstract = ((TileEntityCommand) tileentity).a();
                            }
                        } else if (b0 == 1) {
                            Entity entity = this.player.world.getEntity(packetdataserializer.readInt());

                            if (entity instanceof EntityMinecartCommandBlock) {
                                commandblocklistenerabstract = ((EntityMinecartCommandBlock) entity).e();
                            }
                        }

                        String s = packetdataserializer.c(packetdataserializer.readableBytes());

                        if (commandblocklistenerabstract != null) {
                            commandblocklistenerabstract.a(s);
                            commandblocklistenerabstract.e();
                            this.player.sendMessage(new ChatMessage("advMode.setCommand.success", new Object[] { s}));
                        }
                    } catch (Exception exception3) {
                        c.error("Couldn\'t set command block", exception3);
                    }
                } else {
                    this.player.sendMessage(new ChatMessage("advMode.notAllowed", new Object[0]));
                }
            } else if ("MC|Beacon".equals(packetplayincustompayload.c())) {
                if (this.player.activeContainer instanceof ContainerBeacon) {
                    try {
                        datainputstream = new DataInputStream(new ByteArrayInputStream(packetplayincustompayload.e()));
                        i = datainputstream.readInt();
                        int j = datainputstream.readInt();
                        ContainerBeacon containerbeacon = (ContainerBeacon) this.player.activeContainer;
                        Slot slot = containerbeacon.getSlot(0);

                        if (slot.e()) {
                            slot.a(1);
                            TileEntityBeacon tileentitybeacon = containerbeacon.e();

                            tileentitybeacon.d(i);
                            tileentitybeacon.e(j);
                            tileentitybeacon.update();
                        }
                    } catch (Exception exception4) {
                        c.error("Couldn\'t set beacon", exception4);
                    }
                }
            } else if ("MC|ItemName".equals(packetplayincustompayload.c()) && this.player.activeContainer instanceof ContainerAnvil) {
                ContainerAnvil containeranvil = (ContainerAnvil) this.player.activeContainer;

                if (packetplayincustompayload.e() != null && packetplayincustompayload.e().length >= 1) {
                    String s1 = SharedConstants.a(new String(packetplayincustompayload.e(), Charsets.UTF_8));

                    if (s1.length() <= 30) {
                        containeranvil.a(s1);
                    }
                } else {
                    containeranvil.a("");
                }
            }
        }
    }

    public void a(EnumProtocol enumprotocol, EnumProtocol enumprotocol1) {
        if (enumprotocol1 != EnumProtocol.PLAY) {
            throw new IllegalStateException("Unexpected change in protocol!");
        }
    }
}
