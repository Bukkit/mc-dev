package net.minecraft.server;

public class CommandNetstat extends CommandAbstract {

    public CommandNetstat() {}

    public String getCommand() {
        return "netstat";
    }

    public int a() {
        return 0;
    }

    public String c(ICommandListener icommandlistener) {
        return "commands.players.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        if (icommandlistener instanceof EntityHuman) {
            icommandlistener.sendMessage(new ChatComponentText("Command is not available for players"));
        } else {
            if (astring.length > 0 && astring[0].length() > 1) {
                if ("hottest-read".equals(astring[0])) {
                    icommandlistener.sendMessage(new ChatComponentText(NetworkManager.h.e().toString()));
                } else if ("hottest-write".equals(astring[0])) {
                    icommandlistener.sendMessage(new ChatComponentText(NetworkManager.h.g().toString()));
                } else if ("most-read".equals(astring[0])) {
                    icommandlistener.sendMessage(new ChatComponentText(NetworkManager.h.f().toString()));
                } else if ("most-write".equals(astring[0])) {
                    icommandlistener.sendMessage(new ChatComponentText(NetworkManager.h.h().toString()));
                } else {
                    PacketStatistics packetstatistics;
                    int i;

                    if ("packet-read".equals(astring[0])) {
                        if (astring.length > 1 && astring[1].length() > 0) {
                            try {
                                i = Integer.parseInt(astring[1].trim());
                                packetstatistics = NetworkManager.h.a(i);
                                this.a(icommandlistener, i, packetstatistics);
                            } catch (Exception exception) {
                                icommandlistener.sendMessage(new ChatComponentText("Packet " + astring[1] + " not found!"));
                            }
                        } else {
                            icommandlistener.sendMessage(new ChatComponentText("Packet id is missing"));
                        }
                    } else if ("packet-write".equals(astring[0])) {
                        if (astring.length > 1 && astring[1].length() > 0) {
                            try {
                                i = Integer.parseInt(astring[1].trim());
                                packetstatistics = NetworkManager.h.b(i);
                                this.a(icommandlistener, i, packetstatistics);
                            } catch (Exception exception1) {
                                icommandlistener.sendMessage(new ChatComponentText("Packet " + astring[1] + " not found!"));
                            }
                        } else {
                            icommandlistener.sendMessage(new ChatComponentText("Packet id is missing"));
                        }
                    } else if ("read-count".equals(astring[0])) {
                        icommandlistener.sendMessage(new ChatComponentText("total-read-count" + String.valueOf(NetworkManager.h.c())));
                    } else if ("write-count".equals(astring[0])) {
                        icommandlistener.sendMessage(new ChatComponentText("total-write-count" + String.valueOf(NetworkManager.h.d())));
                    } else {
                        icommandlistener.sendMessage(new ChatComponentText("Unrecognized: " + astring[0]));
                    }
                }
            } else {
                String s = "reads: " + NetworkManager.h.a();

                s = s + ", writes: " + NetworkManager.h.b();
                icommandlistener.sendMessage(new ChatComponentText(s));
            }
        }
    }

    private void a(ICommandListener icommandlistener, int i, PacketStatistics packetstatistics) {
        if (packetstatistics != null) {
            icommandlistener.sendMessage(new ChatComponentText(packetstatistics.toString()));
        } else {
            icommandlistener.sendMessage(new ChatComponentText("Packet " + i + " not found!"));
        }
    }
}
