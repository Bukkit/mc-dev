package net.minecraft.server;

public class CommandTestFor extends CommandAbstract {

    public CommandTestFor() {}

    public String c() {
        return "testfor";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.testfor.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length != 1) {
            throw new ExceptionUsage("commands.testfor.usage", new Object[0]);
        } else if (!(icommandlistener instanceof CommandBlockListenerAbstract)) {
            throw new CommandException("commands.testfor.failed", new Object[0]);
        } else {
            d(icommandlistener, astring[0]);
        }
    }

    public boolean a(String[] astring, int i) {
        return i == 0;
    }
}
