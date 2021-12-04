import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("Enter the upper bound: ");
        Scanner s = new Scanner(System.in);
        int upperBound = s.nextInt();
        Calc.algorithm(5, upperBound);
        Calc.writeTreeMapToFile("primes.txt", Calc.getHighScores());
        long time = System.nanoTime();
        System.out.println(System.nanoTime()-time);
        System.out.println(Calc.getHighScores());

        //System.out.println(readArrayFromFile("primes.txt"));
    }
}
