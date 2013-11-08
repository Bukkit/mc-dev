package net.minecraft.server;

import java.util.List;

public class CommandSetBlock extends CommandAbstract {

    public CommandSetBlock() {}

    public String c() {
        return "setblock";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.setblock.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 4) {
            int i = icommandlistener.getChunkCoordinates().x;
            int j = icommandlistener.getChunkCoordinates().y;
            int k = icommandlistener.getChunkCoordinates().z;

            i = MathHelper.floor(a(icommandlistener, (double) i, astring[0]));
            j = MathHelper.floor(a(icommandlistener, (double) j, astring[1]));
            k = MathHelper.floor(a(icommandlistener, (double) k, astring[2]));
            Block block = CommandAbstract.g(icommandlistener, astring[3]);
            int l = 0;

            if (astring.length >= 5) {
                l = a(icommandlistener, astring[4], 0, 15);
            }

            World world = icommandlistener.getWorld();

            if (!world.isLoaded(i, j, k)) {
                throw new CommandException("commands.setblock.outOfWorld", new Object[0]);
            } else {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                boolean flag = false;

                if (astring.length >= 7 && block.isTileEntity()) {
                    String s = a(icommandlistener, astring, 6).c();

                    try {
                        NBTBase nbtbase = MojangsonParser.a(s);

                        if (!(nbtbase instanceof NBTTagCompound)) {
                            throw new CommandException("commands.setblock.tagError", new Object[] { "Not a valid tag"});
                        }

                        nbttagcompound = (NBTTagCompound) nbtbase;
                        flag = true;
                    } catch (MojangsonParseException mojangsonparseexception) {
                        throw new CommandException("commands.setblock.tagError", new Object[] { mojangsonparseexception.getMessage()});
                    }
                }

                if (astring.length >= 6) {
                    if (astring[5].equals("destroy")) {
                        world.setAir(i, j, k, true);
                    } else if (astring[5].equals("keep") && !world.isEmpty(i, j, k)) {
                        throw new CommandException("commands.setblock.noChange", new Object[0]);
                    }
                }

                if (!world.setTypeAndData(i, j, k, block, l, 3)) {
                    throw new CommandException("commands.setblock.noChange", new Object[0]);
                } else {
                    if (flag) {
                        TileEntity tileentity = world.getTileEntity(i, j, k);

                        if (tileentity != null) {
                            nbttagcompound.setInt("x", i);
                            nbttagcompound.setInt("y", j);
                            nbttagcompound.setInt("z", k);
                            tileentity.a(nbttagcompound);
                        }
                    }

                    a(icommandlistener, "commands.setblock.success", new Object[0]);
                }
            }
        } else {
            throw new ExceptionUsage("commands.setblock.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 4 ? a(astring, Block.REGISTRY.b()) : (astring.length == 6 ? a(astring, new String[] { "replace", "destroy", "keep"}) : null);
    }
}
