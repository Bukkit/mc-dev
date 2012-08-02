package net.minecraft.server;

public class CommandList extends CommandAbstract {

    public CommandList() {}

    public String b() {
        return "list";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        icommandlistener.sendMessage(icommandlistener.a("commands.players.list", new Object[] { Integer.valueOf(MinecraftServer.getServer().x()), Integer.valueOf(MinecraftServer.getServer().y())}));
        icommandlistener.sendMessage(MinecraftServer.getServer().getServerConfigurationManager().c());
    }
}
