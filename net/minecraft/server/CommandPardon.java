package net.minecraft.server;

import java.util.List;

public class CommandPardon extends CommandAbstract {

    public CommandPardon() {}

    public String c() {
        return "pardon";
    }

    public int a() {
        return 3;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.unban.usage";
    }

    public boolean a(ICommandListener icommandlistener) {
        return MinecraftServer.getServer().getPlayerList().getNameBans().isEnabled() && super.a(icommandlistener);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length == 1 && astring[0].length() > 0) {
            MinecraftServer.getServer().getPlayerList().getNameBans().remove(astring[0]);
            a(icommandlistener, "commands.unban.success", new Object[] { astring[0]});
        } else {
            throw new ExceptionUsage("commands.unban.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, MinecraftServer.getServer().getPlayerList().getNameBans().getEntries().keySet()) : null;
    }
}
