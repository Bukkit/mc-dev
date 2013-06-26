package net.minecraft.server;

public class CommandList extends CommandAbstract {

    public CommandList() {}

    public String c() {
        return "list";
    }

    public int a() {
        return 0;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.players.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        icommandlistener.sendMessage(ChatMessage.b("commands.players.list", new Object[] { Integer.valueOf(MinecraftServer.getServer().A()), Integer.valueOf(MinecraftServer.getServer().B())}));
        icommandlistener.sendMessage(ChatMessage.d(MinecraftServer.getServer().getPlayerList().c()));
    }
}
