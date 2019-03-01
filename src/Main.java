import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println(calculateNeededPaperFromTxtFile(Paths.get("./test_input.txt")) + " dm2");

    }

    private static int calculateNeededPaperSurface(int a, int b, int c) {
        int firstWall = a * b;
        int secondWall = a * c;
        int thirdWall = b * c;

        return 2 * firstWall + 2 * secondWall + 2 * thirdWall + Math.min(firstWall, Math.min(secondWall, thirdWall));
    }

    private static int calculateNeededPaperFromTxtFile(Path path) {
        List<Integer[]> dimensionList = new ArrayList<>();
        int sum = 0;
        String temp = "";

        try (BufferedReader reader = Files.newBufferedReader(path,
                Charset.forName("UTF-8"))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                Integer[] dimensions = new Integer[3];
                int i = 0;
                int position = 0;
                for (Character ch : currentLine.toCharArray()) {
                    position++;
                    if (Character.isDigit(ch)) {
                        temp += ch;
                    } else {
                        dimensions[i] = Integer.parseInt(temp);
                        temp = "";
                        i++;
                    }
                    if (i == 2 && position == currentLine.length()) {
                        dimensions[i] = Integer.parseInt(temp);
                    }
                }
                dimensionList.add(dimensions);
                temp = "";
            }
        } catch (IOException e) {
            e.getMessage();
        }

        for (Integer[] integers : dimensionList) {
            System.out.println(Arrays.toString(integers));
            sum += calculateNeededPaperSurface(integers[0], integers[1], integers[2]);
        }

        return sum;
    }
}
