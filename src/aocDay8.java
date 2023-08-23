import java.io.*;
import java.util.*;

public class aocDay8 {

    public static Set<String> visible = new HashSet<>();

    public static int[] initLargestArray(String line){
        int m = line.length();
        int[] largest = new int[m];
        for (int i = 0; i < m; i++){
            largest[i] = (int) line.charAt(i) - '0';
        }
        return largest;
    }

    public static int[] updateLargestArrayFromLeftTop(String newLine, int[] largest, int row, int[] scoreTop){
        int m = newLine.length();
        int left = newLine.charAt(0) - '0';
        int[] score = new int[m];
        for (int i = 1; i < m - 1; i++){
            int num = (int) newLine.charAt(i) - '0';
//            if (left < num || largest[i] < num) System.out.println(row+"," +i);
            if (left < num ) {
                visible.add(row + "," + i);
                left = num;
            }
            else {
                score[i] = score[i-1] + 1;
            }
//            System.out.println("-" + largest[i]);
            if (largest[i] < num) {
                visible.add(row + "," + i);
                largest[i] = num;
            }
            else {
                scoreTop[i] = scoreTop[i-1] + 1;
                score[i] *= scoreTop[i];
            }
        }
        return score;
    }

    public static void updateLargestArrayFromRightBot(String newLine, int[] largest, int row){
        int m = newLine.length();
        int right = newLine.charAt(m-1) - '0';
        int[] score = new int[m];
        for (int i = m-2; i >= 1; i--){
            int num = (int) newLine.charAt(i) - '0';
            if (right < num ) {
                visible.add(row + "," + i);
                right = num;

            }
            if (largest[i] < num) {
                visible.add(row + "," + i);
                largest[i] = num;
            }

        }
    }

    public static void initScoreArray(int n, int m){

    }

    public static void main(String[] args){
        int n = 0;
        int m = 0;
        try{
            File file = new File("inputData/aocDay8Input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String next, line = br.readLine();

            List<String> lineList = new ArrayList<>();

            int[] largestFromTop = initLargestArray(line);
            int[] scoreTop = new int[m];
//            System.out.println(Arrays.toString(largestFromTop));
            m = line.length();
            List<int[]> scoreArray = new ArrayList<>();
            lineList.add(line);
            line = br.readLine();
            int row = 1;
            for (boolean last = (line == null); !last; line = next) {
//                System.out.println(line);
                last = ((next = br.readLine()) == null);
                int[] score = new int[m];
                if (!last) {
                    score = updateLargestArrayFromLeftTop(line, largestFromTop, row, scoreTop);
                }
                lineList.add(line);
                row++;
            }

            n = lineList.size();
            int[] largestFromBot = initLargestArray(lineList.get(n-1));
//            System.out.println(Arrays.toString(largestFromBot));
            for (int i = n - 2; i >= 1; i--){
                int[] score = new int[m];
                updateLargestArrayFromRightBot(lineList.get(i), largestFromBot, i);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        int outerTrees = 2 * (n + m) - 4;
        System.out.println(outerTrees);
        int visibleCount = outerTrees + visible.size();
        System.out.println(visibleCount);
//        for (String s : visible){
//            System.out.println(s);
//        }

    }
}
