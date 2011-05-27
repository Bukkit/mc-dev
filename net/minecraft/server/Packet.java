package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Packet {

    private static Map a = new HashMap();
    private static Map b = new HashMap();
    public final long j = System.currentTimeMillis();
    public boolean k = false;
    private static HashMap c;
    private static int d;

    public Packet() {}

    static void a(int i, Class oclass) {
        if (a.containsKey(Integer.valueOf(i))) {
            throw new IllegalArgumentException("Duplicate packet id:" + i);
        } else if (b.containsKey(oclass)) {
            throw new IllegalArgumentException("Duplicate packet class:" + oclass);
        } else {
            a.put(Integer.valueOf(i), oclass);
            b.put(oclass, Integer.valueOf(i));
        }
    }

    public static Packet a(int i) {
        try {
            Class oclass = (Class) a.get(Integer.valueOf(i));

            return oclass == null ? null : (Packet) oclass.newInstance();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Skipping packet with id " + i);
            return null;
        }
    }

    public final int b() {
        return ((Integer) b.get(this.getClass())).intValue();
    }

    public static Packet b(DataInputStream datainputstream) {
        boolean flag = false;
        Packet packet = null;

        datainputstream.mark(16384);

        int i;

        try {
            i = datainputstream.read();
            if (i == -1) {
                return null;
            }

            packet = a(i);
            if (packet == null) {
                throw new IOException("Bad packet id " + i);
            }

            packet.a(datainputstream);
        } catch (EOFException eofexception) {
            System.out.println("Reached end of stream");
            datainputstream.reset();
            return null;
        }

        PacketCounter packetcounter = (PacketCounter) c.get(Integer.valueOf(i));

        if (packetcounter == null) {
            packetcounter = new PacketCounter((EmptyClass1) null);
            c.put(Integer.valueOf(i), packetcounter);
        }

        packetcounter.a(packet.a());
        ++d;
        if (d % 1000 == 0) {
            ;
        }

        return packet;
    }

    public static void a(Packet packet, DataOutputStream dataoutputstream) {
        dataoutputstream.write(packet.b());
        packet.a(dataoutputstream);
    }

    public abstract void a(DataInputStream datainputstream);

    public abstract void a(DataOutputStream dataoutputstream);

    public abstract void a(NetHandler nethandler);

    public abstract int a();

    static {
        a(0, Packet0KeepAlive.class);
        a(1, Packet1Login.class);
        a(2, Packet2Handshake.class);
        a(3, Packet3Chat.class);
        a(4, Packet4UpdateTime.class);
        a(5, Packet5EntityEquipment.class);
        a(6, Packet6SpawnPosition.class);
        a(7, Packet7UseEntity.class);
        a(8, Packet8UpdateHealth.class);
        a(9, Packet9Respawn.class);
        a(10, Packet10Flying.class);
        a(11, Packet11PlayerPosition.class);
        a(12, Packet12PlayerLook.class);
        a(13, Packet13PlayerLookMove.class);
        a(14, Packet14BlockDig.class);
        a(15, Packet15Place.class);
        a(16, Packet16BlockItemSwitch.class);
        a(17, Packet17.class);
        a(18, Packet18ArmAnimation.class);
        a(19, Packet19EntityAction.class);
        a(20, Packet20NamedEntitySpawn.class);
        a(21, Packet21PickupSpawn.class);
        a(22, Packet22Collect.class);
        a(23, Packet23VehicleSpawn.class);
        a(24, Packet24MobSpawn.class);
        a(25, Packet25EntityPainting.class);
        a(27, Packet27.class);
        a(28, Packet28EntityVelocity.class);
        a(29, Packet29DestroyEntity.class);
        a(30, Packet30Entity.class);
        a(31, Packet31RelEntityMove.class);
        a(32, Packet32EntityLook.class);
        a(33, Packet33RelEntityMoveLook.class);
        a(34, Packet34EntityTeleport.class);
        a(38, Packet38EntityStatus.class);
        a(39, Packet39AttachEntity.class);
        a(40, Packet40EntityMetadata.class);
        a(50, Packet50PreChunk.class);
        a(51, Packet51MapChunk.class);
        a(52, Packet52MultiBlockChange.class);
        a(53, Packet53BlockChange.class);
        a(54, Packet54PlayNoteBlock.class);
        a(60, Packet60Explosion.class);
        a(100, Packet100OpenWindow.class);
        a(101, Packet101CloseWindow.class);
        a(102, Packet102WindowClick.class);
        a(103, Packet103SetSlot.class);
        a(104, Packet104WindowItems.class);
        a(105, Packet105CraftProgressBar.class);
        a(106, Packet106Transaction.class);
        a(130, Packet130UpdateSign.class);
        a(255, Packet255KickDisconnect.class);
        c = new HashMap();
        d = 0;
    }
}
