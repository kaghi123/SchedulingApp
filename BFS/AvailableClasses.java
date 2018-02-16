package BFS;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import BFS.MakeTree;

public class AvailableClasses {

	private Node<?> current;
	private ArrayList<ClassInfo> classesTaken = new ArrayList<ClassInfo>();
	private ArrayList<ClassInfo> availableClasses = new ArrayList<ClassInfo>();
	long startTime = MakeTree.startTime;
	private Set<ClassInfo> setOfAllClasses;
	
	
	public AvailableClasses(Node<?> classtaken) {
		this.current = classtaken;
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ClassInfo> checkAvailableClasses(ArrayList<ClassInfo> allClasses) {
		
		setOfAllClasses = new HashSet<ClassInfo>(allClasses);
		ArrayList<Node<ArrayList<ClassInfo>>> tempArr = new ArrayList<Node<ArrayList<ClassInfo>>>();
		tempArr = (ArrayList<Node<ArrayList<ClassInfo>>>) this.current.getData();
		
		
		for(int i = 0; i < tempArr.size(); i++){
			for(int j = 0; j < tempArr.get(i).getData().size(); j++){
				classesTaken.add(tempArr.get(i).getData().get(j));
			}
		}
		
		if(classesTaken.size() == 0) {

			for(ClassInfo aClass: allClasses){
				if(aClass.getPrerequisites() == null){
					this.availableClasses.add(aClass);
				}
			}
			return this.availableClasses;
		}
		
		if(checkGoalNode()){ //instead of checking each class, make the whole arraylist a goal. Make into hashset containsall
			for(int i = 0; i < tempArr.size(); i++){
				for(int j = 0; j < tempArr.get(i).getData().size(); j++){
					System.out.println((tempArr.get(i).getData().get(j)));
				}
				System.out.println("");
				System.out.println("");
			}
			
			long endTime = System.currentTimeMillis();
			long totaltime = endTime  - startTime;
			System.out.print(totaltime);
			System.exit(0);
		}
		

//		System.out.println("All classes before removeAll.");
//		for(ClassInfo c : this.setOfAllClasses) {
//			System.out.println(c);
//		}
		setOfAllClasses.removeAll(this.classesTaken);
//		System.out.println("All classes after removeAll.");
//		for(ClassInfo c : this.setOfAllClasses) {
//			System.out.println(c);
//		}
		//boolean that checks if student has taken that class
//		boolean isTaken = false;
		//gets the element in allclasses arraylist to take out
//		int elementTakeOut = 0;
//		
//		for(ClassInfo taken: this.classesTaken) { //goes through classes taken
//			for(int i = 0; i < allClasses.size(); i++) { //goes through list of classes available
//				if(taken.getName().toLowerCase().equals(allClasses.get(i).getName().toLowerCase())) { //if course name equals to class taken course name
//					isTaken = true;
//					elementTakeOut = i;
//					break;
//				}
//			}
//			if(isTaken) {
//				allClasses.remove(elementTakeOut); //avoid checking that class again
//				elementTakeOut = 0; //resets back to 0
//				
//				//if student doesn't have the prerequisites, then they can't add the class
//				isTaken = false;
//			}
//		}
		
		
		
		
		for(ClassInfo aClass: this.setOfAllClasses){
			//for(ClassInfo taken: this.classesTaken){
				if(aClass.getPrerequisites() == null){ //if no prerequisite, add class to available classes that user can add 
					if(isClass(availableClasses, aClass)){
						this.availableClasses.add(aClass);
					}		
				}
					
				//if there's a prerequisite, check to see if the student has taken the classes needed to add class to available classes that user can add
				else if(aClass.getPrerequisites() != null && this.classesTaken.containsAll(aClass.getPrerequisites())) { 
					//send availableClasses and aCLass to method to see if aClass is already in there
					if(isClass(availableClasses, aClass)){
						this.availableClasses.add(aClass);
					}
				}
			//}		
		}	

		return this.availableClasses;
		
	}
	
	private boolean checkPrereq(ClassInfo current, ArrayList<ClassInfo> prereq) {
		int numOfPrereq = prereq.size();
		int counter = 0;
		for(ClassInfo c: prereq) {
			if(prereq.size() == 1 && c.getName().toLowerCase().equals(current.getName().toLowerCase())) {
				return true;
			}
			else {
				for(ClassInfo taken: this.classesTaken) {
					if(taken.getName().toLowerCase().equals(c.getName().toLowerCase())) {
						counter++;
					}
				}
				if(counter == numOfPrereq) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	//make method that checks classes inside arraylist
	public boolean isClass(ArrayList<ClassInfo> availableClasses, ClassInfo aClass){
		
		int counter = 0;
		
		for(int i = 0; i < availableClasses.size(); i++){
			if(availableClasses.get(i).getName().equals(aClass.getName())){
				
			}else{
				counter++;
			}
		}
		
		
		if(counter == availableClasses.size()){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean checkGoalNode() {
		for(ClassInfo c: this.classesTaken) {
			if(c.getName().toLowerCase().equals("cs4963")) {
				for(ClassInfo i: this.classesTaken){
					if(i.getName().toLowerCase().equals("cs4962")){
						return true;
					}
				}
				return false;
				
			}
		}
		return false;
	}
}