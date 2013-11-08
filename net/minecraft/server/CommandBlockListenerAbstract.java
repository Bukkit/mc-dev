package net.minecraft.server;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class CommandBlockListenerAbstract implements ICommandListener {

    private static final SimpleDateFormat a = new SimpleDateFormat("HH:mm:ss");
    private int b;
    private boolean c = true;
    private IChatBaseComponent d = null;
    private String e = "";
    private String f = "@";

    public CommandBlockListenerAbstract() {}

    public int g() {
        return this.b;
    }

    public IChatBaseComponent h() {
        return this.d;
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.setString("Command", this.e);
        nbttagcompound.setInt("SuccessCount", this.b);
        nbttagcompound.setString("CustomName", this.f);
        if (this.d != null) {
            nbttagcompound.setString("LastOutput", ChatSerializer.a(this.d));
        }

        nbttagcompound.setBoolean("TrackOutput", this.c);
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.e = nbttagcompound.getString("Command");
        this.b = nbttagcompound.getInt("SuccessCount");
        if (nbttagcompound.hasKeyOfType("CustomName", 8)) {
            this.f = nbttagcompound.getString("CustomName");
        }

        if (nbttagcompound.hasKeyOfType("LastOutput", 8)) {
            this.d = ChatSerializer.a(nbttagcompound.getString("LastOutput"));
        }

        if (nbttagcompound.hasKeyOfType("TrackOutput", 1)) {
            this.c = nbttagcompound.getBoolean("TrackOutput");
        }
    }

    public boolean a(int i, String s) {
        return i <= 2;
    }

    public void a(String s) {
        this.e = s;
    }

    public String i() {
        return this.e;
    }

    public void a(World world) {
        if (world.isStatic) {
            this.b = 0;
        }

        MinecraftServer minecraftserver = MinecraftServer.getServer();

        if (minecraftserver != null && minecraftserver.getEnableCommandBlock()) {
            ICommandHandler icommandhandler = minecraftserver.getCommandHandler();

            this.b = icommandhandler.a(this, this.e);
        } else {
            this.b = 0;
        }
    }

    public String getName() {
        return this.f;
    }

    public IChatBaseComponent getScoreboardDisplayName() {
        return new ChatComponentText(this.getName());
    }

    public void b(String s) {
        this.f = s;
    }

    public void sendMessage(IChatBaseComponent ichatbasecomponent) {
        if (this.c && this.getWorld() != null && !this.getWorld().isStatic) {
            this.d = (new ChatComponentText("[" + a.format(new Date()) + "] ")).a(ichatbasecomponent);
            this.e();
        }
    }

    public abstract void e();

    public void b(IChatBaseComponent ichatbasecomponent) {
        this.d = ichatbasecomponent;
    }
}
