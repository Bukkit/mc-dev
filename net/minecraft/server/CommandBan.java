package net.minecraft.server;

import java.util.List;

public class CommandBan extends CommandAbstract {

    public CommandBan() {}

    public String b() {
        return "ban";
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.ban.usage", new Object[0]);
    }

    public boolean b(ICommandListener icommandlistener) {
        return MinecraftServer.getServer().getServerConfigurationManager().getNameBans().isEnabled() && super.b(icommandlistener);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 1 && astring[0].length() > 0) {
            EntityPlayer entityplayer = MinecraftServer.getServer().getServerConfigurationManager().f(astring[0]);
            BanEntry banentry = new BanEntry(astring[0]);

            banentry.setSource(icommandlistener.getName());
            if (astring.length >= 2) {
                banentry.setReason(a(astring, 1));
            }

            MinecraftServer.getServer().getServerConfigurationManager().getNameBans().add(banentry);
            if (entityplayer != null) {
                entityplayer.netServerHandler.disconnect("You are banned from this server.");
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
