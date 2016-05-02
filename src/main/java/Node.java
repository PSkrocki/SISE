import java.util.ArrayList;
import java.util.Arrays;

import lombok.Data;



public @Data class Node {

    private int[][] array;
    private int arraysLengthY;
    private int arraysLengthX;
    private int emptyElementsX;
    private int emptyElementsY;
    private int order;
    private char direction;

    public Node(int[][] array) {
        this.array = array;
        this.arraysLengthY = array.length;
        this.arraysLengthX = array[0].length;
        getEmptyElement();
        printArray();
        System.out.println("wykreowa�em z pierwszego konstruktora");
        
    }

    public Node(int[][] array, char direction) {
        this.array = array;
        this.arraysLengthY = array.length;
        this.arraysLengthX = array[0].length;
        this.direction = direction;
        getEmptyElement();
        printArray();
        System.out.println();
    }
    
    public void printArray(){
        for (int i = 0; i < arraysLengthY; i++) {
            for (int j = 0; j < arraysLengthX; j++) {
                System.out.print(array[i][j]);
                
                System.out.printf(" ");
                }
            System.out.println();
            }
    }

    public void getEmptyElement() {
        for (int i = 0; i < arraysLengthY; i++) {
            for (int j = 0; j < arraysLengthX; j++) {
                if (array[i][j] == 0) {
                    emptyElementsX = j;
                    emptyElementsY = i;
                    break;
                }
            }
        }
    }

    public ArrayList<Node> generateChildren() {
        ArrayList<Node> nodesChildren = new ArrayList<Node>();
        if (canMoveUp()) {
            nodesChildren.add(new Node(moveElement('G'), 'G'));
        }
        if (canMoveDown()) {
            nodesChildren.add(new Node(moveElement('D'), 'D'));
        }
        if (canMoveLeft()) {
            nodesChildren.add(new Node(moveElement('L'), 'L'));
        }
        if (canMoveRight()) {
            nodesChildren.add(new Node(moveElement('P'), 'P'));
        }
        return nodesChildren;
    }

    public boolean canMoveUp() {
        if (emptyElementsY == 0)
            return false;
        return true;
    }

    public boolean canMoveDown() {
        if (emptyElementsY == arraysLengthY - 1)
            return false;
        return true;

    }

    public boolean canMoveLeft() {
        if (emptyElementsX == 0)
            return false;
        return true;

    }

    public boolean canMoveRight() {
        if (emptyElementsX == arraysLengthX - 1)
            return false;
        return true;

    }

    public int[][] moveElement(char direction) {
    	int[][] childsArray = new int[arraysLengthY][arraysLengthX];

        for (int i = 0; i < arraysLengthX; i++) {
            System.arraycopy(array[i], 0, childsArray[i], 0, arraysLengthY);
        }

        int temp = childsArray[emptyElementsY][emptyElementsX];
        switch (direction) {
            case 'G':
                childsArray[emptyElementsY][emptyElementsX] = childsArray[emptyElementsY - 1][emptyElementsX];
                childsArray[emptyElementsY - 1][emptyElementsX] = temp;
                break;
            case 'D':
                childsArray[emptyElementsY][emptyElementsX] = childsArray[emptyElementsY + 1][emptyElementsX];
                childsArray[emptyElementsY + 1][emptyElementsX] = temp;
                break;
            case 'P':
                childsArray[emptyElementsY][emptyElementsX] = childsArray[emptyElementsY][emptyElementsX + 1];
                childsArray[emptyElementsY][emptyElementsX + 1] = temp;
                break;
            case 'L':
                childsArray[emptyElementsY][emptyElementsX] = childsArray[emptyElementsY][emptyElementsX - 1];
                childsArray[emptyElementsY][emptyElementsX - 1] = temp;
                break;
            default:
                break;
        }
        return childsArray;
    }
    
    public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Node other = (Node) obj;
		if (!Arrays.deepEquals(array, other.array))
			return false;
		
		return true;
	}


    public int[][] getArray() {
        return array;
    }

    public void setArray(int[][] array) {
        this.array = array;
    }

    public int getArraysLengthX() {
        return arraysLengthX;
    }

    public void setArraysLengthX(int arraysLengthX) {
        this.arraysLengthX = arraysLengthX;
    }

    public int getArraysLengthY() {
        return arraysLengthY;
    }

    public void setArraysLengthY(int arraysLengthY) {
        this.arraysLengthY = arraysLengthY;
    }

    public int getEmptyElementsY() {
        return emptyElementsY;
    }

    public void setEmptyElementsY(int emptyElementsY) {
        this.emptyElementsY = emptyElementsY;
    }

    public int getEmptyElementsX() {
        return emptyElementsX;
    }

    public void setEmptyElementsX(int emptyElementsX) {
        this.emptyElementsX = emptyElementsX;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
