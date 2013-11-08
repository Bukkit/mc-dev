package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommandWhitelist extends CommandAbstract {

    public CommandWhitelist() {}

    public String c() {
        return "whitelist";
    }

    public int a() {
        return 3;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.whitelist.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 1) {
            if (astring[0].equals("on")) {
                MinecraftServer.getServer().getPlayerList().setHasWhitelist(true);
                a(icommandlistener, "commands.whitelist.enabled", new Object[0]);
                return;
            }

            if (astring[0].equals("off")) {
                MinecraftServer.getServer().getPlayerList().setHasWhitelist(false);
                a(icommandlistener, "commands.whitelist.disabled", new Object[0]);
                return;
            }

            if (astring[0].equals("list")) {
                icommandlistener.sendMessage(new ChatMessage("commands.whitelist.list", new Object[] { Integer.valueOf(MinecraftServer.getServer().getPlayerList().getWhitelisted().size()), Integer.valueOf(MinecraftServer.getServer().getPlayerList().getSeenPlayers().length)}));
                Set set = MinecraftServer.getServer().getPlayerList().getWhitelisted();

                icommandlistener.sendMessage(new ChatComponentText(a(set.toArray(new String[set.size()]))));
                return;
            }

            if (astring[0].equals("add")) {
                if (astring.length < 2) {
                    throw new ExceptionUsage("commands.whitelist.add.usage", new Object[0]);
                }

                MinecraftServer.getServer().getPlayerList().addWhitelist(astring[1]);
                a(icommandlistener, "commands.whitelist.add.success", new Object[] { astring[1]});
                return;
            }

            if (astring[0].equals("remove")) {
                if (astring.length < 2) {
                    throw new ExceptionUsage("commands.whitelist.remove.usage", new Object[0]);
                }

                MinecraftServer.getServer().getPlayerList().removeWhitelist(astring[1]);
                a(icommandlistener, "commands.whitelist.remove.success", new Object[] { astring[1]});
                return;
            }

            if (astring[0].equals("reload")) {
                MinecraftServer.getServer().getPlayerList().reloadWhitelist();
                a(icommandlistener, "commands.whitelist.reloaded", new Object[0]);
                return;
            }
        }

        throw new ExceptionUsage("commands.whitelist.usage", new Object[0]);
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        if (astring.length == 1) {
            return a(astring, new String[] { "on", "off", "list", "add", "remove", "reload"});
        } else {
            if (astring.length == 2) {
                if (astring[0].equals("add")) {
                    String[] astring1 = MinecraftServer.getServer().getPlayerList().getSeenPlayers();
                    ArrayList arraylist = new ArrayList();
                    String s = astring[astring.length - 1];
                    String[] astring2 = astring1;
                    int i = astring1.length;

                    for (int j = 0; j < i; ++j) {
                        String s1 = astring2[j];

                        if (a(s, s1) && !MinecraftServer.getServer().getPlayerList().getWhitelisted().contains(s1)) {
                            arraylist.add(s1);
                        }
                    }

                    return arraylist;
                }

                if (astring[0].equals("remove")) {
                    return a(astring, MinecraftServer.getServer().getPlayerList().getWhitelisted());
                }
            }

            return null;
        }
    }
}
