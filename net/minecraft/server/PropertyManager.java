package net.minecraft.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyManager {

    public static Logger a = Logger.getLogger("Minecraft");
    private Properties properties = new Properties();
    private File c;

    public PropertyManager(File file1) {
        this.c = file1;
        if (file1.exists()) {
            FileInputStream fileinputstream = null;

            try {
                fileinputstream = new FileInputStream(file1);
                this.properties.load(fileinputstream);
            } catch (Exception exception) {
                a.log(Level.WARNING, "Failed to load " + file1, exception);
                this.a();
            } finally {
                if (fileinputstream != null) {
                    try {
                        fileinputstream.close();
                    } catch (IOException ioexception) {
                        ;
                    }
                }
            }
        } else {
            a.log(Level.WARNING, file1 + " does not exist");
            this.a();
        }
    }

    public void a() {
        a.log(Level.INFO, "Generating new properties file");
        this.savePropertiesFile();
    }

    public void savePropertiesFile() {
        FileOutputStream fileoutputstream = null;

        try {
            fileoutputstream = new FileOutputStream(this.c);
            this.properties.store(fileoutputstream, "Minecraft server properties");
        } catch (Exception exception) {
            a.log(Level.WARNING, "Failed to save " + this.c, exception);
            this.a();
        } finally {
            if (fileoutputstream != null) {
                try {
                    fileoutputstream.close();
                } catch (IOException ioexception) {
                    ;
                }
            }
        }
    }

    public File c() {
        return this.c;
    }

    public String getString(String s, String s1) {
        if (!this.properties.containsKey(s)) {
            this.properties.setProperty(s, s1);
            this.savePropertiesFile();
        }

        return this.properties.getProperty(s, s1);
    }

    public int getInt(String s, int i) {
        try {
            return Integer.parseInt(this.getString(s, "" + i));
        } catch (Exception exception) {
            this.properties.setProperty(s, "" + i);
            return i;
        }
    }

    public boolean getBoolean(String s, boolean flag) {
        try {
            return Boolean.parseBoolean(this.getString(s, "" + flag));
        } catch (Exception exception) {
            this.properties.setProperty(s, "" + flag);
            return flag;
        }
    }

    public void a(String s, Object object) {
        this.properties.setProperty(s, "" + object);
    }
}
