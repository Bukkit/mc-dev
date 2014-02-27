package net.minecraft.server;

public class CommandPublish extends CommandAbstract {

    public CommandPublish() {}

    public String getCommand() {
        return "publish";
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.publish.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        String s = MinecraftServer.getServer().a(EnumGamemode.SURVIVAL, false);

        if (s != null) {
            a(icommandlistener, this, "commands.publish.started", new Object[] { s});
        } else {
            a(icommandlistener, this, "commands.publish.failed", new Object[0]);
        }
    }
}
