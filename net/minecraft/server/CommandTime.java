package net.minecraft.server;

import java.util.List;

public class CommandTime extends CommandAbstract {

    public CommandTime() {}

    public String c() {
        return "time";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.time.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 1) {
            int i;

            if (astring[0].equals("set")) {
                if (astring[1].equals("day")) {
                    i = 1000;
                } else if (astring[1].equals("night")) {
                    i = 13000;
                } else {
                    i = a(icommandlistener, astring[1], 0);
                }

                this.a(icommandlistener, i);
                a(icommandlistener, "commands.time.set", new Object[] { Integer.valueOf(i)});
                return;
            }

            if (astring[0].equals("add")) {
                i = a(icommandlistener, astring[1], 0);
                this.b(icommandlistener, i);
                a(icommandlistener, "commands.time.added", new Object[] { Integer.valueOf(i)});
                return;
            }
        }

        throw new ExceptionUsage("commands.time.usage", new Object[0]);
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "set", "add"}) : (astring.length == 2 && astring[0].equals("set") ? a(astring, new String[] { "day", "night"}) : null);
    }

    protected void a(ICommandListener icommandlistener, int i) {
        for (int j = 0; j < MinecraftServer.getServer().worldServer.length; ++j) {
            MinecraftServer.getServer().worldServer[j].setDayTime((long) i);
        }
    }

    protected void b(ICommandListener icommandlistener, int i) {
        for (int j = 0; j < MinecraftServer.getServer().worldServer.length; ++j) {
            WorldServer worldserver = MinecraftServer.getServer().worldServer[j];

            worldserver.setDayTime(worldserver.getDayTime() + (long) i);
        }
    }
}
