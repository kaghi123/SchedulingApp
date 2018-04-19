package BFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Constraint {
	Scanner in = new Scanner(System.in);
	public List<List<SemesterCourses>> listOfPaths;
	
	
	public Constraint() {
		
	}
	
@SuppressWarnings("unused")
public Constraint(List<ClassInfo> classInfo, List<String> classesTaken, int maxUnits, List<String> lockedClasses){
		List<String> classes = new ArrayList<String>();
		for(int i = 0; i < lockedClasses.size(); i++){
			String[] temp = lockedClasses.get(i).split(" ");
			classes.add(temp[0]);
			classes.add(temp[1]);
			classes.add(temp[2]);
		}
		
		DisplayClass DC = new DisplayClass(classInfo, classesTaken, true, classes, maxUnits);
		List<SemesterCourses> sc = DC.Display(maxUnits);
		listOfPaths = DC.getListOfPaths();
		
}

	public boolean isAvailable(String name, String semester, String year, List<ClassInfo> classInfo){
		
		for(int i = 0; i < classInfo.size(); i++){
			if(classInfo.get(i).getName().equals(name)){
				if(classInfo.get(i).getSemester().contains(semester)){
						return true;
				}
			}
		}
		return false;
	}
	
	public List<List<SemesterCourses>> getList(){
		return listOfPaths;
	}
}