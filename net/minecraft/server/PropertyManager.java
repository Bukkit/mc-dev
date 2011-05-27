package net.minecraft.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyManager {

    public static Logger a = Logger.getLogger("Minecraft");
    private Properties b = new Properties();
    private File c;

    public PropertyManager(File file1) {
        this.c = file1;
        if (file1.exists()) {
            try {
                this.b.load(new FileInputStream(file1));
            } catch (Exception exception) {
                a.log(Level.WARNING, "Failed to load " + file1, exception);
                this.a();
            }
        } else {
            a.log(Level.WARNING, file1 + " does not exist");
            this.a();
        }
    }

    public void a() {
        a.log(Level.INFO, "Generating new properties file");
        this.b();
    }

    public void b() {
        try {
            this.b.store(new FileOutputStream(this.c), "Minecraft server properties");
        } catch (Exception exception) {
            a.log(Level.WARNING, "Failed to save " + this.c, exception);
            this.a();
        }
    }

    public String a(String s, String s1) {
        if (!this.b.containsKey(s)) {
            this.b.setProperty(s, s1);
            this.b();
        }

        return this.b.getProperty(s, s1);
    }

    public int a(String s, int i) {
        try {
            return Integer.parseInt(this.a(s, "" + i));
        } catch (Exception exception) {
            this.b.setProperty(s, "" + i);
            return i;
        }
    }

    public boolean a(String s, boolean flag) {
        try {
            return Boolean.parseBoolean(this.a(s, "" + flag));
        } catch (Exception exception) {
            this.b.setProperty(s, "" + flag);
            return flag;
        }
    }
}
