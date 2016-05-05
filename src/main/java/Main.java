import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        String sourcePath;
        String exploringMethod;
        char[] exploringOrder = null;
        String heuristic = null;

        if (args.length == 3) {
            sourcePath = args[0];
            exploringMethod = args[1];
            if (args[1].equals("dfsh") || args[1].equals("bfsh")){
                heuristic = args[2];
            }
            else {
                exploringOrder = args[2].toCharArray();
            }

            Explorer explorer = new Explorer();
            switch (exploringMethod) {
                case "dfs":
                    explorer.DFS(new Node(Serializer.loadFile(sourcePath)), exploringOrder);
                    break;
                case "idfs":
                    explorer.IDFS(new Node(Serializer.loadFile(sourcePath)), exploringOrder);
                    break;
                case "bfs":
                    explorer.BFS(new Node(Serializer.loadFile(sourcePath)), exploringOrder);
                    break;
                case "dfsh":
//                    TODO
//                    if(heuristic.equals("M"))
//                        explorer.DFSWithHeuristic(new Node(Serializer.loadFile(sourcePath)), new Manhattan());
//                    if(heuristic.equals("G"))
//                        explorer.DFSWithHeuristic(new Node(Serializer.loadFile(sourcePath)), new Manhattan());
                    break;
                case "bfsh":
                    if(heuristic.equals("M"))
                        explorer.BFSWithHeuristic(new Node(Serializer.loadFile(sourcePath)), new Manhattan());
//                    TODO
//                    if(heuristic.equals("G"))
//                        explorer.BFSWithHeuristic(new Node(Serializer.loadFile(sourcePath)), new Manhattan());
                    break;
                case "exit":
                    break;
            }
        } else {
            System.out.println("Podaj odpowiednie parametry: \n" +
                    "1. Sciezke wejscia np input.txt \n" +
                    "2. Strategie poszukiwana rozwiazania: dfs/idfs/bfs/dfsh/bfsh\n" +
                    "3a. Kolejnosc wyszukiwana np GDPL lub R w przypadku bez heurystyki\n" +
                    "3b. Heurystyka w przypadku algorytmu wykorzystujacego heurystyke: M");
        }
    }
}
