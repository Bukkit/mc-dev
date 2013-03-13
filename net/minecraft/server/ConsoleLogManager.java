package net.minecraft.server;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLogManager implements IConsoleLogManager {

    private final Logger a;
    private final String b;
    private final String c;
    private final String d;

    public ConsoleLogManager(String s, String s1, String s2) {
        this.a = Logger.getLogger(s);
        this.c = s;
        this.d = s1;
        this.b = s2;
        this.b();
    }

    private void b() {
        this.a.setUseParentHandlers(false);
        Handler[] ahandler = this.a.getHandlers();
        int i = ahandler.length;

        for (int j = 0; j < i; ++j) {
            Handler handler = ahandler[j];

            this.a.removeHandler(handler);
        }

        ConsoleLogFormatter consolelogformatter = new ConsoleLogFormatter(this, (EmptyClass3) null);
        ConsoleHandler consolehandler = new ConsoleHandler();

        consolehandler.setFormatter(consolelogformatter);
        this.a.addHandler(consolehandler);

        try {
            FileHandler filehandler = new FileHandler(this.b, true);

            filehandler.setFormatter(consolelogformatter);
            this.a.addHandler(filehandler);
        } catch (Exception exception) {
            this.a.log(Level.WARNING, "Failed to log " + this.c + " to " + this.b, exception);
        }
    }

    public Logger getLogger() {
        return this.a;
    }

    public void info(String s) {
        this.a.log(Level.INFO, s);
    }

    public void warning(String s) {
        this.a.log(Level.WARNING, s);
    }

    public void warning(String s, Object... aobject) {
        this.a.log(Level.WARNING, s, aobject);
    }

    public void warning(String s, Throwable throwable) {
        this.a.log(Level.WARNING, s, throwable);
    }

    public void severe(String s) {
        this.a.log(Level.SEVERE, s);
    }

    public void severe(String s, Throwable throwable) {
        this.a.log(Level.SEVERE, s, throwable);
    }

    static String a(ConsoleLogManager consolelogmanager) {
        return consolelogmanager.d;
    }
}
