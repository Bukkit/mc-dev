package net.minecraft.server;

import java.util.List;
import java.util.Map;

public interface ICommandHandler {

    void a(ICommandListener icommandlistener, String s);

    List b(ICommandListener icommandlistener, String s);

    List a(ICommandListener icommandlistener);

    Map a();
}
