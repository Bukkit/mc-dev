package net.minecraft.server;

import java.util.List;

public class CommandTp extends CommandAbstract {

    public CommandTp() {}

    public String b() {
        return "tp";
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.tp.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 1) {
            throw new ExceptionUsage("commands.tp.usage", new Object[0]);
        } else {
            MinecraftServer minecraftserver = MinecraftServer.getServer();
            EntityPlayer entityplayer;

            if (astring.length != 2 && astring.length != 4) {
                entityplayer = (EntityPlayer) c(icommandlistener);
            } else {
                entityplayer = minecraftserver.getServerConfigurationManager().f(astring[0]);
                if (entityplayer == null) {
                    throw new ExceptionPlayerNotFound();
                }
            }

            if (astring.length != 3 && astring.length != 4) {
                if (astring.length == 1 || astring.length == 2) {
                    EntityPlayer entityplayer1 = minecraftserver.getServerConfigurationManager().f(astring[astring.length - 1]);

                    if (entityplayer1 == null) {
                        throw new ExceptionPlayerNotFound();
                    }

                    entityplayer.netServerHandler.a(entityplayer1.locX, entityplayer1.locY, entityplayer1.locZ, entityplayer1.yaw, entityplayer1.pitch);
                    a(icommandlistener, "commands.tp.success", new Object[] { entityplayer.getLocalizedName(), entityplayer1.getLocalizedName()});
                }
            } else if (entityplayer.world != null) {
                int i = astring.length - 3;
                int j = 30000000;
                int k = a(icommandlistener, astring[i++], -j, j);
                int l = a(icommandlistener, astring[i++], 0, 256);
                int i1 = a(icommandlistener, astring[i++], -j, j);

                entityplayer.enderTeleportTo((double) ((float) k + 0.5F), (double) l, (double) ((float) i1 + 0.5F));
                a(icommandlistener, "commands.tp.coordinates", new Object[] { entityplayer.getLocalizedName(), Integer.valueOf(k), Integer.valueOf(l), Integer.valueOf(i1)});
            }
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length != 1 && astring.length != 2 ? null : a(astring, MinecraftServer.getServer().getPlayers());
    }
}
