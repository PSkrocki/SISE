import java.io.File;
import java.io.FileNotFoundException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by kamil on 5/1/16.
 */
public class Serializer {

    public static int[][] loadFile(String path) throws FileNotFoundException {
        int[][] startingArray;
        int Xsize;
        int Ysize;

        File file = new File(path);
        Scanner inFile = new Scanner(file);
        String valuesLine;
        String[] values;

        valuesLine = inFile.nextLine();
        values = valuesLine.split(" ");

        Xsize = Integer.parseInt(values[0]);
        System.out.println(Xsize);

        Ysize = Integer.parseInt(values[1]);
        System.out.println(Ysize);

        startingArray = new int[Xsize][Ysize];
        int i = 0;

        while (inFile.hasNext()) {

            valuesLine = inFile.nextLine();
            System.out.println(valuesLine);
            values = valuesLine.split(" ");
            for (int j = 0; j < Xsize; j++)
                startingArray[i][j] = Integer.parseInt(values[j]);
            if (i == Ysize) {
                break;
            }
            i++;
        }
        inFile.close();
        return startingArray;
    }

    public static void saveFile(String pathToResult) throws FileNotFoundException {
        String outputPath = "output.txt";
        try (PrintWriter out = new PrintWriter(outputPath)) {
            out.println(pathToResult);
        }
    }
}
