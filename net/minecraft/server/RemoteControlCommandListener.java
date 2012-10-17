package net.minecraft.server;

public class RemoteControlCommandListener implements ICommandListener {

    public static final RemoteControlCommandListener instance = new RemoteControlCommandListener();
    private StringBuffer b = new StringBuffer();

    public RemoteControlCommandListener() {}

    public void c() {
        this.b.setLength(0);
    }

    public String d() {
        return this.b.toString();
    }

    public String getName() {
        return "Rcon";
    }

    public void sendMessage(String s) {
        this.b.append(s);
    }

    public boolean a(int i, String s) {
        return true;
    }

    public String a(String s, Object... aobject) {
        return LocaleLanguage.a().a(s, aobject);
    }

    public ChunkCoordinates b() {
        return new ChunkCoordinates(0, 0, 0);
    }
}
