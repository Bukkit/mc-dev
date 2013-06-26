package net.minecraft.server;

public class TileEntityCommand extends TileEntity implements ICommandListener {

    private int a;
    private String b = "";
    private String c = "@";

    public TileEntityCommand() {}

    public void a(String s) {
        this.b = s;
        this.update();
    }

    public int a(World world) {
        if (world.isStatic) {
            return 0;
        } else {
            MinecraftServer minecraftserver = MinecraftServer.getServer();

            if (minecraftserver != null && minecraftserver.getEnableCommandBlock()) {
                ICommandHandler icommandhandler = minecraftserver.getCommandHandler();

                return icommandhandler.a(this, this.b);
            } else {
                return 0;
            }
        }
    }

    public String getName() {
        return this.c;
    }

    public void b(String s) {
        this.c = s;
    }

    public void sendMessage(ChatMessage chatmessage) {}

    public boolean a(int i, String s) {
        return i <= 2;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setString("Command", this.b);
        nbttagcompound.setInt("SuccessCount", this.a);
        nbttagcompound.setString("CustomName", this.c);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.b = nbttagcompound.getString("Command");
        this.a = nbttagcompound.getInt("SuccessCount");
        if (nbttagcompound.hasKey("CustomName")) {
            this.c = nbttagcompound.getString("CustomName");
        }
    }

    public ChunkCoordinates b() {
        return new ChunkCoordinates(this.x, this.y, this.z);
    }

    public World f_() {
        return this.getWorld();
    }

    public Packet getUpdatePacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        this.b(nbttagcompound);
        return new Packet132TileEntityData(this.x, this.y, this.z, 2, nbttagcompound);
    }

    public int f() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }
}
