import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		String sourcePath = "input.txt";
		String exploringMethod = "dfs";
		char[] exploringOrder = {'L','P','G','D'};
		Explorer explorer = new Explorer();

		switch (exploringMethod) {
		case "idfs":
			explorer.IDFS(new Node(Serializer.loadFile(sourcePath)), exploringOrder);
			break;
		case "dfs":
			explorer.DFS(new Node(Serializer.loadFile(sourcePath)), exploringOrder);
			break;
		case "bfs":
			explorer.BFS(new Node(Serializer.loadFile(sourcePath)), exploringOrder);
			break;
		case "bfsh":
			explorer.BFSWithHeuristic(new Node(Serializer.loadFile(sourcePath)),new Manhattan());
			break;
		}
	}
}
