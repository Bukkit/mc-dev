package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class CommandWeather extends CommandAbstract {

    public CommandWeather() {}

    public String c() {
        return "weather";
    }

    public int a() {
        return 2;
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 1) {
            throw new ExceptionUsage("commands.weather.usage", new Object[0]);
        } else {
            int i = (300 + (new Random()).nextInt(600)) * 20;

            if (astring.length >= 2) {
                i = a(icommandlistener, astring[1], 1, 1000000) * 20;
            }

            WorldServer worldserver = MinecraftServer.getServer().worldServer[0];
            WorldData worlddata = worldserver.getWorldData();

            worlddata.setWeatherDuration(i);
            worlddata.setThunderDuration(i);
            if ("clear".equalsIgnoreCase(astring[0])) {
                worlddata.setStorm(false);
                worlddata.setThundering(false);
                a(icommandlistener, "commands.weather.clear", new Object[0]);
            } else if ("rain".equalsIgnoreCase(astring[0])) {
                worlddata.setStorm(true);
                worlddata.setThundering(false);
                a(icommandlistener, "commands.weather.rain", new Object[0]);
            } else if ("thunder".equalsIgnoreCase(astring[0])) {
                worlddata.setStorm(true);
                worlddata.setThundering(true);
                a(icommandlistener, "commands.weather.thunder", new Object[0]);
            }
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "clear", "rain", "thunder"}) : null;
    }
}
