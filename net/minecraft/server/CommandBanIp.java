package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandBanIp extends CommandAbstract {

    public static final Pattern a = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    public CommandBanIp() {}

    public String c() {
        return "ban-ip";
    }

    public int a() {
        return 3;
    }

    public boolean b(ICommandListener icommandlistener) {
        return MinecraftServer.getServer().getPlayerList().getIPBans().isEnabled() && super.b(icommandlistener);
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.banip.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 1 && astring[0].length() > 1) {
            Matcher matcher = a.matcher(astring[0]);
            String s = null;

            if (astring.length >= 2) {
                s = a(icommandlistener, astring, 1);
            }

            if (matcher.matches()) {
                this.a(icommandlistener, astring[0], s);
            } else {
                EntityPlayer entityplayer = MinecraftServer.getServer().getPlayerList().getPlayer(astring[0]);

                if (entityplayer == null) {
                    throw new ExceptionPlayerNotFound("commands.banip.invalid", new Object[0]);
                }

                this.a(icommandlistener, entityplayer.p(), s);
            }
        } else {
            throw new ExceptionUsage("commands.banip.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }

    protected void a(ICommandListener icommandlistener, String s, String s1) {
        BanEntry banentry = new BanEntry(s);

        banentry.setSource(icommandlistener.getName());
        if (s1 != null) {
            banentry.setReason(s1);
        }

        MinecraftServer.getServer().getPlayerList().getIPBans().add(banentry);
        List list = MinecraftServer.getServer().getPlayerList().j(s);
        String[] astring = new String[list.size()];
        int i = 0;

        EntityPlayer entityplayer;

        for (Iterator iterator = list.iterator(); iterator.hasNext(); astring[i++] = entityplayer.getLocalizedName()) {
            entityplayer = (EntityPlayer) iterator.next();
            entityplayer.playerConnection.disconnect("You have been IP banned.");
        }

        if (list.isEmpty()) {
            a(icommandlistener, "commands.banip.success", new Object[] { s});
        } else {
            a(icommandlistener, "commands.banip.success.players", new Object[] { s, a(astring)});
        }
    }
}
