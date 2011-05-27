package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Container {

    public List d = new ArrayList();
    public List e = new ArrayList();
    public int f = 0;
    private short a = 0;
    protected List g = new ArrayList();
    private Set b = new HashSet();

    public Container() {}

    protected void a(Slot slot) {
        slot.c = this.e.size();
        this.e.add(slot);
        this.d.add(null);
    }

    public void a(ICrafting icrafting) {
        this.g.add(icrafting);
        ArrayList arraylist = new ArrayList();

        for (int i = 0; i < this.e.size(); ++i) {
            arraylist.add(((Slot) this.e.get(i)).c());
        }

        icrafting.a(this, arraylist);
        this.a();
    }

    public void a() {
        for (int i = 0; i < this.e.size(); ++i) {
            ItemStack itemstack = ((Slot) this.e.get(i)).c();
            ItemStack itemstack1 = (ItemStack) this.d.get(i);

            if (!ItemStack.a(itemstack1, itemstack)) {
                itemstack1 = itemstack == null ? null : itemstack.d();
                this.d.set(i, itemstack1);

                for (int j = 0; j < this.g.size(); ++j) {
                    ((ICrafting) this.g.get(j)).a(this, i, itemstack1);
                }
            }
        }
    }

    public Slot a(IInventory iinventory, int i) {
        for (int j = 0; j < this.e.size(); ++j) {
            Slot slot = (Slot) this.e.get(j);

            if (slot.a(iinventory, i)) {
                return slot;
            }
        }

        return null;
    }

    public Slot a(int i) {
        return (Slot) this.e.get(i);
    }

    public ItemStack a(int i, int j, EntityHuman entityhuman) {
        ItemStack itemstack = null;

        if (j == 0 || j == 1) {
            InventoryPlayer inventoryplayer = entityhuman.an;

            if (i == -999) {
                if (inventoryplayer.i() != null && i == -999) {
                    if (j == 0) {
                        entityhuman.b(inventoryplayer.i());
                        inventoryplayer.b((ItemStack) null);
                    }

                    if (j == 1) {
                        entityhuman.b(inventoryplayer.i().a(1));
                        if (inventoryplayer.i().a == 0) {
                            inventoryplayer.b((ItemStack) null);
                        }
                    }
                }
            } else {
                Slot slot = (Slot) this.e.get(i);

                if (slot != null) {
                    slot.d();
                    ItemStack itemstack1 = slot.c();

                    if (itemstack1 != null) {
                        itemstack = itemstack1.d();
                    }

                    if (itemstack1 != null || inventoryplayer.i() != null) {
                        int k;

                        if (itemstack1 != null && inventoryplayer.i() == null) {
                            k = j == 0 ? itemstack1.a : (itemstack1.a + 1) / 2;
                            inventoryplayer.b(slot.a(k));
                            if (itemstack1.a == 0) {
                                slot.b((ItemStack) null);
                            }

                            slot.b();
                        } else if (itemstack1 == null && inventoryplayer.i() != null && slot.a(inventoryplayer.i())) {
                            k = j == 0 ? inventoryplayer.i().a : 1;
                            if (k > slot.a()) {
                                k = slot.a();
                            }

                            slot.b(inventoryplayer.i().a(k));
                            if (inventoryplayer.i().a == 0) {
                                inventoryplayer.b((ItemStack) null);
                            }
                        } else if (itemstack1 != null && inventoryplayer.i() != null) {
                            if (slot.a(inventoryplayer.i())) {
                                if (itemstack1.c != inventoryplayer.i().c) {
                                    if (inventoryplayer.i().a <= slot.a()) {
                                        slot.b(inventoryplayer.i());
                                        inventoryplayer.b(itemstack1);
                                    }
                                } else if (itemstack1.c == inventoryplayer.i().c) {
                                    if (j == 0) {
                                        k = inventoryplayer.i().a;
                                        if (k > slot.a() - itemstack1.a) {
                                            k = slot.a() - itemstack1.a;
                                        }

                                        if (k > inventoryplayer.i().b() - itemstack1.a) {
                                            k = inventoryplayer.i().b() - itemstack1.a;
                                        }

                                        inventoryplayer.i().a(k);
                                        if (inventoryplayer.i().a == 0) {
                                            inventoryplayer.b((ItemStack) null);
                                        }

                                        itemstack1.a += k;
                                    } else if (j == 1) {
                                        k = 1;
                                        if (k > slot.a() - itemstack1.a) {
                                            k = slot.a() - itemstack1.a;
                                        }

                                        if (k > inventoryplayer.i().b() - itemstack1.a) {
                                            k = inventoryplayer.i().b() - itemstack1.a;
                                        }

                                        inventoryplayer.i().a(k);
                                        if (inventoryplayer.i().a == 0) {
                                            inventoryplayer.b((ItemStack) null);
                                        }

                                        itemstack1.a += k;
                                    }
                                }
                            } else if (itemstack1.c == inventoryplayer.i().c && inventoryplayer.i().b() > 1) {
                                k = itemstack1.a;
                                if (k > 0 && k + inventoryplayer.i().a <= inventoryplayer.i().b()) {
                                    ItemStack itemstack2 = inventoryplayer.i();

                                    itemstack2.a += k;
                                    itemstack1.a(k);
                                    if (itemstack1.a == 0) {
                                        slot.b((ItemStack) null);
                                    }

                                    slot.b();
                                }
                            }
                        }
                    }
                }
            }
        }

        return itemstack;
    }

    public void a(EntityHuman entityhuman) {
        InventoryPlayer inventoryplayer = entityhuman.an;

        if (inventoryplayer.i() != null) {
            entityhuman.b(inventoryplayer.i());
            inventoryplayer.b((ItemStack) null);
        }
    }

    public void a(IInventory iinventory) {
        this.a();
    }

    public boolean c(EntityHuman entityhuman) {
        return !this.b.contains(entityhuman);
    }

    public void a(EntityHuman entityhuman, boolean flag) {
        if (flag) {
            this.b.remove(entityhuman);
        } else {
            this.b.add(entityhuman);
        }
    }

    public abstract boolean b(EntityHuman entityhuman);
}
