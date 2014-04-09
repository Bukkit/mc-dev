package net.minecraft.server;

import java.util.List;

public class CommandBanList extends CommandAbstract {

    public CommandBanList() {}

    public String getCommand() {
        return "banlist";
    }

    public int a() {
        return 3;
    }

    public boolean canUse(ICommandListener icommandlistener) {
        return (MinecraftServer.getServer().getPlayerList().getIPBans().isEnabled() || MinecraftServer.getServer().getPlayerList().getProfileBans().isEnabled()) && super.canUse(icommandlistener);
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.banlist.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 1 && astring[0].equalsIgnoreCase("ips")) {
            icommandlistener.sendMessage(new ChatMessage("commands.banlist.ips", new Object[] { Integer.valueOf(MinecraftServer.getServer().getPlayerList().getIPBans().getEntries().length)}));
            icommandlistener.sendMessage(new ChatComponentText(a(MinecraftServer.getServer().getPlayerList().getIPBans().getEntries())));
        } else {
            icommandlistener.sendMessage(new ChatMessage("commands.banlist.players", new Object[] { Integer.valueOf(MinecraftServer.getServer().getPlayerList().getProfileBans().getEntries().length)}));
            icommandlistener.sendMessage(new ChatComponentText(a(MinecraftServer.getServer().getPlayerList().getProfileBans().getEntries())));
        }
    }

    public List tabComplete(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "players", "ips"}) : null;
    }
}
