package net.minecraft.server;

import java.util.List;

public class CommandDeop extends CommandAbstract {

    public CommandDeop() {}

    public String b() {
        return "deop";
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.deop.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length == 1 && astring[0].length() > 0) {
            MinecraftServer.getServer().getServerConfigurationManager().removeOp(astring[0]);
            a(icommandlistener, "commands.deop.success", new Object[] { astring[0]});
        } else {
            throw new ExceptionUsage("commands.deop.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, MinecraftServer.getServer().getServerConfigurationManager().getOPs()) : null;
    }
}
