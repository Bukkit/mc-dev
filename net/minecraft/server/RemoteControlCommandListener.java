package net.minecraft.server;

public class RemoteControlCommandListener implements ICommandListener {

    public static final RemoteControlCommandListener instance = new RemoteControlCommandListener();
    private StringBuffer b = new StringBuffer();

    public RemoteControlCommandListener() {}

    public void d() {
        this.b.setLength(0);
    }

    public String e() {
        return this.b.toString();
    }

    public String getName() {
        return "Rcon";
    }

    public void sendMessage(ChatMessage chatmessage) {
        this.b.append(chatmessage.toString());
    }

    public boolean a(int i, String s) {
        return true;
    }

    public ChunkCoordinates b() {
        return new ChunkCoordinates(0, 0, 0);
    }

    public World f_() {
        return MinecraftServer.getServer().f_();
    }
}
