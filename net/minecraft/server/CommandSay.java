package net.minecraft.server;

import java.util.List;

public class CommandSay extends CommandAbstract {

    public CommandSay() {}

    public String c() {
        return "say";
    }

    public int a() {
        return 1;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.say.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0 && astring[0].length() > 0) {
            IChatBaseComponent ichatbasecomponent = a(icommandlistener, astring, 0, true);

            MinecraftServer.getServer().getPlayerList().sendMessage(new ChatMessage("chat.type.announcement", new Object[] { icommandlistener.getName(), ichatbasecomponent}));
        } else {
            throw new ExceptionUsage("commands.say.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length >= 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }
}
