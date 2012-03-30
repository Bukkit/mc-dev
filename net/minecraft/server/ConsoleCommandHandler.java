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
        String[] astring = s.split(" ");
        String s1 = astring[0];
        String s2 = s.substring(s1.length()).trim();
        ICommandListener icommandlistener = servercommand.source;
        String s3 = icommandlistener.getName();
        ServerConfigurationManager serverconfigurationmanager = this.server.serverConfigurationManager;

        if (!s1.equalsIgnoreCase("help") && !s1.equalsIgnoreCase("?")) {
            if (s1.equalsIgnoreCase("list")) {
                icommandlistener.sendMessage("Connected players: " + serverconfigurationmanager.c());
            } else if (s1.equalsIgnoreCase("stop")) {
                this.print(s3, "Stopping the server..");
                this.server.safeShutdown();
            } else {
                int i;
                WorldServer worldserver;

                if (s1.equalsIgnoreCase("save-all")) {
                    this.print(s3, "Forcing save..");
                    if (serverconfigurationmanager != null) {
                        serverconfigurationmanager.savePlayers();
                    }

                    for (i = 0; i < this.server.worldServer.length; ++i) {
                        worldserver = this.server.worldServer[i];
                        boolean flag = worldserver.savingDisabled;

                        worldserver.savingDisabled = false;
                        worldserver.save(true, (IProgressUpdate) null);
                        worldserver.savingDisabled = flag;
                    }

                    this.print(s3, "Save complete.");
                } else if (s1.equalsIgnoreCase("save-off")) {
                    this.print(s3, "Disabling level saving..");

                    for (i = 0; i < this.server.worldServer.length; ++i) {
                        worldserver = this.server.worldServer[i];
                        worldserver.savingDisabled = true;
                    }
                } else if (s1.equalsIgnoreCase("save-on")) {
                    this.print(s3, "Enabling level saving..");

                    for (i = 0; i < this.server.worldServer.length; ++i) {
                        worldserver = this.server.worldServer[i];
                        worldserver.savingDisabled = false;
                    }
                } else if (s1.equalsIgnoreCase("op")) {
                    serverconfigurationmanager.addOp(s2);
                    this.print(s3, "Opping " + s2);
                    serverconfigurationmanager.a(s2, "\u00A7eYou are now op!");
                } else if (s1.equalsIgnoreCase("deop")) {
                    serverconfigurationmanager.removeOp(s2);
                    serverconfigurationmanager.a(s2, "\u00A7eYou are no longer op!");
                    this.print(s3, "De-opping " + s2);
                } else if (s1.equalsIgnoreCase("ban-ip")) {
                    serverconfigurationmanager.addIpBan(s2);
                    this.print(s3, "Banning ip " + s2);
                } else if (s1.equalsIgnoreCase("pardon-ip")) {
                    serverconfigurationmanager.removeIpBan(s2);
                    this.print(s3, "Pardoning ip " + s2);
                } else {
                    EntityPlayer entityplayer;

                    if (s1.equalsIgnoreCase("ban")) {
                        serverconfigurationmanager.addUserBan(s2);
                        this.print(s3, "Banning " + s2);
                        entityplayer = serverconfigurationmanager.i(s2);
                        if (entityplayer != null) {
                            entityplayer.netServerHandler.disconnect("Banned by admin");
                        }
                    } else if (s1.equalsIgnoreCase("pardon")) {
                        serverconfigurationmanager.removeUserBan(s2);
                        this.print(s3, "Pardoning " + s2);
                    } else {
                        String s4;
                        int j;

                        if (s1.equalsIgnoreCase("kick")) {
                            s4 = s2;
                            entityplayer = null;

                            for (j = 0; j < serverconfigurationmanager.players.size(); ++j) {
                                EntityPlayer entityplayer1 = (EntityPlayer) serverconfigurationmanager.players.get(j);

                                if (entityplayer1.name.equalsIgnoreCase(s4)) {
                                    entityplayer = entityplayer1;
                                }
                            }

                            if (entityplayer != null) {
                                entityplayer.netServerHandler.disconnect("Kicked by admin");
                                this.print(s3, "Kicking " + entityplayer.name);
                            } else {
                                icommandlistener.sendMessage("Can\'t find user " + s4 + ". No kick.");
                            }
                        } else if (s1.equalsIgnoreCase("tp")) {
                            if (astring.length == 3) {
                                EntityPlayer entityplayer2 = serverconfigurationmanager.i(astring[1]);

                                entityplayer = serverconfigurationmanager.i(astring[2]);
                                if (entityplayer2 == null) {
                                    icommandlistener.sendMessage("Can\'t find user " + astring[1] + ". No tp.");
                                } else if (entityplayer == null) {
                                    icommandlistener.sendMessage("Can\'t find user " + astring[2] + ". No tp.");
                                } else if (entityplayer2.dimension != entityplayer.dimension) {
                                    icommandlistener.sendMessage("User " + astring[1] + " and " + astring[2] + " are in different dimensions. No tp.");
                                } else {
                                    entityplayer2.netServerHandler.a(entityplayer.locX, entityplayer.locY, entityplayer.locZ, entityplayer.yaw, entityplayer.pitch);
                                    this.print(s3, "Teleporting " + astring[1] + " to " + astring[2] + ".");
                                }
                            } else {
                                icommandlistener.sendMessage("Syntax error, please provide a source and a target.");
                            }
                        } else if (s1.equalsIgnoreCase("give")) {
                            if (astring.length != 3 && astring.length != 4 && astring.length != 5) {
                                return;
                            }

                            s4 = astring[1];
                            entityplayer = serverconfigurationmanager.i(s4);
                            if (entityplayer != null) {
                                try {
                                    j = Integer.parseInt(astring[2]);
                                    if (Item.byId[j] != null) {
                                        this.print(s3, "Giving " + entityplayer.name + " some " + j);
                                        int k = 1;
                                        int l = 0;

                                        if (astring.length > 3) {
                                            k = this.a(astring[3], 1);
                                        }

                                        if (astring.length > 4) {
                                            l = this.a(astring[4], 1);
                                        }

                                        if (k < 1) {
                                            k = 1;
                                        }

                                        if (k > 64) {
                                            k = 64;
                                        }

                                        entityplayer.drop(new ItemStack(j, k, l));
                                    } else {
                                        icommandlistener.sendMessage("There\'s no item with id " + j);
                                    }
                                } catch (NumberFormatException numberformatexception) {
                                    icommandlistener.sendMessage("There\'s no item with id " + astring[2]);
                                }
                            } else {
                                icommandlistener.sendMessage("Can\'t find user " + s4);
                            }
                        } else if (s1.equalsIgnoreCase("xp")) {
                            if (astring.length != 3) {
                                return;
                            }

                            s4 = astring[1];
                            entityplayer = serverconfigurationmanager.i(s4);
                            if (entityplayer != null) {
                                try {
                                    j = Integer.parseInt(astring[2]);
                                    j = j > 5000 ? 5000 : j;
                                    this.print(s3, "Giving " + j + " orbs to " + entityplayer.name);
                                    entityplayer.giveExp(j);
                                } catch (NumberFormatException numberformatexception1) {
                                    icommandlistener.sendMessage("Invalid orb count: " + astring[2]);
                                }
                            } else {
                                icommandlistener.sendMessage("Can\'t find user " + s4);
                            }
                        } else if (s1.equalsIgnoreCase("gamemode")) {
                            if (astring.length != 3) {
                                return;
                            }

                            s4 = astring[1];
                            entityplayer = serverconfigurationmanager.i(s4);
                            if (entityplayer != null) {
                                try {
                                    j = Integer.parseInt(astring[2]);
                                    j = WorldSettings.a(j);
                                    if (entityplayer.itemInWorldManager.getGameMode() != j) {
                                        this.print(s3, "Setting " + entityplayer.name + " to game mode " + j);
                                        entityplayer.itemInWorldManager.setGameMode(j);
                                        entityplayer.netServerHandler.sendPacket(new Packet70Bed(3, j));
                                    } else {
                                        this.print(s3, entityplayer.name + " already has game mode " + j);
                                    }
                                } catch (NumberFormatException numberformatexception2) {
                                    icommandlistener.sendMessage("There\'s no game mode with id " + astring[2]);
                                }
                            } else {
                                icommandlistener.sendMessage("Can\'t find user " + s4);
                            }
                        } else if (s1.equalsIgnoreCase("time")) {
                            if (astring.length != 3) {
                                return;
                            }

                            s4 = astring[1];

                            try {
                                int i1 = Integer.parseInt(astring[2]);
                                WorldServer worldserver1;

                                if ("add".equalsIgnoreCase(s4)) {
                                    for (j = 0; j < this.server.worldServer.length; ++j) {
                                        worldserver1 = this.server.worldServer[j];
                                        worldserver1.setTimeAndFixTicklists(worldserver1.getTime() + (long) i1);
                                    }

                                    this.print(s3, "Added " + i1 + " to time");
                                } else if ("set".equalsIgnoreCase(s4)) {
                                    for (j = 0; j < this.server.worldServer.length; ++j) {
                                        worldserver1 = this.server.worldServer[j];
                                        worldserver1.setTimeAndFixTicklists((long) i1);
                                    }

                                    this.print(s3, "Set time to " + i1);
                                } else {
                                    icommandlistener.sendMessage("Unknown method, use either \"add\" or \"set\"");
                                }
                            } catch (NumberFormatException numberformatexception3) {
                                icommandlistener.sendMessage("Unable to convert time value, " + astring[2]);
                            }
                        } else if (s1.equalsIgnoreCase("say") && s2.length() > 0) {
                            a.info("[" + s3 + "] " + s2);
                            serverconfigurationmanager.sendAll(new Packet3Chat("\u00A7d[Server] " + s2));
                        } else if (s1.equalsIgnoreCase("tell")) {
                            if (astring.length >= 3) {
                                s = s.substring(s.indexOf(" ")).trim();
                                s = s.substring(s.indexOf(" ")).trim();
                                a.info("[" + s3 + "->" + astring[1] + "] " + s);
                                s = "\u00A77" + s3 + " whispers " + s;
                                a.info(s);
                                if (!serverconfigurationmanager.a(astring[1], (Packet) (new Packet3Chat(s)))) {
                                    icommandlistener.sendMessage("There\'s no player by that name online.");
                                }
                            }
                        } else if (s1.equalsIgnoreCase("whitelist")) {
                            this.a(s3, s, icommandlistener);
                        } else if (s1.equalsIgnoreCase("toggledownfall")) {
                            this.server.worldServer[0].j();
                            icommandlistener.sendMessage("Toggling rain and snow, hold on...");
                        } else if (s1.equalsIgnoreCase("banlist")) {
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
        icommandlistener.sendMessage("   toggledownfall            toggles rain on or off");
        icommandlistener.sendMessage("   xp <player> <amount>      gives the player the amount of xp (0-5000)");
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
