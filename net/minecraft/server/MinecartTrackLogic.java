package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

class MinecartTrackLogic {

    private World b;
    private int c;
    private int d;
    private int e;
    private int f;
    private List g;

    final BlockMinecartTrack a;

    public MinecartTrackLogic(BlockMinecartTrack blockminecarttrack, World world, int i, int j, int k) {
        this.a = blockminecarttrack;
        this.g = new ArrayList();
        this.b = world;
        this.c = i;
        this.d = j;
        this.e = k;
        this.f = world.b(i, j, k);
        this.a();
    }

    private void a() {
        this.g.clear();
        if (this.f == 0) {
            this.g.add(new ChunkPosition(this.c, this.d, this.e - 1));
            this.g.add(new ChunkPosition(this.c, this.d, this.e + 1));
        } else if (this.f == 1) {
            this.g.add(new ChunkPosition(this.c - 1, this.d, this.e));
            this.g.add(new ChunkPosition(this.c + 1, this.d, this.e));
        } else if (this.f == 2) {
            this.g.add(new ChunkPosition(this.c - 1, this.d, this.e));
            this.g.add(new ChunkPosition(this.c + 1, this.d + 1, this.e));
        } else if (this.f == 3) {
            this.g.add(new ChunkPosition(this.c - 1, this.d + 1, this.e));
            this.g.add(new ChunkPosition(this.c + 1, this.d, this.e));
        } else if (this.f == 4) {
            this.g.add(new ChunkPosition(this.c, this.d + 1, this.e - 1));
            this.g.add(new ChunkPosition(this.c, this.d, this.e + 1));
        } else if (this.f == 5) {
            this.g.add(new ChunkPosition(this.c, this.d, this.e - 1));
            this.g.add(new ChunkPosition(this.c, this.d + 1, this.e + 1));
        } else if (this.f == 6) {
            this.g.add(new ChunkPosition(this.c + 1, this.d, this.e));
            this.g.add(new ChunkPosition(this.c, this.d, this.e + 1));
        } else if (this.f == 7) {
            this.g.add(new ChunkPosition(this.c - 1, this.d, this.e));
            this.g.add(new ChunkPosition(this.c, this.d, this.e + 1));
        } else if (this.f == 8) {
            this.g.add(new ChunkPosition(this.c - 1, this.d, this.e));
            this.g.add(new ChunkPosition(this.c, this.d, this.e - 1));
        } else if (this.f == 9) {
            this.g.add(new ChunkPosition(this.c + 1, this.d, this.e));
            this.g.add(new ChunkPosition(this.c, this.d, this.e - 1));
        }
    }

    private void b() {
        for (int i = 0; i < this.g.size(); ++i) {
            MinecartTrackLogic minecarttracklogic = this.a((ChunkPosition) this.g.get(i));

            if (minecarttracklogic != null && minecarttracklogic.b(this)) {
                this.g.set(i, new ChunkPosition(minecarttracklogic.c, minecarttracklogic.d, minecarttracklogic.e));
            } else {
                this.g.remove(i--);
            }
        }
    }

    private boolean a(int i, int j, int k) {
        return this.b.a(i, j, k) == this.a.bi ? true : (this.b.a(i, j + 1, k) == this.a.bi ? true : this.b.a(i, j - 1, k) == this.a.bi);
    }

    private MinecartTrackLogic a(ChunkPosition chunkposition) {
        return this.b.a(chunkposition.a, chunkposition.b, chunkposition.c) == this.a.bi ? new MinecartTrackLogic(this.a, this.b, chunkposition.a, chunkposition.b, chunkposition.c) : (this.b.a(chunkposition.a, chunkposition.b + 1, chunkposition.c) == this.a.bi ? new MinecartTrackLogic(this.a, this.b, chunkposition.a, chunkposition.b + 1, chunkposition.c) : (this.b.a(chunkposition.a, chunkposition.b - 1, chunkposition.c) == this.a.bi ? new MinecartTrackLogic(this.a, this.b, chunkposition.a, chunkposition.b - 1, chunkposition.c) : null));
    }

    private boolean b(MinecartTrackLogic minecarttracklogic) {
        for (int i = 0; i < this.g.size(); ++i) {
            ChunkPosition chunkposition = (ChunkPosition) this.g.get(i);

            if (chunkposition.a == minecarttracklogic.c && chunkposition.c == minecarttracklogic.e) {
                return true;
            }
        }

        return false;
    }

    private boolean b(int i, int j, int k) {
        for (int l = 0; l < this.g.size(); ++l) {
            ChunkPosition chunkposition = (ChunkPosition) this.g.get(l);

            if (chunkposition.a == i && chunkposition.c == k) {
                return true;
            }
        }

        return false;
    }

    private int c() {
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

    private boolean c(MinecartTrackLogic minecarttracklogic) {
        if (this.b(minecarttracklogic)) {
            return true;
        } else if (this.g.size() == 2) {
            return false;
        } else if (this.g.size() == 0) {
            return true;
        } else {
            ChunkPosition chunkposition = (ChunkPosition) this.g.get(0);

            return minecarttracklogic.d == this.d && chunkposition.b == this.d ? true : true;
        }
    }

    private void d(MinecartTrackLogic minecarttracklogic) {
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

        if (b0 == 0) {
            if (this.b.a(this.c, this.d + 1, this.e - 1) == this.a.bi) {
                b0 = 4;
            }

            if (this.b.a(this.c, this.d + 1, this.e + 1) == this.a.bi) {
                b0 = 5;
            }
        }

        if (b0 == 1) {
            if (this.b.a(this.c + 1, this.d + 1, this.e) == this.a.bi) {
                b0 = 2;
            }

            if (this.b.a(this.c - 1, this.d + 1, this.e) == this.a.bi) {
                b0 = 3;
            }
        }

        if (b0 < 0) {
            b0 = 0;
        }

        this.b.b(this.c, this.d, this.e, b0);
    }

    private boolean c(int i, int j, int k) {
        MinecartTrackLogic minecarttracklogic = this.a(new ChunkPosition(i, j, k));

        if (minecarttracklogic == null) {
            return false;
        } else {
            minecarttracklogic.b();
            return minecarttracklogic.c(this);
        }
    }

    public void a(boolean flag) {
        boolean flag1 = this.c(this.c, this.d, this.e - 1);
        boolean flag2 = this.c(this.c, this.d, this.e + 1);
        boolean flag3 = this.c(this.c - 1, this.d, this.e);
        boolean flag4 = this.c(this.c + 1, this.d, this.e);
        byte b0 = -1;

        if ((flag1 || flag2) && !flag3 && !flag4) {
            b0 = 0;
        }

        if ((flag3 || flag4) && !flag1 && !flag2) {
            b0 = 1;
        }

        if (flag2 && flag4 && !flag1 && !flag3) {
            b0 = 6;
        }

        if (flag2 && flag3 && !flag1 && !flag4) {
            b0 = 7;
        }

        if (flag1 && flag3 && !flag2 && !flag4) {
            b0 = 8;
        }

        if (flag1 && flag4 && !flag2 && !flag3) {
            b0 = 9;
        }

        if (b0 == -1) {
            if (flag1 || flag2) {
                b0 = 0;
            }

            if (flag3 || flag4) {
                b0 = 1;
            }

            if (flag) {
                if (flag2 && flag4) {
                    b0 = 6;
                }

                if (flag3 && flag2) {
                    b0 = 7;
                }

                if (flag4 && flag1) {
                    b0 = 9;
                }

                if (flag1 && flag3) {
                    b0 = 8;
                }
            } else {
                if (flag1 && flag3) {
                    b0 = 8;
                }

                if (flag4 && flag1) {
                    b0 = 9;
                }

                if (flag3 && flag2) {
                    b0 = 7;
                }

                if (flag2 && flag4) {
                    b0 = 6;
                }
            }
        }

        if (b0 == 0) {
            if (this.b.a(this.c, this.d + 1, this.e - 1) == this.a.bi) {
                b0 = 4;
            }

            if (this.b.a(this.c, this.d + 1, this.e + 1) == this.a.bi) {
                b0 = 5;
            }
        }

        if (b0 == 1) {
            if (this.b.a(this.c + 1, this.d + 1, this.e) == this.a.bi) {
                b0 = 2;
            }

            if (this.b.a(this.c - 1, this.d + 1, this.e) == this.a.bi) {
                b0 = 3;
            }
        }

        if (b0 < 0) {
            b0 = 0;
        }

        this.f = b0;
        this.a();
        this.b.b(this.c, this.d, this.e, b0);

        for (int i = 0; i < this.g.size(); ++i) {
            MinecartTrackLogic minecarttracklogic = this.a((ChunkPosition) this.g.get(i));

            if (minecarttracklogic != null) {
                minecarttracklogic.b();
                if (minecarttracklogic.c(this)) {
                    minecarttracklogic.d(this);
                }
            }
        }
    }

    static int a(MinecartTrackLogic minecarttracklogic) {
        return minecarttracklogic.c();
    }
}
