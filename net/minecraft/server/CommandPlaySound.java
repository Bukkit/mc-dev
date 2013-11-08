package net.minecraft.server;

public class CommandPlaySound extends CommandAbstract {

    public CommandPlaySound() {}

    public String c() {
        return "playsound";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.playsound.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 2) {
            throw new ExceptionUsage(this.c(icommandlistener), new Object[0]);
        } else {
            byte b0 = 0;
            int i = b0 + 1;
            String s = astring[b0];
            EntityPlayer entityplayer = d(icommandlistener, astring[i++]);
            double d0 = (double) entityplayer.getChunkCoordinates().x;
            double d1 = (double) entityplayer.getChunkCoordinates().y;
            double d2 = (double) entityplayer.getChunkCoordinates().z;
            double d3 = 1.0D;
            double d4 = 1.0D;
            double d5 = 0.0D;

            if (astring.length > i) {
                d0 = a(icommandlistener, d0, astring[i++]);
            }

            if (astring.length > i) {
                d1 = a(icommandlistener, d1, astring[i++], 0, 0);
            }

            if (astring.length > i) {
                d2 = a(icommandlistener, d2, astring[i++]);
            }

            if (astring.length > i) {
                d3 = a(icommandlistener, astring[i++], 0.0D, 3.4028234663852886E38D);
            }

            if (astring.length > i) {
                d4 = a(icommandlistener, astring[i++], 0.0D, 2.0D);
            }

            if (astring.length > i) {
                d5 = a(icommandlistener, astring[i++], 0.0D, 1.0D);
            }

            double d6 = d3 > 1.0D ? d3 * 16.0D : 16.0D;
            double d7 = entityplayer.f(d0, d1, d2);

            if (d7 > d6) {
                if (d5 <= 0.0D) {
                    throw new CommandException("commands.playsound.playerTooFar", new Object[] { entityplayer.getName()});
                }

                double d8 = d0 - entityplayer.locX;
                double d9 = d1 - entityplayer.locY;
                double d10 = d2 - entityplayer.locZ;
                double d11 = Math.sqrt(d8 * d8 + d9 * d9 + d10 * d10);
                double d12 = entityplayer.locX;
                double d13 = entityplayer.locY;
                double d14 = entityplayer.locZ;

                if (d11 > 0.0D) {
                    d12 += d8 / d11 * 2.0D;
                    d13 += d9 / d11 * 2.0D;
                    d14 += d10 / d11 * 2.0D;
                }

                entityplayer.playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect(s, d12, d13, d14, (float) d5, (float) d4));
            } else {
                entityplayer.playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect(s, d0, d1, d2, (float) d3, (float) d4));
            }

            a(icommandlistener, "commands.playsound.success", new Object[] { s, entityplayer.getName()});
        }
    }

    public boolean a(String[] astring, int i) {
        return i == 1;
    }
}
