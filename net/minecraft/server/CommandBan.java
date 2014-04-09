package net.minecraft.server;

import java.util.Date;
import java.util.List;

import net.minecraft.util.com.mojang.authlib.GameProfile;

public class CommandBan extends CommandAbstract {

    public CommandBan() {}

    public String getCommand() {
        return "ban";
    }

    public int a() {
        return 3;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.ban.usage";
    }

    public boolean canUse(ICommandListener icommandlistener) {
        return MinecraftServer.getServer().getPlayerList().getProfileBans().isEnabled() && super.canUse(icommandlistener);
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 1 && astring[0].length() > 0) {
            MinecraftServer minecraftserver = MinecraftServer.getServer();
            GameProfile gameprofile = minecraftserver.getUserCache().a(astring[0]);

            if (gameprofile == null) {
                throw new CommandException("commands.ban.failed", new Object[] { astring[0]});
            } else {
                String s = null;

                if (astring.length >= 2) {
                    s = a(icommandlistener, astring, 1).c();
                }

                GameProfileBanEntry gameprofilebanentry = new GameProfileBanEntry(gameprofile, (Date) null, icommandlistener.getName(), (Date) null, s);

                minecraftserver.getPlayerList().getProfileBans().add(gameprofilebanentry);
                EntityPlayer entityplayer = minecraftserver.getPlayerList().getPlayer(astring[0]);

                if (entityplayer != null) {
                    entityplayer.playerConnection.disconnect("You are banned from this server.");
                }

                a(icommandlistener, this, "commands.ban.success", new Object[] { astring[0]});
            }
        } else {
            throw new ExceptionUsage("commands.ban.usage", new Object[0]);
        }
    }

    public List tabComplete(ICommandListener icommandlistener, String[] astring) {
        return astring.length >= 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }
}
