package net.minecraft.server;

public class PathfinderGoalSheepBreed extends PathfinderGoalBreed {

    private final InventoryCrafting d = new InventoryCrafting(new ContainerSheepBreed(this), 2, 1);

    public PathfinderGoalSheepBreed(EntityAnimal entityanimal, float f) {
        super(entityanimal, f);
        this.d.setItem(0, new ItemStack(Item.INK_SACK, 1, 0));
        this.d.setItem(1, new ItemStack(Item.INK_SACK, 1, 0));
    }

    protected void a(EntityAnimal entityanimal, EntityAnimal entityanimal1, EntityAnimal entityanimal2) {
        super.a(entityanimal, entityanimal1, entityanimal2);
        if (entityanimal instanceof EntitySheep && entityanimal1 instanceof EntitySheep && entityanimal2 instanceof EntitySheep) {
            int i = this.a(entityanimal, entityanimal1);

            ((EntitySheep) entityanimal2).setColor(15 - i);
        }
    }

    private int a(EntityAnimal entityanimal, EntityAnimal entityanimal1) {
        int i = this.a(entityanimal);
        int j = this.a(entityanimal1);

        this.d.getItem(0).setData(i);
        this.d.getItem(1).setData(j);
        ItemStack itemstack = CraftingManager.getInstance().craft(this.d, ((EntitySheep) entityanimal).world);
        int k;

        if (itemstack != null && itemstack.getItem().id == Item.INK_SACK.id) {
            k = itemstack.getData();
        } else {
            k = this.a.random.nextFloat() > 0.5F ? i : j;
        }

        return k;
    }

    private int a(EntityAnimal entityanimal) {
        return 15 - ((EntitySheep) entityanimal).getColor();
    }
}
