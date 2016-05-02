import java.io.FileNotFoundException;

/**
 * Created by kamil on 5/1/16.
 */
public class Main {

<<<<<<< HEAD
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String sourcePath = "input.txt";
        String exploringMethod = "dfs";
        char[] exploringOrder = {'L','P','G','D'};
        Explorer explorer = new Explorer();
=======
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		String sourcePath = "input.txt";
		String exploringMethod = "bfs";
		char[] exploringOrder = {'G','L','P','D'};
		Explorer explorer = new Explorer();
>>>>>>> c4bd5e415855638594f0c29525a731608b254ed4

		switch (exploringMethod) {
		case "dfs":
			explorer.DFS(new Node(Serializer.loadFile(sourcePath)), exploringOrder);
			break;
		case "bfs":
			explorer.BFS(new Node(Serializer.loadFile(sourcePath)), exploringOrder);
			break;
		}
	}
}
