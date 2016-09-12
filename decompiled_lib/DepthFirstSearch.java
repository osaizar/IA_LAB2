//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package searchHidden;

import searchHidden.GraphSearch;
import searchShared.EnqueAtFront;

public class DepthFirstSearch extends GraphSearch {
    public DepthFirstSearch(int maxDepth) {
        super(maxDepth, new EnqueAtFront());
    }
}
