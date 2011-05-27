package net.minecraft.server;

public class ConvertProgressUpdater implements IProgressUpdate {

    private long b;

    final MinecraftServer a;

    public ConvertProgressUpdater(MinecraftServer minecraftserver) {
        this.a = minecraftserver;
        this.b = System.currentTimeMillis();
    }

    public void a(String s) {}

    public void a(int i) {
        if (System.currentTimeMillis() - this.b >= 1000L) {
            this.b = System.currentTimeMillis();
            MinecraftServer.log.info("Converting... " + i + "%");
        }
    }

    public void b(String s) {}
}
