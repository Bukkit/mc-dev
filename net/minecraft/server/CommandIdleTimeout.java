package net.minecraft.server;

public class CommandIdleTimeout extends CommandAbstract {

    public CommandIdleTimeout() {}

    public String getCommand() {
        return "setidletimeout";
    }

    public int a() {
        return 3;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.setidletimeout.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        if (astring.length == 1) {
            int i = a(icommandlistener, astring[0], 0);

            MinecraftServer.getServer().setIdleTimeout(i);
            a(icommandlistener, this, "commands.setidletimeout.success", new Object[] { Integer.valueOf(i)});
        } else {
            throw new ExceptionUsage("commands.setidletimeout.usage", new Object[0]);
        }
    }
}
