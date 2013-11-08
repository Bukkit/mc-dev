package net.minecraft.server;

import java.util.List;

public class CommandXp extends CommandAbstract {

    public CommandXp() {}

    public String c() {
        return "xp";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.xp.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length <= 0) {
            throw new ExceptionUsage("commands.xp.usage", new Object[0]);
        } else {
            String s = astring[0];
            boolean flag = s.endsWith("l") || s.endsWith("L");

            if (flag && s.length() > 1) {
                s = s.substring(0, s.length() - 1);
            }

            int i = a(icommandlistener, s);
            boolean flag1 = i < 0;

            if (flag1) {
                i *= -1;
            }

            EntityPlayer entityplayer;

            if (astring.length > 1) {
                entityplayer = d(icommandlistener, astring[1]);
            } else {
                entityplayer = b(icommandlistener);
            }

            if (flag) {
                if (flag1) {
                    entityplayer.levelDown(-i);
                    a(icommandlistener, "commands.xp.success.negative.levels", new Object[] { Integer.valueOf(i), entityplayer.getName()});
                } else {
                    entityplayer.levelDown(i);
                    a(icommandlistener, "commands.xp.success.levels", new Object[] { Integer.valueOf(i), entityplayer.getName()});
                }
            } else {
                if (flag1) {
                    throw new ExceptionUsage("commands.xp.failure.widthdrawXp", new Object[0]);
                }

                entityplayer.giveExp(i);
                a(icommandlistener, "commands.xp.success", new Object[] { Integer.valueOf(i), entityplayer.getName()});
            }
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 2 ? a(astring, this.d()) : null;
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    public boolean a(String[] astring, int i) {
        return i == 1;
    }
}
