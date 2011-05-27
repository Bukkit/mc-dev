package net.minecraft.server;

public class ServerCommand {

    public final String command;
    public final ICommandListener b;

    public ServerCommand(String s, ICommandListener icommandlistener) {
        this.command = s;
        this.b = icommandlistener;
    }
}
