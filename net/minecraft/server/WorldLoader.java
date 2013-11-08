package net.minecraft.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldLoader implements Convertable {

    private static final Logger b = LogManager.getLogger();
    protected final File a;

    public WorldLoader(File file1) {
        if (!file1.exists()) {
            file1.mkdirs();
        }

        this.a = file1;
    }

    public void d() {}

    public WorldData c(String s) {
        File file1 = new File(this.a, s);

        if (!file1.exists()) {
            return null;
        } else {
            File file2 = new File(file1, "level.dat");
            NBTTagCompound nbttagcompound;
            NBTTagCompound nbttagcompound1;

            if (file2.exists()) {
                try {
                    nbttagcompound = NBTCompressedStreamTools.a((InputStream) (new FileInputStream(file2)));
                    nbttagcompound1 = nbttagcompound.getCompound("Data");
                    return new WorldData(nbttagcompound1);
                } catch (Exception exception) {
                    b.error("Exception reading " + file2, exception);
                }
            }

            file2 = new File(file1, "level.dat_old");
            if (file2.exists()) {
                try {
                    nbttagcompound = NBTCompressedStreamTools.a((InputStream) (new FileInputStream(file2)));
                    nbttagcompound1 = nbttagcompound.getCompound("Data");
                    return new WorldData(nbttagcompound1);
                } catch (Exception exception1) {
                    b.error("Exception reading " + file2, exception1);
                }
            }

            return null;
        }
    }

    public boolean e(String s) {
        File file1 = new File(this.a, s);

        if (!file1.exists()) {
            return true;
        } else {
            b.info("Deleting level " + s);

            for (int i = 1; i <= 5; ++i) {
                b.info("Attempt " + i + "...");
                if (a(file1.listFiles())) {
                    break;
                }

                b.warn("Unsuccessful in deleting contents.");
                if (i < 5) {
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException interruptedexception) {
                        ;
                    }
                }
            }

            return file1.delete();
        }
    }

    protected static boolean a(File[] afile) {
        for (int i = 0; i < afile.length; ++i) {
            File file1 = afile[i];

            b.debug("Deleting " + file1);
            if (file1.isDirectory() && !a(file1.listFiles())) {
                b.warn("Couldn\'t delete directory " + file1);
                return false;
            }

            if (!file1.delete()) {
                b.warn("Couldn\'t delete file " + file1);
                return false;
            }
        }

        return true;
    }

    public IDataManager a(String s, boolean flag) {
        return new WorldNBTStorage(this.a, s, flag);
    }

    public boolean isConvertable(String s) {
        return false;
    }

    public boolean convert(String s, IProgressUpdate iprogressupdate) {
        return false;
    }
}
