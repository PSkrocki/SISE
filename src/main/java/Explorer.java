import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**
 * Created by kamil on 5/1/16.
 */
public class Explorer {
	public static int[][] ResultArray = new int[][]{
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
		Node resultNode = new Node(ResultArray);
		boolean rozwiazanie = false;
		do {
			Node actualNode = stackNode.pop();
			if (!searchedNode.contains(actualNode))
				searchedNode.add(actualNode);
			if (actualNode.equals(resultNode)) {
				//saveFile("plik.txt", actualNode.getArray());  //Sprawdzenie czy aktualny węzeł nie jest rozwiązaniem
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
								searchedNode.add(childNode);
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
}
