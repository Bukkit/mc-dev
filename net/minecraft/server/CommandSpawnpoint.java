package net.minecraft.server;

import java.util.List;

public class CommandSpawnpoint extends CommandAbstract {

    public CommandSpawnpoint() {}

    public String c() {
        return "spawnpoint";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.spawnpoint.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        EntityPlayer entityplayer = astring.length == 0 ? b(icommandlistener) : d(icommandlistener, astring[0]);

        if (astring.length == 4) {
            if (entityplayer.world != null) {
                byte b0 = 1;
                int i = 30000000;
                int j = b0 + 1;
                int k = a(icommandlistener, astring[b0], -i, i);
                int l = a(icommandlistener, astring[j++], 0, 256);
                int i1 = a(icommandlistener, astring[j++], -i, i);

                entityplayer.setRespawnPosition(new ChunkCoordinates(k, l, i1), true);
                a(icommandlistener, "commands.spawnpoint.success", new Object[] { entityplayer.getName(), Integer.valueOf(k), Integer.valueOf(l), Integer.valueOf(i1)});
            }
        } else {
            if (astring.length > 1) {
                throw new ExceptionUsage("commands.spawnpoint.usage", new Object[0]);
            }

            ChunkCoordinates chunkcoordinates = entityplayer.getChunkCoordinates();

            entityplayer.setRespawnPosition(chunkcoordinates, true);
            a(icommandlistener, "commands.spawnpoint.success", new Object[] { entityplayer.getName(), Integer.valueOf(chunkcoordinates.x), Integer.valueOf(chunkcoordinates.y), Integer.valueOf(chunkcoordinates.z)});
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length != 1 && astring.length != 2 ? null : a(astring, MinecraftServer.getServer().getPlayers());
    }

    public boolean a(String[] astring, int i) {
        return i == 0;
    }
}
