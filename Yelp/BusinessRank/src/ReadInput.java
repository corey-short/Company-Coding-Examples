import java.io.*;

/**
 * A class to read an input file.
 * Created by Corey Short on 8/1/15.
 */
public class ReadInput {

    public ReadInput(String input) {

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {

            String currLine;

            while ((currLine = br.readLine()) != null) {
                // Merge sort

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
