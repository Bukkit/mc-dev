package net.minecraft.server;

import java.util.List;

public class CommandDifficulty extends CommandAbstract {

    private static final String[] a = new String[] { "options.difficulty.peaceful", "options.difficulty.easy", "options.difficulty.normal", "options.difficulty.hard"};

    public CommandDifficulty() {}

    public String c() {
        return "difficulty";
    }

    public int a() {
        return 2;
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.difficulty.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0) {
            int i = this.d(icommandlistener, astring[0]);

            MinecraftServer.getServer().c(i);
            String s = LocaleI18n.get(a[i]);

            a(icommandlistener, 1, "commands.difficulty.success", new Object[] { s});
        } else {
            throw new ExceptionUsage("commands.difficulty.usage", new Object[0]);
        }
    }

    protected int d(ICommandListener icommandlistener, String s) {
        return !s.equalsIgnoreCase("peaceful") && !s.equalsIgnoreCase("p") ? (!s.equalsIgnoreCase("easy") && !s.equalsIgnoreCase("e") ? (!s.equalsIgnoreCase("normal") && !s.equalsIgnoreCase("n") ? (!s.equalsIgnoreCase("hard") && !s.equalsIgnoreCase("h") ? a(icommandlistener, s, 0, 3) : 3) : 2) : 1) : 0;
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "peaceful", "easy", "normal", "hard"}) : null;
    }
}
