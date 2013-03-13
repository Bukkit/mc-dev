package net.minecraft.server;

public class CommandGamemodeDefault extends CommandGamemode {

    public CommandGamemodeDefault() {}

    public String c() {
        return "defaultgamemode";
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.defaultgamemode.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0) {
            EnumGamemode enumgamemode = this.e(icommandlistener, astring[0]);

            this.a(enumgamemode);
            String s = LocaleI18n.get("gameMode." + enumgamemode.b());

            a(icommandlistener, "commands.defaultgamemode.success", new Object[] { s});
        } else {
            throw new ExceptionUsage("commands.defaultgamemode.usage", new Object[0]);
        }
    }

    protected void a(EnumGamemode enumgamemode) {
        MinecraftServer.getServer().a(enumgamemode);
    }
}
