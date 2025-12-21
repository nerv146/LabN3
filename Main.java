import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        // Test 1
            ElementsOfStatistic list = new ElementsOfStatistic();
            list.addNumber(5);
            list.addNumber(3);
            list.addNumber(7);
            list.addNumber(3);
            list.addNumber(2);
            list.printAllCharacteristics();

        // Test 2
            ElementsOfStatistic list1 = new ElementsOfStatistic();
            list.addNumber(5.5);
            list.addNumber(4.4);
            list.addNumber(3.3);
            list.addNumber(2.2);
            list.addNumber(1.1);
            list1.printAllCharacteristics();



        // Test 3
            ElementsOfStatistic list2 = new ElementsOfStatistic();
            list.addNumber(0);
            list.addNumber(0);
            list.addNumber(1);
            list.addNumber(31);
            list.addNumber(1);
            list2.printAllCharacteristics();
      


    }
}
