package net.minecraft.server;

public class CommandException extends RuntimeException {

    private Object[] a;

    public CommandException(String s, Object... aobject) {
        super(s);
        this.a = aobject;
    }

    public Object[] getArgs() {
        return this.a;
    }
}
