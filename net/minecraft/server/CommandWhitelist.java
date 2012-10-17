package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class CommandWhitelist extends CommandAbstract {

    public CommandWhitelist() {}

    public String c() {
        return "whitelist";
    }

    public int a() {
        return 3;
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.whitelist.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 1) {
            if (astring[0].equals("on")) {
                MinecraftServer.getServer().getServerConfigurationManager().setHasWhitelist(true);
                a(icommandlistener, "commands.whitelist.enabled", new Object[0]);
                return;
            }

            if (astring[0].equals("off")) {
                MinecraftServer.getServer().getServerConfigurationManager().setHasWhitelist(false);
                a(icommandlistener, "commands.whitelist.disabled", new Object[0]);
                return;
            }

            if (astring[0].equals("list")) {
                icommandlistener.sendMessage(icommandlistener.a("commands.whitelist.list", new Object[] { Integer.valueOf(MinecraftServer.getServer().getServerConfigurationManager().getWhitelisted().size()), Integer.valueOf(MinecraftServer.getServer().getServerConfigurationManager().getSeenPlayers().length)}));
                icommandlistener.sendMessage(a(MinecraftServer.getServer().getServerConfigurationManager().getWhitelisted().toArray(new String[0])));
                return;
            }

            if (astring[0].equals("add")) {
                if (astring.length < 2) {
                    throw new ExceptionUsage("commands.whitelist.add.usage", new Object[0]);
                }

                MinecraftServer.getServer().getServerConfigurationManager().addWhitelist(astring[1]);
                a(icommandlistener, "commands.whitelist.add.success", new Object[] { astring[1]});
                return;
            }

            if (astring[0].equals("remove")) {
                if (astring.length < 2) {
                    throw new ExceptionUsage("commands.whitelist.remove.usage", new Object[0]);
                }

                MinecraftServer.getServer().getServerConfigurationManager().removeWhitelist(astring[1]);
                a(icommandlistener, "commands.whitelist.remove.success", new Object[] { astring[1]});
                return;
            }

            if (astring[0].equals("reload")) {
                MinecraftServer.getServer().getServerConfigurationManager().reloadWhitelist();
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
                    String[] astring1 = MinecraftServer.getServer().getServerConfigurationManager().getSeenPlayers();
                    ArrayList arraylist = new ArrayList();
                    String s = astring[astring.length - 1];
                    String[] astring2 = astring1;
                    int i = astring1.length;

                    for (int j = 0; j < i; ++j) {
                        String s1 = astring2[j];

                        if (a(s, s1) && !MinecraftServer.getServer().getServerConfigurationManager().getWhitelisted().contains(s1)) {
                            arraylist.add(s1);
                        }
                    }

                    return arraylist;
                }

                if (astring[0].equals("remove")) {
                    return a(astring, MinecraftServer.getServer().getServerConfigurationManager().getWhitelisted());
                }
            }

            return null;
        }
    }
}
