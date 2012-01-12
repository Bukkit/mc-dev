package net.minecraft.server;

public class RemoteControlCommandListener implements ICommandListener {

    public static final RemoteControlCommandListener instance = new RemoteControlCommandListener();
    private StringBuffer b = new StringBuffer();

    public RemoteControlCommandListener() {}

    public void a() {
        this.b.setLength(0);
    }

    public String b() {
        return this.b.toString();
    }

    public void sendMessage(String s) {
        this.b.append(s);
    }

    public String getName() {
        return "Rcon";
    }
}
