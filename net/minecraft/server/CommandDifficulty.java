package net.minecraft.server;

import java.util.List;

public class CommandDifficulty extends CommandAbstract {

    public CommandDifficulty() {}

    public String c() {
        return "difficulty";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.difficulty.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 0) {
            EnumDifficulty enumdifficulty = this.h(icommandlistener, astring[0]);

            MinecraftServer.getServer().a(enumdifficulty);
            a(icommandlistener, "commands.difficulty.success", new Object[] { new ChatMessage(enumdifficulty.b(), new Object[0])});
        } else {
            throw new ExceptionUsage("commands.difficulty.usage", new Object[0]);
        }
    }

    protected EnumDifficulty h(ICommandListener icommandlistener, String s) {
        return !s.equalsIgnoreCase("peaceful") && !s.equalsIgnoreCase("p") ? (!s.equalsIgnoreCase("easy") && !s.equalsIgnoreCase("e") ? (!s.equalsIgnoreCase("normal") && !s.equalsIgnoreCase("n") ? (!s.equalsIgnoreCase("hard") && !s.equalsIgnoreCase("h") ? EnumDifficulty.a(a(icommandlistener, s, 0, 3)) : EnumDifficulty.HARD) : EnumDifficulty.NORMAL) : EnumDifficulty.EASY) : EnumDifficulty.PEACEFUL;
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "peaceful", "easy", "normal", "hard"}) : null;
    }
}
