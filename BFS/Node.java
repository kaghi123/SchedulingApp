package BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Node{
    private List<String> classTaken;
    private Node parent;
    private int numOfElectiveUnits = 0;
    private boolean isGoal = false;
    List<Node> children = new ArrayList<>();
    

	private List<Node> path = new ArrayList<>();
    private List<String> takenClassesFromPath = new ArrayList<String>();

    public Node(List<String> data) {
        this.classTaken = data;
    }

    public List<Node> addChild(Node child) {
    	
        child.setParent(this);
        children.add(child);
        return children;
    }

	public void addChildren(List<Node> children) {
        for(Node t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

	public List<Node> getChildren(HashMap<String, ClassInfo> listOfClasses, List<String> classesTaken, int unitsMin, int unitsMax) {
		
		
		Set<String> keySet = listOfClasses.keySet();
		List<String> allClasses = new ArrayList<String>(keySet);
		
		//find classes that are available to take next
		AvailableClasses av = new AvailableClasses(classesTaken);
		List<String> available = av.checkAvailableClasses(allClasses, listOfClasses);
				
		//find all combination
		Combinations cb = new Combinations();
		List<Node> combOfClasses = cb.findCombination(listOfClasses, available, unitsMin, unitsMax);
				
		return combOfClasses;
        
    }
    
//    public void insertChildren(Node<?> child) {
//    	
//    }
//    
//    public void deleteChildren(Node<?> child) {
//    	
//    }

    public List<String> getData() {
        return classTaken;
    }

    public void setData(List<String> data) {
        this.classTaken = data;
    }

    private void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public int getNumOfElectiveUnits() {
		return numOfElectiveUnits;
	}

	public void addNumOfElectiveUnits(int numOfElectives) {
		this.numOfElectiveUnits += numOfElectives;
	}
	
	public void startPath(Node currentNode) {
		this.path.add(currentNode);
		setTakenClasses();
		
	}

	public void addToPath(Node currentNode, List<Node> pathNode) {
		for(Node n : pathNode) {
			this.path.add(n);
		}
		this.path.add(currentNode);
		setTakenClasses();
	}
	
	public List<Node> getPath(){
		return this.path;
	}
	
	private void setTakenClasses(){
		if(this.path.get(this.path.size() - 1).getData() == null) {//get the previous node from the path and get the list of  classes taken from that point on the path
			return;
		}
		for(Node n : this.path) {
			takenClassesFromPath.addAll(n.getData());
//			for(String c : n.getData()) {
//				takenClassesFromPath.add(c);
//				//System.out.println(c);
//			}
		}
//		System.out.println("--------------------------------------");
//		System.out.println("");
//		System.out.println("");
		
		
		
	}
	
	public List<String> getTakenClasses(){
		return takenClassesFromPath;
	}

	public boolean isGoal() {
		return isGoal;
	}

	public void setGoal(boolean isGoal) {
		this.isGoal = isGoal;
	}
	
	
	
}