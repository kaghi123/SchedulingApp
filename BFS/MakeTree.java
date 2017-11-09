package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import BFS.Node;

public class MakeTree {
	@SuppressWarnings("rawtypes")
	private Queue<Node> queue = new LinkedList<Node>();
	
	//initial parent node
	Node<ArrayList<ClassInfo>> parentNode = new Node<ArrayList<ClassInfo>>(null);

	public static long startTime = System.currentTimeMillis();
	
	
	
	@SuppressWarnings("unchecked")
	public MakeTree(ArrayList<ClassInfo> classesTaken, ArrayList<ClassInfo> listOfClassInfo, int unitsMin, int unitsMax) {
				
		ArrayList<ClassInfo> newList = new ArrayList<ClassInfo>();	
		newList = createNode(classesTaken);
				
		parentNode.setData(newList);
		queue.add(parentNode);
		parentNode.startPath(parentNode);	
		
		for(int i = 0; i < queue.size();){
			
			goThroughQueue(queue.element(), queue.element(), listOfClassInfo, unitsMin, unitsMax);
			queue.remove();
		}
	}
	
	public ArrayList<ClassInfo> createNode(ArrayList<ClassInfo> classes){
		
		ArrayList<ClassInfo> aList = new ArrayList<ClassInfo>();
		
		for(ClassInfo current : classes) {
			aList.add(current);
		}
		
		return aList;
	}
	
	
	public ArrayList<ArrayList<ClassInfo>> findAvailAndCombo(Node<?> classesTaken, ArrayList<ClassInfo> listOfClasses, int unitsMin, int unitsMax){
		
		//find classes that are available to take next
		AvailableClasses av = new AvailableClasses(classesTaken);
		ArrayList<ClassInfo> available;
		available = av.checkAvailableClasses(listOfClasses);
				
		//make arraylist an array to send through combination 
		ClassInfo availArr[];
		availArr = new ClassInfo[available.size()];
				
		for(int i = 0; i < available.size(); i++){
			availArr[i] = available.get(i);
		}
		
		//find all combination
		ArrayList<ArrayList<ClassInfo>> combOfClasses;
		Combination cb = new Combination();
		combOfClasses = cb.findCombination(availArr, unitsMin, unitsMax);
		
		return combOfClasses;
		
	}
	
	
	public void goThroughQueue(Node<ArrayList<ClassInfo>> parent, Node<ArrayList<ClassInfo>> classesTaken, ArrayList<ClassInfo> listOfClassInfo, int unitsMin, int unitsMax){
		ArrayList<ArrayList<ClassInfo>> combOfClasses;
		ArrayList<ClassInfo> listOfClasses = new ArrayList<ClassInfo>();
		
		for(int i = 0; i < listOfClassInfo.size(); i++){
			listOfClasses.add(listOfClassInfo.get(i));
		}
		
		combOfClasses = findAvailAndCombo(classesTaken.getListNodes(), listOfClasses, unitsMin, unitsMax);
		
		//turn combinations into nodes and add those nodes as children
		ArrayList<ClassInfo> childList = new ArrayList<ClassInfo>();
		
		for(int i = 0; i < combOfClasses.size(); i++){
			Node<ArrayList<ClassInfo>> nodeClasses = new Node<ArrayList<ClassInfo>>(null);
			childList = createNode(combOfClasses.get(i));
			
			nodeClasses.setData(childList);
			parent.addChild(nodeClasses);
			nodeClasses.addToPath(nodeClasses, parent.getPath());
			
			//add to queue
			queue.add(nodeClasses);
		}
	}	
}