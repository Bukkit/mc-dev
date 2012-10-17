package net.minecraft.server;

public class CommandSaveOn extends CommandAbstract {

    public CommandSaveOn() {}

    public String c() {
        return "save-on";
    }

    public int a() {
        return 4;
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        MinecraftServer minecraftserver = MinecraftServer.getServer();

        for (int i = 0; i < minecraftserver.worldServer.length; ++i) {
            if (minecraftserver.worldServer[i] != null) {
                WorldServer worldserver = minecraftserver.worldServer[i];

                worldserver.savingDisabled = false;
            }
        }

        a(icommandlistener, "commands.save.enabled", new Object[0]);
    }
}
