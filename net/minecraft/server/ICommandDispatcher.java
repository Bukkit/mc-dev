package net.minecraft.server;

public interface ICommandDispatcher {

    void a(ICommandListener icommandlistener, ICommand icommand, int i, String s, Object... aobject);
}
