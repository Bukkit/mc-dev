package net.minecraft.server;

public class CommandStop extends CommandAbstract {

    public CommandStop() {}

    public String c() {
        return "stop";
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.stop.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        a(icommandlistener, "commands.stop.start", new Object[0]);
        MinecraftServer.getServer().safeShutdown();
    }
}
