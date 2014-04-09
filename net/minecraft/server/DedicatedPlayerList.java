package net.minecraft.server;

import java.io.IOException;

import net.minecraft.util.com.mojang.authlib.GameProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DedicatedPlayerList extends PlayerList {

    private static final Logger g = LogManager.getLogger();

    public DedicatedPlayerList(DedicatedServer dedicatedserver) {
        super(dedicatedserver);
        this.a(dedicatedserver.a("view-distance", 10));
        this.maxPlayers = dedicatedserver.a("max-players", 20);
        this.setHasWhitelist(dedicatedserver.a("white-list", false));
        if (!dedicatedserver.N()) {
            this.getProfileBans().a(true);
            this.getIPBans().a(true);
        }

        this.y();
        this.w();
        this.x();
        this.v();
        this.z();
        this.B();
        this.A();
        if (!this.getWhitelist().c().exists()) {
            this.C();
        }
    }

    public void setHasWhitelist(boolean flag) {
        super.setHasWhitelist(flag);
        this.getServer().a("white-list", Boolean.valueOf(flag));
        this.getServer().a();
    }

    public void addOp(GameProfile gameprofile) {
        super.addOp(gameprofile);
        this.A();
    }

    public void removeOp(GameProfile gameprofile) {
        super.removeOp(gameprofile);
        this.A();
    }

    public void removeWhitelist(GameProfile gameprofile) {
        super.removeWhitelist(gameprofile);
        this.C();
    }

    public void addWhitelist(GameProfile gameprofile) {
        super.addWhitelist(gameprofile);
        this.C();
    }

    public void reloadWhitelist() {
        this.B();
    }

    private void v() {
        try {
            this.getIPBans().save();
        } catch (IOException ioexception) {
            g.warn("Failed to save ip banlist: ", ioexception);
        }
    }

    private void w() {
        try {
            this.getProfileBans().save();
        } catch (IOException ioexception) {
            g.warn("Failed to save user banlist: ", ioexception);
        }
    }

    private void x() {
        try {
            this.getIPBans().load();
        } catch (IOException ioexception) {
            g.warn("Failed to load ip banlist: ", ioexception);
        }
    }

    private void y() {
        try {
            this.getProfileBans().load();
        } catch (IOException ioexception) {
            g.warn("Failed to load user banlist: ", ioexception);
        }
    }

    private void z() {
        try {
            this.getOPs().load();
        } catch (Exception exception) {
            g.warn("Failed to load operators list: ", exception);
        }
    }

    private void A() {
        try {
            this.getOPs().save();
        } catch (Exception exception) {
            g.warn("Failed to save operators list: ", exception);
        }
    }

    private void B() {
        try {
            this.getWhitelist().load();
        } catch (Exception exception) {
            g.warn("Failed to load white-list: ", exception);
        }
    }

    private void C() {
        try {
            this.getWhitelist().save();
        } catch (Exception exception) {
            g.warn("Failed to save white-list: ", exception);
        }
    }

    public boolean isWhitelisted(GameProfile gameprofile) {
        return !this.getHasWhitelist() || this.isOp(gameprofile) || this.getWhitelist().isWhitelisted(gameprofile);
    }

    public DedicatedServer getServer() {
        return (DedicatedServer) super.getServer();
    }

    public MinecraftServer getServer() {
        return this.getServer();
    }
}
