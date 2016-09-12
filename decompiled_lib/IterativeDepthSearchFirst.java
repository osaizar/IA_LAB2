//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package searchHidden;

import java.util.ArrayList;
import searchHidden.DepthFirstSearch;
import searchShared.Problem;
import searchShared.SearchNode;

public class IterativeDepthFirstSearch extends DepthFirstSearch {
    private ArrayList<SearchNode> lastAllExpanded = new ArrayList();
    private ArrayList<SearchNode> lastFrontier = new ArrayList();
    private ArrayList<SearchNode> lastExplored = new ArrayList();

    public IterativeDepthFirstSearch(int maxDepth) {
        super(maxDepth);
    }

    public ArrayList<SearchNode> search(Problem p) {
        for(int nextSearchDepth = 1; nextSearchDepth <= this.maxDepth; ++nextSearchDepth) {
            System.out.println("IDS: Searching at depth  " + nextSearchDepth);
            DepthFirstSearch searchObject = new DepthFirstSearch(nextSearchDepth);
            new ArrayList();
            ArrayList searchResult = searchObject.search(p);
            this.lastAllExpanded.clear();
            this.lastAllExpanded.addAll(searchObject.getAllExpandedNodes());
            this.lastFrontier.clear();
            this.lastFrontier.addAll(searchObject.getAllExpandedNodes());
            this.lastExplored.clear();
            this.lastExplored.addAll(searchObject.getAllExpandedNodes());
            if(searchResult.size() > 0) {
                this.path = searchResult;
                return this.path;
            }
        }

        return new ArrayList();
    }

    public ArrayList<SearchNode> getPath() {
        return this.path;
    }

    public ArrayList<SearchNode> getFrontierNodes() {
        return this.lastFrontier;
    }

    public ArrayList<SearchNode> getExploredNodes() {
        return this.lastExplored;
    }

    public ArrayList<SearchNode> getAllExpandedNodes() {
        return this.lastAllExpanded;
    }
}
