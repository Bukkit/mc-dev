package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class ContainerEnchantTable extends Container {

    public IInventory enchantSlots = new ContainerEnchantTableInventory(this, "Enchant", true, 1);
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

        for (int i = 0; i < this.listeners.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.listeners.get(i);

            icrafting.setContainerData(this, 0, this.costs[0]);
            icrafting.setContainerData(this, 1, this.costs[1]);
            icrafting.setContainerData(this, 2, this.costs[2]);
        }
    }

    public void a(IInventory iinventory) {
        if (iinventory == this.enchantSlots) {
            ItemStack itemstack = iinventory.getItem(0);
            int i;

            if (itemstack != null && itemstack.x()) {
                this.f = this.l.nextLong();
                if (!this.world.isStatic) {
                    i = 0;

                    int j;

                    for (j = -1; j <= 1; ++j) {
                        for (int k = -1; k <= 1; ++k) {
                            if ((j != 0 || k != 0) && this.world.isEmpty(this.x + k, this.y, this.z + j) && this.world.isEmpty(this.x + k, this.y + 1, this.z + j)) {
                                if (this.world.getType(this.x + k * 2, this.y, this.z + j * 2) == Blocks.BOOKSHELF) {
                                    ++i;
                                }

                                if (this.world.getType(this.x + k * 2, this.y + 1, this.z + j * 2) == Blocks.BOOKSHELF) {
                                    ++i;
                                }

                                if (k != 0 && j != 0) {
                                    if (this.world.getType(this.x + k * 2, this.y, this.z + j) == Blocks.BOOKSHELF) {
                                        ++i;
                                    }

                                    if (this.world.getType(this.x + k * 2, this.y + 1, this.z + j) == Blocks.BOOKSHELF) {
                                        ++i;
                                    }

                                    if (this.world.getType(this.x + k, this.y, this.z + j * 2) == Blocks.BOOKSHELF) {
                                        ++i;
                                    }

                                    if (this.world.getType(this.x + k, this.y + 1, this.z + j * 2) == Blocks.BOOKSHELF) {
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
                boolean flag = itemstack.getItem() == Items.BOOK;

                if (list != null) {
                    entityhuman.levelDown(-this.costs[i]);
                    if (flag) {
                        itemstack.setItem(Items.ENCHANTED_BOOK);
                    }

                    int j = flag && list.size() > 1 ? this.l.nextInt(list.size()) : -1;

                    for (int k = 0; k < list.size(); ++k) {
                        EnchantmentInstance enchantmentinstance = (EnchantmentInstance) list.get(k);

                        if (!flag || k != j) {
                            if (flag) {
                                Items.ENCHANTED_BOOK.a(itemstack, enchantmentinstance);
                            } else {
                                itemstack.addEnchantment(enchantmentinstance.enchantment, enchantmentinstance.level);
                            }
                        }
                    }

                    this.a(this.enchantSlots);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public void b(EntityHuman entityhuman) {
        super.b(entityhuman);
        if (!this.world.isStatic) {
            ItemStack itemstack = this.enchantSlots.splitWithoutUpdate(0);

            if (itemstack != null) {
                entityhuman.drop(itemstack, false);
            }
        }
    }

    public boolean a(EntityHuman entityhuman) {
        return this.world.getType(this.x, this.y, this.z) != Blocks.ENCHANTMENT_TABLE ? false : entityhuman.e((double) this.x + 0.5D, (double) this.y + 0.5D, (double) this.z + 0.5D) <= 64.0D;
    }

    public ItemStack b(EntityHuman entityhuman, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.c.get(i);

        if (slot != null && slot.e()) {
            ItemStack itemstack1 = slot.getItem();

            itemstack = itemstack1.cloneItemStack();
            if (i == 0) {
                if (!this.a(itemstack1, 1, 37, true)) {
                    return null;
                }
            } else {
                if (((Slot) this.c.get(0)).e() || !((Slot) this.c.get(0)).isAllowed(itemstack1)) {
                    return null;
                }

                if (itemstack1.hasTag() && itemstack1.count == 1) {
                    ((Slot) this.c.get(0)).set(itemstack1.cloneItemStack());
                    itemstack1.count = 0;
                } else if (itemstack1.count >= 1) {
                    ((Slot) this.c.get(0)).set(new ItemStack(itemstack1.getItem(), 1, itemstack1.getData()));
                    --itemstack1.count;
                }
            }

            if (itemstack1.count == 0) {
                slot.set((ItemStack) null);
            } else {
                slot.f();
            }

            if (itemstack1.count == itemstack.count) {
                return null;
            }

            slot.a(entityhuman, itemstack1);
        }

        return itemstack;
    }
}
