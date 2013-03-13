package net.minecraft.server;

import java.util.List;

public class CommandGamemode extends CommandAbstract {

    public CommandGamemode() {}

    public String c() {
        return "gamemode";
    }

    public int a() {
        return 2;
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.gamemode.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0) {
            EnumGamemode enumgamemode = this.e(icommandlistener, astring[0]);
            EntityPlayer entityplayer = astring.length >= 2 ? c(icommandlistener, astring[1]) : c(icommandlistener);

            entityplayer.a(enumgamemode);
            entityplayer.fallDistance = 0.0F;
            String s = LocaleI18n.get("gameMode." + enumgamemode.b());

            if (entityplayer != icommandlistener) {
                a(icommandlistener, 1, "commands.gamemode.success.other", new Object[] { entityplayer.getLocalizedName(), s});
            } else {
                a(icommandlistener, 1, "commands.gamemode.success.self", new Object[] { s});
            }
        } else {
            throw new ExceptionUsage("commands.gamemode.usage", new Object[0]);
        }
    }

    protected EnumGamemode e(ICommandListener icommandlistener, String s) {
        return !s.equalsIgnoreCase(EnumGamemode.SURVIVAL.b()) && !s.equalsIgnoreCase("s") ? (!s.equalsIgnoreCase(EnumGamemode.CREATIVE.b()) && !s.equalsIgnoreCase("c") ? (!s.equalsIgnoreCase(EnumGamemode.ADVENTURE.b()) && !s.equalsIgnoreCase("a") ? WorldSettings.a(a(icommandlistener, s, 0, EnumGamemode.values().length - 2)) : EnumGamemode.ADVENTURE) : EnumGamemode.CREATIVE) : EnumGamemode.SURVIVAL;
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "survival", "creative", "adventure"}) : (astring.length == 2 ? a(astring, this.d()) : null);
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    public boolean a(String[] astring, int i) {
        return i == 1;
    }
}
