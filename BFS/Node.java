package BFS;

import java.util.ArrayList;
import java.util.List;

public class Node<T>{
    private T data = null;
    @SuppressWarnings("rawtypes")
	private List<Node> children = new ArrayList<>();
    private Node<?> parent = null;
    private int distance;
    @SuppressWarnings("rawtypes")
	private ArrayList<Node> path = new ArrayList<>();

    public Node(T data) {
        this.data = data;
    }

    public void addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(T data) {
        Node<T> newChild = new Node<>(data);
        newChild.setParent(this);
        children.add(newChild);
    }

    @SuppressWarnings("rawtypes")
	public void addChildren(List<Node> children) {
        for(Node<?> t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    @SuppressWarnings("rawtypes")
	public List<Node> getChildren() {
        return children;
    }
    
    public void insertChildren(Node<?> child) {
    	
    }
    
    public void deleteChildren(Node<?> child) {
    	
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(Node<?> parent) {
        this.parent = parent;
    }

    public Node<?> getParent() {
        return parent;
    }

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public void startPath(Node<T> currentNode) {
		this.path.add(currentNode);
		
	}

	@SuppressWarnings("rawtypes")
	public void addToPath(Node<T> currentNode, ArrayList<Node> pathNode) {
		for(Node<?> n : pathNode) {
			this.path.add(n);
		}
		this.path.add(currentNode);
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList<Node> getPath(){
		return this.path;
	}
	
	@SuppressWarnings("rawtypes")
	public Node<ArrayList<Node>> getListNodes(){
		Node<ArrayList<Node>> listOfNodes = new Node<ArrayList<Node>>(this.path);
		return listOfNodes;
	}

	
}
