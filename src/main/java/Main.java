import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		String sourcePath = "input.txt";
		String exploringMethod = "bfsh";
		char[] exploringOrder = {'L','P','D','G'};
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
			explorer.BFSH(new Node(Serializer.loadFile(sourcePath)),new Manhattan());
			break;
		case "dfsh":
			explorer.DFSH(new Node(Serializer.loadFile(sourcePath)),new Manhattan());
			break;
		case "bfsh2":
			explorer.BFSH(new Node(Serializer.loadFile(sourcePath)),new GoodRowAndColumn());
			break;
		case "dfsh2":
			explorer.DFSH(new Node(Serializer.loadFile(sourcePath)),new GoodRowAndColumn());
			break;
		}
	}
}