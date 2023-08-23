import java.io.*;
import java.util.*;

public class aocDay3 {
    /*
    With the already established ascii values, subtract correct amount to get correct priorities.
    a - z is 1 - 26 where ascii a is 97 and A - Z is 27 - 52 where ascii A is 65
     */
    public static int getPriority(char item){
        int ascii = (int) item;
        if (ascii >= 97) return ascii - 96;
        return ascii - 38;

    }

    /*
    For part 1: Finds the common item from both compartments of the rucksack
     */
    public static char findCommon(String rucksack){
        int size = rucksack.length();
        Set<Character> firstCompartment = new HashSet<>();
        for (int i = 0; i < size; i++){
            char item = rucksack.charAt(i);
            if (i < size/2){
                firstCompartment.add(item);
            }
            else{
                if (firstCompartment.contains(item)) return item;
            }
        }
        return '-';
    }

    /*
    For part 2: Finds the common item ie. the badge in the rucksacks of three Elves
     */
    public static char findCommon2(String[] rucksacks){
        Set<Character> sack1 = new HashSet<>();
        Set<Character> sack2 = new HashSet<>();
        for (int i = 0; i < 3; i++){
            String sack = rucksacks[i];
            for (char item: sack.toCharArray()) {
                if (i == 0){
                    sack1.add(item);
                }
                else if (i == 1){
                    if (sack1.contains(item)) sack2.add(item);
                }
                else{
                    if (sack2.contains(item)) return item;
                }
            }
        }
        return '-';
    }

    /*
    Processes input data containing the contents of the rucksack where groups
    of three Elves are grouped by three lines
     */
    public static void main(String[] args){
        int prioritySum = 0;
        int prioritySum2 = 0;
        try{
            File file = new File("inputData/aocDay3Input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            int count = 0; // in order to keep track of groups of three Elves
            String[] rucksacks = new String[3];
            while((line = br.readLine()) != null){
                // for part 1
                char sharedItem = findCommon(line);
                assert (sharedItem != '-');
                prioritySum += getPriority(sharedItem);

                // for part 2
                rucksacks[count % 3] = line;
                if (count % 3 == 2){
                    char sharedItem2 = findCommon2(rucksacks);
                    assert (sharedItem2 != '-');
                    prioritySum2 += getPriority(sharedItem2);
                }
                count ++;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("The sum of the priorities of the common item in both compartments is: " + prioritySum);
        System.out.println("The sum of the priorities of the badge in each group of three Elves is: " + prioritySum2);
    }
}
