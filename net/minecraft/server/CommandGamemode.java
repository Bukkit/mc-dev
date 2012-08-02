package net.minecraft.server;

import java.util.List;

public class CommandGamemode extends CommandAbstract {

    public CommandGamemode() {}

    public String b() {
        return "gamemode";
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.gamemode.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0) {
            EnumGamemode enumgamemode = this.b(icommandlistener, astring[0]);
            EntityHuman entityhuman = astring.length >= 2 ? this.a(astring[1]) : c(icommandlistener);

            entityhuman.a(enumgamemode);
            String s = LocaleI18n.get("gameMode." + enumgamemode.b());

            if (entityhuman != icommandlistener) {
                a(icommandlistener, 1, "commands.gamemode.success.other", new Object[] { entityhuman.getLocalizedName(), s});
            } else {
                a(icommandlistener, 1, "commands.gamemode.success.self", new Object[] { s});
            }
        } else {
            throw new ExceptionUsage("commands.gamemode.usage", new Object[0]);
        }
    }

    protected EnumGamemode b(ICommandListener icommandlistener, String s) {
        return !s.equalsIgnoreCase(EnumGamemode.SURVIVAL.b()) && !s.equalsIgnoreCase("s") ? (!s.equalsIgnoreCase(EnumGamemode.CREATIVE.b()) && !s.equalsIgnoreCase("c") ? (!s.equalsIgnoreCase(EnumGamemode.ADVENTURE.b()) && !s.equalsIgnoreCase("a") ? WorldSettings.a(a(icommandlistener, s, 0, EnumGamemode.values().length - 2)) : EnumGamemode.ADVENTURE) : EnumGamemode.CREATIVE) : EnumGamemode.SURVIVAL;
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "survival", "creative", "adventure"}) : (astring.length == 2 ? a(astring, this.c()) : null);
    }

    protected EntityHuman a(String s) {
        EntityPlayer entityplayer = MinecraftServer.getServer().getServerConfigurationManager().f(s);

        if (entityplayer == null) {
            throw new ExceptionPlayerNotFound();
        } else {
            return entityplayer;
        }
    }

    protected String[] c() {
        return MinecraftServer.getServer().getPlayers();
    }
}
