package net.minecraft.server;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLogManager {

    public static Logger a = Logger.getLogger("Minecraft");

    public ConsoleLogManager() {}

    public static void a() {
        ConsoleLogFormatter consolelogformatter = new ConsoleLogFormatter();

        a.setUseParentHandlers(false);
        ConsoleHandler consolehandler = new ConsoleHandler();

        consolehandler.setFormatter(consolelogformatter);
        a.addHandler(consolehandler);

        try {
            FileHandler filehandler = new FileHandler("server.log");

            filehandler.setFormatter(consolelogformatter);
            a.addHandler(filehandler);
        } catch (Exception exception) {
            a.log(Level.WARNING, "Failed to log to server.log", exception);
        }
    }
}
