package net.minecraft.server;

public abstract class NetHandler {

    public NetHandler() {}

    public abstract boolean c();

    public void a(Packet51MapChunk packet51mapchunk) {}

    public void a(Packet packet) {}

    public void a(String s, Object[] aobject) {}

    public void a(Packet255KickDisconnect packet255kickdisconnect) {
        this.a((Packet) packet255kickdisconnect);
    }

    public void a(Packet1Login packet1login) {
        this.a((Packet) packet1login);
    }

    public void a(Packet10Flying packet10flying) {
        this.a((Packet) packet10flying);
    }

    public void a(Packet52MultiBlockChange packet52multiblockchange) {
        this.a((Packet) packet52multiblockchange);
    }

    public void a(Packet14BlockDig packet14blockdig) {
        this.a((Packet) packet14blockdig);
    }

    public void a(Packet53BlockChange packet53blockchange) {
        this.a((Packet) packet53blockchange);
    }

    public void a(Packet50PreChunk packet50prechunk) {
        this.a((Packet) packet50prechunk);
    }

    public void a(Packet20NamedEntitySpawn packet20namedentityspawn) {
        this.a((Packet) packet20namedentityspawn);
    }

    public void a(Packet30Entity packet30entity) {
        this.a((Packet) packet30entity);
    }

    public void a(Packet34EntityTeleport packet34entityteleport) {
        this.a((Packet) packet34entityteleport);
    }

    public void a(Packet15Place packet15place) {
        this.a((Packet) packet15place);
    }

    public void a(Packet16BlockItemSwitch packet16blockitemswitch) {
        this.a((Packet) packet16blockitemswitch);
    }

    public void a(Packet29DestroyEntity packet29destroyentity) {
        this.a((Packet) packet29destroyentity);
    }

    public void a(Packet21PickupSpawn packet21pickupspawn) {
        this.a((Packet) packet21pickupspawn);
    }

    public void a(Packet22Collect packet22collect) {
        this.a((Packet) packet22collect);
    }

    public void a(Packet3Chat packet3chat) {
        this.a((Packet) packet3chat);
    }

    public void a(Packet23VehicleSpawn packet23vehiclespawn) {
        this.a((Packet) packet23vehiclespawn);
    }

    public void a(Packet18ArmAnimation packet18armanimation) {
        this.a((Packet) packet18armanimation);
    }

    public void a(Packet19EntityAction packet19entityaction) {
        this.a((Packet) packet19entityaction);
    }

    public void a(Packet2Handshake packet2handshake) {
        this.a((Packet) packet2handshake);
    }

    public void a(Packet24MobSpawn packet24mobspawn) {
        this.a((Packet) packet24mobspawn);
    }

    public void a(Packet4UpdateTime packet4updatetime) {
        this.a((Packet) packet4updatetime);
    }

    public void a(Packet6SpawnPosition packet6spawnposition) {
        this.a((Packet) packet6spawnposition);
    }

    public void a(Packet28EntityVelocity packet28entityvelocity) {
        this.a((Packet) packet28entityvelocity);
    }

    public void a(Packet40EntityMetadata packet40entitymetadata) {
        this.a((Packet) packet40entitymetadata);
    }

    public void a(Packet39AttachEntity packet39attachentity) {
        this.a((Packet) packet39attachentity);
    }

    public void a(Packet7UseEntity packet7useentity) {
        this.a((Packet) packet7useentity);
    }

    public void a(Packet38EntityStatus packet38entitystatus) {
        this.a((Packet) packet38entitystatus);
    }

    public void a(Packet8UpdateHealth packet8updatehealth) {
        this.a((Packet) packet8updatehealth);
    }

    public void a(Packet9Respawn packet9respawn) {
        this.a((Packet) packet9respawn);
    }

    public void a(Packet60Explosion packet60explosion) {
        this.a((Packet) packet60explosion);
    }

    public void a(Packet100OpenWindow packet100openwindow) {
        this.a((Packet) packet100openwindow);
    }

    public void a(Packet101CloseWindow packet101closewindow) {
        this.a((Packet) packet101closewindow);
    }

    public void a(Packet102WindowClick packet102windowclick) {
        this.a((Packet) packet102windowclick);
    }

    public void a(Packet103SetSlot packet103setslot) {
        this.a((Packet) packet103setslot);
    }

    public void a(Packet104WindowItems packet104windowitems) {
        this.a((Packet) packet104windowitems);
    }

    public void a(Packet130UpdateSign packet130updatesign) {
        this.a((Packet) packet130updatesign);
    }

    public void a(Packet105CraftProgressBar packet105craftprogressbar) {
        this.a((Packet) packet105craftprogressbar);
    }

    public void a(Packet5EntityEquipment packet5entityequipment) {
        this.a((Packet) packet5entityequipment);
    }

    public void a(Packet106Transaction packet106transaction) {
        this.a((Packet) packet106transaction);
    }

    public void a(Packet25EntityPainting packet25entitypainting) {
        this.a((Packet) packet25entitypainting);
    }

    public void a(Packet54PlayNoteBlock packet54playnoteblock) {
        this.a((Packet) packet54playnoteblock);
    }

    public void a(Packet200Statistic packet200statistic) {
        this.a((Packet) packet200statistic);
    }

    public void a(Packet17EntityLocationAction packet17entitylocationaction) {
        this.a((Packet) packet17entitylocationaction);
    }

    public void a(Packet27PlayerInput packet27playerinput) {
        this.a((Packet) packet27playerinput);
    }

    public void a(Packet70Bed packet70bed) {
        this.a((Packet) packet70bed);
    }

    public void a(Packet71Weather packet71weather) {
        this.a((Packet) packet71weather);
    }

    public void a(Packet131ItemData packet131itemdata) {
        this.a((Packet) packet131itemdata);
    }

    public void a(Packet61WorldEvent packet61worldevent) {
        this.a((Packet) packet61worldevent);
    }

    public void a(Packet254GetInfo packet254getinfo) {
        this.a((Packet) packet254getinfo);
    }

    public void a(Packet41MobEffect packet41mobeffect) {
        this.a((Packet) packet41mobeffect);
    }

    public void a(Packet42RemoveMobEffect packet42removemobeffect) {
        this.a((Packet) packet42removemobeffect);
    }

    public void a(Packet201PlayerInfo packet201playerinfo) {
        this.a((Packet) packet201playerinfo);
    }

    public void a(Packet0KeepAlive packet0keepalive) {
        this.a((Packet) packet0keepalive);
    }

    public void a(Packet43SetExperience packet43setexperience) {
        this.a((Packet) packet43setexperience);
    }

    public void a(Packet107SetCreativeSlot packet107setcreativeslot) {
        this.a((Packet) packet107setcreativeslot);
    }

    public void a(Packet26AddExpOrb packet26addexporb) {
        this.a((Packet) packet26addexporb);
    }

    public void a(Packet108ButtonClick packet108buttonclick) {}
}
