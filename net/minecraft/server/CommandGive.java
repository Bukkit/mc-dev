package net.minecraft.server;

import java.util.List;

public class CommandGive extends CommandAbstract {

    public CommandGive() {}

    public String c() {
        return "give";
    }

    public int a() {
        return 2;
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.give.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 2) {
            EntityPlayer entityplayer = c(icommandlistener, astring[0]);
            int i = a(icommandlistener, astring[1], 1);
            int j = 1;
            int k = 0;

            if (Item.byId[i] == null) {
                throw new ExceptionInvalidNumber("commands.give.notFound", new Object[] { Integer.valueOf(i)});
            } else {
                if (astring.length >= 3) {
                    j = a(icommandlistener, astring[2], 1, 64);
                }

                if (astring.length >= 4) {
                    k = a(icommandlistener, astring[3]);
                }

                ItemStack itemstack = new ItemStack(i, j, k);
                EntityItem entityitem = entityplayer.drop(itemstack);

                entityitem.pickupDelay = 0;
                a(icommandlistener, "commands.give.success", new Object[] { Item.byId[i].k(itemstack), Integer.valueOf(i), Integer.valueOf(j), entityplayer.getLocalizedName()});
            }
        } else {
            throw new ExceptionUsage("commands.give.usage", new Object[0]);
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
