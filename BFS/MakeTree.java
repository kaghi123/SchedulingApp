package BFS;

import java.util.ArrayList;
import java.util.List;

import BFS.Node;
import BFS.T_BFS;

public class MakeTree {

	private ArrayList<ClassInfo> listOfClassInfo;
	List<ClassInfo> root1 = new ArrayList<ClassInfo>();
	Node<List<ClassInfo>> root = new Node<>(root1); 
	
	public MakeTree(){
		
	}
	
	public MakeTree(ArrayList<ClassInfo> listOfClassInfo) {
		this.listOfClassInfo = listOfClassInfo;
	}

	public void MakeT() {
		
		ArrayList<ClassInfo> CL = new ArrayList<>();
		CL = listOfClassInfo;
		
		//Put ClassInfo into Node
		ArrayList<Node<List<ClassInfo>>> cI = new ArrayList<>();
		for(int i = 0; i < CL.size(); i++){
			List<ClassInfo> list = new ArrayList<ClassInfo>(); 
			list.add(CL.get(i));
			Node<List<ClassInfo>> list_ = new Node<>(list);
			cI.add(list_);
		}
		
	}
				
	T_BFS bfs = new T_BFS(root);

}
