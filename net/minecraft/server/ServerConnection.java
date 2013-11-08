package net.minecraft.server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

import net.minecraft.util.com.google.common.util.concurrent.ThreadFactoryBuilder;
import net.minecraft.util.io.netty.bootstrap.ServerBootstrap;
import net.minecraft.util.io.netty.channel.ChannelFuture;
import net.minecraft.util.io.netty.channel.nio.NioEventLoopGroup;
import net.minecraft.util.io.netty.channel.socket.nio.NioServerSocketChannel;
import net.minecraft.util.io.netty.util.concurrent.GenericFutureListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerConnection {

    private static final Logger b = LogManager.getLogger();
    private static final NioEventLoopGroup c = new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty IO #%d").setDaemon(true).build());
    private final MinecraftServer d;
    public volatile boolean a;
    private final List e = Collections.synchronizedList(new ArrayList());
    private final List f = Collections.synchronizedList(new ArrayList());

    public ServerConnection(MinecraftServer minecraftserver) {
        this.d = minecraftserver;
        this.a = true;
    }

    public void a(InetAddress inetaddress, int i) {
        List list = this.e;

        synchronized (this.e) {
            this.e.add(((ServerBootstrap) ((ServerBootstrap) (new ServerBootstrap()).channel(NioServerSocketChannel.class)).childHandler(new ServerConnectionChannel(this)).group(c).localAddress(inetaddress, i)).bind().syncUninterruptibly());
        }
    }

    public void b() {
        this.a = false;
        Iterator iterator = this.e.iterator();

        while (iterator.hasNext()) {
            ChannelFuture channelfuture = (ChannelFuture) iterator.next();

            channelfuture.channel().close().syncUninterruptibly();
        }
    }

    public void c() {
        List list = this.f;

        synchronized (this.f) {
            Iterator iterator = this.f.iterator();

            while (iterator.hasNext()) {
                NetworkManager networkmanager = (NetworkManager) iterator.next();

                if (!networkmanager.d()) {
                    iterator.remove();
                    if (networkmanager.f() != null) {
                        networkmanager.getPacketListener().a(networkmanager.f());
                    } else if (networkmanager.getPacketListener() != null) {
                        networkmanager.getPacketListener().a(new ChatComponentText("Disconnected"));
                    }
                } else {
                    try {
                        networkmanager.a();
                    } catch (Exception exception) {
                        if (networkmanager.c()) {
                            CrashReport crashreport = CrashReport.a(exception, "Ticking memory connection");
                            CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Ticking connection");

                            crashreportsystemdetails.a("Connection", (Callable) (new CrashReportServerConnection(this, networkmanager)));
                            throw new ReportedException(crashreport);
                        }

                        b.warn("Failed to handle packet for " + networkmanager.getSocketAddress(), exception);
                        ChatComponentText chatcomponenttext = new ChatComponentText("Internal server error");

                        networkmanager.handle(new PacketPlayOutKickDisconnect(chatcomponenttext), new GenericFutureListener[] { new ServerConnectionFuture(this, networkmanager, chatcomponenttext)});
                        networkmanager.g();
                    }
                }
            }
        }
    }

    public MinecraftServer d() {
        return this.d;
    }

    static List a(ServerConnection serverconnection) {
        return serverconnection.f;
    }

    static MinecraftServer b(ServerConnection serverconnection) {
        return serverconnection.d;
    }
}
