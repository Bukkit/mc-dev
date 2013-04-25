package net.minecraft.server;

public class CommandSaveAll extends CommandAbstract {

    public CommandSaveAll() {}

    public String c() {
        return "save-all";
    }

    public int a() {
        return 4;
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        MinecraftServer minecraftserver = MinecraftServer.getServer();

        icommandlistener.sendMessage(icommandlistener.a("commands.save.start", new Object[0]));
        if (minecraftserver.getPlayerList() != null) {
            minecraftserver.getPlayerList().savePlayers();
        }

        try {
            int i;
            WorldServer worldserver;
            boolean flag;

            for (i = 0; i < minecraftserver.worldServer.length; ++i) {
                if (minecraftserver.worldServer[i] != null) {
                    worldserver = minecraftserver.worldServer[i];
                    flag = worldserver.savingDisabled;
                    worldserver.savingDisabled = false;
                    worldserver.save(true, (IProgressUpdate) null);
                    worldserver.savingDisabled = flag;
                }
            }

            if (astring.length > 0 && "flush".equals(astring[0])) {
                icommandlistener.sendMessage(icommandlistener.a("commands.save.flushStart", new Object[0]));

                for (i = 0; i < minecraftserver.worldServer.length; ++i) {
                    if (minecraftserver.worldServer[i] != null) {
                        worldserver = minecraftserver.worldServer[i];
                        flag = worldserver.savingDisabled;
                        worldserver.savingDisabled = false;
                        worldserver.flushSave();
                        worldserver.savingDisabled = flag;
                    }
                }

                icommandlistener.sendMessage(icommandlistener.a("commands.save.flushEnd", new Object[0]));
            }
        } catch (ExceptionWorldConflict exceptionworldconflict) {
            a(icommandlistener, "commands.save.failed", new Object[] { exceptionworldconflict.getMessage()});
            return;
        }

        a(icommandlistener, "commands.save.success", new Object[0]);
    }
}
