package BFS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MakeTree {
	
	//test time
	public static long startTime = System.currentTimeMillis();
	private List<List<SemesterCourses>> listOfPaths = new ArrayList<>();
	
	//set the initial taken classes as parent node. Start the BFS. Go through the queue, the 
	//children of the element, remove the head, repeat

	public MakeTree() {
		
	}

	public List<SemesterCourses> start(List<String> classesTaken, HashMap<String, ClassInfo> listOfClassInfo, int unitsMax) {
		Queue<Node> queue = new LinkedList<Node>();
		Set<List<String>> visited = new HashSet<List<String>>();
		List<SemesterCourses> sc = null;
		boolean breakWhile = false;
		int numberOfRoadMapsGenerated = 0;
		int amountOfRoadMaps = 3;
		int currLevelSize = 0;
		int nextLevelSize = 0;
		int counter = 0;
		
//		String[] semesters = {"Winter", "Spring", "Summer", "Fall"};
		String[] semesters = {"Spring", "Fall"};
//		int weekOfYear = Integer.parseInt(new SimpleDateFormat("w").format(new java.util.Date()));
		
		int index = 1;
		
//		if(weekOfYear >= 32 && weekOfYear < 51) {
//			index = 3;
//			semesterCode += semesters[index] + " " + year;
//		}
//		else if(weekOfYear >= 1 && weekOfYear < 3) {
//			index = 0;
//			semesterCode += semesters[index] + " " + ++year;
//		}
//		else if(weekOfYear >= 3 && weekOfYear < 21) {
//			index = 1;
//			semesterCode += semesters[index] + " " + ++year;
//		}
//		else if(weekOfYear >= 21 && weekOfYear < 32) {
//			index = 2;
//			semesterCode += semesters[index] + " " + ++year;
//		}
		
//		if(weekOfYear >= 21 && weekOfYear < 51) {
//			index = 1;
//		}
//		else if(weekOfYear >= 1 && weekOfYear < 21) {
//			index = 0;
//		}

		//initial parent node
		Node parentNode = new Node(null);
		parentNode.setData(classesTaken);
		queue.add(parentNode); 
		parentNode.startPath(parentNode);	
		
		//start of BFS
		while(!queue.isEmpty()){
			//get the head of the queue
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
					
					//list of semester courses for the current path
					sc = curr.getSemesterCourses();
					
					if(numberOfRoadMapsGenerated < amountOfRoadMaps) {//add path to roadmap
						numberOfRoadMapsGenerated++;
						listOfPaths.add(sc);
					}
					else {
						System.out.println("Number of paths: " + listOfPaths.size());
						for(List<SemesterCourses> c : listOfPaths) {
							System.out.println(c);
						}
						breakWhile = true;
					}
					
					//checks time in milliseconds for testing puropses
					long endTime = System.currentTimeMillis();
					long totaltime = endTime  - startTime;
					System.out.print(totaltime);
					

				}else{
					counter++;
					
					//add children to the path
					for( Node c : curr.getChildren(listOfClassInfo, curr.getTakenClasses(), unitsMax, semesters[index])){
						curr.addChild(c);
						c.addToPath(c, curr.getPath());
						
						//get the children and add them to the queue
						queue.add(c);
						currLevelSize++;
					}
					
					//increment semesters
//					if(nextLevelSize == 0) {//after getting the children for the first time set currlevel to nextlevel to keep track of level of when the semester should change
//						nextLevelSize = currLevelSize;
//						currLevelSize = 0;
//						counter = 0;
//						//increment semesters
//						if(index % 2 == 0) { 
//							index++;
//						}
//						else {
//							index = 0;
//						}
//					}
//					else if(counter == nextLevelSize) {//counter is the the amount of children in the current level and if it equals to nextlevel, transition to next semester
//						nextLevelSize = currLevelSize;
//						currLevelSize = 0;
//						counter = 0;
						//increment semesters
						if(index % 2 == 0) { 
							index++;
						}
						else {
							index = 0;
						}
//					}
				}
			}
			
			//will stop the while loop and return the semester courses when it gets to checkgoal is true
			if(breakWhile == true){
				break;
			}
		}
		
//		if(queue.size() == 0){
//			System.out.println("The program stopped because the input sizes you put in did not work with the number of classes we have");
//		}
		
		return sc;
	}
	
	public List<List<SemesterCourses>> getSemesterCourses(){
		return listOfPaths;
	}
	
	//visited is a set that keeps track of everything that goes through the queue. If there is a repeated element visited doesn't let the children go into the queue again
	public boolean isVisited(Set<List<String>> visited, Node curr){
		
		int pSize = curr.getPath().size();
		
		if( visited.contains(curr.getPath().get(pSize - 1).getData()) && !curr.getPath().get(pSize - 1).getData().isEmpty()){
			return true;
		}else{
			visited.add(curr.getPath().get(pSize - 1).getData());
			return false;
		}
	}
	
	//checks if a semester has cs4962 and cs4963
	public boolean checkGoal(Node curr){
		if(curr.getData().contains("CS4962") && curr.getData().contains("CS4963")){
			return true;
		}else{
			
			return false;
		}
	}
}


//
////make sure to use getNumOfElectives method in combinations class to get the correct combination of classes and not add too many elective units
////int currentElectiveUnits = nodeClasses.getParent().getNumOfElectiveUnits();//get amount of elective units from parent node and adds it to current node
////nodeClasses.addNumOfElectiveUnits(currentElectiveUnits);//adds total elective units taken to current node
//for(ClassInfo c : combOfClasses.get(i)) { //this goes through the children for this node to check amount of electives and check goal node
//	if(c.isElective()) {
//		nodeClasses.addNumOfElectiveUnits(c.getUnits());//add the amount of elective units taken from a combination of classes to current node
//	}
//	if(c.getName().toLowerCase().equals("cs4962") || c.getName().toLowerCase().equals("cs4963")) {//temporary check goal node fix 
//		nodeClasses.setGoal(true);
//	}
//}





















/*
package BFS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MakeTree {
	
	//test time
	public static long startTime = System.currentTimeMillis();
	private List<List<SemesterCourses>> listOfPaths = new ArrayList<>();
	
	//set the initial taken classes as parent node. Start the BFS. Go through the queue, the 
	//children of the element, remove the head, repeat

	public MakeTree() {
		
	}

	public List<SemesterCourses> start(List<String> classesTaken, HashMap<String, ClassInfo> listOfClassInfo, int unitsMax) {
		Queue<Node> queue = new LinkedList<Node>();
		Set<List<String>> visited = new HashSet<List<String>>();
		List<SemesterCourses> sc = null;
		boolean breakWhile = false;int numberOfRoadMapsGenerated = 0;
		int amountOfRoadMaps = 3;
		int currLevelSize = 0;
		int nextLevelSize = 0;
		int counter = 0;
		
//		String[] semesters = {"Winter", "Spring", "Summer", "Fall"};
		String[] semesters = {"Spring", "Fall"};
		//int weekOfYear = Integer.parseInt(new SimpleDateFormat("w").format(new java.util.Date()));
		
		int index = 1;
		
//		if(weekOfYear >= 32 && weekOfYear < 51) {
//			index = 3;
//			semesterCode += semesters[index] + " " + year;
//		}
//		else if(weekOfYear >= 1 && weekOfYear < 3) {
//			index = 0;
//			semesterCode += semesters[index] + " " + ++year;
//		}
//		else if(weekOfYear >= 3 && weekOfYear < 21) {
//			index = 1;
//			semesterCode += semesters[index] + " " + ++year;
//		}
//		else if(weekOfYear >= 21 && weekOfYear < 32) {
//			index = 2;
//			semesterCode += semesters[index] + " " + ++year;
//		}
		
//		if(weekOfYear >= 21 && weekOfYear < 51) {
//			index = 1;
//		}
//		else if(weekOfYear >= 1 && weekOfYear < 21) {
//			index = 0;
//		}

		//initial parent node
		Node parentNode = new Node(null);
		parentNode.setData(classesTaken);
		queue.add(parentNode); 
		parentNode.startPath(parentNode);	
		
		//start of BFS
		while(!queue.isEmpty()){
			//get the head of the queue
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
					
					//list of semester courses for the current path
					sc = curr.getSemesterCourses();
					
					if(numberOfRoadMapsGenerated < amountOfRoadMaps) {//add path to roadmap
						numberOfRoadMapsGenerated++;
						listOfPaths.add(sc);
					}
					else {
						System.out.println("Number of paths: " + listOfPaths.size());
						for(List<SemesterCourses> c : listOfPaths) {
							System.out.println(c);
						}
						breakWhile = true;
					}
					
					//checks time in milliseconds for testing puropses
					long endTime = System.currentTimeMillis();
					long totaltime = endTime  - startTime;
					System.out.print(totaltime);
					

				}else{
					counter++;
					
					//add children to the path
					for( Node c : curr.getChildren(listOfClassInfo, curr.getTakenClasses(), unitsMax, semesters[index])){
						curr.addChild(c);
						c.addToPath(c, curr.getPath());
						
						//get the children and add them to the queue
						queue.add(c);
						currLevelSize++;
					}
					
					//increment semesters
					//after getting the children for the first time set currlevel to nextlevel to keep track of level of when the semester should change
					if(nextLevelSize == 0) {
						nextLevelSize = currLevelSize;
						currLevelSize = 0;
						counter = 0;
						//increment semesters
						if(index % 2 == 0) { 
							index++;
						}
						else {
							index = 0;
						}
					}
					//counter is the the amount of children in the current level and if it equals to nextlevel, transition to next semester
					else if(counter == nextLevelSize) {
						nextLevelSize = currLevelSize;
						currLevelSize = 0;
						counter = 0;
						//increment semesters
						if(index % 2 == 0) { 
							index++;
						}
						else {
							index = 0;
						}
					}
				}
			}
			
			//will stop the while loop and return the semester courses when it gets to checkgoal is true
			if(breakWhile == true){
				break;
			}
		}
		
//		if(queue.size() == 0){
//			System.out.println("The program stopped because the input sizes you put in did not work with the number of classes we have");
//		}
		
		return sc;
	}
	
	public List<List<SemesterCourses>> getSemesterCourses(){
		return listOfPaths;
	}
	
	//visited is a set that keeps track of everything that goes through the queue. If there is a repeated element visited doesn't let the children go into the queue again
	public boolean isVisited(Set<List<String>> visited, Node curr){
		
		int pSize = curr.getPath().size();
		
		if( visited.contains(curr.getPath().get(pSize - 1).getData()) && !curr.getPath().get(pSize - 1).getData().isEmpty()){
			return true;
		}else{
			visited.add(curr.getPath().get(pSize - 1).getData());
			return false;
		}
	}
	
	//checks if a semester has cs4962 and cs4963
	public boolean checkGoal(Node curr){
		//962") && curr.getData().contains("CS4963
		if(curr.getData().contains("CS4962") && curr.getData().contains("CS4963")){
			return true;
		}else{
			
			return false;
		}
	}
}


//
////make sure to use getNumOfElectives method in combinations class to get the correct combination of classes and not add too many elective units
////int currentElectiveUnits = nodeClasses.getParent().getNumOfElectiveUnits();//get amount of elective units from parent node and adds it to current node
////nodeClasses.addNumOfElectiveUnits(currentElectiveUnits);//adds total elective units taken to current node
//for(ClassInfo c : combOfClasses.get(i)) { //this goes through the children for this node to check amount of electives and check goal node
//	if(c.isElective()) {
//		nodeClasses.addNumOfElectiveUnits(c.getUnits());//add the amount of elective units taken from a combination of classes to current node
//	}
//	if(c.getName().toLowerCase().equals("cs4962") || c.getName().toLowerCase().equals("cs4963")) {//temporary check goal node fix 
//		nodeClasses.setGoal(true);
//	}
/*/