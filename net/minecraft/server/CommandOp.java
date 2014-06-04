package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.com.mojang.authlib.GameProfile;

public class CommandOp extends CommandAbstract {

    public CommandOp() {}

    public String getCommand() {
        return "op";
    }

    public int a() {
        return 3;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.op.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        if (astring.length == 1 && astring[0].length() > 0) {
            MinecraftServer minecraftserver = MinecraftServer.getServer();
            GameProfile gameprofile = minecraftserver.getUserCache().getProfile(astring[0]);

            if (gameprofile == null) {
                throw new CommandException("commands.op.failed", new Object[] { astring[0]});
            } else {
                minecraftserver.getPlayerList().addOp(gameprofile);
                a(icommandlistener, this, "commands.op.success", new Object[] { astring[0]});
            }
        } else {
            throw new ExceptionUsage("commands.op.usage", new Object[0]);
        }
    }

    public List tabComplete(ICommandListener icommandlistener, String[] astring) {
        if (astring.length == 1) {
            String s = astring[astring.length - 1];
            ArrayList arraylist = new ArrayList();
            GameProfile[] agameprofile = MinecraftServer.getServer().F();
            int i = agameprofile.length;

            for (int j = 0; j < i; ++j) {
                GameProfile gameprofile = agameprofile[j];

                if (!MinecraftServer.getServer().getPlayerList().isOp(gameprofile) && a(s, gameprofile.getName())) {
                    arraylist.add(gameprofile.getName());
                }
            }

            return arraylist;
        } else {
            return null;
        }
    }
}
