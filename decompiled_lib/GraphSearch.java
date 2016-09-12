//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package searchHidden;

import java.util.ArrayList;
import java.util.HashSet;
import searchShared.NodeQueue;
import searchShared.OrderingFunction;
import searchShared.Problem;
import searchShared.SearchNode;
import searchShared.SearchObject;
import world.GridPos;

public class GraphSearch implements SearchObject {
    private HashSet<SearchNode> explored;
    private NodeQueue frontier;
    private OrderingFunction orderingFunction;
    protected ArrayList<SearchNode> path;
    protected int maxDepth = -1;

    public GraphSearch(OrderingFunction ordering) {
        this.orderingFunction = ordering;
    }

    public GraphSearch(int _maxDepth, OrderingFunction ordering) {
        this.maxDepth = _maxDepth;
        this.orderingFunction = ordering;
    }

    public ArrayList<SearchNode> search(Problem p) {
        this.frontier = new NodeQueue();
        this.explored = new HashSet();
        GridPos startState = p.getInitialState();
        this.frontier.addNodeToFront(new SearchNode(startState));
        this.path = new ArrayList();

        while(!this.frontier.isEmpty()) {
            SearchNode currentNode = this.frontier.removeFirst();
            this.explored.add(currentNode);
            GridPos currentState = currentNode.getState();
            ArrayList childStates = p.getReachableStatesFrom(currentState);

            for(int i = 0; i < childStates.size(); ++i) {
                GridPos childState = (GridPos)childStates.get(i);
                SearchNode childNode = new SearchNode(childState, currentNode);
                if(this.shouldExpandState(childNode, currentNode)) {
                    if(p.isGoalState(childState)) {
                        this.path = childNode.getPathFromRoot();
                        return this.path;
                    }

                    this.orderingFunction.addNodeToQueue(childNode, this.frontier);
                }
            }
        }

        return this.path;
    }

    private boolean shouldExpandState(SearchNode childNode, SearchNode parentNode) {
        boolean unexploredState = !this.explored.contains(childNode);
        boolean inFrontier = this.frontier.contains(childNode);
        boolean shouldGoDeeper = true;
        if(this.maxDepth > 0) {
            shouldGoDeeper = parentNode.getDepth() < this.maxDepth + 1;
        }

        return unexploredState && !inFrontier && shouldGoDeeper;
    }

    public ArrayList<SearchNode> getPath() {
        return this.path;
    }

    public ArrayList<SearchNode> getFrontierNodes() {
        return new ArrayList(this.frontier.toList());
    }

    public ArrayList<SearchNode> getExploredNodes() {
        return new ArrayList(this.explored);
    }

    public ArrayList<SearchNode> getAllExpandedNodes() {
        ArrayList allNodes = new ArrayList();
        allNodes.addAll(this.getFrontierNodes());
        allNodes.addAll(this.getExploredNodes());
        return allNodes;
    }
}
