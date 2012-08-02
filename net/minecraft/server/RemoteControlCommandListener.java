package net.minecraft.server;

public class RemoteControlCommandListener implements ICommandListener {

    public static final RemoteControlCommandListener instance = new RemoteControlCommandListener();
    private StringBuffer b = new StringBuffer();

    public RemoteControlCommandListener() {}

    public void b() {
        this.b.setLength(0);
    }

    public String c() {
        return this.b.toString();
    }

    public String getName() {
        return "Rcon";
    }

    public void sendMessage(String s) {
        this.b.append(s);
    }

    public boolean b(String s) {
        return true;
    }

    public String a(String s, Object... aobject) {
        return LocaleLanguage.a().a(s, aobject);
    }
}
