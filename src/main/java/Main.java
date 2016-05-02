import java.io.FileNotFoundException;

/**
 * Created by kamil on 5/1/16.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String sourcePath = "input.txt";
        String exploringMethod = "dfs";
        char[] exploringOrder = {'L','P','G','D'};
        Explorer explorer = new Explorer();

        switch (exploringMethod) {
            case "dfs":
                explorer.DFS(new Node(Serializer.loadFile(sourcePath)), exploringOrder);
                break;
            case "bfs":

                break;
        }
    }
}
