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

    public String c(ICommandListener icommandlistener) {
        return "commands.give.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 2) {
            throw new ExceptionUsage("commands.give.usage", new Object[0]);
        } else {
            EntityPlayer entityplayer = d(icommandlistener, astring[0]);
            Item item = f(icommandlistener, astring[1]);
            int i = 1;
            int j = 0;

            if (astring.length >= 3) {
                i = a(icommandlistener, astring[2], 1, 64);
            }

            if (astring.length >= 4) {
                j = a(icommandlistener, astring[3]);
            }

            ItemStack itemstack = new ItemStack(item, i, j);

            if (astring.length >= 5) {
                String s = a(icommandlistener, astring, 4).c();

                try {
                    NBTBase nbtbase = MojangsonParser.a(s);

                    if (!(nbtbase instanceof NBTTagCompound)) {
                        a(icommandlistener, "commands.give.tagError", new Object[] { "Not a valid tag"});
                        return;
                    }

                    itemstack.setTag((NBTTagCompound) nbtbase);
                } catch (MojangsonParseException mojangsonparseexception) {
                    a(icommandlistener, "commands.give.tagError", new Object[] { mojangsonparseexception.getMessage()});
                    return;
                }
            }

            EntityItem entityitem = entityplayer.drop(itemstack, false);

            entityitem.pickupDelay = 0;
            entityitem.a(entityplayer.getName());
            a(icommandlistener, "commands.give.success", new Object[] { itemstack.E(), Integer.valueOf(i), entityplayer.getName()});
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, this.d()) : (astring.length == 2 ? a(astring, Item.REGISTRY.b()) : null);
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    public boolean a(String[] astring, int i) {
        return i == 0;
    }
}
