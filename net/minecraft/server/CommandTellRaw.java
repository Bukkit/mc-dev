package net.minecraft.server;

import java.util.List;

import net.minecraft.util.com.google.gson.JsonParseException;
import net.minecraft.util.org.apache.commons.lang3.exception.ExceptionUtils;

public class CommandTellRaw extends CommandAbstract {

    public CommandTellRaw() {}

    public String c() {
        return "tellraw";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.tellraw.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 2) {
            throw new ExceptionUsage("commands.tellraw.usage", new Object[0]);
        } else {
            EntityPlayer entityplayer = d(icommandlistener, astring[0]);
            String s = b(icommandlistener, astring, 1);

            try {
                IChatBaseComponent ichatbasecomponent = ChatSerializer.a(s);

                entityplayer.sendMessage(ichatbasecomponent);
            } catch (JsonParseException jsonparseexception) {
                throw new ExceptionInvalidSyntax("commands.tellraw.jsonException", new Object[] { ExceptionUtils.getRootCause(jsonparseexception).getMessage()});
            }
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }

    public boolean a(String[] astring, int i) {
        return i == 0;
    }
}
