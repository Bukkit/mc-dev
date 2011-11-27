package net.minecraft.server;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class ConsoleCommandHandler {

    private static Logger a = Logger.getLogger("Minecraft");
    private MinecraftServer server;

    public ConsoleCommandHandler(MinecraftServer minecraftserver) {
        this.server = minecraftserver;
    }

    public synchronized void handle(ServerCommand servercommand) {
        String s = servercommand.command;
        ICommandListener icommandlistener = servercommand.b;
        String s1 = icommandlistener.getName();
        ServerConfigurationManager serverconfigurationmanager = this.server.serverConfigurationManager;

        if (!s.toLowerCase().startsWith("help") && !s.toLowerCase().startsWith("?")) {
            if (s.toLowerCase().startsWith("list")) {
                icommandlistener.sendMessage("Connected players: " + serverconfigurationmanager.c());
            } else if (s.toLowerCase().startsWith("stop")) {
                this.print(s1, "Stopping the server..");
                this.server.safeShutdown();
            } else {
                int i;
                WorldServer worldserver;

                if (s.toLowerCase().startsWith("save-all")) {
                    this.print(s1, "Forcing save..");
                    if (serverconfigurationmanager != null) {
                        serverconfigurationmanager.savePlayers();
                    }

                    for (i = 0; i < this.server.worldServer.length; ++i) {
                        worldserver = this.server.worldServer[i];
                        worldserver.save(true, (IProgressUpdate) null);
                    }

                    this.print(s1, "Save complete.");
                } else if (s.toLowerCase().startsWith("save-off")) {
                    this.print(s1, "Disabling level saving..");

                    for (i = 0; i < this.server.worldServer.length; ++i) {
                        worldserver = this.server.worldServer[i];
                        worldserver.savingDisabled = true;
                    }
                } else if (s.toLowerCase().startsWith("save-on")) {
                    this.print(s1, "Enabling level saving..");

                    for (i = 0; i < this.server.worldServer.length; ++i) {
                        worldserver = this.server.worldServer[i];
                        worldserver.savingDisabled = false;
                    }
                } else {
                    String s2;

                    if (s.toLowerCase().startsWith("op ")) {
                        s2 = s.substring(s.indexOf(" ")).trim();
                        serverconfigurationmanager.addOp(s2);
                        this.print(s1, "Opping " + s2);
                        serverconfigurationmanager.a(s2, "\u00A7eYou are now op!");
                    } else if (s.toLowerCase().startsWith("deop ")) {
                        s2 = s.substring(s.indexOf(" ")).trim();
                        serverconfigurationmanager.removeOp(s2);
                        serverconfigurationmanager.a(s2, "\u00A7eYou are no longer op!");
                        this.print(s1, "De-opping " + s2);
                    } else if (s.toLowerCase().startsWith("ban-ip ")) {
                        s2 = s.substring(s.indexOf(" ")).trim();
                        serverconfigurationmanager.addIpBan(s2);
                        this.print(s1, "Banning ip " + s2);
                    } else if (s.toLowerCase().startsWith("pardon-ip ")) {
                        s2 = s.substring(s.indexOf(" ")).trim();
                        serverconfigurationmanager.removeIpBan(s2);
                        this.print(s1, "Pardoning ip " + s2);
                    } else {
                        EntityPlayer entityplayer;

                        if (s.toLowerCase().startsWith("ban ")) {
                            s2 = s.substring(s.indexOf(" ")).trim();
                            serverconfigurationmanager.addUserBan(s2);
                            this.print(s1, "Banning " + s2);
                            entityplayer = serverconfigurationmanager.i(s2);
                            if (entityplayer != null) {
                                entityplayer.netServerHandler.disconnect("Banned by admin");
                            }
                        } else if (s.toLowerCase().startsWith("pardon ")) {
                            s2 = s.substring(s.indexOf(" ")).trim();
                            serverconfigurationmanager.removeUserBan(s2);
                            this.print(s1, "Pardoning " + s2);
                        } else {
                            int j;

                            if (s.toLowerCase().startsWith("kick ")) {
                                s2 = s.substring(s.indexOf(" ")).trim();
                                entityplayer = null;

                                for (j = 0; j < serverconfigurationmanager.players.size(); ++j) {
                                    EntityPlayer entityplayer1 = (EntityPlayer) serverconfigurationmanager.players.get(j);

                                    if (entityplayer1.name.equalsIgnoreCase(s2)) {
                                        entityplayer = entityplayer1;
                                    }
                                }

                                if (entityplayer != null) {
                                    entityplayer.netServerHandler.disconnect("Kicked by admin");
                                    this.print(s1, "Kicking " + entityplayer.name);
                                } else {
                                    icommandlistener.sendMessage("Can\'t find user " + s2 + ". No kick.");
                                }
                            } else {
                                EntityPlayer entityplayer2;
                                String[] astring;

                                if (s.toLowerCase().startsWith("tp ")) {
                                    astring = s.split(" ");
                                    if (astring.length == 3) {
                                        entityplayer = serverconfigurationmanager.i(astring[1]);
                                        entityplayer2 = serverconfigurationmanager.i(astring[2]);
                                        if (entityplayer == null) {
                                            icommandlistener.sendMessage("Can\'t find user " + astring[1] + ". No tp.");
                                        } else if (entityplayer2 == null) {
                                            icommandlistener.sendMessage("Can\'t find user " + astring[2] + ". No tp.");
                                        } else if (entityplayer.dimension != entityplayer2.dimension) {
                                            icommandlistener.sendMessage("User " + astring[1] + " and " + astring[2] + " are in different dimensions. No tp.");
                                        } else {
                                            entityplayer.netServerHandler.a(entityplayer2.locX, entityplayer2.locY, entityplayer2.locZ, entityplayer2.yaw, entityplayer2.pitch);
                                            this.print(s1, "Teleporting " + astring[1] + " to " + astring[2] + ".");
                                        }
                                    } else {
                                        icommandlistener.sendMessage("Syntax error, please provice a source and a target.");
                                    }
                                } else {
                                    int k;
                                    String s3;

                                    if (s.toLowerCase().startsWith("give ")) {
                                        astring = s.split(" ");
                                        if (astring.length != 3 && astring.length != 4 && astring.length != 5) {
                                            return;
                                        }

                                        s3 = astring[1];
                                        entityplayer2 = serverconfigurationmanager.i(s3);
                                        if (entityplayer2 != null) {
                                            try {
                                                k = Integer.parseInt(astring[2]);
                                                if (Item.byId[k] != null) {
                                                    this.print(s1, "Giving " + entityplayer2.name + " some " + k);
                                                    int l = 1;
                                                    int i1 = 0;

                                                    if (astring.length > 3) {
                                                        l = this.a(astring[3], 1);
                                                    }

                                                    if (astring.length > 4) {
                                                        i1 = this.a(astring[4], 1);
                                                    }

                                                    if (l < 1) {
                                                        l = 1;
                                                    }

                                                    if (l > 64) {
                                                        l = 64;
                                                    }

                                                    entityplayer2.b(new ItemStack(k, l, i1));
                                                } else {
                                                    icommandlistener.sendMessage("There\'s no item with id " + k);
                                                }
                                            } catch (NumberFormatException numberformatexception) {
                                                icommandlistener.sendMessage("There\'s no item with id " + astring[2]);
                                            }
                                        } else {
                                            icommandlistener.sendMessage("Can\'t find user " + s3);
                                        }
                                    } else if (s.toLowerCase().startsWith("xp")) {
                                        astring = s.split(" ");
                                        if (astring.length != 3) {
                                            return;
                                        }

                                        s3 = astring[1];
                                        entityplayer2 = serverconfigurationmanager.i(s3);
                                        if (entityplayer2 != null) {
                                            try {
                                                k = Integer.parseInt(astring[2]);
                                                k = k > 5000 ? 5000 : k;
                                                this.print(s1, "Giving " + k + " orbs to " + entityplayer2.name);
                                                entityplayer2.giveExp(k);
                                            } catch (NumberFormatException numberformatexception1) {
                                                icommandlistener.sendMessage("Invalid orb count: " + astring[2]);
                                            }
                                        } else {
                                            icommandlistener.sendMessage("Can\'t find user " + s3);
                                        }
                                    } else if (s.toLowerCase().startsWith("gamemode ")) {
                                        astring = s.split(" ");
                                        if (astring.length != 3) {
                                            return;
                                        }

                                        s3 = astring[1];
                                        entityplayer2 = serverconfigurationmanager.i(s3);
                                        if (entityplayer2 != null) {
                                            try {
                                                k = Integer.parseInt(astring[2]);
                                                k = WorldSettings.a(k);
                                                if (entityplayer2.itemInWorldManager.a() != k) {
                                                    this.print(s1, "Setting " + entityplayer2.name + " to game mode " + k);
                                                    entityplayer2.itemInWorldManager.a(k);
                                                    entityplayer2.netServerHandler.sendPacket(new Packet70Bed(3, k));
                                                } else {
                                                    this.print(s1, entityplayer2.name + " already has game mode " + k);
                                                }
                                            } catch (NumberFormatException numberformatexception2) {
                                                icommandlistener.sendMessage("There\'s no game mode with id " + astring[2]);
                                            }
                                        } else {
                                            icommandlistener.sendMessage("Can\'t find user " + s3);
                                        }
                                    } else if (s.toLowerCase().startsWith("time ")) {
                                        astring = s.split(" ");
                                        if (astring.length != 3) {
                                            return;
                                        }

                                        s3 = astring[1];

                                        try {
                                            j = Integer.parseInt(astring[2]);
                                            WorldServer worldserver1;

                                            if ("add".equalsIgnoreCase(s3)) {
                                                for (k = 0; k < this.server.worldServer.length; ++k) {
                                                    worldserver1 = this.server.worldServer[k];
                                                    worldserver1.setTimeAndFixTicklists(worldserver1.getTime() + (long) j);
                                                }

                                                this.print(s1, "Added " + j + " to time");
                                            } else if ("set".equalsIgnoreCase(s3)) {
                                                for (k = 0; k < this.server.worldServer.length; ++k) {
                                                    worldserver1 = this.server.worldServer[k];
                                                    worldserver1.setTimeAndFixTicklists((long) j);
                                                }

                                                this.print(s1, "Set time to " + j);
                                            } else {
                                                icommandlistener.sendMessage("Unknown method, use either \"add\" or \"set\"");
                                            }
                                        } catch (NumberFormatException numberformatexception3) {
                                            icommandlistener.sendMessage("Unable to convert time value, " + astring[2]);
                                        }
                                    } else if (s.toLowerCase().startsWith("say ")) {
                                        s = s.substring(s.indexOf(" ")).trim();
                                        a.info("[" + s1 + "] " + s);
                                        serverconfigurationmanager.sendAll(new Packet3Chat("\u00A7d[Server] " + s));
                                    } else if (s.toLowerCase().startsWith("tell ")) {
                                        astring = s.split(" ");
                                        if (astring.length >= 3) {
                                            s = s.substring(s.indexOf(" ")).trim();
                                            s = s.substring(s.indexOf(" ")).trim();
                                            a.info("[" + s1 + "->" + astring[1] + "] " + s);
                                            s = "\u00A77" + s1 + " whispers " + s;
                                            a.info(s);
                                            if (!serverconfigurationmanager.a(astring[1], (Packet) (new Packet3Chat(s)))) {
                                                icommandlistener.sendMessage("There\'s no player by that name online.");
                                            }
                                        }
                                    } else if (s.toLowerCase().startsWith("whitelist ")) {
                                        this.a(s1, s, icommandlistener);
                                    } else if (s.toLowerCase().startsWith("toggledownfall")) {
                                        this.server.worldServer[0].j();
                                        icommandlistener.sendMessage("Toggling rain and snow, hold on...");
                                    } else if (s.toLowerCase().startsWith("banlist")) {
                                        astring = s.split(" ");
                                        if (astring.length == 2) {
                                            if (astring[1].equals("ips")) {
                                                icommandlistener.sendMessage("IP Ban list:" + this.a(this.server.q(), ", "));
                                            }
                                        } else {
                                            icommandlistener.sendMessage("Ban list:" + this.a(this.server.r(), ", "));
                                        }
                                    } else {
                                        a.info("Unknown console command. Type \"help\" for help.");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            this.a(icommandlistener);
        }
    }

    private void a(String s, String s1, ICommandListener icommandlistener) {
        String[] astring = s1.split(" ");

        if (astring.length >= 2) {
            String s2 = astring[1].toLowerCase();

            if ("on".equals(s2)) {
                this.print(s, "Turned on white-listing");
                this.server.propertyManager.setBoolean("white-list", true);
            } else if ("off".equals(s2)) {
                this.print(s, "Turned off white-listing");
                this.server.propertyManager.setBoolean("white-list", false);
            } else if ("list".equals(s2)) {
                Set set = this.server.serverConfigurationManager.getWhitelisted();
                String s3 = "";

                String s4;

                for (Iterator iterator = set.iterator(); iterator.hasNext(); s3 = s3 + s4 + " ") {
                    s4 = (String) iterator.next();
                }

                icommandlistener.sendMessage("White-listed players: " + s3);
            } else {
                String s5;

                if ("add".equals(s2) && astring.length == 3) {
                    s5 = astring[2].toLowerCase();
                    this.server.serverConfigurationManager.addWhitelist(s5);
                    this.print(s, "Added " + s5 + " to white-list");
                } else if ("remove".equals(s2) && astring.length == 3) {
                    s5 = astring[2].toLowerCase();
                    this.server.serverConfigurationManager.removeWhitelist(s5);
                    this.print(s, "Removed " + s5 + " from white-list");
                } else if ("reload".equals(s2)) {
                    this.server.serverConfigurationManager.reloadWhitelist();
                    this.print(s, "Reloaded white-list from file");
                }
            }
        }
    }

    private void a(ICommandListener icommandlistener) {
        icommandlistener.sendMessage("To run the server without a gui, start it like this:");
        icommandlistener.sendMessage("   java -Xmx1024M -Xms1024M -jar minecraft_server.jar nogui");
        icommandlistener.sendMessage("Console commands:");
        icommandlistener.sendMessage("   help  or  ?               shows this message");
        icommandlistener.sendMessage("   kick <player>             removes a player from the server");
        icommandlistener.sendMessage("   ban <player>              bans a player from the server");
        icommandlistener.sendMessage("   pardon <player>           pardons a banned player so that they can connect again");
        icommandlistener.sendMessage("   ban-ip <ip>               bans an IP address from the server");
        icommandlistener.sendMessage("   pardon-ip <ip>            pardons a banned IP address so that they can connect again");
        icommandlistener.sendMessage("   op <player>               turns a player into an op");
        icommandlistener.sendMessage("   deop <player>             removes op status from a player");
        icommandlistener.sendMessage("   tp <player1> <player2>    moves one player to the same location as another player");
        icommandlistener.sendMessage("   give <player> <id> [num]  gives a player a resource");
        icommandlistener.sendMessage("   tell <player> <message>   sends a private message to a player");
        icommandlistener.sendMessage("   stop                      gracefully stops the server");
        icommandlistener.sendMessage("   save-all                  forces a server-wide level save");
        icommandlistener.sendMessage("   save-off                  disables terrain saving (useful for backup scripts)");
        icommandlistener.sendMessage("   save-on                   re-enables terrain saving");
        icommandlistener.sendMessage("   list                      lists all currently connected players");
        icommandlistener.sendMessage("   say <message>             broadcasts a message to all players");
        icommandlistener.sendMessage("   time <add|set> <amount>   adds to or sets the world time (0-24000)");
        icommandlistener.sendMessage("   gamemode <player> <mode>  sets player\'s game mode (0 or 1)");
    }

    private void print(String s, String s1) {
        String s2 = s + ": " + s1;

        this.server.serverConfigurationManager.j("\u00A77(" + s2 + ")");
        a.info(s2);
    }

    private int a(String s, int i) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException numberformatexception) {
            return i;
        }
    }

    private String a(String[] astring, String s) {
        int i = astring.length;

        if (0 == i) {
            return "";
        } else {
            StringBuilder stringbuilder = new StringBuilder();

            stringbuilder.append(astring[0]);

            for (int j = 1; j < i; ++j) {
                stringbuilder.append(s).append(astring[j]);
            }

            return stringbuilder.toString();
        }
    }
}
