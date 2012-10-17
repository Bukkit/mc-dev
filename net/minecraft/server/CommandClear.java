package net.minecraft.server;

import java.util.List;

public class CommandClear extends CommandAbstract {

    public CommandClear() {}

    public String c() {
        return "clear";
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.clear.usage", new Object[0]);
    }

    public int a() {
        return 2;
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        EntityPlayer entityplayer = astring.length == 0 ? c(icommandlistener) : c(icommandlistener, astring[0]);
        int i = astring.length >= 2 ? a(icommandlistener, astring[1], 1) : -1;
        int j = astring.length >= 3 ? a(icommandlistener, astring[2], 0) : -1;
        int k = entityplayer.inventory.b(i, j);

        entityplayer.defaultContainer.b();
        a(icommandlistener, "commands.clear.success", new Object[] { entityplayer.getLocalizedName(), Integer.valueOf(k)});
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, this.d()) : null;
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    public boolean a(int i) {
        return i == 0;
    }
}
