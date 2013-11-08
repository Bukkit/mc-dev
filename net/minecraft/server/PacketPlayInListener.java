package net.minecraft.server;

public interface PacketPlayInListener extends PacketListener {

    void a(PacketPlayInArmAnimation packetplayinarmanimation);

    void a(PacketPlayInChat packetplayinchat);

    void a(PacketPlayInTabComplete packetplayintabcomplete);

    void a(PacketPlayInClientCommand packetplayinclientcommand);

    void a(PacketPlayInSettings packetplayinsettings);

    void a(PacketPlayInTransaction packetplayintransaction);

    void a(PacketPlayInEnchantItem packetplayinenchantitem);

    void a(PacketPlayInWindowClick packetplayinwindowclick);

    void a(PacketPlayInCloseWindow packetplayinclosewindow);

    void a(PacketPlayInCustomPayload packetplayincustompayload);

    void a(PacketPlayInUseEntity packetplayinuseentity);

    void a(PacketPlayInKeepAlive packetplayinkeepalive);

    void a(PacketPlayInFlying packetplayinflying);

    void a(PacketPlayInAbilities packetplayinabilities);

    void a(PacketPlayInBlockDig packetplayinblockdig);

    void a(PacketPlayInEntityAction packetplayinentityaction);

    void a(PacketPlayInSteerVehicle packetplayinsteervehicle);

    void a(PacketPlayInHeldItemSlot packetplayinhelditemslot);

    void a(PacketPlayInSetCreativeSlot packetplayinsetcreativeslot);

    void a(PacketPlayInUpdateSign packetplayinupdatesign);

    void a(PacketPlayInBlockPlace packetplayinblockplace);
}
