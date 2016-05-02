import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Explorer {
	public static int[][] resultArray = new int[][]{
		{1, 2, 3, 4},
		{5, 6, 7, 8},
		{9, 10, 11, 12},
		{13, 14, 15, 0},
	};


	public void DFS(Node firstNode, char[] nodeOrder) throws InterruptedException {
		int depth = 0;
		List<Node> childrenList = new ArrayList<Node>();
		List<Node> searchedNode = new ArrayList<Node>();
		Stack<Node> stackNode = new Stack<Node>();
		stackNode.push(firstNode);
		Node resultNode = new Node(resultArray);
		boolean rozwiazanie = false;
		do {
			Node actualNode = stackNode.pop();
			if (!searchedNode.contains(actualNode))
				searchedNode.add(actualNode);
			if (actualNode.equals(resultNode)) {
				//saveFile("plik.txt", actualNode.getArray());  //Sprawdzenie czy aktualny wÄ™zeÅ‚ nie jest rozwiÄ…zaniem
				rozwiazanie = true;
				System.out.println("znalazlem rozwiazanie po przeszukaniu " + searchedNode.size() + "wezlow");
				break;
			} else {
				childrenList = actualNode.generateChildren();
				for (int i = 0; i < nodeOrder.length; i++)
					for (Node childNode : childrenList) {
						if (childNode.getDirection() == nodeOrder[i]) {
							System.out.println("stworzylem wezel ale nie wiem czy nalezy"+childNode.getDirection());
							childNode.printArray();
							if (!searchedNode.contains(childNode)){
								System.out.println("Sprawdzilem-- Nie nalezy"+ childNode.getDirection());
								//searchedNode.add(childNode);
								stackNode.push(childNode);}

						}
					}
			}
			//Thread.sleep(100);
		}
		while (!stackNode.isEmpty());
		if (rozwiazanie == false) {
			System.out.println("nie znalazlem rozwiazania");
		}

	}

	public void BFS(Node firstNode, char[] nodeOrder) throws InterruptedException {
		int depth = 0;
		List<Node> childrenList = new ArrayList<Node>();
		List<Node> searchedNode = new ArrayList<Node>();
		Queue<Node> queueNode = new LinkedList<>();
		queueNode.add(firstNode);
		Node resultNode = new Node(resultArray);
		boolean rozwiazanie = false;
		do {
			Node actualNode = queueNode.remove();
			if (!searchedNode.contains(actualNode))
				searchedNode.add(actualNode);
			if (actualNode.equals(resultNode)) {
				//saveFile("plik.txt", actualNode.getArray());  //Sprawdzenie czy aktualny wezel‚ nie jest rozwiazaniem
				rozwiazanie = true;
				System.out.println("znalazlem rozwiazanie po przeszukaniu " + searchedNode.size() + "wezlow");
				break;
			} else {
				childrenList = actualNode.generateChildren();
				for (int i = 0; i < nodeOrder.length; i++)
					for (Node childNode : childrenList) {
						if (childNode.getDirection() == nodeOrder[i]) {
							System.out.println("stworzylem wezel ale nie wiem czy nalezy"+childNode.getDirection());
							childNode.printArray();
							if (!searchedNode.contains(childNode)){
								System.out.println("Sprawdzilem-- Nie nalezy"+ childNode.getDirection());
								//searchedNode.add(childNode);
								queueNode.add(childNode);}
						}
					}
			}
			//Thread.sleep(100);
		}
		while (!queueNode.isEmpty());
		if (rozwiazanie == false) {
			System.out.println("nie znalazlem rozwiazania");
		}
	}

	public void BFSWithHeuristic(Node firstNode, IHeuristic heuristic) throws InterruptedException {
		int depth = 0;
		List<Node> childrenList = new ArrayList<Node>();
		List<Node> searchedNode = new ArrayList<Node>();
		Queue<Node> queueNode = new LinkedList<>();
		queueNode.add(firstNode);
		Node resultNode = new Node(resultArray);
		boolean rozwiazanie = false;
		do {
			Node actualNode = queueNode.remove();
			if (!searchedNode.contains(actualNode))
				searchedNode.add(actualNode);
			if (actualNode.equals(resultNode)) {
				//saveFile("plik.txt", actualNode.getArray());  //Sprawdzenie czy aktualny wezel‚ nie jest rozwiazaniem
				rozwiazanie = true;
				System.out.println("znalazlem rozwiazanie po przeszukaniu " + searchedNode.size() + "wezlow");
				break;
			} else {

				childrenList = actualNode.generateChildren();
				List<Integer> nodeOrder= new ArrayList<Integer>();
				int i=0;				
				for (Node childNode : childrenList){
					childNode.setOrder(heuristic.calculateOrders((childNode.getArray())));
					nodeOrder.add(heuristic.calculateOrders(childNode.getArray()));
					System.out.println("przydzielono mi kolejnosc:"+nodeOrder.get(i));
					i+=i;
				}
				
				Collections.sort(nodeOrder);
				System.out.println("kolejnosc po sortowaniu");
			    for (int x: nodeOrder)
				System.out.println(x);
				
				for (int j = 0; j < nodeOrder.size(); j++){
					for (Node childNode : childrenList) {
						if (childNode.getOrder() == nodeOrder.get(j)) {
							System.out.println("stworzylem wezel ale nie wiem czy nalezy"+childNode.getOrder());
							childNode.printArray();
							if (!searchedNode.contains(childNode)){
								System.out.println("Sprawdzilem-- Nie nalezy"+ childNode.getOrder());
								//searchedNode.add(childNode);
								queueNode.add(childNode);}
						}
					}
				}
			}
			//Thread.sleep(100);
		}
		while (!queueNode.isEmpty());
		if (rozwiazanie == false) {
			System.out.println("nie znalazlem rozwiazania");
		}
	}
}
