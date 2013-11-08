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

    public boolean a(ICommandListener icommandlistener) {
        return (MinecraftServer.getServer().getPlayerList().getIPBans().isEnabled() || MinecraftServer.getServer().getPlayerList().getNameBans().isEnabled()) && super.a(icommandlistener);
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.banlist.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 1 && astring[0].equalsIgnoreCase("ips")) {
            icommandlistener.sendMessage(new ChatMessage("commands.banlist.ips", new Object[] { Integer.valueOf(MinecraftServer.getServer().getPlayerList().getIPBans().getEntries().size())}));
            icommandlistener.sendMessage(new ChatComponentText(a(MinecraftServer.getServer().getPlayerList().getIPBans().getEntries().keySet().toArray())));
        } else {
            icommandlistener.sendMessage(new ChatMessage("commands.banlist.players", new Object[] { Integer.valueOf(MinecraftServer.getServer().getPlayerList().getNameBans().getEntries().size())}));
            icommandlistener.sendMessage(new ChatComponentText(a(MinecraftServer.getServer().getPlayerList().getNameBans().getEntries().keySet().toArray())));
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "players", "ips"}) : null;
    }
}
