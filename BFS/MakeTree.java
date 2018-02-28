package BFS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MakeTree {
	
	//test time
	public static long startTime = System.currentTimeMillis();
	
	//set the initial taken classes as parent node. Start the BFS. Go through the queue, the 
	//children of the element, remove the head, repeat
	
	public MakeTree(List<String> classesTaken, HashMap<String, ClassInfo> listOfClassInfo, int unitsMin, int unitsMax) {
		
		Queue<Node> queue = new LinkedList<Node>();
		
		Set<List<String>> visited = new HashSet<List<String>>();
		
		//initial parent node
		Node parentNode = new Node(null);
		parentNode.setData(classesTaken);
		queue.add(parentNode); 
		parentNode.startPath(parentNode);	
		
		while(!queue.isEmpty()){
			
			Node curr = new Node(null);
			curr = queue.remove();
			
			//check if curr is in visited
			if(isVisited(visited, curr)){
				
				//if it is visited then we dont need to add the children again
				//the rest shall be skipped and the queue will move on to the next element
				
			}else{
				
				//check if curr is goal node
				if(checkGoal(curr)){
					
					//if so print path
					List<Node> path = curr.getPath();
					for(int i = 0; i < path.size(); i++){
						System.out.println(path.get(i).getData());
					}
					
					long endTime = System.currentTimeMillis();
					long totaltime = endTime  - startTime;
					System.out.print(totaltime);
					System.exit(0);

					
					
				}else{
					
					//add children to the path
					for( Node c : curr.getChildren(listOfClassInfo, curr.getTakenClasses(), unitsMin, unitsMax)){
						
						curr.addChild(c);
						c.addToPath(c, curr.getPath());
						
						//get the children and add them to the queue
						queue.add(c);
					}
				}
			}
		}
	}
	
	public boolean isVisited(Set<List<String>> visited, Node curr){
		
		if( visited.containsAll(curr.getTakenClasses()) && !curr.getTakenClasses().isEmpty()){
			return true;
		}else{
			visited.add(curr.getTakenClasses());
			return false;
		}
	}
	
	public boolean checkGoal(Node curr){
		//962") && curr.getData().contains("CS4963")
		if(curr.getData().contains("CS4962") && curr.getData().contains("CS4963")){
			
			return true;
		}else{
			
			return false;
		}
	}
	
	
	

//	
//	//make sure to use getNumOfElectives method in combinations class to get the correct combination of classes and not add too many elective units
//	//int currentElectiveUnits = nodeClasses.getParent().getNumOfElectiveUnits();//get amount of elective units from parent node and adds it to current node
//	//nodeClasses.addNumOfElectiveUnits(currentElectiveUnits);//adds total elective units taken to current node
//	for(ClassInfo c : combOfClasses.get(i)) { //this goes through the children for this node to check amount of electives and check goal node
//		if(c.isElective()) {
//			nodeClasses.addNumOfElectiveUnits(c.getUnits());//add the amount of elective units taken from a combination of classes to current node
//		}
//		if(c.getName().toLowerCase().equals("cs4962") || c.getName().toLowerCase().equals("cs4963")) {//temporary check goal node fix 
//			nodeClasses.setGoal(true);
//		}
//	}
//
}