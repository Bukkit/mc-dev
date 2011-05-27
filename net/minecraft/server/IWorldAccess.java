package net.minecraft.server;

public interface IWorldAccess {

    void a(int i, int j, int k);

    void a(int i, int j, int k, int l, int i1, int j1);

    void a(String s, double d0, double d1, double d2, float f, float f1);

    void a(String s, double d0, double d1, double d2, double d3, double d4, double d5);

    void a(Entity entity);

    void b(Entity entity);

    void a();

    void a(String s, int i, int j, int k);

    void a(int i, int j, int k, TileEntity tileentity);

    void a(EntityHuman entityhuman, int i, int j, int k, int l, int i1);
}
