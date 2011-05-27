package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class NetServerHandler extends NetHandler implements ICommandListener {

    public static Logger a = Logger.getLogger("Minecraft");
    public NetworkManager b;
    public boolean c = false;
    private MinecraftServer d;
    private EntityPlayer e;
    private int f;
    private int g;
    private boolean h;
    private double i;
    private double j;
    private double k;
    private boolean l = true;
    private Map m = new HashMap();

    public NetServerHandler(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        this.d = minecraftserver;
        this.b = networkmanager;
        networkmanager.a((NetHandler) this);
        this.e = entityplayer;
        entityplayer.a = this;
    }

    public void a() {
        this.h = false;
        this.b.a();
        if (this.f - this.g > 20) {
            this.b((Packet) (new Packet0KeepAlive()));
        }
    }

    public void a(String s) {
        this.b((Packet) (new Packet255KickDisconnect(s)));
        this.b.c();
        this.d.f.a((Packet) (new Packet3Chat("\u00A7e" + this.e.name + " left the game.")));
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(Packet27 packet27) {
        this.e.a(packet27.c(), packet27.e(), packet27.g(), packet27.h(), packet27.d(), packet27.f());
    }

    public void a(Packet10Flying packet10flying) {
        this.h = true;
        double d0;

        if (!this.l) {
            d0 = packet10flying.b - this.j;
            if (packet10flying.a == this.i && d0 * d0 < 0.01D && packet10flying.c == this.k) {
                this.l = true;
            }
        }

        if (this.l) {
            double d1;
            double d2;
            double d3;
            double d4;

            if (this.e.vehicle != null) {
                float f = this.e.yaw;
                float f1 = this.e.pitch;

                this.e.vehicle.h_();
                d1 = this.e.locX;
                d2 = this.e.locY;
                d3 = this.e.locZ;
                double d5 = 0.0D;

                d4 = 0.0D;
                if (packet10flying.i) {
                    f = packet10flying.e;
                    f1 = packet10flying.f;
                }

                if (packet10flying.h && packet10flying.b == -999.0D && packet10flying.d == -999.0D) {
                    d5 = packet10flying.a;
                    d4 = packet10flying.c;
                }

                this.e.onGround = packet10flying.g;
                this.e.a(true);
                this.e.c(d5, 0.0D, d4);
                this.e.b(d1, d2, d3, f, f1);
                this.e.motX = d5;
                this.e.motZ = d4;
                if (this.e.vehicle != null) {
                    this.d.e.b(this.e.vehicle, true);
                }

                if (this.e.vehicle != null) {
                    this.e.vehicle.h_();
                }

                this.d.f.b(this.e);
                this.i = this.e.locX;
                this.j = this.e.locY;
                this.k = this.e.locZ;
                this.d.e.f(this.e);
                return;
            }

            d0 = this.e.locY;
            this.i = this.e.locX;
            this.j = this.e.locY;
            this.k = this.e.locZ;
            d1 = this.e.locX;
            d2 = this.e.locY;
            d3 = this.e.locZ;
            float f2 = this.e.yaw;
            float f3 = this.e.pitch;

            if (packet10flying.h && packet10flying.b == -999.0D && packet10flying.d == -999.0D) {
                packet10flying.h = false;
            }

            if (packet10flying.h) {
                d1 = packet10flying.a;
                d2 = packet10flying.b;
                d3 = packet10flying.c;
                d4 = packet10flying.d - packet10flying.b;
                if (d4 > 1.65D || d4 < 0.1D) {
                    this.a("Illegal stance");
                    a.warning(this.e.name + " had an illegal stance: " + d4);
                }
            }

            if (packet10flying.i) {
                f2 = packet10flying.e;
                f3 = packet10flying.f;
            }

            this.e.a(true);
            this.e.bl = 0.0F;
            this.e.b(this.i, this.j, this.k, f2, f3);
            d4 = d1 - this.e.locX;
            double d6 = d2 - this.e.locY;
            double d7 = d3 - this.e.locZ;
            float f4 = 0.0625F;
            boolean flag = this.d.e.a(this.e, this.e.boundingBox.b().e((double) f4, (double) f4, (double) f4)).size() == 0;

            this.e.c(d4, d6, d7);
            d4 = d1 - this.e.locX;
            d6 = d2 - this.e.locY;
            if (d6 > -0.5D || d6 < 0.5D) {
                d6 = 0.0D;
            }

            d7 = d3 - this.e.locZ;
            double d8 = d4 * d4 + d6 * d6 + d7 * d7;
            boolean flag1 = false;

            if (d8 > 0.0625D && !this.e.E()) {
                flag1 = true;
                a.warning(this.e.name + " moved wrongly!");
                System.out.println("Got position " + d1 + ", " + d2 + ", " + d3);
                System.out.println("Expected " + this.e.locX + ", " + this.e.locY + ", " + this.e.locZ);
            }

            this.e.b(d1, d2, d3, f2, f3);
            boolean flag2 = this.d.e.a(this.e, this.e.boundingBox.b().e((double) f4, (double) f4, (double) f4)).size() == 0;

            if (flag && (flag1 || !flag2) && !this.e.E()) {
                this.a(this.i, this.j, this.k, f2, f3);
                return;
            }

            this.e.onGround = packet10flying.g;
            this.d.f.b(this.e);
            this.e.b(this.e.locY - d0, packet10flying.g);
        }
    }

    public void a(double d0, double d1, double d2, float f, float f1) {
        this.l = false;
        this.i = d0;
        this.j = d1;
        this.k = d2;
        this.e.b(d0, d1, d2, f, f1);
        this.e.a.b((Packet) (new Packet13PlayerLookMove(d0, d1 + 1.6200000047683716D, d1, d2, f, f1, false)));
    }

    public void a(Packet14BlockDig packet14blockdig) {
        if (packet14blockdig.e == 4) {
            this.e.y();
        } else {
            boolean flag = this.d.e.v = this.d.f.h(this.e.name);
            boolean flag1 = false;

            if (packet14blockdig.e == 0) {
                flag1 = true;
            }

            if (packet14blockdig.e == 2) {
                flag1 = true;
            }

            int i = packet14blockdig.a;
            int j = packet14blockdig.b;
            int k = packet14blockdig.c;

            if (flag1) {
                double d0 = this.e.locX - ((double) i + 0.5D);
                double d1 = this.e.locY - ((double) j + 0.5D);
                double d2 = this.e.locZ - ((double) k + 0.5D);
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (d3 > 36.0D) {
                    return;
                }
            }

            ChunkCoordinates chunkcoordinates = this.d.e.l();
            int l = (int) MathHelper.e((float) (i - chunkcoordinates.a));
            int i1 = (int) MathHelper.e((float) (k - chunkcoordinates.c));

            if (l > i1) {
                i1 = l;
            }

            if (packet14blockdig.e == 0) {
                if (i1 > 16 || flag) {
                    this.e.c.a(i, j, k);
                }
            } else if (packet14blockdig.e == 2) {
                this.e.c.b(i, j, k);
            } else if (packet14blockdig.e == 3) {
                double d4 = this.e.locX - ((double) i + 0.5D);
                double d5 = this.e.locY - ((double) j + 0.5D);
                double d6 = this.e.locZ - ((double) k + 0.5D);
                double d7 = d4 * d4 + d5 * d5 + d6 * d6;

                if (d7 < 256.0D) {
                    this.e.a.b((Packet) (new Packet53BlockChange(i, j, k, this.d.e)));
                }
            }

            this.d.e.v = false;
        }
    }

    public void a(Packet15Place packet15place) {
        ItemStack itemstack = this.e.inventory.b();
        boolean flag = this.d.e.v = this.d.f.h(this.e.name);

        if (packet15place.d == 255) {
            if (itemstack == null) {
                return;
            }

            this.e.c.a(this.e, this.d.e, itemstack);
        } else {
            int i = packet15place.a;
            int j = packet15place.b;
            int k = packet15place.c;
            int l = packet15place.d;
            ChunkCoordinates chunkcoordinates = this.d.e.l();
            int i1 = (int) MathHelper.e((float) (i - chunkcoordinates.a));
            int j1 = (int) MathHelper.e((float) (k - chunkcoordinates.c));

            if (i1 > j1) {
                j1 = i1;
            }

            if (j1 > 16 || flag) {
                this.e.c.a(this.e, this.d.e, itemstack, i, j, k, l);
            }

            this.e.a.b((Packet) (new Packet53BlockChange(i, j, k, this.d.e)));
            if (l == 0) {
                --j;
            }

            if (l == 1) {
                ++j;
            }

            if (l == 2) {
                --k;
            }

            if (l == 3) {
                ++k;
            }

            if (l == 4) {
                --i;
            }

            if (l == 5) {
                ++i;
            }

            this.e.a.b((Packet) (new Packet53BlockChange(i, j, k, this.d.e)));
        }

        if (itemstack != null && itemstack.count == 0) {
            this.e.inventory.a[this.e.inventory.c] = null;
        }

        this.e.h = true;
        this.e.inventory.a[this.e.inventory.c] = ItemStack.b(this.e.inventory.a[this.e.inventory.c]);
        Slot slot = this.e.activeContainer.a(this.e.inventory, this.e.inventory.c);

        this.e.activeContainer.a();
        this.e.h = false;
        if (!ItemStack.a(this.e.inventory.b(), packet15place.e)) {
            this.b((Packet) (new Packet103SetSlot(this.e.activeContainer.f, slot.a, this.e.inventory.b())));
        }

        this.d.e.v = false;
    }

    public void a(String s, Object[] aobject) {
        a.info(this.e.name + " lost connection: " + s);
        this.d.f.a((Packet) (new Packet3Chat("\u00A7e" + this.e.name + " left the game.")));
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(Packet packet) {
        a.warning(this.getClass() + " wasn\'t prepared to deal with a " + packet.getClass());
        this.a("Protocol error, unexpected packet");
    }

    public void b(Packet packet) {
        this.b.a(packet);
        this.g = this.f;
    }

    public void a(Packet16BlockItemSwitch packet16blockitemswitch) {
        this.e.inventory.c = packet16blockitemswitch.a;
    }

    public void a(Packet3Chat packet3chat) {
        String s = packet3chat.a;

        if (s.length() > 100) {
            this.a("Chat message too long");
        } else {
            s = s.trim();

            for (int i = 0; i < s.length(); ++i) {
                if (FontAllowedCharacters.a.indexOf(s.charAt(i)) < 0) {
                    this.a("Illegal characters in chat");
                    return;
                }
            }

            if (s.startsWith("/")) {
                this.c(s);
            } else {
                s = "<" + this.e.name + "> " + s;
                a.info(s);
                this.d.f.a((Packet) (new Packet3Chat(s)));
            }
        }
    }

    private void c(String s) {
        if (s.toLowerCase().startsWith("/me ")) {
            s = "* " + this.e.name + " " + s.substring(s.indexOf(" ")).trim();
            a.info(s);
            this.d.f.a((Packet) (new Packet3Chat(s)));
        } else if (s.toLowerCase().startsWith("/kill")) {
            this.e.a((Entity) null, 1000);
        } else if (s.toLowerCase().startsWith("/tell ")) {
            String[] astring = s.split(" ");

            if (astring.length >= 3) {
                s = s.substring(s.indexOf(" ")).trim();
                s = s.substring(s.indexOf(" ")).trim();
                s = "\u00A77" + this.e.name + " whispers " + s;
                a.info(s + " to " + astring[1]);
                if (!this.d.f.a(astring[1], (Packet) (new Packet3Chat(s)))) {
                    this.b((Packet) (new Packet3Chat("\u00A7cThere\'s no player by that name online.")));
                }
            }
        } else {
            String s1;

            if (this.d.f.h(this.e.name)) {
                s1 = s.substring(1);
                a.info(this.e.name + " issued server command: " + s1);
                this.d.a(s1, (ICommandListener) this);
            } else {
                s1 = s.substring(1);
                a.info(this.e.name + " tried command: " + s1);
            }
        }
    }

    public void a(Packet18ArmAnimation packet18armanimation) {
        if (packet18armanimation.b == 1) {
            this.e.r();
        }
    }

    public void a(Packet19EntityAction packet19entityaction) {
        if (packet19entityaction.b == 1) {
            this.e.b(true);
        } else if (packet19entityaction.b == 2) {
            this.e.b(false);
        } else if (packet19entityaction.b == 3) {
            this.e.a(false, true);
            this.l = false;
        }
    }

    public void a(Packet255KickDisconnect packet255kickdisconnect) {
        this.b.a("disconnect.quitting", new Object[0]);
    }

    public int b() {
        return this.b.d();
    }

    public void b(String s) {
        this.b((Packet) (new Packet3Chat("\u00A77" + s)));
    }

    public String c() {
        return this.e.name;
    }

    public void a(Packet7UseEntity packet7useentity) {
        Entity entity = this.d.e.a(packet7useentity.b);

        if (entity != null && this.e.e(entity) && this.e.f(entity) < 4.0F) {
            if (packet7useentity.c == 0) {
                this.e.c(entity);
            } else if (packet7useentity.c == 1) {
                this.e.d(entity);
            }
        }
    }

    public void a(Packet9Respawn packet9respawn) {
        if (this.e.health <= 0) {
            this.e = this.d.f.d(this.e);
        }
    }

    public void a(Packet101CloseWindow packet101closewindow) {
        this.e.v();
    }

    public void a(Packet102WindowClick packet102windowclick) {
        if (this.e.activeContainer.f == packet102windowclick.a && this.e.activeContainer.c(this.e)) {
            ItemStack itemstack = this.e.activeContainer.a(packet102windowclick.b, packet102windowclick.c, this.e);

            if (ItemStack.a(packet102windowclick.e, itemstack)) {
                this.e.a.b((Packet) (new Packet106Transaction(packet102windowclick.a, packet102windowclick.d, true)));
                this.e.h = true;
                this.e.activeContainer.a();
                this.e.u();
                this.e.h = false;
            } else {
                this.m.put(Integer.valueOf(this.e.activeContainer.f), Short.valueOf(packet102windowclick.d));
                this.e.a.b((Packet) (new Packet106Transaction(packet102windowclick.a, packet102windowclick.d, false)));
                this.e.activeContainer.a(this.e, false);
                ArrayList arraylist = new ArrayList();

                for (int i = 0; i < this.e.activeContainer.e.size(); ++i) {
                    arraylist.add(((Slot) this.e.activeContainer.e.get(i)).b());
                }

                this.e.a(this.e.activeContainer, arraylist);
            }
        }
    }

    public void a(Packet106Transaction packet106transaction) {
        Short oshort = (Short) this.m.get(Integer.valueOf(this.e.activeContainer.f));

        if (oshort != null && packet106transaction.b == oshort.shortValue() && this.e.activeContainer.f == packet106transaction.a && !this.e.activeContainer.c(this.e)) {
            this.e.activeContainer.a(this.e, true);
        }
    }

    public void a(Packet130UpdateSign packet130updatesign) {
        if (this.d.e.f(packet130updatesign.a, packet130updatesign.b, packet130updatesign.c)) {
            TileEntity tileentity = this.d.e.getTileEntity(packet130updatesign.a, packet130updatesign.b, packet130updatesign.c);

            int i;
            int j;

            for (i = 0; i < 4; ++i) {
                boolean flag = true;

                if (packet130updatesign.d[i].length() > 15) {
                    flag = false;
                } else {
                    for (j = 0; j < packet130updatesign.d[i].length(); ++j) {
                        if (FontAllowedCharacters.a.indexOf(packet130updatesign.d[i].charAt(j)) < 0) {
                            flag = false;
                        }
                    }
                }

                if (!flag) {
                    packet130updatesign.d[i] = "!?";
                }
            }

            if (tileentity instanceof TileEntitySign) {
                i = packet130updatesign.a;
                int k = packet130updatesign.b;

                j = packet130updatesign.c;
                TileEntitySign tileentitysign = (TileEntitySign) tileentity;

                for (int l = 0; l < 4; ++l) {
                    tileentitysign.a[l] = packet130updatesign.d[l];
                }

                tileentitysign.h();
                this.d.e.g(i, k, j);
            }
        }
    }
}
