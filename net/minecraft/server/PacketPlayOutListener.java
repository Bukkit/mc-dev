package net.minecraft.server;

public interface PacketPlayOutListener extends PacketListener {

    void a(PacketPlayOutSpawnEntity packetplayoutspawnentity);

    void a(PacketPlayOutSpawnEntityExperienceOrb packetplayoutspawnentityexperienceorb);

    void a(PacketPlayOutSpawnEntityWeather packetplayoutspawnentityweather);

    void a(PacketPlayOutSpawnEntityLiving packetplayoutspawnentityliving);

    void a(PacketPlayOutScoreboardObjective packetplayoutscoreboardobjective);

    void a(PacketPlayOutSpawnEntityPainting packetplayoutspawnentitypainting);

    void a(PacketPlayOutNamedEntitySpawn packetplayoutnamedentityspawn);

    void a(PacketPlayOutAnimation packetplayoutanimation);

    void a(PacketPlayOutStatistic packetplayoutstatistic);

    void a(PacketPlayOutBlockBreakAnimation packetplayoutblockbreakanimation);

    void a(PacketPlayOutOpenSignEditor packetplayoutopensigneditor);

    void a(PacketPlayOutTileEntityData packetplayouttileentitydata);

    void a(PacketPlayOutBlockAction packetplayoutblockaction);

    void a(PacketPlayOutBlockChange packetplayoutblockchange);

    void a(PacketPlayOutChat packetplayoutchat);

    void a(PacketPlayOutTabComplete packetplayouttabcomplete);

    void a(PacketPlayOutMultiBlockChange packetplayoutmultiblockchange);

    void a(PacketPlayOutMap packetplayoutmap);

    void a(PacketPlayOutTransaction packetplayouttransaction);

    void a(PacketPlayOutCloseWindow packetplayoutclosewindow);

    void a(PacketPlayOutWindowItems packetplayoutwindowitems);

    void a(PacketPlayOutOpenWindow packetplayoutopenwindow);

    void a(PacketPlayOutCraftProgressBar packetplayoutcraftprogressbar);

    void a(PacketPlayOutSetSlot packetplayoutsetslot);

    void a(PacketPlayOutCustomPayload packetplayoutcustompayload);

    void a(PacketPlayOutKickDisconnect packetplayoutkickdisconnect);

    void a(PacketPlayOutBed packetplayoutbed);

    void a(PacketPlayOutEntityStatus packetplayoutentitystatus);

    void a(PacketPlayOutAttachEntity packetplayoutattachentity);

    void a(PacketPlayOutExplosion packetplayoutexplosion);

    void a(PacketPlayOutGameStateChange packetplayoutgamestatechange);

    void a(PacketPlayOutKeepAlive packetplayoutkeepalive);

    void a(PacketPlayOutMapChunk packetplayoutmapchunk);

    void a(PacketPlayOutMapChunkBulk packetplayoutmapchunkbulk);

    void a(PacketPlayOutWorldEvent packetplayoutworldevent);

    void a(PacketPlayOutLogin packetplayoutlogin);

    void a(PacketPlayOutEntity packetplayoutentity);

    void a(PacketPlayOutPosition packetplayoutposition);

    void a(PacketPlayOutWorldParticles packetplayoutworldparticles);

    void a(PacketPlayOutAbilities packetplayoutabilities);

    void a(PacketPlayOutPlayerInfo packetplayoutplayerinfo);

    void a(PacketPlayOutEntityDestroy packetplayoutentitydestroy);

    void a(PacketPlayOutRemoveEntityEffect packetplayoutremoveentityeffect);

    void a(PacketPlayOutRespawn packetplayoutrespawn);

    void a(PacketPlayOutEntityHeadRotation packetplayoutentityheadrotation);

    void a(PacketPlayOutHeldItemSlot packetplayouthelditemslot);

    void a(PacketPlayOutScoreboardDisplayObjective packetplayoutscoreboarddisplayobjective);

    void a(PacketPlayOutEntityMetadata packetplayoutentitymetadata);

    void a(PacketPlayOutEntityVelocity packetplayoutentityvelocity);

    void a(PacketPlayOutEntityEquipment packetplayoutentityequipment);

    void a(PacketPlayOutExperience packetplayoutexperience);

    void a(PacketPlayOutUpdateHealth packetplayoutupdatehealth);

    void a(PacketPlayOutScoreboardTeam packetplayoutscoreboardteam);

    void a(PacketPlayOutScoreboardScore packetplayoutscoreboardscore);

    void a(PacketPlayOutSpawnPosition packetplayoutspawnposition);

    void a(PacketPlayOutUpdateTime packetplayoutupdatetime);

    void a(PacketPlayOutUpdateSign packetplayoutupdatesign);

    void a(PacketPlayOutNamedSoundEffect packetplayoutnamedsoundeffect);

    void a(PacketPlayOutCollect packetplayoutcollect);

    void a(PacketPlayOutEntityTeleport packetplayoutentityteleport);

    void a(PacketPlayOutUpdateAttributes packetplayoutupdateattributes);

    void a(PacketPlayOutEntityEffect packetplayoutentityeffect);
}
