package BFS;

import java.util.ArrayList;
import java.util.Scanner;

public class DisplayClass {

	private ArrayList<ClassInfo> listOfClassInfo;
	ArrayList<ClassInfo> CL = new ArrayList<>();
	ArrayList<ClassInfo> available = new ArrayList<>();
	ArrayList<ClassInfo> classesTaken = new ArrayList<>();
	int unitsPS;

	Scanner in = new Scanner(System.in);
	String option;
	String grade;
	String units;

	public DisplayClass() {

	}

	public DisplayClass(ArrayList<ClassInfo> listOfClassInfo) {
		this.listOfClassInfo = listOfClassInfo;
	}

	public void Display() {

		CL = listOfClassInfo;

		// ask if user has taken class
		for (int i = 0; i < CL.size(); i++) {
			askQuestion(i);
		}

		// display classes that have been taken
		System.out.println();
		System.out.println();
		for (int i = 0; i < CL.size(); i++) {
			if (CL.get(i).isCompleted()) {
				System.out.println(CL.get(i).toString());
				classesTaken.add(CL.get(i));
			}

		}
		
		
		
		
		
		System.out.println("What is the minimum number of Units you would like to take per Semester. "); // put different recemendations, international students, part time full time, 12 is usual, 18 max
		units = in.next();
		unitsPS = Integer.parseInt(units);
		
		
		Node<ArrayList<ClassInfo>> newNode = new Node<ArrayList<ClassInfo>>(null);
		ArrayList<ClassInfo> newList = new ArrayList<ClassInfo>();
		for(ClassInfo current : listOfClassInfo) {
			newList.add(current);
		}
		
		newNode.setData(newList);
		
		AvailableClasses av = new AvailableClasses(newNode, listOfClassInfo);
		
		available = av.checkAvailableClasses();
		
		System.out.println("Test available classes");
		
		for(int i = 0; i < available.size(); i++){
			System.out.println(available.get(i));
		}
		
		
		
		ClassInfo availArr[];
		availArr = new ClassInfo[available.size()];
		
		for(int i = 0; i < available.size(); i++){
			availArr[i] = available.get(i);
		
		}
		
		Combination cb = new Combination(availArr, 0, unitsPS);
		
		
		
	}

	public void askQuestion(int i) {
		System.out.println();
		System.out.println(CL.get(i).getName());
		System.out.println("Have you taken this class? (y/n) ");
		option = in.next();

		if (option.equals("y") || option.equals("Y")) {
			CL.get(i).setCompleted(true);

			System.out.println("Enter a grade:  ");
			grade = in.next();
			if (grade.equals("A+") || grade.equals("A") || grade.equals("A-") || grade.equals("B+")
					|| grade.equals("B") || grade.equals("B-") || grade.equals("C+") || grade.equals("C")
					|| grade.equals("C-") || grade.equals("D+") || grade.equals("D") || grade.equals("D-")|| grade.equals("F")) {
				CL.get(i).setGrade(grade);
			}else {
				System.out.println("Invalid Input");
				askQuestion(i);
			}

		} else if (option.equals("n") || option.equals("N")) {

		} else {
			System.out.println("Invalid Input");
			askQuestion(i);

		}
	}
}
