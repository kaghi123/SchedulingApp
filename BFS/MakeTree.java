package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MakeTree {

	@SuppressWarnings("rawtypes")
	private Queue<Node> queue = new LinkedList<Node>();
	
	//test time
	public static long startTime = System.currentTimeMillis();
	
	//set the initial taken classes as parent node. Start the BFS. Go through the queue, the 
	//children of the element, remove the head, repeat
	@SuppressWarnings("unchecked")
	public MakeTree(ArrayList<ClassInfo> classesTaken, ArrayList<ClassInfo> listOfClassInfo, int unitsMin, int unitsMax) {
		
		//initial parent node
		Node<ArrayList<ClassInfo>> parentNode = new Node<ArrayList<ClassInfo>>(null);
		
		parentNode.setData(classesTaken);
		queue.add(parentNode);
		parentNode.startPath(parentNode);	
		
		//BFS
		for(int i = 0; i < queue.size();){
			goThroughQueue(queue.element(), listOfClassInfo, unitsMin, unitsMax);
			queue.remove();
		}
	}
	
	//Find combinations of classes. Add those combinations as children into the queue
	public void goThroughQueue(Node<ArrayList<ClassInfo>> parent, ArrayList<ClassInfo> listOfClassInfo, int unitsMin, int unitsMax){
		
		ArrayList<ArrayList<ClassInfo>> combOfClasses = findAvailAndCombo(parent.getListNodes(), listOfClassInfo, unitsMin, unitsMax);
		
		ArrayList<ClassInfo> childList = new ArrayList<ClassInfo>();
		
		for(int i = 0; i < combOfClasses.size(); i++){
			childList = combOfClasses.get(i);
			
			//add combinations to path
			Node<ArrayList<ClassInfo>> nodeClasses = new Node<ArrayList<ClassInfo>>(null);
			nodeClasses.setData(childList);
			parent.addChild(nodeClasses);
			nodeClasses.addToPath(nodeClasses, parent.getPath());
			
			//add to queue
			queue.add(nodeClasses);
		}
	}
	
	
	public ArrayList<ArrayList<ClassInfo>> findAvailAndCombo(Node<?> classesTaken, ArrayList<ClassInfo> listOfClasses, int unitsMin, int unitsMax){
		
		//find classes that are available to take next
		AvailableClasses av = new AvailableClasses(classesTaken);
		ArrayList<ClassInfo> available = av.checkAvailableClasses(listOfClasses);
		
		//find all combination
		Combinations cb = new Combinations();
		ArrayList<ArrayList<ClassInfo>> combOfClasses = cb.findCombination(available, unitsMin, unitsMax);
		
		return combOfClasses;
		
	}
	
	
}