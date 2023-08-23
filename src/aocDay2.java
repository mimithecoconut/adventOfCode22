import java.io.*;

public class aocDay2 {

    // written such that right letter beats left eg. B beats A
    // recall that A is rock, B is paper, and C is scissors
    public static String[][] cheatSheet = new String[][] {{"A", "B"}, {"B", "C"}, {"C", "A"}};

    /*
    Given your move, return point value
     */
    public static int getBasePoints(String s){
        int points = 0;
        if (s.equals("A")) points += 1;
        else if (s.equals("B")) points += 2;
        else points += 3;
        return points;
    }

    /*
    For part 1: Converts XYZ format for rock paper scissors to ABC for simplicity
     */
    public static String convertToABC(String s){
        if (s.equals("X")) return "A";
        if (s.equals("Y")) return "B";
        return "C";
    }

    /*
    For part 1: Determines whether you have beat your opponent with help of cheatsheet.
     */
    public static boolean beatsOpp(String[] round){
        for (String[] win : cheatSheet) {
            if (win[0].equals(round[0]) && win[1].equals(round[1]) ) {
                return true;
            }
        }
        return false;
    }

     /*
    For part 1: Calculates the point for one round. First value of round is opponent move
    and second value of round is your move (rock, paper, scissors)
     */
    public static int calculatePoints(String[] round){
        String opp = round[0];
        String you = convertToABC(round[1]);
        int points = getBasePoints(you);
        if (opp.equals(you)) points += 3;
        else if (beatsOpp(new String[] {opp, you})){
            points += 6;
        }
        return points;
    }

    /*
   For part 2: Gives the projected move given outcome and what opponent plays. Uses cheatsheet.
    */
    public static String getMove(String opp, String outcome){
        for (String[] win : cheatSheet) {
            if (outcome.equals("X") && win[1].equals(opp)) {
                return win[0];
            }
            else if (outcome.equals("Z") && win[0].equals(opp)){
                return win[1];
            }
        }
        return opp;
    }

    /*
    For part 2: Calculates the point for one round. First value of round is opponent move
    and second value of round is the outcome (loss, draw, win)
     */
    public static int calculatePoints2(String[] round){
        String opp = round[0];
        String outcome = round[1];
        String you = getMove(opp, outcome);
        int points = getBasePoints(you);
        if (outcome.equals("Y")) points += 3;
        else if (outcome.equals("Z")){
            points += 6;
        }
        return points;
    }

    /*
    Process input data containing information about opponent and you. Perform algorithm to
    determine the total points earned. For part 1 the second value is your move. For
    part 2 the second value is game outcome.
     */
    public static void main(String[] args){
        int totalPoints = 0; // points count for part 1
        int totalPoints2 = 0; // points count for part 2
        try{
            File file = new File("inputData/aocDay2Input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            while((line = br.readLine()) != null){
                String[] round = line.split(" ");
                totalPoints += calculatePoints(round); // for part 1
                totalPoints2 += calculatePoints2(round); // for part 2
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        // Some test cases for first part
        System.out.println(calculatePoints(new String[] {"A", "Y"}));
        System.out.println(calculatePoints(new String[] {"B", "X"}));
        System.out.println(calculatePoints(new String[] {"C", "Z"}));
        System.out.println("The total number of points from Rock Paper Scissors with your rule: " + totalPoints);

        // Some test cases for second part
        System.out.println(calculatePoints2(new String[] {"A", "Y"}));
        System.out.println(calculatePoints2(new String[] {"B", "X"}));
        System.out.println(calculatePoints2(new String[] {"C", "Z"}));
        System.out.println("The total number of points from Rock Paper Scissors with Elf's rule: " + totalPoints2);


    }
}
