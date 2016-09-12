//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package searchHidden;

import searchShared.OrderingFunction;
import searchShared.SearchNode;

public abstract class HeuristicOrderingFunction extends OrderingFunction {
    public HeuristicOrderingFunction() {
    }

    public abstract int getFCost(SearchNode var1);

    public abstract int getHCost(SearchNode var1);
}
