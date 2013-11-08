package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportItemName implements Callable {

    final ItemStack a;
    final PlayerInventory b;

    CrashReportItemName(PlayerInventory playerinventory, ItemStack itemstack) {
        this.b = playerinventory;
        this.a = itemstack;
    }

    public String a() {
        return this.a.getName();
    }

    public Object call() {
        return this.a();
    }
}
