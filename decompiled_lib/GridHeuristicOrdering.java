//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package searchHidden;

import java.util.Comparator;
import searchHidden.HeuristicOrderingFunction;
import searchShared.NodeQueue;
import searchShared.SearchNode;
import world.GridPos;

public class GridHeuristicOrdering extends HeuristicOrderingFunction implements Comparator {
    private GridPos m_GoalState = null;
    private float alpha = 1.0F;

    public GridHeuristicOrdering(int weight) {
        this.alpha = (float)weight;
    }

    public GridHeuristicOrdering() {
    }

    public void addNodeToQueue(SearchNode node, NodeQueue q) {
        q.addNodeToFront(node);
        q.sort(this);
    }

    public int compare(Object n1, Object n2) {
        SearchNode node1 = (SearchNode)n1;
        SearchNode node2 = (SearchNode)n2;
        int f1 = this.getFCost(node1);
        int f2 = this.getFCost(node2);
        return f1 < f2?-1:(f1 == f2?0:1);
    }

    public int getFCost(SearchNode n) {
        if(this.m_GoalState == null) {
            System.err.println("Grid heuristic: No Goal State set!!");
            return 1;
        } else {
            GridPos pos = n.getState();
            int ret = (int)(this.alpha * (float)this.getHCost(n) + (float)(n.getDepth() - 1));
            return ret;
        }
    }

    public int getHCost(SearchNode n) {
        if(this.m_GoalState == null) {
            System.err.println("Grid heuristic: No Goal State set!!");
            return 1;
        } else {
            GridPos pos = n.getState();
            int ret = (int)pos.getDistanceManhattan(this.m_GoalState);
            return ret;
        }
    }

    public void setGoalState(GridPos pos) {
        this.m_GoalState = pos;
    }
}
