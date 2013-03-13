package net.minecraft.server;

import java.util.logging.Logger;

public interface IConsoleLogManager {

    Logger getLogger();

    void info(String s);

    void warning(String s);

    void warning(String s, Object... aobject);

    void warning(String s, Throwable throwable);

    void severe(String s);

    void severe(String s, Throwable throwable);
}
