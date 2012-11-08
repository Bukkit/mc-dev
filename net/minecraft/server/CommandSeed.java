package net.minecraft.server;

public class CommandSeed extends CommandAbstract {

    public CommandSeed() {}

    public boolean b(ICommandListener icommandlistener) {
        return MinecraftServer.getServer().I() || super.b(icommandlistener);
    }

    public String c() {
        return "seed";
    }

    public int a() {
        return 2;
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        Object object = icommandlistener instanceof EntityHuman ? ((EntityHuman) icommandlistener).world : MinecraftServer.getServer().getWorldServer(0);

        icommandlistener.sendMessage("Seed: " + ((World) object).getSeed());
    }
}
