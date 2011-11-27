package net.minecraft.server;

public class BiomeTheEndDecorator extends BiomeDecorator {

    protected WorldGenerator L;

    public BiomeTheEndDecorator(BiomeBase biomebase) {
        super(biomebase);
        this.L = new WorldGenEnder(Block.WHITESTONE.id);
    }

    protected void a() {
        this.b();
        if (this.b.nextInt(5) == 0) {
            int i = this.c + this.b.nextInt(16) + 8;
            int j = this.d + this.b.nextInt(16) + 8;
            int k = this.a.f(i, j);

            if (k > 0) {
                ;
            }

            this.L.a(this.a, this.b, i, k, j);
        }

        if (this.c == 0 && this.d == 0) {
            EntityEnderDragon entityenderdragon = new EntityEnderDragon(this.a);

            entityenderdragon.setPositionRotation(0.0D, 128.0D, 0.0D, this.b.nextFloat() * 360.0F, 0.0F);
            this.a.addEntity(entityenderdragon);
        }
    }
}
