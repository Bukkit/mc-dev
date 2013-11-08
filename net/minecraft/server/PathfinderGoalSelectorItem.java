package net.minecraft.server;

class PathfinderGoalSelectorItem {

    public PathfinderGoal a;
    public int b;
    final PathfinderGoalSelector c;

    public PathfinderGoalSelectorItem(PathfinderGoalSelector pathfindergoalselector, int i, PathfinderGoal pathfindergoal) {
        this.c = pathfindergoalselector;
        this.b = i;
        this.a = pathfindergoal;
    }
}
