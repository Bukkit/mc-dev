package net.minecraft.server;

import java.util.List;

public class CommandClear extends CommandAbstract {

    public CommandClear() {}

    public String c() {
        return "clear";
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.clear.usage";
    }

    public int a() {
        return 2;
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        EntityPlayer entityplayer = astring.length == 0 ? b(icommandlistener) : d(icommandlistener, astring[0]);
        int i = astring.length >= 2 ? a(icommandlistener, astring[1], 1) : -1;
        int j = astring.length >= 3 ? a(icommandlistener, astring[2], 0) : -1;
        int k = entityplayer.inventory.b(i, j);

        entityplayer.defaultContainer.b();
        if (!entityplayer.abilities.canInstantlyBuild) {
            entityplayer.broadcastCarriedItem();
        }

        if (k == 0) {
            throw new CommandException("commands.clear.failure", new Object[] { entityplayer.getLocalizedName()});
        } else {
            a(icommandlistener, "commands.clear.success", new Object[] { entityplayer.getLocalizedName(), Integer.valueOf(k)});
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, this.d()) : null;
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    public boolean a(String[] astring, int i) {
        return i == 0;
    }
}
