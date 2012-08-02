package net.minecraft.server;

public class ExceptionUnknownCommand extends CommandException {

    public ExceptionUnknownCommand() {
        this("commands.generic.notFound", new Object[0]);
    }

    public ExceptionUnknownCommand(String s, Object... aobject) {
        super(s, aobject);
    }
}
