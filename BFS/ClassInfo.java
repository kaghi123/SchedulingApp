package BFS;

import java.util.ArrayList;

public class ClassInfo {
	
	private String name;
	private int courseNumber;
	private ArrayList<ClassInfo> corequisites;
	private ArrayList<ClassInfo> prerequisites;
	private int units;
	private String grade;
	private boolean completed;
	
	//constructor if class has both prerequisite and corequisite
	public ClassInfo(String name, int courseNumber, 
			ArrayList<ClassInfo> corequisites, ArrayList<ClassInfo> prerequisites,
			int units, String grade, boolean completed) {
		this.name = name;
		this.courseNumber = courseNumber;
		this.corequisites = corequisites;
		this.prerequisites = prerequisites;
		this.units = units;
		this.grade = grade;
		this.completed = completed;
	}
	
	//constructor if class has just prerequisite
	public ClassInfo(String name, int courseNumber, 
			ArrayList<ClassInfo> prerequisites,
			int units, String grade, boolean completed) {
		this();
		this.name = name;
		this.courseNumber = courseNumber;
		this.prerequisites = prerequisites;
		this.units = units;
		this.grade = grade;
		this.completed = completed;
	}
	
	//constructor if class has just corequisite
	public ClassInfo(String name, int courseNumber, 
			int units, String grade, 
			ArrayList<ClassInfo> corequisites, boolean completed) {
		this.name = name;
		this.courseNumber = courseNumber;
		this.corequisites = corequisites;
		this.units = units;
		this.grade = grade;
		this.completed = completed;
	}
	
	//constructor if class has neither prerequisite and corequisite
	public ClassInfo(String name, int courseNumber,
			int units, String grade, boolean completed) {
		this.name = name;
		this.courseNumber = courseNumber;
		this.units = units;
		this.grade = grade;
		this.completed = completed;
	}
	
	public ClassInfo() {
		
	}

	public ArrayList<ClassInfo> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(ArrayList<ClassInfo> prerequisites) {
		this.prerequisites = prerequisites;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	public ArrayList<ClassInfo> getCorequisites() {
		return corequisites;
	}

	public void setCorequisites(ArrayList<ClassInfo> corequisites) {
		this.corequisites = corequisites;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	private String convertToString(ArrayList<ClassInfo> ci) {
		if(ci != null) {
			String s = "";
			for(ClassInfo c: ci){
				s += c.getName() + ", ";
			}
			
			return s;
		}
		else {
			return "";
		}
		
		
	}
	
	public String toString() {
		
		if(this.name == null){
			return "";
		}
		else{
			return "Class: " + this.name + "\nCourse Number: " + this.courseNumber + "\nCorequisites: " + 
					convertToString(this.corequisites) + "\nPrerequisites: " + convertToString(this.prerequisites) + 
					"\nUnits: " + this.units + "\nGrade: " + this.grade + "\nCompleted: " + String.valueOf(this.completed)
					+ "\n";
		}
		
	}
	
}
