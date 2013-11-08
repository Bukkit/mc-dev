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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BanList {

    private static final Logger a = LogManager.getLogger();
    private final InsensitiveStringMap b = new InsensitiveStringMap();
    private final File c;
    private boolean d = true;

    public BanList(File file1) {
        this.c = file1;
    }

    public boolean isEnabled() {
        return this.d;
    }

    public void setEnabled(boolean flag) {
        this.d = flag;
    }

    public Map getEntries() {
        this.removeExpired();
        return this.b;
    }

    public boolean isBanned(String s) {
        if (!this.isEnabled()) {
            return false;
        } else {
            this.removeExpired();
            return this.b.containsKey(s);
        }
    }

    public void add(BanEntry banentry) {
        this.b.put(banentry.getName(), banentry);
        this.save();
    }

    public void remove(String s) {
        this.b.remove(s);
        this.save();
    }

    public void removeExpired() {
        Iterator iterator = this.b.values().iterator();

        while (iterator.hasNext()) {
            BanEntry banentry = (BanEntry) iterator.next();

            if (banentry.hasExpired()) {
                iterator.remove();
            }
        }
    }

    public void load() {
        if (this.c.isFile()) {
            BufferedReader bufferedreader;

            try {
                bufferedreader = new BufferedReader(new FileReader(this.c));
            } catch (FileNotFoundException filenotfoundexception) {
                throw new Error();
            }

            String s;

            try {
                while ((s = bufferedreader.readLine()) != null) {
                    if (!s.startsWith("#")) {
                        BanEntry banentry = BanEntry.c(s);

                        if (banentry != null) {
                            this.b.put(banentry.getName(), banentry);
                        }
                    }
                }
            } catch (IOException ioexception) {
                a.error("Could not load ban list", ioexception);
            }
        }
    }

    public void save() {
        this.save(true);
    }

    public void save(boolean flag) {
        this.removeExpired();

        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.c, false));

            if (flag) {
                printwriter.println("# Updated " + (new SimpleDateFormat()).format(new Date()) + " by Minecraft " + "1.7.2");
                printwriter.println("# victim name | ban date | banned by | banned until | reason");
                printwriter.println();
            }

            Iterator iterator = this.b.values().iterator();

            while (iterator.hasNext()) {
                BanEntry banentry = (BanEntry) iterator.next();

                printwriter.println(banentry.g());
            }

            printwriter.close();
        } catch (IOException ioexception) {
            a.error("Could not save ban list", ioexception);
        }
    }
}
