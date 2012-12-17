package net.minecraft.server;

public abstract class Connection {

    public Connection() {}

    public abstract boolean a();

    public void a(Packet51MapChunk packet51mapchunk) {}

    public void onUnhandledPacket(Packet packet) {}

    public void a(String s, Object[] aobject) {}

    public void a(Packet255KickDisconnect packet255kickdisconnect) {
        this.onUnhandledPacket(packet255kickdisconnect);
    }

    public void a(Packet1Login packet1login) {
        this.onUnhandledPacket(packet1login);
    }

    public void a(Packet10Flying packet10flying) {
        this.onUnhandledPacket(packet10flying);
    }

    public void a(Packet52MultiBlockChange packet52multiblockchange) {
        this.onUnhandledPacket(packet52multiblockchange);
    }

    public void a(Packet14BlockDig packet14blockdig) {
        this.onUnhandledPacket(packet14blockdig);
    }

    public void a(Packet53BlockChange packet53blockchange) {
        this.onUnhandledPacket(packet53blockchange);
    }

    public void a(Packet20NamedEntitySpawn packet20namedentityspawn) {
        this.onUnhandledPacket(packet20namedentityspawn);
    }

    public void a(Packet30Entity packet30entity) {
        this.onUnhandledPacket(packet30entity);
    }

    public void a(Packet34EntityTeleport packet34entityteleport) {
        this.onUnhandledPacket(packet34entityteleport);
    }

    public void a(Packet15Place packet15place) {
        this.onUnhandledPacket(packet15place);
    }

    public void a(Packet16BlockItemSwitch packet16blockitemswitch) {
        this.onUnhandledPacket(packet16blockitemswitch);
    }

    public void a(Packet29DestroyEntity packet29destroyentity) {
        this.onUnhandledPacket(packet29destroyentity);
    }

    public void a(Packet22Collect packet22collect) {
        this.onUnhandledPacket(packet22collect);
    }

    public void a(Packet3Chat packet3chat) {
        this.onUnhandledPacket(packet3chat);
    }

    public void a(Packet23VehicleSpawn packet23vehiclespawn) {
        this.onUnhandledPacket(packet23vehiclespawn);
    }

    public void a(Packet18ArmAnimation packet18armanimation) {
        this.onUnhandledPacket(packet18armanimation);
    }

    public void a(Packet19EntityAction packet19entityaction) {
        this.onUnhandledPacket(packet19entityaction);
    }

    public void a(Packet2Handshake packet2handshake) {
        this.onUnhandledPacket(packet2handshake);
    }

    public void a(Packet253KeyRequest packet253keyrequest) {
        this.onUnhandledPacket(packet253keyrequest);
    }

    public void a(Packet252KeyResponse packet252keyresponse) {
        this.onUnhandledPacket(packet252keyresponse);
    }

    public void a(Packet24MobSpawn packet24mobspawn) {
        this.onUnhandledPacket(packet24mobspawn);
    }

    public void a(Packet4UpdateTime packet4updatetime) {
        this.onUnhandledPacket(packet4updatetime);
    }

    public void a(Packet6SpawnPosition packet6spawnposition) {
        this.onUnhandledPacket(packet6spawnposition);
    }

    public void a(Packet28EntityVelocity packet28entityvelocity) {
        this.onUnhandledPacket(packet28entityvelocity);
    }

    public void a(Packet40EntityMetadata packet40entitymetadata) {
        this.onUnhandledPacket(packet40entitymetadata);
    }

    public void a(Packet39AttachEntity packet39attachentity) {
        this.onUnhandledPacket(packet39attachentity);
    }

    public void a(Packet7UseEntity packet7useentity) {
        this.onUnhandledPacket(packet7useentity);
    }

    public void a(Packet38EntityStatus packet38entitystatus) {
        this.onUnhandledPacket(packet38entitystatus);
    }

    public void a(Packet8UpdateHealth packet8updatehealth) {
        this.onUnhandledPacket(packet8updatehealth);
    }

    public void a(Packet9Respawn packet9respawn) {
        this.onUnhandledPacket(packet9respawn);
    }

    public void a(Packet60Explosion packet60explosion) {
        this.onUnhandledPacket(packet60explosion);
    }

    public void a(Packet100OpenWindow packet100openwindow) {
        this.onUnhandledPacket(packet100openwindow);
    }

    public void handleContainerClose(Packet101CloseWindow packet101closewindow) {
        this.onUnhandledPacket(packet101closewindow);
    }

    public void a(Packet102WindowClick packet102windowclick) {
        this.onUnhandledPacket(packet102windowclick);
    }

    public void a(Packet103SetSlot packet103setslot) {
        this.onUnhandledPacket(packet103setslot);
    }

    public void a(Packet104WindowItems packet104windowitems) {
        this.onUnhandledPacket(packet104windowitems);
    }

    public void a(Packet130UpdateSign packet130updatesign) {
        this.onUnhandledPacket(packet130updatesign);
    }

    public void a(Packet105CraftProgressBar packet105craftprogressbar) {
        this.onUnhandledPacket(packet105craftprogressbar);
    }

    public void a(Packet5EntityEquipment packet5entityequipment) {
        this.onUnhandledPacket(packet5entityequipment);
    }

    public void a(Packet106Transaction packet106transaction) {
        this.onUnhandledPacket(packet106transaction);
    }

    public void a(Packet25EntityPainting packet25entitypainting) {
        this.onUnhandledPacket(packet25entitypainting);
    }

    public void a(Packet54PlayNoteBlock packet54playnoteblock) {
        this.onUnhandledPacket(packet54playnoteblock);
    }

    public void a(Packet200Statistic packet200statistic) {
        this.onUnhandledPacket(packet200statistic);
    }

    public void a(Packet17EntityLocationAction packet17entitylocationaction) {
        this.onUnhandledPacket(packet17entitylocationaction);
    }

    public void a(Packet70Bed packet70bed) {
        this.onUnhandledPacket(packet70bed);
    }

    public void a(Packet71Weather packet71weather) {
        this.onUnhandledPacket(packet71weather);
    }

    public void a(Packet131ItemData packet131itemdata) {
        this.onUnhandledPacket(packet131itemdata);
    }

    public void a(Packet61WorldEvent packet61worldevent) {
        this.onUnhandledPacket(packet61worldevent);
    }

    public void a(Packet254GetInfo packet254getinfo) {
        this.onUnhandledPacket(packet254getinfo);
    }

    public void a(Packet41MobEffect packet41mobeffect) {
        this.onUnhandledPacket(packet41mobeffect);
    }

    public void a(Packet42RemoveMobEffect packet42removemobeffect) {
        this.onUnhandledPacket(packet42removemobeffect);
    }

    public void a(Packet201PlayerInfo packet201playerinfo) {
        this.onUnhandledPacket(packet201playerinfo);
    }

    public void a(Packet0KeepAlive packet0keepalive) {
        this.onUnhandledPacket(packet0keepalive);
    }

    public void a(Packet43SetExperience packet43setexperience) {
        this.onUnhandledPacket(packet43setexperience);
    }

    public void a(Packet107SetCreativeSlot packet107setcreativeslot) {
        this.onUnhandledPacket(packet107setcreativeslot);
    }

    public void a(Packet26AddExpOrb packet26addexporb) {
        this.onUnhandledPacket(packet26addexporb);
    }

    public void a(Packet108ButtonClick packet108buttonclick) {}

    public void a(Packet250CustomPayload packet250custompayload) {}

    public void a(Packet35EntityHeadRotation packet35entityheadrotation) {
        this.onUnhandledPacket(packet35entityheadrotation);
    }

    public void a(Packet132TileEntityData packet132tileentitydata) {
        this.onUnhandledPacket(packet132tileentitydata);
    }

    public void a(Packet202Abilities packet202abilities) {
        this.onUnhandledPacket(packet202abilities);
    }

    public void a(Packet203TabComplete packet203tabcomplete) {
        this.onUnhandledPacket(packet203tabcomplete);
    }

    public void a(Packet204LocaleAndViewDistance packet204localeandviewdistance) {
        this.onUnhandledPacket(packet204localeandviewdistance);
    }

    public void a(Packet62NamedSoundEffect packet62namedsoundeffect) {
        this.onUnhandledPacket(packet62namedsoundeffect);
    }

    public void a(Packet55BlockBreakAnimation packet55blockbreakanimation) {
        this.onUnhandledPacket(packet55blockbreakanimation);
    }

    public void a(Packet205ClientCommand packet205clientcommand) {}

    public void a(Packet56MapChunkBulk packet56mapchunkbulk) {
        this.onUnhandledPacket(packet56mapchunkbulk);
    }

    public boolean b() {
        return false;
    }
}
