package net.minecraft.server;

import java.util.Arrays;
import java.util.List;

public class CommandTell extends CommandAbstract {

    public CommandTell() {}

    public boolean b(ICommandListener icommandlistener) {
        return (!MinecraftServer.getServer().H() || MinecraftServer.getServer().x() > 1) && super.b(icommandlistener);
    }

    public List a() {
        return Arrays.asList(new String[] { "w", "msg"});
    }

    public String b() {
        return "tell";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 2) {
            throw new ExceptionUsage("commands.message.usage", new Object[0]);
        } else {
            EntityPlayer entityplayer = MinecraftServer.getServer().getServerConfigurationManager().f(astring[0]);

            if (entityplayer == null) {
                throw new ExceptionPlayerNotFound();
            } else if (entityplayer == icommandlistener) {
                throw new ExceptionPlayerNotFound("commands.message.sameTarget", new Object[0]);
            } else {
                String s = a(astring, 1);

                entityplayer.sendMessage("\u00A77\u00A7o" + entityplayer.a("commands.message.display.incoming", new Object[] { icommandlistener.getName(), s}));
                icommandlistener.sendMessage("\u00A77\u00A7o" + icommandlistener.a("commands.message.display.outgoing", new Object[] { entityplayer.getName(), s}));
            }
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return a(astring, MinecraftServer.getServer().getPlayers());
    }
}
