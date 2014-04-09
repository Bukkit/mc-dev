package net.minecraft.server;

public class CommandList extends CommandAbstract {

    public CommandList() {}

    public String getCommand() {
        return "list";
    }

    public int a() {
        return 0;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.players.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        icommandlistener.sendMessage(new ChatMessage("commands.players.list", new Object[] { Integer.valueOf(MinecraftServer.getServer().C()), Integer.valueOf(MinecraftServer.getServer().D())}));
        icommandlistener.sendMessage(new ChatComponentText(MinecraftServer.getServer().getPlayerList().b(astring.length > 0 && "uuids".equalsIgnoreCase(astring[0]))));
    }
}
