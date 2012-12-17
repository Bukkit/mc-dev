package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ServerConnection {

    public static Logger a = Logger.getLogger("Minecraft");
    private final MinecraftServer c;
    private final List d = Collections.synchronizedList(new ArrayList());
    public volatile boolean b = false;

    public ServerConnection(MinecraftServer minecraftserver) {
        this.c = minecraftserver;
        this.b = true;
    }

    public void a(PlayerConnection playerconnection) {
        this.d.add(playerconnection);
    }

    public void a() {
        this.b = false;
    }

    public void b() {
        for (int i = 0; i < this.d.size(); ++i) {
            PlayerConnection playerconnection = (PlayerConnection) this.d.get(i);

            try {
                playerconnection.d();
            } catch (Exception exception) {
                if (playerconnection.networkManager instanceof MemoryNetworkManager) {
                    CrashReport crashreport = CrashReport.a(exception, "Ticking memory connection");

                    throw new ReportedException(crashreport);
                }

                a.log(Level.WARNING, "Failed to handle packet for " + playerconnection.player.getLocalizedName() + "/" + playerconnection.player.q() + ": " + exception, exception);
                playerconnection.disconnect("Internal server error");
            }

            if (playerconnection.disconnected) {
                this.d.remove(i--);
            }

            playerconnection.networkManager.a();
        }
    }

    public MinecraftServer d() {
        return this.c;
    }
}
