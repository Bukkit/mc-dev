package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class MinecartTrackLogic {

    private World b;
    private int c;
    private int d;
    private int e;
    private final boolean f;
    private List g;
    final BlockMinecartTrackAbstract a;

    public MinecartTrackLogic(BlockMinecartTrackAbstract blockminecarttrackabstract, World world, int i, int j, int k) {
        this.a = blockminecarttrackabstract;
        this.g = new ArrayList();
        this.b = world;
        this.c = i;
        this.d = j;
        this.e = k;
        Block block = world.getType(i, j, k);
        int l = world.getData(i, j, k);

        if (((BlockMinecartTrackAbstract) block).a) {
            this.f = true;
            l &= -9;
        } else {
            this.f = false;
        }

        this.a(l);
    }

    private void a(int i) {
        this.g.clear();
        if (i == 0) {
            this.g.add(new ChunkPosition(this.c, this.d, this.e - 1));
            this.g.add(new ChunkPosition(this.c, this.d, this.e + 1));
        } else if (i == 1) {
            this.g.add(new ChunkPosition(this.c - 1, this.d, this.e));
            this.g.add(new ChunkPosition(this.c + 1, this.d, this.e));
        } else if (i == 2) {
            this.g.add(new ChunkPosition(this.c - 1, this.d, this.e));
            this.g.add(new ChunkPosition(this.c + 1, this.d + 1, this.e));
        } else if (i == 3) {
            this.g.add(new ChunkPosition(this.c - 1, this.d + 1, this.e));
            this.g.add(new ChunkPosition(this.c + 1, this.d, this.e));
        } else if (i == 4) {
            this.g.add(new ChunkPosition(this.c, this.d + 1, this.e - 1));
            this.g.add(new ChunkPosition(this.c, this.d, this.e + 1));
        } else if (i == 5) {
            this.g.add(new ChunkPosition(this.c, this.d, this.e - 1));
            this.g.add(new ChunkPosition(this.c, this.d + 1, this.e + 1));
        } else if (i == 6) {
            this.g.add(new ChunkPosition(this.c + 1, this.d, this.e));
            this.g.add(new ChunkPosition(this.c, this.d, this.e + 1));
        } else if (i == 7) {
            this.g.add(new ChunkPosition(this.c - 1, this.d, this.e));
            this.g.add(new ChunkPosition(this.c, this.d, this.e + 1));
        } else if (i == 8) {
            this.g.add(new ChunkPosition(this.c - 1, this.d, this.e));
            this.g.add(new ChunkPosition(this.c, this.d, this.e - 1));
        } else if (i == 9) {
            this.g.add(new ChunkPosition(this.c + 1, this.d, this.e));
            this.g.add(new ChunkPosition(this.c, this.d, this.e - 1));
        }
    }

    private void b() {
        for (int i = 0; i < this.g.size(); ++i) {
            MinecartTrackLogic minecarttracklogic = this.a((ChunkPosition) this.g.get(i));

            if (minecarttracklogic != null && minecarttracklogic.a(this)) {
                this.g.set(i, new ChunkPosition(minecarttracklogic.c, minecarttracklogic.d, minecarttracklogic.e));
            } else {
                this.g.remove(i--);
            }
        }
    }

    private boolean a(int i, int j, int k) {
        return BlockMinecartTrackAbstract.b_(this.b, i, j, k) ? true : (BlockMinecartTrackAbstract.b_(this.b, i, j + 1, k) ? true : BlockMinecartTrackAbstract.b_(this.b, i, j - 1, k));
    }

    private MinecartTrackLogic a(ChunkPosition chunkposition) {
        return BlockMinecartTrackAbstract.b_(this.b, chunkposition.x, chunkposition.y, chunkposition.z) ? new MinecartTrackLogic(this.a, this.b, chunkposition.x, chunkposition.y, chunkposition.z) : (BlockMinecartTrackAbstract.b_(this.b, chunkposition.x, chunkposition.y + 1, chunkposition.z) ? new MinecartTrackLogic(this.a, this.b, chunkposition.x, chunkposition.y + 1, chunkposition.z) : (BlockMinecartTrackAbstract.b_(this.b, chunkposition.x, chunkposition.y - 1, chunkposition.z) ? new MinecartTrackLogic(this.a, this.b, chunkposition.x, chunkposition.y - 1, chunkposition.z) : null));
    }

    private boolean a(MinecartTrackLogic minecarttracklogic) {
        for (int i = 0; i < this.g.size(); ++i) {
            ChunkPosition chunkposition = (ChunkPosition) this.g.get(i);

            if (chunkposition.x == minecarttracklogic.c && chunkposition.z == minecarttracklogic.e) {
                return true;
            }
        }

        return false;
    }

    private boolean b(int i, int j, int k) {
        for (int l = 0; l < this.g.size(); ++l) {
            ChunkPosition chunkposition = (ChunkPosition) this.g.get(l);

            if (chunkposition.x == i && chunkposition.z == k) {
                return true;
            }
        }

        return false;
    }

    protected int a() {
        int i = 0;

        if (this.a(this.c, this.d, this.e - 1)) {
            ++i;
        }

        if (this.a(this.c, this.d, this.e + 1)) {
            ++i;
        }

        if (this.a(this.c - 1, this.d, this.e)) {
            ++i;
        }

        if (this.a(this.c + 1, this.d, this.e)) {
            ++i;
        }

        return i;
    }

    private boolean b(MinecartTrackLogic minecarttracklogic) {
        return this.a(minecarttracklogic) ? true : (this.g.size() == 2 ? false : (this.g.isEmpty() ? true : true));
    }

    private void c(MinecartTrackLogic minecarttracklogic) {
        this.g.add(new ChunkPosition(minecarttracklogic.c, minecarttracklogic.d, minecarttracklogic.e));
        boolean flag = this.b(this.c, this.d, this.e - 1);
        boolean flag1 = this.b(this.c, this.d, this.e + 1);
        boolean flag2 = this.b(this.c - 1, this.d, this.e);
        boolean flag3 = this.b(this.c + 1, this.d, this.e);
        byte b0 = -1;

        if (flag || flag1) {
            b0 = 0;
        }

        if (flag2 || flag3) {
            b0 = 1;
        }

        if (!this.f) {
            if (flag1 && flag3 && !flag && !flag2) {
                b0 = 6;
            }

            if (flag1 && flag2 && !flag && !flag3) {
                b0 = 7;
            }

            if (flag && flag2 && !flag1 && !flag3) {
                b0 = 8;
            }

            if (flag && flag3 && !flag1 && !flag2) {
                b0 = 9;
            }
        }

        if (b0 == 0) {
            if (BlockMinecartTrackAbstract.b_(this.b, this.c, this.d + 1, this.e - 1)) {
                b0 = 4;
            }

            if (BlockMinecartTrackAbstract.b_(this.b, this.c, this.d + 1, this.e + 1)) {
                b0 = 5;
            }
        }

        if (b0 == 1) {
            if (BlockMinecartTrackAbstract.b_(this.b, this.c + 1, this.d + 1, this.e)) {
                b0 = 2;
            }

            if (BlockMinecartTrackAbstract.b_(this.b, this.c - 1, this.d + 1, this.e)) {
                b0 = 3;
            }
        }

        if (b0 < 0) {
            b0 = 0;
        }

        int i = b0;

        if (this.f) {
            i = this.b.getData(this.c, this.d, this.e) & 8 | b0;
        }

        this.b.setData(this.c, this.d, this.e, i, 3);
    }

    private boolean c(int i, int j, int k) {
        MinecartTrackLogic minecarttracklogic = this.a(new ChunkPosition(i, j, k));

        if (minecarttracklogic == null) {
            return false;
        } else {
            minecarttracklogic.b();
            return minecarttracklogic.b(this);
        }
    }

    public void a(boolean flag, boolean flag1) {
        boolean flag2 = this.c(this.c, this.d, this.e - 1);
        boolean flag3 = this.c(this.c, this.d, this.e + 1);
        boolean flag4 = this.c(this.c - 1, this.d, this.e);
        boolean flag5 = this.c(this.c + 1, this.d, this.e);
        byte b0 = -1;

        if ((flag2 || flag3) && !flag4 && !flag5) {
            b0 = 0;
        }

        if ((flag4 || flag5) && !flag2 && !flag3) {
            b0 = 1;
        }

        if (!this.f) {
            if (flag3 && flag5 && !flag2 && !flag4) {
                b0 = 6;
            }

            if (flag3 && flag4 && !flag2 && !flag5) {
                b0 = 7;
            }

            if (flag2 && flag4 && !flag3 && !flag5) {
                b0 = 8;
            }

            if (flag2 && flag5 && !flag3 && !flag4) {
                b0 = 9;
            }
        }

        if (b0 == -1) {
            if (flag2 || flag3) {
                b0 = 0;
            }

            if (flag4 || flag5) {
                b0 = 1;
            }

            if (!this.f) {
                if (flag) {
                    if (flag3 && flag5) {
                        b0 = 6;
                    }

                    if (flag4 && flag3) {
                        b0 = 7;
                    }

                    if (flag5 && flag2) {
                        b0 = 9;
                    }

                    if (flag2 && flag4) {
                        b0 = 8;
                    }
                } else {
                    if (flag2 && flag4) {
                        b0 = 8;
                    }

                    if (flag5 && flag2) {
                        b0 = 9;
                    }

                    if (flag4 && flag3) {
                        b0 = 7;
                    }

                    if (flag3 && flag5) {
                        b0 = 6;
                    }
                }
            }
        }

        if (b0 == 0) {
            if (BlockMinecartTrackAbstract.b_(this.b, this.c, this.d + 1, this.e - 1)) {
                b0 = 4;
            }

            if (BlockMinecartTrackAbstract.b_(this.b, this.c, this.d + 1, this.e + 1)) {
                b0 = 5;
            }
        }

        if (b0 == 1) {
            if (BlockMinecartTrackAbstract.b_(this.b, this.c + 1, this.d + 1, this.e)) {
                b0 = 2;
            }

            if (BlockMinecartTrackAbstract.b_(this.b, this.c - 1, this.d + 1, this.e)) {
                b0 = 3;
            }
        }

        if (b0 < 0) {
            b0 = 0;
        }

        this.a(b0);
        int i = b0;

        if (this.f) {
            i = this.b.getData(this.c, this.d, this.e) & 8 | b0;
        }

        if (flag1 || this.b.getData(this.c, this.d, this.e) != i) {
            this.b.setData(this.c, this.d, this.e, i, 3);

            for (int j = 0; j < this.g.size(); ++j) {
                MinecartTrackLogic minecarttracklogic = this.a((ChunkPosition) this.g.get(j));

                if (minecarttracklogic != null) {
                    minecarttracklogic.b();
                    if (minecarttracklogic.b(this)) {
                        minecarttracklogic.c(this);
                    }
                }
            }
        }
    }
}
