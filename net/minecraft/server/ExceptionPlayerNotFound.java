package net.minecraft.server;

public class ExceptionPlayerNotFound extends CommandException {

    public ExceptionPlayerNotFound() {
        this("commands.generic.player.notFound", new Object[0]);
    }

    public ExceptionPlayerNotFound(String s, Object... aobject) {
        super(s, aobject);
    }
}
