//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package searchHidden;

import searchHidden.GraphSearch;
import searchShared.EnqueueAtEnd;

public class BreadthFirstSearch extends GraphSearch {
    public BreadthFirstSearch(int maxDepth) {
        super(maxDepth, new EnqueueAtEnd());
    }
}
