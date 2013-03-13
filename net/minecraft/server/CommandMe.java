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

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.me.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0) {
            String s = a(icommandlistener, astring, 0, icommandlistener.a(1, "me"));

            MinecraftServer.getServer().getPlayerList().sendAll(new Packet3Chat("* " + icommandlistener.getName() + " " + s));
        } else {
            throw new ExceptionUsage("commands.me.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return a(astring, MinecraftServer.getServer().getPlayers());
    }
}
