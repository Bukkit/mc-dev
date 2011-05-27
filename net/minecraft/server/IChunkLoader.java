package net.minecraft.server;

public interface IChunkLoader {

    Chunk a(World world, int i, int j);

    void a(World world, Chunk chunk);

    void b(World world, Chunk chunk);

    void a();

    void b();
}
