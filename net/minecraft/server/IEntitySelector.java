package net.minecraft.server;

public interface IEntitySelector {

    IEntitySelector a = new EntitySelectorLiving();
    IEntitySelector b = new EntitySelectorContainer();

    boolean a(Entity entity);

}
