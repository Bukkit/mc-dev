package net.minecraft.server;

import java.util.List;

public class CommandEffect extends CommandAbstract {

    public CommandEffect() {}

    public String c() {
        return "effect";
    }

    public int a() {
        return 2;
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.effect.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 2) {
            EntityPlayer entityplayer = c(icommandlistener, astring[0]);
            int i = a(icommandlistener, astring[1], 1);
            int j = 600;
            int k = 30;
            int l = 0;

            if (i >= 0 && i < MobEffectList.byId.length && MobEffectList.byId[i] != null) {
                if (astring.length >= 3) {
                    k = a(icommandlistener, astring[2], 0, 1000000);
                    if (MobEffectList.byId[i].isInstant()) {
                        j = k;
                    } else {
                        j = k * 20;
                    }
                } else if (MobEffectList.byId[i].isInstant()) {
                    j = 1;
                }

                if (astring.length >= 4) {
                    l = a(icommandlistener, astring[3], 0, 255);
                }

                if (k == 0) {
                    if (!entityplayer.hasEffect(i)) {
                        throw new CommandException("commands.effect.failure.notActive", new Object[] { LocaleI18n.get(MobEffectList.byId[i].a()), entityplayer.getLocalizedName()});
                    }

                    entityplayer.o(i);
                    a(icommandlistener, "commands.effect.success.removed", new Object[] { LocaleI18n.get(MobEffectList.byId[i].a()), entityplayer.getLocalizedName()});
                } else {
                    MobEffect mobeffect = new MobEffect(i, j, l);

                    entityplayer.addEffect(mobeffect);
                    a(icommandlistener, "commands.effect.success", new Object[] { LocaleI18n.get(mobeffect.f()), Integer.valueOf(i), Integer.valueOf(l), entityplayer.getLocalizedName(), Integer.valueOf(k)});
                }
            } else {
                throw new ExceptionInvalidNumber("commands.effect.notFound", new Object[] { Integer.valueOf(i)});
            }
        } else {
            throw new ExceptionUsage("commands.effect.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, this.d()) : null;
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    public boolean a(String[] astring, int i) {
        return i == 0;
    }
}
