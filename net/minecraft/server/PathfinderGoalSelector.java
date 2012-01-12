package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;

public class PathfinderGoalSelector {

    private ArrayList a = new ArrayList();
    private ArrayList b = new ArrayList();

    public PathfinderGoalSelector() {}

    public void a(int i, PathfinderGoal pathfindergoal) {
        this.a.add(new PathfinderGoalSelectorItem(this, i, pathfindergoal));
    }

    public void a() {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.a.iterator();

        PathfinderGoalSelectorItem pathfindergoalselectoritem;

        while (iterator.hasNext()) {
            pathfindergoalselectoritem = (PathfinderGoalSelectorItem) iterator.next();
            boolean flag = this.b.contains(pathfindergoalselectoritem);

            if (flag) {
                if (!pathfindergoalselectoritem.a.g() || !this.a(pathfindergoalselectoritem)) {
                    pathfindergoalselectoritem.a.d();
                    this.b.remove(pathfindergoalselectoritem);
                }
            } else if (pathfindergoalselectoritem.a.a() && this.a(pathfindergoalselectoritem)) {
                arraylist.add(pathfindergoalselectoritem);
                this.b.add(pathfindergoalselectoritem);
            }
        }

        iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            pathfindergoalselectoritem = (PathfinderGoalSelectorItem) iterator.next();
            pathfindergoalselectoritem.a.e();
        }

        iterator = this.b.iterator();

        while (iterator.hasNext()) {
            pathfindergoalselectoritem = (PathfinderGoalSelectorItem) iterator.next();
            pathfindergoalselectoritem.a.b();
        }
    }

    private boolean a(PathfinderGoalSelectorItem pathfindergoalselectoritem) {
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            PathfinderGoalSelectorItem pathfindergoalselectoritem1 = (PathfinderGoalSelectorItem) iterator.next();

            if (pathfindergoalselectoritem1 != pathfindergoalselectoritem) {
                if (pathfindergoalselectoritem.b >= pathfindergoalselectoritem1.b) {
                    if (this.b.contains(pathfindergoalselectoritem1) && !this.a(pathfindergoalselectoritem, pathfindergoalselectoritem1)) {
                        return false;
                    }
                } else if (this.b.contains(pathfindergoalselectoritem1) && !pathfindergoalselectoritem1.a.f()) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean a(PathfinderGoalSelectorItem pathfindergoalselectoritem, PathfinderGoalSelectorItem pathfindergoalselectoritem1) {
        return (pathfindergoalselectoritem.a.c() & pathfindergoalselectoritem1.a.c()) == 0;
    }
}
