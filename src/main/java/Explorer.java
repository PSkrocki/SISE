import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Explorer {
    int depth = 0;
    List<Node> childrenList;
    List<Node> searchedNode;
    boolean result;
    Node resultNodeGlobal;
    private String resultPath;

    public static int[][] resultArray = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0},
    };

    public static char[] nodesDirections = new char[]{'G', 'D', 'L', 'P'};

    public void DFS(Node actualNode, char[] nodeOrder) throws InterruptedException {
        childrenList = new ArrayList<Node>();
        searchedNode = new ArrayList<Node>();
        result = false;
        DFSRun(actualNode, nodeOrder);
        if (result)
            System.out.println("znalazlem rozwiazanie po oprzeszukaniu " + searchedNode.size() + "wezlow");
        if (resultNodeGlobal != null)
            resultNodeGlobal.printArray();
    }

    public void DFSRun(Node actualNode, char[] nodeOrder) throws InterruptedException {

        actualNode.printArray();
        Node resultNode = new Node(resultArray);
        boolean randomShuffling = false;
        if (nodeOrder[0] == 'R') {
            randomShuffling = true;
        }
        if (!searchedNode.contains(actualNode))
            searchedNode.add(actualNode);
        if (actualNode.equals(resultNode)) {
            result = true;
            resultNodeGlobal = actualNode;
            System.out.println("pokaz mi wynik: ");
            actualNode.printArray();
            System.out.println("^ to jest wynik");
            Thread.sleep(10000);
            return;
        } else {
            childrenList = actualNode.generateChildren();
            depth++;
            System.out.println(depth);
            if (randomShuffling) {
                nodeOrder = shuffleNodeOrder();
            }
            for (int i = nodeOrder.length - 1; i >= 0; i--) {
                for (Node childNode : childrenList) {
                    if (childNode.getDirection() == nodeOrder[i]) {
                        if (!searchedNode.contains(childNode)) {
                            if (depth < 3000)
                                DFSRun(childNode, nodeOrder);
                            Thread.sleep(100);
                        }
                    }
                }
            }
        }
    }

    public void IDFS(Node firstNode, char[] nodeOrder) throws InterruptedException {
        int depth = 0;
        List<Node> childrenList = new ArrayList<Node>();
        List<Node> searchedNode = new ArrayList<Node>();
        Stack<Node> stackNode = new Stack<Node>();
        stackNode.push(firstNode);
        Node resultNode = new Node(resultArray);
        boolean rozwiazanie = false;
        boolean randomShuffling = false;
        if (nodeOrder[0] == 'R') {
            randomShuffling = true;
        }
        do {
            Node actualNode = stackNode.pop();
            if (!searchedNode.contains(actualNode))
                searchedNode.add(actualNode);
            if (actualNode.equals(resultNode)) {
                rozwiazanie = true;
                System.out.println("znalazlem rozwiazanie po oprzeszukaniu " + searchedNode.size() + "wezlow");
                break;
            } else {
                childrenList = actualNode.generateChildren();
                if (randomShuffling) {
                    nodeOrder = shuffleNodeOrder();
                }
                for (int i = nodeOrder.length - 1; i >= 0; i--) {
                    for (Node childNode : childrenList) {
                        if (childNode.getDirection() == nodeOrder[i]) {
                            System.out.println("stworzylem wezel ale nie wiem czy nalezy" + childNode.getDirection());
                            childNode.printArray();
                            if (!searchedNode.contains(childNode)) {
                                stackNode.push(childNode);
                            }
                        }
                    }
                }
            }
            Thread.sleep(100);
        }
        while (!stackNode.isEmpty());
        if (!rozwiazanie) {
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
        boolean hasResults = false;
        boolean randomShuffling = false;
        if (nodeOrder[0] == 'R') {
            randomShuffling = true;
        }

        do {
            Node actualNode = queueNode.remove();
            if (!searchedNode.contains(actualNode))
                searchedNode.add(actualNode);
            if (actualNode.equals(resultNode)) {
                hasResults = true;
                System.out.println("znalazlem hasResults po przeszukaniu " + searchedNode.size() + "wezlow");
                while (actualNode.isHasParent()) {
                    resultPath += actualNode.getDirection();
                    for (Node node : searchedNode) {
                        if (actualNode.getParentsHashCode() == node.getHashCode()) {
                            actualNode = node;
                            break;
                        }
                    }
                }
                System.out.println(resultPath);
                System.out.println("Droga jaka pokonalem aby zwyciezyc: " + convertResultPath(resultPath));
                break;
            } else {
                childrenList = actualNode.generateChildren();
                if (randomShuffling) {
                    nodeOrder = shuffleNodeOrder();
                }
                for (int i = 0; i < nodeOrder.length; i++)
                    for (Node childNode : childrenList) {
                        if (childNode.getDirection() == nodeOrder[i]) {
                            System.out.println("stworzylem wezel ale nie wiem czy nalezy" + childNode.getDirection());
                            childNode.printArray();
                            if (!searchedNode.contains(childNode)) {
                                System.out.println("Sprawdzilem-- Nie nalezy" + childNode.getDirection());
                                queueNode.add(childNode);
                            }
                        }
                    }
            }
            //Thread.sleep(100);
        }
        while (!queueNode.isEmpty());
        if (!hasResults) {
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
        boolean hasResult = false;

        do {
            Node actualNode = queueNode.remove();
            if (!searchedNode.contains(actualNode))
                searchedNode.add(actualNode);
            if (actualNode.equals(resultNode)) {
                hasResult = true;
                System.out.println("znalazlem hasResult po przeszukaniu " + searchedNode.size() + "wezlow");
                break;
            } else {

                childrenList = actualNode.generateChildren();
                int[] nodeOrder = new int[childrenList.size()];
                int i = 0;
                for (Node childNode : childrenList) {
                    childNode.setOrder(heuristic.calculateOrders((childNode.getArray())));
                    nodeOrder[i] = heuristic.calculateOrders(childNode.getArray());
                    System.out.println("przydzielono mi kolejnosc:" + nodeOrder[i]);
                    i += i;
                }
                Arrays.sort(nodeOrder);
                for (int j = 0; j < nodeOrder.length; j++) {
                    for (Node childNode : childrenList) {
                        if (childNode.getOrder() == nodeOrder[j]) {
                            System.out.println("stworzylem wezel ale nie wiem czy nalezy" + childNode.getOrder());
                            childNode.printArray();
                            if (!searchedNode.contains(childNode)) {
                                System.out.println("Sprawdzilem-- Nie nalezy" + childNode.getOrder());
                                queueNode.add(childNode);
                            }
                        }
                    }
                }
            }
            //Thread.sleep(100);
        }
        while (!queueNode.isEmpty());
        if (!hasResult) {
            System.out.println("nie znalazlem rozwiazania");
        }
    }

    public char[] shuffleNodeOrder() {
        char[] nodeOrder = nodesDirections;
        Random rnd = ThreadLocalRandom.current();
        for (int i = nodeOrder.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            char a = nodeOrder[index];
            nodeOrder[index] = nodeOrder[i];
            nodeOrder[i] = a;
        }
        System.out.print("Teraz ustawione wg:");
        for (int i = 0; i < nodeOrder.length; i++) {
            System.out.print(nodeOrder[i] + " ");
        }
        System.out.println();
        return nodeOrder;
    }

    public String convertResultPath(String resultPath) {
        String newResultPath = null;

        for (int i = resultPath.length() - 1; i >= 0; i--) {
            if (resultPath.charAt(i) == 'l'){
                break;
            }
            else if(newResultPath == null){
                newResultPath = Character.toString(resultPath.charAt(i));
            } else
                newResultPath += resultPath.charAt(i);
        }
        return newResultPath;
    }
}
