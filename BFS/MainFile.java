package BFS;

import java.io.IOException;
import BFS.FileInput;
import BFS.DisplayClass;
import BFS.Combination;

public class MainFile { 

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		
	
		FileInput f = new FileInput("Sample_Classes.csv");
		
		System.out.println("");
		
		DisplayClass DC = new DisplayClass(f.getListOfClassInfo());
		
		DC.Display();
		
		
		
		
		//example
		ClassInfo c1 = new ClassInfo("CS1000", 01231, 3, "NA", false);
		ClassInfo c2 = new ClassInfo("CS1010", 04531, 3, "NA", false);
		ClassInfo c3 = new ClassInfo("CS2500", 01231, 3, "NA", false);
		ClassInfo c4 = new ClassInfo("CS3900", 01231, 3, "NA", false);
		ClassInfo c5 = new ClassInfo("CS4440", 01231, 3, "NA", false);
		ClassInfo c6 = new ClassInfo("CS4961", 01231, 3, "NA", false);

		//ClassInfo arr[] = {c1, c2, c3, c4, c5, c6};
		//Combination cb = new Combination(arr, 12, 12);
		

		
	/*	
		MakeTree MT = new MakeTree(f.getListOfClassInfo());
		MT.MakeT();
		List<ClassInfo> root1 = new ArrayList<ClassInfo>();
		Node<List<ClassInfo>> root = new Node<>(root1);

		ClassInfo child1 = new ClassInfo("CS1000", 01231, 3, "NA", false, 1);
		List<ClassInfo> l1 = new ArrayList<ClassInfo>();
		l1.add(child1);
		Node<List<ClassInfo>> child_1 = new Node<>(l1);
		
		ClassInfo child2 = new ClassInfo("CS1010", 04531, 3, "NA", false, 2);
		List<ClassInfo> l2 = new ArrayList<ClassInfo>();
		l2.add(child2);
		Node<List<ClassInfo>> child_2 = new Node<>(l2);
		
		
		root.addChild(child_1);
		root.addChild(child_2);
		
	

		ClassInfo gchild1 = new ClassInfo("CS2500", 01231, 3, "NA", false, 3);
		List<ClassInfo> l3 = new ArrayList<ClassInfo>();
		l3.add(gchild1);
		Node<List<ClassInfo>> gchild_1 = new Node<>(l3);
		
		ClassInfo gchild2 = new ClassInfo("CS3900", 01231, 3, "NA", false, 3);
		List<ClassInfo> l4 = new ArrayList<ClassInfo>();
		l4.add(gchild2);
		Node<List<ClassInfo>> gchild_2 = new Node<>(l4);

	
		child_1.addChild(gchild_1);
		child_1.addChild(gchild_2);

		ClassInfo ggchild1 = new ClassInfo("CS4440", 01231, 3, "NA", false, 5);
		List<ClassInfo> l5 = new ArrayList<ClassInfo>();
		l5.add(ggchild1);
		Node<List<ClassInfo>> ggchild_1 = new Node<>(l5);
	
		gchild_1.addChild(ggchild_1);

		System.out.println("Breadth First Search : ");
		
		T_BFS bfs = new T_BFS(root);
		//ArrayList<Node> path = bfs.findShortestPath();
		
		*/
	
	
	}
	

}
