/**
 * Main method to sort businesses by their id and rank.
 * Created by Corey Short on 8/1/15.
 */
public class Main {

    private MaxHeap maxHeap;

    public static void main(String[] args) {
        /* If no input file specified then tell the user and exit the program */
        if (args.length == 0) {
            System.out.println("Proper usage is: Supply an input file.");
            System.exit(0);
        }

        final BusinessRank businessRank = new BusinessRank();

        ReadInput input = new ReadInput("~/Desktop/repo/Company-Coding-Examples/Yelp/BusinesssRank/input1.txt");

        businessRank.sortBusinesses(input);


    }
}
