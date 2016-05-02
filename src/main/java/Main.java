import java.io.FileNotFoundException;


<<<<<<< HEAD
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

=======
public class Main {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		String sourcePath = "input.txt";
		String exploringMethod = "bfsh";
		char[] exploringOrder = {'G','L','P','D'};
		Explorer explorer = new Explorer();

>>>>>>> fc3977e2dd39ab97645846c5bcfbf3c42a1b1800
		switch (exploringMethod) {
		case "dfs":
			explorer.DFS(new Node(Serializer.loadFile(sourcePath)), exploringOrder);
			break;
		case "bfs":
			explorer.BFS(new Node(Serializer.loadFile(sourcePath)), exploringOrder);
			break;
<<<<<<< HEAD
=======
		case "bfsh":
			explorer.BFSWithHeuristic(new Node(Serializer.loadFile(sourcePath)),new Manhattan());
			break;
>>>>>>> fc3977e2dd39ab97645846c5bcfbf3c42a1b1800
		}
	}
}
