package net.minecraft.server;

import java.util.List;

public class CommandGamerule extends CommandAbstract {

    public CommandGamerule() {}

    public String c() {
        return "gamerule";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.gamerule.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        String s;

        if (astring.length == 2) {
            s = astring[0];
            String s1 = astring[1];
            GameRules gamerules = this.d();

            if (gamerules.e(s)) {
                gamerules.set(s, s1);
                a(icommandlistener, "commands.gamerule.success", new Object[0]);
            } else {
                a(icommandlistener, "commands.gamerule.norule", new Object[] { s});
            }
        } else if (astring.length == 1) {
            s = astring[0];
            GameRules gamerules1 = this.d();

            if (gamerules1.e(s)) {
                String s2 = gamerules1.get(s);

                icommandlistener.sendMessage((new ChatComponentText(s)).a(" = ").a(s2));
            } else {
                a(icommandlistener, "commands.gamerule.norule", new Object[] { s});
            }
        } else if (astring.length == 0) {
            GameRules gamerules2 = this.d();

            icommandlistener.sendMessage(new ChatComponentText(a(gamerules2.b())));
        } else {
            throw new ExceptionUsage("commands.gamerule.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, this.d().b()) : (astring.length == 2 ? a(astring, new String[] { "true", "false"}) : null);
    }

    private GameRules d() {
        return MinecraftServer.getServer().getWorldServer(0).getGameRules();
    }
}
