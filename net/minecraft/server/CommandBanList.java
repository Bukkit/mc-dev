package net.minecraft.server;

import java.util.List;

public class CommandBanList extends CommandAbstract {

    public CommandBanList() {}

    public String c() {
        return "banlist";
    }

    public int a() {
        return 3;
    }

    public boolean b(ICommandListener icommandlistener) {
        return (MinecraftServer.getServer().getPlayerList().getIPBans().isEnabled() || MinecraftServer.getServer().getPlayerList().getNameBans().isEnabled()) && super.b(icommandlistener);
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.banlist.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 1 && astring[0].equalsIgnoreCase("ips")) {
            icommandlistener.sendMessage(icommandlistener.a("commands.banlist.ips", new Object[] { Integer.valueOf(MinecraftServer.getServer().getPlayerList().getIPBans().getEntries().size())}));
            icommandlistener.sendMessage(a(MinecraftServer.getServer().getPlayerList().getIPBans().getEntries().keySet().toArray()));
        } else {
            icommandlistener.sendMessage(icommandlistener.a("commands.banlist.players", new Object[] { Integer.valueOf(MinecraftServer.getServer().getPlayerList().getNameBans().getEntries().size())}));
            icommandlistener.sendMessage(a(MinecraftServer.getServer().getPlayerList().getNameBans().getEntries().keySet().toArray()));
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "players", "ips"}) : null;
    }
}
