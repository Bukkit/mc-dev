package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class ServerConnection {

    private final MinecraftServer b;
    private final List c = Collections.synchronizedList(new ArrayList());
    public volatile boolean a;

    public ServerConnection(MinecraftServer minecraftserver) {
        this.b = minecraftserver;
        this.a = true;
    }

    public void a(PlayerConnection playerconnection) {
        this.c.add(playerconnection);
    }

    public void a() {
        this.a = false;
    }

    public void b() {
        for (int i = 0; i < this.c.size(); ++i) {
            PlayerConnection playerconnection = (PlayerConnection) this.c.get(i);

            try {
                playerconnection.d();
            } catch (Exception exception) {
                if (playerconnection.networkManager instanceof MemoryNetworkManager) {
                    CrashReport crashreport = CrashReport.a((Throwable) exception, "Ticking memory connection");
                    CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Ticking connection");

                    crashreportsystemdetails.a("Connection", (Callable) (new CallableConnection(this, playerconnection)));
                    throw new ReportedException(crashreport);
                }

                this.b.getLogger().warning("Failed to handle packet for " + playerconnection.player.getLocalizedName() + "/" + playerconnection.player.q() + ": " + exception, (Throwable) exception);
                playerconnection.disconnect("Internal server error");
            }

            if (playerconnection.disconnected) {
                this.c.remove(i--);
            }

            playerconnection.networkManager.a();
        }
    }

    public MinecraftServer d() {
        return this.b;
    }
}
