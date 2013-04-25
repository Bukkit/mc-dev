package net.minecraft.server;

import java.util.List;

public class CommandEnchant extends CommandAbstract {

    public CommandEnchant() {}

    public String c() {
        return "enchant";
    }

    public int a() {
        return 2;
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.enchant.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 2) {
            throw new ExceptionUsage("commands.enchant.usage", new Object[0]);
        } else {
            EntityPlayer entityplayer = c(icommandlistener, astring[0]);
            int i = a(icommandlistener, astring[1], 0, Enchantment.byId.length - 1);
            int j = 1;
            ItemStack itemstack = entityplayer.cd();

            if (itemstack == null) {
                a(icommandlistener, "commands.enchant.noItem", new Object[0]);
            } else {
                Enchantment enchantment = Enchantment.byId[i];

                if (enchantment == null) {
                    throw new ExceptionInvalidNumber("commands.enchant.notFound", new Object[] { Integer.valueOf(i)});
                } else if (!enchantment.canEnchant(itemstack)) {
                    a(icommandlistener, "commands.enchant.cantEnchant", new Object[0]);
                } else {
                    if (astring.length >= 3) {
                        j = a(icommandlistener, astring[2], enchantment.getStartLevel(), enchantment.getMaxLevel());
                    }

                    if (itemstack.hasTag()) {
                        NBTTagList nbttaglist = itemstack.getEnchantments();

                        if (nbttaglist != null) {
                            for (int k = 0; k < nbttaglist.size(); ++k) {
                                short short1 = ((NBTTagCompound) nbttaglist.get(k)).getShort("id");

                                if (Enchantment.byId[short1] != null) {
                                    Enchantment enchantment1 = Enchantment.byId[short1];

                                    if (!enchantment1.a(enchantment)) {
                                        a(icommandlistener, "commands.enchant.cantCombine", new Object[] { enchantment.c(j), enchantment1.c(((NBTTagCompound) nbttaglist.get(k)).getShort("lvl"))});
                                        return;
                                    }
                                }
                            }
                        }
                    }

                    itemstack.addEnchantment(enchantment, j);
                    a(icommandlistener, "commands.enchant.success", new Object[0]);
                }
            }
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
