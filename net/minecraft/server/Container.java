package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Container {

    public List d = new ArrayList();
    public List e = new ArrayList();
    public int windowId = 0;
    private short a = 0;
    protected List listeners = new ArrayList();
    private Set b = new HashSet();

    public Container() {}

    protected void a(Slot slot) {
        slot.a = this.e.size();
        this.e.add(slot);
        this.d.add(null);
    }

    public void a(ICrafting icrafting) {
        if (this.listeners.contains(icrafting)) {
            throw new IllegalArgumentException("Listener already listening");
        } else {
            this.listeners.add(icrafting);
            icrafting.a(this, this.b());
            this.a();
        }
    }

    public List b() {
        ArrayList arraylist = new ArrayList();

        for (int i = 0; i < this.e.size(); ++i) {
            arraylist.add(((Slot) this.e.get(i)).getItem());
        }

        return arraylist;
    }

    public void a() {
        for (int i = 0; i < this.e.size(); ++i) {
            ItemStack itemstack = ((Slot) this.e.get(i)).getItem();
            ItemStack itemstack1 = (ItemStack) this.d.get(i);

            if (!ItemStack.equals(itemstack1, itemstack)) {
                itemstack1 = itemstack == null ? null : itemstack.cloneItemStack();
                this.d.set(i, itemstack1);

                for (int j = 0; j < this.listeners.size(); ++j) {
                    ((ICrafting) this.listeners.get(j)).a(this, i, itemstack1);
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

    public Slot b(int i) {
        return (Slot) this.e.get(i);
    }

    public ItemStack a(int i) {
        Slot slot = (Slot) this.e.get(i);

        return slot != null ? slot.getItem() : null;
    }

    public ItemStack a(int i, int j, boolean flag, EntityHuman entityhuman) {
        ItemStack itemstack = null;

        if (j == 0 || j == 1) {
            InventoryPlayer inventoryplayer = entityhuman.inventory;

            if (i == -999) {
                if (inventoryplayer.j() != null && i == -999) {
                    if (j == 0) {
                        entityhuman.b(inventoryplayer.j());
                        inventoryplayer.b((ItemStack) null);
                    }

                    if (j == 1) {
                        entityhuman.b(inventoryplayer.j().a(1));
                        if (inventoryplayer.j().count == 0) {
                            inventoryplayer.b((ItemStack) null);
                        }
                    }
                }
            } else {
                int k;

                if (flag) {
                    ItemStack itemstack1 = this.a(i);

                    if (itemstack1 != null) {
                        int l = itemstack1.count;

                        itemstack = itemstack1.cloneItemStack();
                        Slot slot = (Slot) this.e.get(i);

                        if (slot != null && slot.getItem() != null) {
                            k = slot.getItem().count;
                            if (k < l) {
                                this.a(i, j, flag, entityhuman);
                            }
                        }
                    }
                } else {
                    Slot slot1 = (Slot) this.e.get(i);

                    if (slot1 != null) {
                        slot1.c();
                        ItemStack itemstack2 = slot1.getItem();
                        ItemStack itemstack3 = inventoryplayer.j();

                        if (itemstack2 != null) {
                            itemstack = itemstack2.cloneItemStack();
                        }

                        if (itemstack2 == null) {
                            if (itemstack3 != null && slot1.isAllowed(itemstack3)) {
                                k = j == 0 ? itemstack3.count : 1;
                                if (k > slot1.d()) {
                                    k = slot1.d();
                                }

                                slot1.c(itemstack3.a(k));
                                if (itemstack3.count == 0) {
                                    inventoryplayer.b((ItemStack) null);
                                }
                            }
                        } else if (itemstack3 == null) {
                            k = j == 0 ? itemstack2.count : (itemstack2.count + 1) / 2;
                            ItemStack itemstack4 = slot1.a(k);

                            inventoryplayer.b(itemstack4);
                            if (itemstack2.count == 0) {
                                slot1.c((ItemStack) null);
                            }

                            slot1.a(inventoryplayer.j());
                        } else if (slot1.isAllowed(itemstack3)) {
                            if (itemstack2.id == itemstack3.id && (!itemstack2.usesData() || itemstack2.getData() == itemstack3.getData())) {
                                k = j == 0 ? itemstack3.count : 1;
                                if (k > slot1.d() - itemstack2.count) {
                                    k = slot1.d() - itemstack2.count;
                                }

                                if (k > itemstack3.getMaxStackSize() - itemstack2.count) {
                                    k = itemstack3.getMaxStackSize() - itemstack2.count;
                                }

                                itemstack3.a(k);
                                if (itemstack3.count == 0) {
                                    inventoryplayer.b((ItemStack) null);
                                }

                                itemstack2.count += k;
                            } else if (itemstack3.count <= slot1.d()) {
                                slot1.c(itemstack3);
                                inventoryplayer.b(itemstack2);
                            }
                        } else if (itemstack2.id == itemstack3.id && itemstack3.getMaxStackSize() > 1 && (!itemstack2.usesData() || itemstack2.getData() == itemstack3.getData())) {
                            k = itemstack2.count;
                            if (k > 0 && k + itemstack3.count <= itemstack3.getMaxStackSize()) {
                                itemstack3.count += k;
                                itemstack2.a(k);
                                if (itemstack2.count == 0) {
                                    slot1.c((ItemStack) null);
                                }

                                slot1.a(inventoryplayer.j());
                            }
                        }
                    }
                }
            }
        }

        return itemstack;
    }

    public void a(EntityHuman entityhuman) {
        InventoryPlayer inventoryplayer = entityhuman.inventory;

        if (inventoryplayer.j() != null) {
            entityhuman.b(inventoryplayer.j());
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

    protected void a(ItemStack itemstack, int i, int j, boolean flag) {
        int k = i;

        if (flag) {
            k = j - 1;
        }

        Slot slot;
        ItemStack itemstack1;

        if (itemstack.isStackable()) {
            while (itemstack.count > 0 && (!flag && k < j || flag && k >= i)) {
                slot = (Slot) this.e.get(k);
                itemstack1 = slot.getItem();
                if (itemstack1 != null && itemstack1.id == itemstack.id && (!itemstack.usesData() || itemstack.getData() == itemstack1.getData())) {
                    int l = itemstack1.count + itemstack.count;

                    if (l <= itemstack.getMaxStackSize()) {
                        itemstack.count = 0;
                        itemstack1.count = l;
                        slot.c();
                    } else if (itemstack1.count < itemstack.getMaxStackSize()) {
                        itemstack.count -= itemstack.getMaxStackSize() - itemstack1.count;
                        itemstack1.count = itemstack.getMaxStackSize();
                        slot.c();
                    }
                }

                if (flag) {
                    --k;
                } else {
                    ++k;
                }
            }
        }

        if (itemstack.count > 0) {
            if (flag) {
                k = j - 1;
            } else {
                k = i;
            }

            while (!flag && k < j || flag && k >= i) {
                slot = (Slot) this.e.get(k);
                itemstack1 = slot.getItem();
                if (itemstack1 == null) {
                    slot.c(itemstack.cloneItemStack());
                    slot.c();
                    itemstack.count = 0;
                    break;
                }

                if (flag) {
                    --k;
                } else {
                    ++k;
                }
            }
        }
    }
}
