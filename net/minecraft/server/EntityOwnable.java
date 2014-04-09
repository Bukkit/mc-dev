package net.minecraft.server;

public interface EntityOwnable {

    String getOwnerUUID();

    Entity getOwner();
}
