package BFS;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import BFS.FileInput;
import BFS.DisplayClass;

public class MainFile { 

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		//classes taken sent in
		List<String> classesTaken = new ArrayList<String>();
		List<String> constraintClasses = new ArrayList<String>();
		boolean constraint = true;
		int maxUnits = 17;
//		classesTaken.add("CS-1010");
//		classesTaken.add("ENGL-1010");
//		classesTaken.add("MATH-2110");
//		classesTaken.add("GE-D1");
//		classesTaken.add("GE-AMERICANINSTITUTION1");
		constraintClasses.add("ENGL-1010 Fall 2020");
		constraintClasses.add("CS-3034 Spring 2021");
		constraintClasses.add("GE-AMERICANINSTITUTION2 Fall 2018");
		
		
		//brings in the csv file and parses it in the FileInput Class
		FileInput f = new FileInput("Sample_Classes.csv");
		
		System.out.println("");
		
		if(!constraint){
			//sends the list of classes to the DisplayCLass Class
			DisplayClass DC = new DisplayClass(f.getListOfClassInfo(), classesTaken, maxUnits);
				
			//returns the list of semester courses
			List<SemesterCourses> sc = DC.Display(maxUnits);
			List<List<SemesterCourses>> listOfPaths = DC.getListOfPaths();
			
		}else{
			//ask if user would like to switch classes
			Constraint c = new Constraint(f.getListOfClassInfo(), classesTaken, maxUnits, constraintClasses);
			List<List<SemesterCourses>> listOfPaths = c.getList();
			
		}
	}
} 