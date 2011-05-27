package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;

public class PlayerNBTManager implements PlayerFileData, IDataManager {

    private static final Logger a = Logger.getLogger("Minecraft");
    private final File b;
    private final File c;
    private final long d = System.currentTimeMillis();

    public PlayerNBTManager(File file1, String s, boolean flag) {
        this.b = new File(file1, s);
        this.b.mkdirs();
        this.c = new File(this.b, "players");
        if (flag) {
            this.c.mkdirs();
        }

        this.f();
    }

    private void f() {
        try {
            File file1 = new File(this.b, "session.lock");
            DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(file1));

            try {
                dataoutputstream.writeLong(this.d);
            } finally {
                dataoutputstream.close();
            }
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
            throw new RuntimeException("Failed to check session lock, aborting");
        }
    }

    protected File a() {
        return this.b;
    }

    public void b() {
        try {
            File file1 = new File(this.b, "session.lock");
            DataInputStream datainputstream = new DataInputStream(new FileInputStream(file1));

            try {
                if (datainputstream.readLong() != this.d) {
                    throw new MinecraftException("The save is being accessed from another location, aborting");
                }
            } finally {
                datainputstream.close();
            }
        } catch (IOException ioexception) {
            throw new MinecraftException("Failed to check session lock, aborting");
        }
    }

    public IChunkLoader a(WorldProvider worldprovider) {
        if (worldprovider instanceof WorldProviderHell) {
            File file1 = new File(this.b, "DIM-1");

            file1.mkdirs();
            return new ChunkLoader(file1, true);
        } else {
            return new ChunkLoader(this.b, true);
        }
    }

    public WorldData c() {
        File file1 = new File(this.b, "level.dat");

        if (file1.exists()) {
            try {
                NBTTagCompound nbttagcompound = CompressedStreamTools.a((InputStream) (new FileInputStream(file1)));
                NBTTagCompound nbttagcompound1 = nbttagcompound.k("Data");

                return new WorldData(nbttagcompound1);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return null;
    }

    public void a(WorldData worlddata, List list) {
        NBTTagCompound nbttagcompound = worlddata.a(list);
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();

        nbttagcompound1.a("Data", (NBTBase) nbttagcompound);

        try {
            File file1 = new File(this.b, "level.dat_new");
            File file2 = new File(this.b, "level.dat_old");
            File file3 = new File(this.b, "level.dat");

            CompressedStreamTools.a(nbttagcompound1, (OutputStream) (new FileOutputStream(file1)));
            if (file2.exists()) {
                file2.delete();
            }

            file3.renameTo(file2);
            if (file3.exists()) {
                file3.delete();
            }

            file1.renameTo(file3);
            if (file1.exists()) {
                file1.delete();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void a(WorldData worlddata) {
        NBTTagCompound nbttagcompound = worlddata.a();
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();

        nbttagcompound1.a("Data", (NBTBase) nbttagcompound);

        try {
            File file1 = new File(this.b, "level.dat_new");
            File file2 = new File(this.b, "level.dat_old");
            File file3 = new File(this.b, "level.dat");

            CompressedStreamTools.a(nbttagcompound1, (OutputStream) (new FileOutputStream(file1)));
            if (file2.exists()) {
                file2.delete();
            }

            file3.renameTo(file2);
            if (file3.exists()) {
                file3.delete();
            }

            file1.renameTo(file3);
            if (file1.exists()) {
                file1.delete();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void a(EntityHuman entityhuman) {
        try {
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            entityhuman.d(nbttagcompound);
            File file1 = new File(this.c, "_tmp_.dat");
            File file2 = new File(this.c, entityhuman.name + ".dat");

            CompressedStreamTools.a(nbttagcompound, (OutputStream) (new FileOutputStream(file1)));
            if (file2.exists()) {
                file2.delete();
            }

            file1.renameTo(file2);
        } catch (Exception exception) {
            a.warning("Failed to save player data for " + entityhuman.name);
        }
    }

    public void b(EntityHuman entityhuman) {
        NBTTagCompound nbttagcompound = this.a(entityhuman.name);

        if (nbttagcompound != null) {
            entityhuman.e(nbttagcompound);
        }
    }

    public NBTTagCompound a(String s) {
        try {
            File file1 = new File(this.c, s + ".dat");

            if (file1.exists()) {
                return CompressedStreamTools.a((InputStream) (new FileInputStream(file1)));
            }
        } catch (Exception exception) {
            a.warning("Failed to load player data for " + s);
        }

        return null;
    }

    public PlayerFileData d() {
        return this;
    }

    public void e() {}
}
