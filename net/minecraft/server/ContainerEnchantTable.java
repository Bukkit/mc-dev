package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ContainerEnchantTable extends Container {

    public IInventory enchantSlots = new ContainerEnchantTableInventory(this, "Enchant", 1);
    private World world;
    private int x;
    private int y;
    private int z;
    private Random l = new Random();
    public long f;
    public int[] costs = new int[3];

    public ContainerEnchantTable(PlayerInventory playerinventory, World world, int i, int j, int k) {
        this.world = world;
        this.x = i;
        this.y = j;
        this.z = k;
        this.a((Slot) (new SlotEnchant(this, this.enchantSlots, 0, 25, 47)));

        int l;

        for (l = 0; l < 3; ++l) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.a(new Slot(playerinventory, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (l = 0; l < 9; ++l) {
            this.a(new Slot(playerinventory, l, 8 + l * 18, 142));
        }
    }

    public void addSlotListener(ICrafting icrafting) {
        super.addSlotListener(icrafting);
        icrafting.setContainerData(this, 0, this.costs[0]);
        icrafting.setContainerData(this, 1, this.costs[1]);
        icrafting.setContainerData(this, 2, this.costs[2]);
    }

    public void b() {
        super.b();
        Iterator iterator = this.listeners.iterator();

        while (iterator.hasNext()) {
            ICrafting icrafting = (ICrafting) iterator.next();

            icrafting.setContainerData(this, 0, this.costs[0]);
            icrafting.setContainerData(this, 1, this.costs[1]);
            icrafting.setContainerData(this, 2, this.costs[2]);
        }
    }

    public void a(IInventory iinventory) {
        if (iinventory == this.enchantSlots) {
            ItemStack itemstack = iinventory.getItem(0);
            int i;

            if (itemstack != null && itemstack.u()) {
                this.f = this.l.nextLong();
                if (!this.world.isStatic) {
                    i = 0;

                    int j;

                    for (j = -1; j <= 1; ++j) {
                        for (int k = -1; k <= 1; ++k) {
                            if ((j != 0 || k != 0) && this.world.isEmpty(this.x + k, this.y, this.z + j) && this.world.isEmpty(this.x + k, this.y + 1, this.z + j)) {
                                if (this.world.getTypeId(this.x + k * 2, this.y, this.z + j * 2) == Block.BOOKSHELF.id) {
                                    ++i;
                                }

                                if (this.world.getTypeId(this.x + k * 2, this.y + 1, this.z + j * 2) == Block.BOOKSHELF.id) {
                                    ++i;
                                }

                                if (k != 0 && j != 0) {
                                    if (this.world.getTypeId(this.x + k * 2, this.y, this.z + j) == Block.BOOKSHELF.id) {
                                        ++i;
                                    }

                                    if (this.world.getTypeId(this.x + k * 2, this.y + 1, this.z + j) == Block.BOOKSHELF.id) {
                                        ++i;
                                    }

                                    if (this.world.getTypeId(this.x + k, this.y, this.z + j * 2) == Block.BOOKSHELF.id) {
                                        ++i;
                                    }

                                    if (this.world.getTypeId(this.x + k, this.y + 1, this.z + j * 2) == Block.BOOKSHELF.id) {
                                        ++i;
                                    }
                                }
                            }
                        }
                    }

                    for (j = 0; j < 3; ++j) {
                        this.costs[j] = EnchantmentManager.a(this.l, j, i, itemstack);
                    }

                    this.b();
                }
            } else {
                for (i = 0; i < 3; ++i) {
                    this.costs[i] = 0;
                }
            }
        }
    }

    public boolean a(EntityHuman entityhuman, int i) {
        ItemStack itemstack = this.enchantSlots.getItem(0);

        if (this.costs[i] > 0 && itemstack != null && (entityhuman.expLevel >= this.costs[i] || entityhuman.abilities.canInstantlyBuild)) {
            if (!this.world.isStatic) {
                List list = EnchantmentManager.b(this.l, itemstack, this.costs[i]);

                if (list != null) {
                    entityhuman.levelDown(this.costs[i]);
                    Iterator iterator = list.iterator();

                    while (iterator.hasNext()) {
                        EnchantmentInstance enchantmentinstance = (EnchantmentInstance) iterator.next();

                        itemstack.addEnchantment(enchantmentinstance.enchantment, enchantmentinstance.level);
                    }

                    this.a(this.enchantSlots);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public void a(EntityHuman entityhuman) {
        super.a(entityhuman);
        if (!this.world.isStatic) {
            ItemStack itemstack = this.enchantSlots.splitWithoutUpdate(0);

            if (itemstack != null) {
                entityhuman.drop(itemstack);
            }
        }
    }

    public boolean c(EntityHuman entityhuman) {
        return this.world.getTypeId(this.x, this.y, this.z) != Block.ENCHANTMENT_TABLE.id ? false : entityhuman.e((double) this.x + 0.5D, (double) this.y + 0.5D, (double) this.z + 0.5D) <= 64.0D;
    }

    public ItemStack b(int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.b.get(i);

        if (slot != null && slot.d()) {
            ItemStack itemstack1 = slot.getItem();

            itemstack = itemstack1.cloneItemStack();
            if (i == 0) {
                if (!this.a(itemstack1, 1, 37, true)) {
                    return null;
                }
            } else {
                if (((Slot) this.b.get(0)).d() || !((Slot) this.b.get(0)).isAllowed(itemstack1)) {
                    return null;
                }

                if (itemstack1.hasTag() && itemstack1.count == 1) {
                    ((Slot) this.b.get(0)).set(itemstack1.cloneItemStack());
                    itemstack1.count = 0;
                } else if (itemstack1.count >= 1) {
                    ((Slot) this.b.get(0)).set(new ItemStack(itemstack1.id, 1, itemstack1.getData()));
                    --itemstack1.count;
                }
            }

            if (itemstack1.count == 0) {
                slot.set((ItemStack) null);
            } else {
                slot.e();
            }

            if (itemstack1.count == itemstack.count) {
                return null;
            }

            slot.b(itemstack1);
        }

        return itemstack;
    }
}
