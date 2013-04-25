package net.minecraft.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class BanList {

    private final InsensitiveStringMap a = new InsensitiveStringMap();
    private final File b;
    private boolean c = true;

    public BanList(File file1) {
        this.b = file1;
    }

    public boolean isEnabled() {
        return this.c;
    }

    public void setEnabled(boolean flag) {
        this.c = flag;
    }

    public Map getEntries() {
        this.removeExpired();
        return this.a;
    }

    public boolean isBanned(String s) {
        if (!this.isEnabled()) {
            return false;
        } else {
            this.removeExpired();
            return this.a.containsKey(s);
        }
    }

    public void add(BanEntry banentry) {
        this.a.put(banentry.getName(), banentry);
        this.save();
    }

    public void remove(String s) {
        this.a.remove(s);
        this.save();
    }

    public void removeExpired() {
        Iterator iterator = this.a.values().iterator();

        while (iterator.hasNext()) {
            BanEntry banentry = (BanEntry) iterator.next();

            if (banentry.hasExpired()) {
                iterator.remove();
            }
        }
    }

    public void load() {
        if (this.b.isFile()) {
            BufferedReader bufferedreader;

            try {
                bufferedreader = new BufferedReader(new FileReader(this.b));
            } catch (FileNotFoundException filenotfoundexception) {
                throw new Error();
            }

            String s;

            try {
                while ((s = bufferedreader.readLine()) != null) {
                    if (!s.startsWith("#")) {
                        BanEntry banentry = BanEntry.c(s);

                        if (banentry != null) {
                            this.a.put(banentry.getName(), banentry);
                        }
                    }
                }
            } catch (IOException ioexception) {
                MinecraftServer.getServer().getLogger().severe("Could not load ban list", ioexception);
            }
        }
    }

    public void save() {
        this.save(true);
    }

    public void save(boolean flag) {
        this.removeExpired();

        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.b, false));

            if (flag) {
                printwriter.println("# Updated " + (new SimpleDateFormat()).format(new Date()) + " by Minecraft " + "1.5.2");
                printwriter.println("# victim name | ban date | banned by | banned until | reason");
                printwriter.println();
            }

            Iterator iterator = this.a.values().iterator();

            while (iterator.hasNext()) {
                BanEntry banentry = (BanEntry) iterator.next();

                printwriter.println(banentry.g());
            }

            printwriter.close();
        } catch (IOException ioexception) {
            MinecraftServer.getServer().getLogger().severe("Could not save ban list", ioexception);
        }
    }
}
