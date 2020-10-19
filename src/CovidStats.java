import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CovidStats {
    public static void main(String[] args) {
        int [] covid19 = null;
        try {
            //read the data from the file
            Path path = Paths.get("data.txt");
            Stream<String> lines = Files.lines(path);

            //convert to array of int
            covid19 = lines.mapToInt(Integer::parseInt).toArray();
        } catch(IOException e) {
            e.printStackTrace();
        }

        //all code to work with array goes here
        int[] dailyCaseCount = new int[covid19.length];
        int previousDayValue = 0;
        for (int i = 0; i < covid19.length; i = i + 1) {
            int dailyIncrease = covid19[i] - previousDayValue;
            dailyCaseCount[i] = dailyIncrease;
            previousDayValue = covid19[i];
        }
        for (int n: dailyCaseCount) {
            System.out.println(n);
        }

        //find the average
        //int sum = 0;
        //for (int i = 0; i < dailyCaseCount.length; i = i + 1) {
        //    sum = sum + dailyCaseCount[i];
        //}

        //enhanced for loop
        int sum = 0;
        for (int n: dailyCaseCount) {
            sum = sum + n;
        }

        //calculate the average as a double value, not an int
        double average = (double)sum / (double)dailyCaseCount.length;
        System.out.printf("The daily average is %.2f\n", average);

        //How many days does it take to double the number of cases?
        //divide total by 2 and look back in the data to when that number occurred
        //need to note the index of the number before that point
        //subtract the index of the halfCount position from the totalCount index
        int totalCount = covid19[covid19.length - 1];
        int halfCount= totalCount / 2;

        int halfCountIndex = -1;
        int i = 0;
        while (i < covid19.length && halfCountIndex == -1) {
            if (covid19[i] > halfCount) {
                halfCountIndex = i - 1;
            }
            i = i + 1;
        }

        int numDaysSinceDouble = (covid19.length - 1) - halfCountIndex;
        System.out.printf("The number of days since the number of cases doubled is: %d\n", numDaysSinceDouble);

        //Largest daily increase and smallest daily increase
        int largest = Integer.MIN_VALUE;
        int largestIndex = -1;
        for (int j = 0; j < dailyCaseCount.length; j = j +1) {
            if (dailyCaseCount[j] > largest) {
                largest = dailyCaseCount[j];
                largestIndex = j;
            }
        }
        System.out.printf("The largest daily increase of %d happened on %d\n", largest, largestIndex);

        //Smallest daily increase
        int smallest = Integer.MAX_VALUE;
        int smallestIndex = -1;
        for (int j = 0; j < dailyCaseCount.length; j = j + 1) {
            if (dailyCaseCount[j] < smallest) {
                smallest = dailyCaseCount[j];
                smallestIndex = j;
            }
        }
        System.out.printf("The smallest daily increase of %d happened on %d\n", smallest, smallestIndex);

        //Weekly average increase
        int weekNum = 1;
        int sevenDaySum = 0;
        for (int k = 0; k < dailyCaseCount.length; k = k + 1) {
            if (k % 7 == 0) {
                if (k != 0) {
                    System.out.printf("Average daily increase for week %d = %.2f\n", weekNum, ((double)sevenDaySum/7.0));
                    weekNum = weekNum + 1;
                }
                sevenDaySum = 0;
            }
            sevenDaySum = sevenDaySum + dailyCaseCount[k];
        }
    }
}
