package net.minecraft.server;

import java.util.List;

public class CommandSay extends CommandAbstract {

    public CommandSay() {}

    public String b() {
        return "say";
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.say.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0 && astring[0].length() > 0) {
            String s = a(astring, 0);

            MinecraftServer.getServer().getServerConfigurationManager().sendAll(new Packet3Chat(String.format("[%s] %s", new Object[] { icommandlistener.getName(), s})));
        } else {
            throw new ExceptionUsage("commands.say.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length >= 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }
}
