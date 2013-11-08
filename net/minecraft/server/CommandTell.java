package net.minecraft.server;

import java.util.Arrays;
import java.util.List;

public class CommandTell extends CommandAbstract {

    public CommandTell() {}

    public List b() {
        return Arrays.asList(new String[] { "w", "msg"});
    }

    public String c() {
        return "tell";
    }

    public int a() {
        return 0;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.message.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 2) {
            throw new ExceptionUsage("commands.message.usage", new Object[0]);
        } else {
            EntityPlayer entityplayer = d(icommandlistener, astring[0]);

            if (entityplayer == null) {
                throw new ExceptionPlayerNotFound();
            } else if (entityplayer == icommandlistener) {
                throw new ExceptionPlayerNotFound("commands.message.sameTarget", new Object[0]);
            } else {
                IChatBaseComponent ichatbasecomponent = a(icommandlistener, astring, 1, !(icommandlistener instanceof EntityHuman));
                ChatMessage chatmessage = new ChatMessage("commands.message.display.incoming", new Object[] { icommandlistener.getScoreboardDisplayName(), ichatbasecomponent.f()});
                ChatMessage chatmessage1 = new ChatMessage("commands.message.display.outgoing", new Object[] { entityplayer.getScoreboardDisplayName(), ichatbasecomponent.f()});

                chatmessage.b().setColor(EnumChatFormat.GRAY).setItalic(Boolean.valueOf(true));
                chatmessage1.b().setColor(EnumChatFormat.GRAY).setItalic(Boolean.valueOf(true));
                entityplayer.sendMessage(chatmessage);
                icommandlistener.sendMessage(chatmessage1);
            }
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return a(astring, MinecraftServer.getServer().getPlayers());
    }

    public boolean a(String[] astring, int i) {
        return i == 0;
    }
}
