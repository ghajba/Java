import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ParallelReader {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("At least two files have to be provided");
            System.exit(-1);
        }
        String file1 = args[0];
        String file2 = args[1];
        wrongSolution(file1, file2);
        workingSolution(file1, file2);

    }

    private static void wrongSolution(String file1, String file2) throws IOException {
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        try {
            br1 = new BufferedReader(new FileReader(file1));
            br2 = new BufferedReader(new FileReader(file2));
            String line1 = "";
            String line2 = "";
            while ((line1 = br1.readLine()) != null || (line2 = br2.readLine()) != null) {
                printLine(line1, line2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br1 != null) {
                br1.close();
            }
            if (br2 != null) {
                br2.close();
            }
        }
    }

    private static void workingSolution(String file1, String file2) throws IOException {
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        try {
            br1 = new BufferedReader(new FileReader(file1));
            br2 = new BufferedReader(new FileReader(file2));
            String line1;
            String line2;
            while ((line1 = br1.readLine()) != null | (line2 = br2.readLine()) != null) {
                printLine(line1, line2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br1 != null) {
                br1.close();
            }
            if (br2 != null) {
                br2.close();
            }
        }
    }
    
    private static void printLine(String line1, String line2) {
        System.out.println((line1 == null ? "" : line1) + " | " + (line2 == null ? "" : line2));
    }
}