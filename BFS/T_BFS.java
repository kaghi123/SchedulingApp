package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class T_BFS{
	
	private Queue<Node> queueOfNodes = new LinkedList<Node>();
	private int distance = 0;	//level or distance away from the root of node
	private Node node;
	
	public T_BFS(Node node) {
		this.node = node;
		BFSAlgorithm();
	}
	
	@SuppressWarnings("unchecked")
	private void BFSAlgorithm() {
		if(this.node == null) {
			return;
		}
		else {
			queueOfNodes.add(node);
			//root distance is 0
			node.setDistance(distance);
			distance += 1;
			while(!queueOfNodes.isEmpty()) {
				//gets head of queue
				Node current = queueOfNodes.element();
				//see if there are any children in this current node
				List<Node> children = current.getChildren();
				if(children != null) {
					for(Node child : children) {
						queueOfNodes.add(child);	//adds child to queue
						child.setDistance(distance); //distance away from root
						System.out.println("New child:");
						List<ClassInfo> l = (List<ClassInfo>) child.getData();
						for(ClassInfo c: l) {
							System.out.println(c.toString());
						}
						//need to adjust the distance algorithm to properly show distance
						System.out.println("Distance away from root: " + child.getDistance());	
						System.out.println();
						System.out.println();
					}
					
					if(current.getParent() != null && current.getDistance() != current.getParent().getDistance() - 1) {
						distance = current.getDistance() + 1;
						current.setDistance(distance);
					}
					queueOfNodes.remove();	//dequeues the current node
					
					
				} 
				else {
					//no children in current node
					queueOfNodes.remove();
				}
//				System.out.println("Size of queue: " + queueOfNodes.size());
			}
		}
	}
	
	public ArrayList<Node> findShortestPath() {
		int distance = 0;
		ArrayList<Node> shortestPath = new ArrayList<>();
		Node currentNode = null;
		
		queueOfNodes.add(node);
		while(!queueOfNodes.isEmpty()) {
			Node current = queueOfNodes.element();
			if(current != null) {
				List<ClassInfo> currentClasses = (List<ClassInfo>)current.getData();
				for(ClassInfo c: currentClasses) {
					if(c.getName().toLowerCase().equals("cs4096")) {
						if(distance == 0) {
							distance = current.getDistance();
							currentNode = current;
						}
						else if(distance > current.getDistance()) { //check to see if current's distance is less than the current lowest distance
							distance = current.getDistance();
							currentNode = current;
						}
					}
				}
			}
			List<Node> neighbors = current.getChildren();
			if(neighbors != null) {
				for(Node n: neighbors) {
					queueOfNodes.add(n);
					
				}
				queueOfNodes.remove();
			}
			
		}
		//adds the last class the user needs to take
		shortestPath.add(currentNode);
		while(currentNode.getParent() != null) {
			currentNode = currentNode.getParent();
			if(currentNode.getDistance() == distance - 1) {//if the parent's distance is one less than the child's
				distance -= 1;
				shortestPath.add(currentNode);
			}
			
			shortestPath.add(currentNode);
		}
		
		//comes out in reverse order since starting from end back to root
		return shortestPath;
	}
	

	
}
