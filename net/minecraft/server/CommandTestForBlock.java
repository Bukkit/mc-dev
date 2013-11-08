package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class CommandTestForBlock extends CommandAbstract {

    public CommandTestForBlock() {}

    public String c() {
        return "testforblock";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.testforblock.usage";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length >= 4) {
            int i = icommandlistener.getChunkCoordinates().x;
            int j = icommandlistener.getChunkCoordinates().y;
            int k = icommandlistener.getChunkCoordinates().z;

            i = MathHelper.floor(a(icommandlistener, (double) i, astring[0]));
            j = MathHelper.floor(a(icommandlistener, (double) j, astring[1]));
            k = MathHelper.floor(a(icommandlistener, (double) k, astring[2]));
            Block block = Block.b(astring[3]);

            if (block == null) {
                throw new ExceptionInvalidNumber("commands.setblock.notFound", new Object[] { astring[3]});
            } else {
                int l = -1;

                if (astring.length >= 5) {
                    l = a(icommandlistener, astring[4], -1, 15);
                }

                World world = icommandlistener.getWorld();

                if (!world.isLoaded(i, j, k)) {
                    throw new CommandException("commands.testforblock.outOfWorld", new Object[0]);
                } else {
                    NBTTagCompound nbttagcompound = new NBTTagCompound();
                    boolean flag = false;

                    if (astring.length >= 6 && block.isTileEntity()) {
                        String s = a(icommandlistener, astring, 5).c();

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

                    Block block1 = world.getType(i, j, k);

                    if (block1 != block) {
                        throw new CommandException("commands.testforblock.failed.tile", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), block1.getName(), block.getName()});
                    } else {
                        if (l > -1) {
                            int i1 = world.getData(i, j, k);

                            if (i1 != l) {
                                throw new CommandException("commands.testforblock.failed.data", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(i1), Integer.valueOf(l)});
                            }
                        }

                        if (flag) {
                            TileEntity tileentity = world.getTileEntity(i, j, k);

                            if (tileentity == null) {
                                throw new CommandException("commands.testforblock.failed.tileEntity", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k)});
                            }

                            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                            tileentity.b(nbttagcompound1);
                            if (!this.a((NBTBase) nbttagcompound, (NBTBase) nbttagcompound1)) {
                                throw new CommandException("commands.testforblock.failed.nbt", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k)});
                            }
                        }

                        icommandlistener.sendMessage(new ChatMessage("commands.testforblock.success", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k)}));
                    }
                }
            }
        } else {
            throw new ExceptionUsage("commands.testforblock.usage", new Object[0]);
        }
    }

    public boolean a(NBTBase nbtbase, NBTBase nbtbase1) {
        if (nbtbase == nbtbase1) {
            return true;
        } else if (nbtbase == null) {
            return true;
        } else if (nbtbase1 == null) {
            return false;
        } else if (!nbtbase.getClass().equals(nbtbase1.getClass())) {
            return false;
        } else if (nbtbase instanceof NBTTagCompound) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbtbase;
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbtbase1;
            Iterator iterator = nbttagcompound.c().iterator();

            String s;
            NBTBase nbtbase2;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                s = (String) iterator.next();
                nbtbase2 = nbttagcompound.get(s);
            } while (this.a(nbtbase2, nbttagcompound1.get(s)));

            return false;
        } else {
            return nbtbase.equals(nbtbase1);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 4 ? a(astring, (Iterable) Block.REGISTRY.b()) : null;
    }
}
