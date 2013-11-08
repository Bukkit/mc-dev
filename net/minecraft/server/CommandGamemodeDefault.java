package net.minecraft.server;

import java.util.Iterator;

public class CommandGamemodeDefault extends CommandGamemode {

    public CommandGamemodeDefault() {}

    public String c() {
        return "defaultgamemode";
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.defaultgamemode.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0) {
            EnumGamemode enumgamemode = this.h(icommandlistener, astring[0]);

            this.a(enumgamemode);
            a(icommandlistener, "commands.defaultgamemode.success", new Object[] { new ChatMessage("gameMode." + enumgamemode.b(), new Object[0])});
        } else {
            throw new ExceptionUsage("commands.defaultgamemode.usage", new Object[0]);
        }
    }

    protected void a(EnumGamemode enumgamemode) {
        MinecraftServer minecraftserver = MinecraftServer.getServer();

        minecraftserver.a(enumgamemode);
        EntityPlayer entityplayer;

        if (minecraftserver.getForceGamemode()) {
            for (Iterator iterator = MinecraftServer.getServer().getPlayerList().players.iterator(); iterator.hasNext(); entityplayer.fallDistance = 0.0F) {
                entityplayer = (EntityPlayer) iterator.next();
                entityplayer.a(enumgamemode);
            }
        }
    }
}
