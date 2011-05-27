package net.minecraft.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WorldServer extends World {

    public ChunkProviderServer y;
    public boolean z = false;
    public boolean A;
    private boolean B;
    private SpawnerCreature C = new SpawnerMonsters(this, 200, IMonster.class, new Class[] { EntityZombie.class, EntitySkeleton.class, EntityCreeper.class, EntitySpider.class, EntitySlime.class});
    private SpawnerCreature D = new SpawnerCreature(15, EntityAnimal.class, new Class[] { EntitySheep.class, EntityPig.class, EntityCow.class, EntityChicken.class});

    public WorldServer(File file1, String s, boolean flag) {
        super(file1, s);
        this.B = flag;
    }

    public void e() {
        super.e();
        if (this.B) {
            this.C.a(this);
        }

        this.D.a(this);
    }

    protected IChunkProvider a(File file1) {
        this.y = new ChunkProviderServer(this, new ChunkLoader(file1, true), new ChunkProviderGenerate(this, this.t));
        return this.y;
    }

    public List d(int i, int j, int k, int l, int i1, int j1) {
        ArrayList arraylist = new ArrayList();

        for (int k1 = 0; k1 < this.b.size(); ++k1) {
            TileEntity tileentity = (TileEntity) this.b.get(k1);

            if (tileentity.b >= i && tileentity.c >= j && tileentity.d >= k && tileentity.b < l && tileentity.c < i1 && tileentity.d < j1) {
                arraylist.add(tileentity);
            }
        }

        return arraylist;
    }
}
