import java.io.*;
import java.util.*;

public class aocDay1 {

    /*
    Calculates the sum of the calories by current Elf and updates the largest
    calories sum
     */
    public static int getMostCalories(int largest, List<Integer> current){
        int sum = 0;
        for (int cal : current){
            sum += cal;
        }
        return largest < sum ? sum : largest;
    }

    /*
    For part 2: Places the new top 3 calories into correct position. top3 is descending
    where index 0 is largest
     */
    public static void getNewTop3(int[] top3, List<Integer> current){
        int large3Old = top3[2];
        top3[2] = getMostCalories(top3[2], current);
        // found a new top 3 calories
        if (large3Old != top3[2]){
            int newTop = top3[2];
            if (newTop >= top3[0]){
                top3[2] = top3[1];
                top3[1] = top3[0];
                top3[0] = newTop;
            }
            else if (newTop >= top3[1]){
                top3[2] = top3[1];
                top3[1] = newTop;
            }
        }
    }

    /*
    Process input data which has calories per line and empty line separates
    each Elf. Perform algorithm to determine the largest and top 3 largest
    calories amount
     */
    public static void main(String[] args){
        int largest = -1;
        int[] top3 = new int[3];
        try{
            File file = new File("inputData/aocDay1Input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            List<Integer> current = new ArrayList<>(); // keeps track of the calories collected by current Elf
            while((line = br.readLine()) != null){
                // end of one Elf's calories so update the largest and top 3 calories
                if (line.isEmpty()){
                    largest = getMostCalories(largest, current); // for part 1
                    getNewTop3(top3, current); // for part 2
                    current = new ArrayList<>();
                }
                // same Elf
                else{
                    current.add(Integer.parseInt(line));
                }
            }
            // edge case for the last Elf
            largest = getMostCalories(largest, current);
            getNewTop3(top3, current);

        }
        catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("The Elf with the most calories carries: " + largest);
        System.out.println("Top three calories amount: " + Arrays.toString(top3));
        int sumCalories = 0;
        for (int n : top3){
            sumCalories += n;
        }
        System.out.println("Sum of top three calories amount: " + sumCalories);
    }
}
