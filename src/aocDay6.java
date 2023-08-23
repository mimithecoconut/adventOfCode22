import java.io.*;
import java.util.*;

public class aocDay6 {

    /*
    Increment the number of char c by 1
     */
    public static void addChar(Map<Character, Integer> map, char c){
        if (!map.containsKey(c)) map.put(c, 0);
        map.put(c, map.get(c) + 1);
    }

    /*
    Find the last index of the first instances of a unique marker that is distinctNum length long. Here index starts
    at 1
     */
    public static int findMarker(String line, int distinctNum){
        // keeps track of current distinctNum of characters where key is character and value is number of occurrences
        Map<Character, Integer> current = new HashMap<>();
        // add initial characters into map
        for (int i = 0; i < distinctNum; i++){
            char c = line.charAt(i);
            addChar(current, c);

        }
        // when dictionary size is equal to distinctNum this means each key has value 1 meaning unique
        if (current.size() == distinctNum) return distinctNum;

        int start = 0;
        int newIdx = distinctNum;
        while (newIdx < line.length()){
            char c = line.charAt(start);
            int removed = current.get(c) - 1;
            if (removed == 0) current.remove(c); // remove key if value is 0
            else current.put(c, removed);
            char newC = line.charAt(newIdx);
            addChar(current, newC);
            if (current.size() == distinctNum) return newIdx + 1;
            start++;
            newIdx = start + distinctNum;
        }
        return 0;
    }

    /*
    Processes input that includes the signal sent by the Elves
     */
    public static void main(String[] args){
        try{
            File file = new File("inputData/aocDay6Input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            while((line = br.readLine()) != null){
                System.out.println("The number of characters needed to be processed before the start-of-packet " +
                        "marker with 4 distinct characters is detected: " + findMarker(line, 4));
                System.out.println("The number of characters needed to be processed before the start-of-packet " +
                        "marker with 14 distinct characters is detected: " + findMarker(line, 14));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
