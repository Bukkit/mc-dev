package net.minecraft.server;

import java.util.List;

public class CommandKick extends CommandAbstract {

    public CommandKick() {}

    public String c() {
        return "kick";
    }

    public int a() {
        return 3;
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.kick.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0 && astring[0].length() > 1) {
            EntityPlayer entityplayer = MinecraftServer.getServer().getPlayerList().getPlayer(astring[0]);
            String s = "Kicked by an operator.";
            boolean flag = false;

            if (entityplayer == null) {
                throw new ExceptionPlayerNotFound();
            } else {
                if (astring.length >= 2) {
                    s = a(icommandlistener, astring, 1);
                    flag = true;
                }

                entityplayer.playerConnection.disconnect(s);
                if (flag) {
                    a(icommandlistener, "commands.kick.success.reason", new Object[] { entityplayer.getLocalizedName(), s});
                } else {
                    a(icommandlistener, "commands.kick.success", new Object[] { entityplayer.getLocalizedName()});
                }
            }
        } else {
            throw new ExceptionUsage("commands.kick.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length >= 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }
}
