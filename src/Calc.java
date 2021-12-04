import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;

public class Calc {
    static int loops = 0;
    static TreeMap<Integer, Integer> highScores= new TreeMap<>();
    static ArrayList<Integer> primes;
    static int upperBound;


    public static void algorithm(int bound1, int bound2) {
        primes = getPrimes(bound2);
        int tenPercent = bound2/10;
        highScores.put(2, 1);
        for (int i = bound1; i <= bound2; i++) {
            loops = 0;
            addPrimefactors(i);
            if(i % tenPercent == 0) {
                System.out.println(i*10/tenPercent+"% done");
            }
            if (loops > highScores.get(highScores.lastKey())) {
                highScores.put(i, loops);
            }
        }

    }

    public static ArrayList<Integer> addPrimefactors(int num) {
        ArrayList<Integer> result = factorize(num);
        if (result.size() > 1) {
            addPrimefactors(addNums(result));
        }

        loops++;
        return result;
    }

    public static ArrayList<Integer> factorize(int num) {
        ArrayList<Integer> result = new ArrayList<>();
        while (num % 2 == 0) {
            num = num / 2;
            result.add(2);
        }

        for (int i = 0; primes.get(i) <= num; i++) {
            int primeFactor = primes.get(i);

            if (primeFactor > Math.sqrt(Double.valueOf(num))) {
                result.add(num);
                break;
            }

            while (num % primeFactor == 0) {
                num = num / primeFactor;
                result.add(primeFactor);
            }
        }
        return result;
    }


    public static ArrayList<Integer> getPrimes(int amount) {
        ArrayList<Integer> primes = new ArrayList<>();
        int tenPercent = amount/10;
        primes.add(2);
        for (int i = 1; i <= amount; i++) {
            boolean divisible = false;
            int num = 2 * i + 1;
            for (int j = 0; j < primes.size(); j++) {
                if (num % primes.get(j) == 0) {
                    divisible = true;
                }
                Double sqrtOfNum = Math.sqrt(Double.valueOf(num));
                if(primes.get(j) > sqrtOfNum) {
                    break;
                }
            }
            if (divisible == false) {
                primes.add(num);
            }
            if(i % tenPercent == 0) {
                System.out.println(i*10/tenPercent+"% done");
            }
        }
        return primes;
    }



    public static int addNums(ArrayList<Integer> factors) {
        int result = 0;
        for (int i = 0; i < factors.size(); i++) {
            result += factors.get(i);
        }

        return result;
    }

    public static void createFile(String fileName) {
        try {
            File primes = new File(fileName);
            if (primes.createNewFile()) {
                System.out.println("File created: " + primes.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static Double calcSineIntegral(Double steps, Double a, Double b) {
        Double result = 0.0;
        Double stepSize = (b - a) / steps;


        for (int i = 0; i <= steps; i++) {
            result += (a + stepSize * i) * stepSize;
        }

        return result;
    }


    public static void writeTreeMapToFile(String fileName, TreeMap<Integer, Integer> tree) {

        try {
            clearTheFile(fileName);
            FileWriter myWriter = new FileWriter(fileName);
            int[] list = new int[tree.size()];
            int k = 0;
            for (Integer i: tree.keySet()) {
                list[k++] = i;
            }
            for (int i = 0; i < tree.size(); i++) {
                String output = list[i]+", "+tree.get(list[i]);
                myWriter.write(output+"\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static ArrayList<Integer> readArrayFromFile(String fileName) {
        try {
            Scanner s = new Scanner(new File(fileName));
            ArrayList<Integer> list = new ArrayList<Integer>();

            while (s.hasNext()){
                list.add(Integer.parseInt(s.next()));

            }
            s.close();
            return list;
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
        return null;
    }



    public static void clearTheFile(String fileName) {
        try {
            FileWriter fwOb = new FileWriter(fileName, false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static int getLoops() {
        return loops;
    }

    public static void setLoops(int loops) {
        Calc.loops = loops;
    }

    public static TreeMap<Integer, Integer> getHighScores() {
        return highScores;
    }

    public static void setHighScores(TreeMap<Integer, Integer> highScores) {
        Calc.highScores = highScores;
    }

    public static ArrayList<Integer> getPrimes() {
        return primes;
    }

    public static void setPrimes(ArrayList<Integer> primes) {
        Calc.primes = primes;
    }

    public static int getUpperBound() {
        return upperBound;
    }

    public static void setUpperBound(int upperBound) {
        Calc.upperBound = upperBound;
    }
}




