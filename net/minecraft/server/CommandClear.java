package net.minecraft.server;

import java.util.List;

public class CommandClear extends CommandAbstract {

    public CommandClear() {}

    public String getCommand() {
        return "clear";
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.clear.usage";
    }

    public int a() {
        return 2;
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        EntityPlayer entityplayer = astring.length == 0 ? b(icommandlistener) : d(icommandlistener, astring[0]);
        Item item = astring.length >= 2 ? f(icommandlistener, astring[1]) : null;
        int i = astring.length >= 3 ? a(icommandlistener, astring[2], 0) : -1;

        if (astring.length >= 2 && item == null) {
            throw new CommandException("commands.clear.failure", new Object[] { entityplayer.getName()});
        } else {
            int j = entityplayer.inventory.a(item, i);

            entityplayer.defaultContainer.b();
            if (!entityplayer.abilities.canInstantlyBuild) {
                entityplayer.broadcastCarriedItem();
            }

            if (j == 0) {
                throw new CommandException("commands.clear.failure", new Object[] { entityplayer.getName()});
            } else {
                a(icommandlistener, this, "commands.clear.success", new Object[] { entityplayer.getName(), Integer.valueOf(j)});
            }
        }
    }

    public List tabComplete(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, this.d()) : (astring.length == 2 ? a(astring, Item.REGISTRY.keySet()) : null);
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 0;
    }
}
