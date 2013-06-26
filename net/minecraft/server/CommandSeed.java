package net.minecraft.server;

public class CommandSeed extends CommandAbstract {

    public CommandSeed() {}

    public boolean a(ICommandListener icommandlistener) {
        return MinecraftServer.getServer().K() || super.a(icommandlistener);
    }

    public String c() {
        return "seed";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.seed.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        Object object = icommandlistener instanceof EntityHuman ? ((EntityHuman) icommandlistener).world : MinecraftServer.getServer().getWorldServer(0);

        icommandlistener.sendMessage(ChatMessage.b("commands.seed.success", new Object[] { Long.valueOf(((World) object).getSeed())}));
    }
}
