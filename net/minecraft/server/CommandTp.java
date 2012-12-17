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

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.tp.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 1) {
            throw new ExceptionUsage("commands.tp.usage", new Object[0]);
        } else {
            EntityPlayer entityplayer;

            if (astring.length != 2 && astring.length != 4) {
                entityplayer = c(icommandlistener);
            } else {
                entityplayer = c(icommandlistener, astring[0]);
                if (entityplayer == null) {
                    throw new ExceptionPlayerNotFound();
                }
            }

            if (astring.length != 3 && astring.length != 4) {
                if (astring.length == 1 || astring.length == 2) {
                    EntityPlayer entityplayer1 = c(icommandlistener, astring[astring.length - 1]);

                    if (entityplayer1 == null) {
                        throw new ExceptionPlayerNotFound();
                    }

                    if (entityplayer1.world != entityplayer.world) {
                        a(icommandlistener, "commands.tp.notSameDimension", new Object[0]);
                        return;
                    }

                    entityplayer.playerConnection.a(entityplayer1.locX, entityplayer1.locY, entityplayer1.locZ, entityplayer1.yaw, entityplayer1.pitch);
                    a(icommandlistener, "commands.tp.success", new Object[] { entityplayer.getLocalizedName(), entityplayer1.getLocalizedName()});
                }
            } else if (entityplayer.world != null) {
                int i = astring.length - 3;
                double d0 = this.a(icommandlistener, entityplayer.locX, astring[i++]);
                double d1 = this.a(icommandlistener, entityplayer.locY, astring[i++], 0, 0);
                double d2 = this.a(icommandlistener, entityplayer.locZ, astring[i++]);

                entityplayer.enderTeleportTo(d0, d1, d2);
                a(icommandlistener, "commands.tp.success.coordinates", new Object[] { entityplayer.getLocalizedName(), Double.valueOf(d0), Double.valueOf(d1), Double.valueOf(d2)});
            }
        }
    }

    private double a(ICommandListener icommandlistener, double d0, String s) {
        return this.a(icommandlistener, d0, s, -30000000, 30000000);
    }

    private double a(ICommandListener icommandlistener, double d0, String s, int i, int j) {
        boolean flag = s.startsWith("~");
        double d1 = flag ? d0 : 0.0D;

        if (!flag || s.length() > 1) {
            boolean flag1 = s.contains(".");

            if (flag) {
                s = s.substring(1);
            }

            d1 += b(icommandlistener, s);
            if (!flag1 && !flag) {
                d1 += 0.5D;
            }
        }

        if (i != 0 || j != 0) {
            if (d1 < (double) i) {
                throw new ExceptionInvalidNumber("commands.generic.double.tooSmall", new Object[] { Double.valueOf(d1), Integer.valueOf(i)});
            }

            if (d1 > (double) j) {
                throw new ExceptionInvalidNumber("commands.generic.double.tooBig", new Object[] { Double.valueOf(d1), Integer.valueOf(j)});
            }
        }

        return d1;
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length != 1 && astring.length != 2 ? null : a(astring, MinecraftServer.getServer().getPlayers());
    }

    public boolean a(int i) {
        return i == 0;
    }
}
