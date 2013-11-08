package net.minecraft.server;

public class CommandSetWorldSpawn extends CommandAbstract {

    public CommandSetWorldSpawn() {}

    public String c() {
        return "setworldspawn";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.setworldspawn.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length == 3) {
            if (icommandlistener.getWorld() == null) {
                throw new ExceptionUsage("commands.setworldspawn.usage", new Object[0]);
            }

            byte b0 = 0;
            int i = b0 + 1;
            int j = a(icommandlistener, astring[b0], -30000000, 30000000);
            int k = a(icommandlistener, astring[i++], 0, 256);
            int l = a(icommandlistener, astring[i++], -30000000, 30000000);

            icommandlistener.getWorld().x(j, k, l);
            a(icommandlistener, "commands.setworldspawn.success", new Object[] { Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(l)});
        } else {
            if (astring.length != 0) {
                throw new ExceptionUsage("commands.setworldspawn.usage", new Object[0]);
            }

            ChunkCoordinates chunkcoordinates = b(icommandlistener).getChunkCoordinates();

            icommandlistener.getWorld().x(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z);
            a(icommandlistener, "commands.setworldspawn.success", new Object[] { Integer.valueOf(chunkcoordinates.x), Integer.valueOf(chunkcoordinates.y), Integer.valueOf(chunkcoordinates.z)});
        }
    }
}
