package net.minecraft.server;

public class CommandList extends CommandAbstract {

    public CommandList() {}

    public String c() {
        return "list";
    }

    public int a() {
        return 0;
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        icommandlistener.sendMessage(icommandlistener.a("commands.players.list", new Object[] { Integer.valueOf(MinecraftServer.getServer().y()), Integer.valueOf(MinecraftServer.getServer().z())}));
        icommandlistener.sendMessage(MinecraftServer.getServer().getPlayerList().c());
    }
}
