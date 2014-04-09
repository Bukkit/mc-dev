package net.minecraft.server;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandBanIp extends CommandAbstract {

    public static final Pattern a = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    public CommandBanIp() {}

    public String getCommand() {
        return "ban-ip";
    }

    public int a() {
        return 3;
    }

    public boolean canUse(ICommandListener icommandlistener) {
        return MinecraftServer.getServer().getPlayerList().getIPBans().isEnabled() && super.canUse(icommandlistener);
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.banip.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 1 && astring[0].length() > 1) {
            Matcher matcher = a.matcher(astring[0]);
            IChatBaseComponent ichatbasecomponent = null;

            if (astring.length >= 2) {
                ichatbasecomponent = a(icommandlistener, astring, 1);
            }

            if (matcher.matches()) {
                this.a(icommandlistener, astring[0], ichatbasecomponent == null ? null : ichatbasecomponent.c());
            } else {
                EntityPlayer entityplayer = MinecraftServer.getServer().getPlayerList().getPlayer(astring[0]);

                if (entityplayer == null) {
                    throw new ExceptionPlayerNotFound("commands.banip.invalid", new Object[0]);
                }

                this.a(icommandlistener, entityplayer.s(), ichatbasecomponent == null ? null : ichatbasecomponent.c());
            }
        } else {
            throw new ExceptionUsage("commands.banip.usage", new Object[0]);
        }
    }

    public List tabComplete(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }

    protected void a(ICommandListener icommandlistener, String s, String s1) {
        IpBanEntry ipbanentry = new IpBanEntry(s, (Date) null, icommandlistener.getName(), (Date) null, s1);

        MinecraftServer.getServer().getPlayerList().getIPBans().add(ipbanentry);
        List list = MinecraftServer.getServer().getPlayerList().b(s);
        String[] astring = new String[list.size()];
        int i = 0;

        EntityPlayer entityplayer;

        for (Iterator iterator = list.iterator(); iterator.hasNext(); astring[i++] = entityplayer.getName()) {
            entityplayer = (EntityPlayer) iterator.next();
            entityplayer.playerConnection.disconnect("You have been IP banned.");
        }

        if (list.isEmpty()) {
            a(icommandlistener, this, "commands.banip.success", new Object[] { s});
        } else {
            a(icommandlistener, this, "commands.banip.success.players", new Object[] { s, a(astring)});
        }
    }
}
