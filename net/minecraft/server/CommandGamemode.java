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

    public String c(ICommandListener icommandlistener) {
        return "commands.gamemode.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0) {
            EnumGamemode enumgamemode = this.h(icommandlistener, astring[0]);
            EntityPlayer entityplayer = astring.length >= 2 ? d(icommandlistener, astring[1]) : b(icommandlistener);

            entityplayer.a(enumgamemode);
            entityplayer.fallDistance = 0.0F;
            ChatMessage chatmessage = new ChatMessage("gameMode." + enumgamemode.b(), new Object[0]);

            if (entityplayer != icommandlistener) {
                a(icommandlistener, 1, "commands.gamemode.success.other", new Object[] { entityplayer.getName(), chatmessage});
            } else {
                a(icommandlistener, 1, "commands.gamemode.success.self", new Object[] { chatmessage});
            }
        } else {
            throw new ExceptionUsage("commands.gamemode.usage", new Object[0]);
        }
    }

    protected EnumGamemode h(ICommandListener icommandlistener, String s) {
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
