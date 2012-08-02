package net.minecraft.server;

import java.util.List;

public class CommandMe extends CommandAbstract {

    public CommandMe() {}

    public String b() {
        return "me";
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.me.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0) {
            String s = a(astring, 0);

            MinecraftServer.getServer().getServerConfigurationManager().sendAll(new Packet3Chat("* " + icommandlistener.getName() + " " + s));
        } else {
            throw new ExceptionUsage("commands.me.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return a(astring, MinecraftServer.getServer().getPlayers());
    }
}
