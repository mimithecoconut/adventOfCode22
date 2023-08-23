import java.io.*;

public class aocDay4 {

    /*
    For part 1: conditions for one range to be in the other
     */
    public static boolean isContained(int elf1Start, int elf1End, int elf2Start, int elf2End){
        return (elf1Start <= elf2Start && elf1End >= elf2End) ||
                (elf2Start <= elf1Start && elf2End >= elf1End);
    }

    /*
    For part 2: conditions for two ranges to overlap
     */
    public static boolean overlaps(int elf1Start, int elf1End, int elf2Start, int elf2End) {
        return elf1End >= elf2Start && elf2End >= elf1Start;
    }

    /*
    Process input data which contains the two ranges and counts the number contained and
    overlapped ranges
     */
    public static void main(String[] args){
        int count = 0;
        int count2 = 0;
        try{
            File file = new File("inputData/aocDay4Input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;

            while((line = br.readLine()) != null){
                String[] elfIds = line.split(",");
                String[] limits1 = elfIds[0].split("-");
                int elf1Start = Integer.parseInt(limits1[0]);
                int elf1End = Integer.parseInt(limits1[1]);
                String[] limits2 = elfIds[1].split("-");
                int elf2Start = Integer.parseInt(limits2[0]);
                int elf2End = Integer.parseInt(limits2[1]);
                if (isContained(elf1Start, elf1End, elf2Start, elf2End)) count ++;
                if (overlaps(elf1Start, elf1End, elf2Start, elf2End)) count2 ++;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Number of contained ranges: " + count);
        System.out.println("Number of overlapped ranges: " + count2);
    }
}
