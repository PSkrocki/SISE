import java.util.ArrayList;
import java.util.Arrays;



public class Node {

    private int[][] array;
    private int arraysLengthY;
    private int arraysLengthX;
    private int emptyElementsX;
    private int emptyElementsY;
    private int order;
    private char direction;
    private boolean hasParent;
    private int parentsHashCode;
    private int hashCode;

    public Node(int[][] array) {
        this.array = array;
        this.arraysLengthY = array.length;
        this.arraysLengthX = array[0].length;
        this.hasParent = false;
        this.hashCode = hashCode();
        getEmptyElement();
        printArray();
        System.out.println("wykreowalem z pierwszego konstruktora");
        
    }

    public Node(int[][] array, char direction, int parentsHashCode) {
        this.array = array;
        this.arraysLengthY = array.length;
        this.arraysLengthX = array[0].length;
        this.direction = direction;
        this.hasParent = true;
        this.hashCode = hashCode();
        this.parentsHashCode = parentsHashCode;
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
            nodesChildren.add(new Node(moveElement('G'), 'G', hashCode));
        }
        if (canMoveDown()) {
            nodesChildren.add(new Node(moveElement('D'), 'D', hashCode));
        }
        if (canMoveLeft()) {
            nodesChildren.add(new Node(moveElement('L'), 'L', hashCode));
        }
        if (canMoveRight()) {
            nodesChildren.add(new Node(moveElement('P'), 'P', hashCode));
        }
        return nodesChildren;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(array);
        return result;
    }

    public boolean canMoveUp() {
        return emptyElementsY != 0;
    }

    public boolean canMoveDown() {
        return emptyElementsY != arraysLengthY - 1;

    }

    public boolean canMoveLeft() {
        return emptyElementsX != 0;

    }

    public boolean canMoveRight() {
        return emptyElementsX != arraysLengthX - 1;

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
        return Arrays.deepEquals(array, other.array);

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

    public boolean isHasParent() {
        return hasParent;
    }

    public void setHasParent(boolean hasParent) {
        this.hasParent = hasParent;
    }

    public int getParentsHashCode() {
        return parentsHashCode;
    }

    public void setParentsHashCode(int parentsHashCode) {
        this.parentsHashCode = parentsHashCode;
    }

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }
}
