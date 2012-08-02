package net.minecraft.server;

import java.util.List;

public class CommandXp extends CommandAbstract {

    public CommandXp() {}

    public String b() {
        return "xp";
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.xp.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0) {
            int i = a(icommandlistener, astring[0], 0, 5000);
            EntityHuman entityhuman;

            if (astring.length > 1) {
                entityhuman = this.a(astring[1]);
            } else {
                entityhuman = c(icommandlistener);
            }

            entityhuman.giveExp(i);
            a(icommandlistener, "commands.xp.success", new Object[] { Integer.valueOf(i), entityhuman.getLocalizedName()});
        } else {
            throw new ExceptionUsage("commands.xp.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 2 ? a(astring, this.c()) : null;
    }

    protected EntityHuman a(String s) {
        EntityPlayer entityplayer = MinecraftServer.getServer().getServerConfigurationManager().f(s);

        if (entityplayer == null) {
            throw new ExceptionPlayerNotFound();
        } else {
            return entityplayer;
        }
    }

    protected String[] c() {
        return MinecraftServer.getServer().getPlayers();
    }
}
