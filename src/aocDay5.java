import java.io.*;
import java.util.*;

public class aocDay5 {

    // Map to store all the stacks
    public static Map<Integer, Deque<Character>> allStacks = new HashMap<>();
    public static Map<Integer, Deque<Character>> allStacks2 = new HashMap<>();

    /*
    Convert vertical format in file to format of map where key is stack number
    and value is the corresponding stack. Utilize count as a way to determine stack number
     */
    public static void addToStackDict(String line, Map<Integer, Deque<Character>> stack){
        int count = 4;
        for (int i = 0; i < line.length(); i++){
            if (line.charAt(i) == '['){
                int stackNum = count / 4;
                if (!stack.containsKey(stackNum)) stack.put(stackNum, new ArrayDeque<>());
                stack.get(stackNum).addFirst(line.charAt(i+1));
            }
            count ++;
        }

    }

    /*
    For part 1: Performs the one by one move of CrateMover 9000
     */
    public static void performMove(String move){
        String[] moveSplit = move.split(" ");
        int times = Integer.parseInt(moveSplit[1]);
        int fromStackNum = Integer.parseInt(moveSplit[3]);
        int toStackNum = Integer.parseInt(moveSplit[5]);
        Deque<Character> fromStack = allStacks.get(fromStackNum);
        Deque<Character> toStack = allStacks.get(toStackNum);
        List<Character> storage = new ArrayList<>();
        for (int i = 0; i < times; i++){
            Character removed = fromStack.removeLast();
            toStack.add(removed);
        }
    }

    /*
    For part 2: Performs the multiple move at once of CrateMover 9001
     */
    public static void performMove2(String move){
        String[] moveSplit = move.split(" ");
        int times = Integer.parseInt(moveSplit[1]);
        int fromStackNum = Integer.parseInt(moveSplit[3]);
        int toStackNum = Integer.parseInt(moveSplit[5]);
        Deque<Character> fromStack = allStacks2.get(fromStackNum);
        Deque<Character> toStack = allStacks2.get(toStackNum);
        List<Character> storage = new ArrayList<>(); // to store the removed crates
        for (int i = 0; i < times; i++){
            Character removed = fromStack.removeLast();
            storage.add(removed);
        }
        // add to the toStack in reverse order in order to preserve the correct order
        for (int i = storage.size() - 1; i >= 0; i--){
            toStack.add(storage.get(i));
        }
    }

    /*
    Processes input data which includes first the stacks in vertical format, followed by
    empty space, followed by all the moves
     */
    public static void main(String[] args){
        try{
            File file = new File("inputData/aocDay5Input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            while((line = br.readLine()) != null){
                if (!line.isEmpty()){
                    if (line.charAt(0) == '[') {
                        addToStackDict(line, allStacks);
                        addToStackDict(line, allStacks2);
                    }
                    else if (line.charAt(0) == 'm') {
                        performMove(line);
                        performMove2(line);
                    }
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        String allTops = "";
        for (int key: allStacks.keySet()){
            allTops += allStacks.get(key).peekLast();
        }
        System.out.println("The tops of the stack for CrateMover 9000 is: " + allTops);

        String allTops2 = "";
        for (int key: allStacks2.keySet()){
            allTops2 += allStacks2.get(key).peekLast();
        }
        System.out.println("The tops of the stack for CrateMover 9001 is: " + allTops2);

    }
}
