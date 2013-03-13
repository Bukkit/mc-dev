package net.minecraft.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;

public class DedicatedPlayerList extends PlayerList {

    private File d;
    private File e;

    public DedicatedPlayerList(DedicatedServer dedicatedserver) {
        super(dedicatedserver);
        this.d = dedicatedserver.e("ops.txt");
        this.e = dedicatedserver.e("white-list.txt");
        this.c = dedicatedserver.a("view-distance", 10);
        this.maxPlayers = dedicatedserver.a("max-players", 20);
        this.setHasWhitelist(dedicatedserver.a("white-list", false));
        if (!dedicatedserver.I()) {
            this.getNameBans().setEnabled(true);
            this.getIPBans().setEnabled(true);
        }

        this.getNameBans().load();
        this.getNameBans().save();
        this.getIPBans().load();
        this.getIPBans().save();
        this.t();
        this.v();
        this.u();
        if (!this.e.exists()) {
            this.w();
        }
    }

    public void setHasWhitelist(boolean flag) {
        super.setHasWhitelist(flag);
        this.getServer().a("white-list", Boolean.valueOf(flag));
        this.getServer().a();
    }

    public void addOp(String s) {
        super.addOp(s);
        this.u();
    }

    public void removeOp(String s) {
        super.removeOp(s);
        this.u();
    }

    public void removeWhitelist(String s) {
        super.removeWhitelist(s);
        this.w();
    }

    public void addWhitelist(String s) {
        super.addWhitelist(s);
        this.w();
    }

    public void reloadWhitelist() {
        this.v();
    }

    private void t() {
        try {
            this.getOPs().clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.d));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.getOPs().add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            this.getServer().getLogger().warning("Failed to load operators list: " + exception);
        }
    }

    private void u() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.d, false));
            Iterator iterator = this.getOPs().iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            this.getServer().getLogger().warning("Failed to save operators list: " + exception);
        }
    }

    private void v() {
        try {
            this.getWhitelisted().clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.e));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.getWhitelisted().add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            this.getServer().getLogger().warning("Failed to load white-list: " + exception);
        }
    }

    private void w() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.e, false));
            Iterator iterator = this.getWhitelisted().iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            this.getServer().getLogger().warning("Failed to save white-list: " + exception);
        }
    }

    public boolean isWhitelisted(String s) {
        s = s.trim().toLowerCase();
        return !this.getHasWhitelist() || this.isOp(s) || this.getWhitelisted().contains(s);
    }

    public DedicatedServer getServer() {
        return (DedicatedServer) super.getServer();
    }

    public MinecraftServer getServer() {
        return this.getServer();
    }
}
