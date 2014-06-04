package net.minecraft.server;

import java.util.List;

public class CommandGamerule extends CommandAbstract {

    public CommandGamerule() {}

    public String getCommand() {
        return "gamerule";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.gamerule.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        String s;

        if (astring.length == 2) {
            s = astring[0];
            String s1 = astring[1];
            GameRules gamerules = this.d();

            if (gamerules.contains(s)) {
                gamerules.set(s, s1);
                a(icommandlistener, this, "commands.gamerule.success", new Object[0]);
            } else {
                a(icommandlistener, this, "commands.gamerule.norule", new Object[] { s});
            }
        } else if (astring.length == 1) {
            s = astring[0];
            GameRules gamerules1 = this.d();

            if (gamerules1.contains(s)) {
                String s2 = gamerules1.get(s);

                icommandlistener.sendMessage((new ChatComponentText(s)).a(" = ").a(s2));
            } else {
                a(icommandlistener, this, "commands.gamerule.norule", new Object[] { s});
            }
        } else if (astring.length == 0) {
            GameRules gamerules2 = this.d();

            icommandlistener.sendMessage(new ChatComponentText(a(gamerules2.getGameRules())));
        } else {
            throw new ExceptionUsage("commands.gamerule.usage", new Object[0]);
        }
    }

    public List tabComplete(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, this.d().getGameRules()) : (astring.length == 2 ? a(astring, new String[] { "true", "false"}) : null);
    }

    private GameRules d() {
        return MinecraftServer.getServer().getWorldServer(0).getGameRules();
    }
}
