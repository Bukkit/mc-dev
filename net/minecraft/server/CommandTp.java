package net.minecraft.server;

import java.util.List;

public class CommandTp extends CommandAbstract {

    public CommandTp() {}

    public String c() {
        return "tp";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.tp.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 1) {
            throw new ExceptionUsage("commands.tp.usage", new Object[0]);
        } else {
            EntityPlayer entityplayer;

            if (astring.length != 2 && astring.length != 4) {
                entityplayer = b(icommandlistener);
            } else {
                entityplayer = d(icommandlistener, astring[0]);
                if (entityplayer == null) {
                    throw new ExceptionPlayerNotFound();
                }
            }

            if (astring.length != 3 && astring.length != 4) {
                if (astring.length == 1 || astring.length == 2) {
                    EntityPlayer entityplayer1 = d(icommandlistener, astring[astring.length - 1]);

                    if (entityplayer1 == null) {
                        throw new ExceptionPlayerNotFound();
                    }

                    if (entityplayer1.world != entityplayer.world) {
                        a(icommandlistener, "commands.tp.notSameDimension", new Object[0]);
                        return;
                    }

                    entityplayer.mount((Entity) null);
                    entityplayer.playerConnection.a(entityplayer1.locX, entityplayer1.locY, entityplayer1.locZ, entityplayer1.yaw, entityplayer1.pitch);
                    a(icommandlistener, "commands.tp.success", new Object[] { entityplayer.getName(), entityplayer1.getName()});
                }
            } else if (entityplayer.world != null) {
                int i = astring.length - 3;
                double d0 = a(icommandlistener, entityplayer.locX, astring[i++]);
                double d1 = a(icommandlistener, entityplayer.locY, astring[i++], 0, 0);
                double d2 = a(icommandlistener, entityplayer.locZ, astring[i++]);

                entityplayer.mount((Entity) null);
                entityplayer.enderTeleportTo(d0, d1, d2);
                a(icommandlistener, "commands.tp.success.coordinates", new Object[] { entityplayer.getName(), Double.valueOf(d0), Double.valueOf(d1), Double.valueOf(d2)});
            }
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length != 1 && astring.length != 2 ? null : a(astring, MinecraftServer.getServer().getPlayers());
    }

    public boolean a(String[] astring, int i) {
        return i == 0;
    }
}
