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
        slot.a = this.e.size();
        this.e.add(slot);
        this.d.add(null);
    }

    public void a(ICrafting icrafting) {
        this.g.add(icrafting);
        ArrayList arraylist = new ArrayList();

        for (int i = 0; i < this.e.size(); ++i) {
            arraylist.add(((Slot) this.e.get(i)).b());
        }

        icrafting.a(this, arraylist);
        this.a();
    }

    public void a() {
        for (int i = 0; i < this.e.size(); ++i) {
            ItemStack itemstack = ((Slot) this.e.get(i)).b();
            ItemStack itemstack1 = (ItemStack) this.d.get(i);

            if (!ItemStack.a(itemstack1, itemstack)) {
                itemstack1 = itemstack == null ? null : itemstack.j();
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
            InventoryPlayer inventoryplayer = entityhuman.inventory;

            if (i == -999) {
                if (inventoryplayer.i() != null && i == -999) {
                    if (j == 0) {
                        entityhuman.b(inventoryplayer.i());
                        inventoryplayer.b((ItemStack) null);
                    }

                    if (j == 1) {
                        entityhuman.b(inventoryplayer.i().a(1));
                        if (inventoryplayer.i().count == 0) {
                            inventoryplayer.b((ItemStack) null);
                        }
                    }
                }
            } else {
                Slot slot = (Slot) this.e.get(i);

                if (slot != null) {
                    slot.c();
                    ItemStack itemstack1 = slot.b();
                    ItemStack itemstack2 = inventoryplayer.i();

                    if (itemstack1 != null) {
                        itemstack = itemstack1.j();
                    }

                    int k;

                    if (itemstack1 == null) {
                        if (itemstack2 != null && slot.a(itemstack2)) {
                            k = j == 0 ? itemstack2.count : 1;
                            if (k > slot.d()) {
                                k = slot.d();
                            }

                            slot.b(itemstack2.a(k));
                            if (itemstack2.count == 0) {
                                inventoryplayer.b((ItemStack) null);
                            }
                        }
                    } else if (itemstack2 == null) {
                        k = j == 0 ? itemstack1.count : (itemstack1.count + 1) / 2;
                        inventoryplayer.b(slot.a(k));
                        if (itemstack1.count == 0) {
                            slot.b((ItemStack) null);
                        }

                        slot.a();
                    } else if (slot.a(itemstack2)) {
                        if (itemstack1.id == itemstack2.id && (!itemstack1.e() || itemstack1.h() == itemstack2.h())) {
                            k = j == 0 ? itemstack2.count : 1;
                            if (k > slot.d() - itemstack1.count) {
                                k = slot.d() - itemstack1.count;
                            }

                            if (k > itemstack2.b() - itemstack1.count) {
                                k = itemstack2.b() - itemstack1.count;
                            }

                            itemstack2.a(k);
                            if (itemstack2.count == 0) {
                                inventoryplayer.b((ItemStack) null);
                            }

                            itemstack1.count += k;
                        } else if (itemstack2.count <= slot.d()) {
                            slot.b(itemstack2);
                            inventoryplayer.b(itemstack1);
                        }
                    } else if (itemstack1.id == itemstack2.id && itemstack2.b() > 1 && (!itemstack1.e() || itemstack1.h() == itemstack2.h())) {
                        k = itemstack1.count;
                        if (k > 0 && k + itemstack2.count <= itemstack2.b()) {
                            itemstack2.count += k;
                            itemstack1.a(k);
                            if (itemstack1.count == 0) {
                                slot.b((ItemStack) null);
                            }

                            slot.a();
                        }
                    }
                }
            }
        }

        return itemstack;
    }

    public void a(EntityHuman entityhuman) {
        InventoryPlayer inventoryplayer = entityhuman.inventory;

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
