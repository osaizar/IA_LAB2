package searchCustom;

import java.util.ArrayList;
import java.util.HashSet;

import searchShared.NodeQueue;
import searchShared.Problem;
import searchShared.SearchNode;
import searchShared.SearchObject;
import world.GridPos;

public class CustomGraphSearch implements SearchObject {

	private HashSet<SearchNode> explored;
	private NodeQueue frontier;
	protected ArrayList<SearchNode> path;
	private boolean insertFront;

	/**
	 * The constructor tells graph search whether it should insert nodes to front or back of the frontier 
	 */
    public CustomGraphSearch(boolean bInsertFront) {
		insertFront = bInsertFront;
    }

	/**
	 * Implements "graph search", which is the foundation of many search algorithms
	 */
	public ArrayList<SearchNode> search(Problem p) {
		// The frontier is a queue of expanded SearchNodes not processed yet
		frontier = new NodeQueue();
		/// The explored set is a set of nodes that have been processed 
		explored = new HashSet<SearchNode>();
		// The start state is given
		GridPos startState = (GridPos) p.getInitialState();
		// Initialize the frontier with the start state  
		frontier.addNodeToFront(new SearchNode(startState));

		// Path will be empty until we find the goal.
		path = new ArrayList<SearchNode>();
		
		// Implement this!
		System.out.println("Implement CustomGraphSearch.java!");
		
		// Algorithm's main loop
		while(!frontier.isEmpty()) {
			// Let's extract our position from the frontier
			SearchNode currentNode = frontier.removeFirst();
			explored.add(currentNode);
			GridPos currentPos = currentNode.getState();  // And get the state associated to it
			if(p.isGoalState(currentPos)) {  // If that state is the goal, we're done!
				path = currentNode.getPathFromRoot();
				return path;
			}
			// Now we want to know where can we go (AKA, our child)
			ArrayList<GridPos> reachableNodes = p.getReachableStatesFrom(currentPos);

			// And we want to develop all of our child options
			for(int i = 0; i < reachableNodes.size(); i++) {
				SearchNode nextNode = new SearchNode(reachableNodes.get(i), currentNode);
				if(!explored.contains(nextNode)) {  // If we've already been there, there's no point in doing it again

					if(insertFront) {  // DepthFirst
						frontier.addNodeToFront(nextNode);
					}
					else {  // BreadthFirst
						frontier.addNodeToBack(nextNode);
						// Just notice that if we insert nextNode in the back, then it's not going to be the node that
						// we're going to check in the next 'while' iteration
					}
				}
			}
		}
	
		return path;
	}

	/*
	 * Functions below are just getters used externally by the program 
	 */
	public ArrayList<SearchNode> getPath() {
		return path;
	}

	public ArrayList<SearchNode> getFrontierNodes() {
		return new ArrayList<SearchNode>(frontier.toList());
	}
	public ArrayList<SearchNode> getExploredNodes() {
		return new ArrayList<SearchNode>(explored);
	}
	public ArrayList<SearchNode> getAllExpandedNodes() {
		ArrayList<SearchNode> allNodes = new ArrayList<SearchNode>();
		allNodes.addAll(getFrontierNodes());
		allNodes.addAll(getExploredNodes());
		return allNodes;
	}

}
