package net.minecraft.server;

import java.util.List;

public class CommandMe extends CommandAbstract {

    public CommandMe() {}

    public String c() {
        return "me";
    }

    public int a() {
        return 0;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.me.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0) {
            IChatBaseComponent ichatbasecomponent = a(icommandlistener, astring, 0, icommandlistener.a(1, "me"));

            MinecraftServer.getServer().getPlayerList().sendMessage(new ChatMessage("chat.type.emote", new Object[] { icommandlistener.getScoreboardDisplayName(), ichatbasecomponent}));
        } else {
            throw new ExceptionUsage("commands.me.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return a(astring, MinecraftServer.getServer().getPlayers());
    }
}
