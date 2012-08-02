package net.minecraft.server;

import java.util.List;

public class CommandGive extends CommandAbstract {

    public CommandGive() {}

    public String b() {
        return "give";
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.give.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 2) {
            EntityHuman entityhuman = this.a(astring[0]);
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

                entityhuman.drop(itemstack);
                a(icommandlistener, "commands.give.success", new Object[] { Item.byId[i].i(itemstack), Integer.valueOf(i), Integer.valueOf(j), entityhuman.getLocalizedName()});
            }
        } else {
            throw new ExceptionUsage("commands.give.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, this.c()) : null;
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
