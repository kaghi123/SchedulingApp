package BFS;
import java.util.ArrayList;

public class AvailableClasses {

	private Node current;
	private ArrayList<ClassInfo> classesTaken;
	private ArrayList<ClassInfo> allClasses;
	private ArrayList<ClassInfo> availableClasses = new ArrayList<ClassInfo>();
	
	
	public AvailableClasses(Node classtaken, ArrayList<ClassInfo> allClasses) {
		this.current = classtaken;
		this.allClasses = allClasses;
	}
	
	public ArrayList<ClassInfo> checkAvailableClasses() {
		if(this.current == null) {
			return null;
		}
		this.classesTaken = (ArrayList<ClassInfo>) this.current.getData();
		
		//boolean that checks if student has taken that class
		boolean isTaken = false;
		ClassInfo currentAvailableClass = null;
		//gets the element in allclasses arraylist to take out
		int elementTakeOut = 0;
		
		for(ClassInfo taken: this.classesTaken) { //goes through classes taken
			for(int i = 0; i < this.allClasses.size(); i++) { //goes through list of classes available
				if(taken.getName().toLowerCase().equals(this.allClasses.get(i).getName().toLowerCase())) { //if course name equals to class taken course name
					isTaken = true;
					elementTakeOut = i;
					break;
				}
			}
			if(isTaken) {
				this.allClasses.remove(elementTakeOut); //avoid checking that class again
				elementTakeOut = 0; //resets back to 0
				
				//if student doesn't have the prerequisites, then they can't add the class
				isTaken = false;
				currentAvailableClass = null;
			}
		}
		
		
		for(ClassInfo aClass: this.allClasses){
			for(ClassInfo taken: this.classesTaken){
				if(aClass.getPrerequisites() == null){ //if no prerequisite, add class to available classes that user can add  //search through allClasses instead of currentAvailableClass
					//send availableClasses and aCLass to method to see if aClass is already in there
							this.availableClasses.add(aClass);
						}
					
				//if there's a prerequisite, check to see if the student has taken the classes needed to add class to available classes that user can add
				else if(aClass.getPrerequisites() != null && checkPrereq(taken, aClass.getPrerequisites())) { 
					//send availableClasses and aCLass to method to see if aClass is already in there
							this.availableClasses.add(aClass);
						}
					}
					
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
}

