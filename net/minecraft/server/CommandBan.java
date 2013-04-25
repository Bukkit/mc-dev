package net.minecraft.server;

import java.util.List;

public class CommandBan extends CommandAbstract {

    public CommandBan() {}

    public String c() {
        return "ban";
    }

    public int a() {
        return 3;
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.ban.usage", new Object[0]);
    }

    public boolean b(ICommandListener icommandlistener) {
        return MinecraftServer.getServer().getPlayerList().getNameBans().isEnabled() && super.b(icommandlistener);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 1 && astring[0].length() > 0) {
            EntityPlayer entityplayer = MinecraftServer.getServer().getPlayerList().getPlayer(astring[0]);
            BanEntry banentry = new BanEntry(astring[0]);

            banentry.setSource(icommandlistener.getName());
            if (astring.length >= 2) {
                banentry.setReason(a(icommandlistener, astring, 1));
            }

            MinecraftServer.getServer().getPlayerList().getNameBans().add(banentry);
            if (entityplayer != null) {
                entityplayer.playerConnection.disconnect("You are banned from this server.");
            }

            a(icommandlistener, "commands.ban.success", new Object[] { astring[0]});
        } else {
            throw new ExceptionUsage("commands.ban.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length >= 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }
}
